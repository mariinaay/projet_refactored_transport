package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ExtensionFluvialeTest {

    private static final ModeTransport FLUVIAL = ModeTransport.FLUVIAL;

    @Test
    void tarifFluvialAppliqueLePrixEtLeMinimum() {
        assertEquals(300.0, FLUVIAL.calculerPrixBase(100, 100, 0));
        assertEquals(600.0, FLUVIAL.calculerPrixBase(1000, 10000, 0));
    }

    @Test
    void delaiFluvialArronditLaDureePuisAjouteUnJour() {
        assertEquals(3, new EstimateurDelai().calculerDelai(365, FLUVIAL, false));
    }

    @Test
    void trajetParisLeHavreEstConnuDansLesDeuxSens() {
        assertEquals(365, Distances.getDistance("Paris", "Le Havre"));
        assertEquals(365, Distances.getDistance("Le Havre", "Paris"));
    }

    @Test
    void flotteAffecteLaPenicheAuxColisFluviauxEtRefuseLeRefrigere() {
        Peniche peniche = assertInstanceOf(Peniche.class,
                new AffectationVehicule(new Flotte())
                        .trouverVehicule(List.of(colis(TypeMarchandise.STANDARD, 100)), FLUVIAL));

        assertEquals("PEN-0001", peniche.getImmatriculation());
        assertEquals(2_500_000.0, peniche.getCapaciteMax(), 0.001);
        assertFalse(peniche.peutPrendreEnCharge(
                List.of(colis(TypeMarchandise.REFRIGERE, 100)), FLUVIAL));
    }

    @Test
    void validationRefuseLeRefrigereEtUrgentEnFluvial() {
        ValidateurExpedition validateur = new ValidateurExpedition();
        Client client = new Client("Test", "test@example.com", "0600000000", "Paris",
                TypeClient.PARTICULIER, null);

        assertFalse(validateur.valider(client,
                List.of(colis(TypeMarchandise.REFRIGERE, 100)), "Paris", "Le Havre",
                FLUVIAL, false));
        assertFalse(validateur.valider(client,
                List.of(colis(TypeMarchandise.STANDARD, 100)), "Paris", "Le Havre",
                FLUVIAL, true));
    }

    @Test
    void leModeFluvialEstAccessibleParSonNom() {
        assertEquals(FLUVIAL, ModeTransport.valueOf("FLUVIAL"));
    }

    @Test
    void leSupplementHorsGabaritSappliqueSeulementAuDelaDeDeuxMetres() {
        CalculateurPrix calculateur = new CalculateurPrix(new AssemblagePrix());
        Colis gabaritLimite = colis(TypeMarchandise.STANDARD, 100, 200);
        Colis horsGabarit = colis(TypeMarchandise.STANDARD, 100, 201);

        assertEquals(130.0, calculateur.calculerPrixHT(List.of(gabaritLimite), 100,
                ModeTransport.ROUTE, false, false, TypeClient.PARTICULIER), 0.001);
        assertEquals(143.0, calculateur.calculerPrixHT(List.of(horsGabarit), 100,
                ModeTransport.ROUTE, false, false, TypeClient.PARTICULIER), 0.001);
    }

    @Test
    void horsGabaritDetecteChaqueDimensionEtNeSeCumulePasParColis() {
        CalculateurPrix calculateur = new CalculateurPrix(new AssemblagePrix());
        for (Colis grand : List.of(
                new Colis("Long", 100, 201, 10, 10, TypeMarchandise.STANDARD, 100),
                new Colis("Large", 100, 10, 201, 10, TypeMarchandise.STANDARD, 100),
                new Colis("Haut", 100, 10, 10, 201, TypeMarchandise.STANDARD, 100))) {
            assertEquals(143.0, calculateur.calculerPrixHT(List.of(grand), 100,
                    ModeTransport.ROUTE, false, false, TypeClient.PARTICULIER), 0.001);
            assertEquals(154.0, calculateur.calculerPrixHT(List.of(grand, grand), 100,
                    ModeTransport.ROUTE, false, false, TypeClient.PARTICULIER), 0.001);
        }
    }

    @Test
    void reglesHistoriquesEtExtensionSontConfigurablesSansChangerLesColis() {
        CalculateurPrix historique = new CalculateurPrix(new AssemblagePrix(List.of()));
        List<Colis> colis = List.of(colis(TypeMarchandise.STANDARD, 100, 201));
        assertEquals(130.0, historique.calculerPrixHT(colis, 100,
                ModeTransport.ROUTE, false, false, TypeClient.PARTICULIER), 0.001);
        assertEquals(143.0, new CalculateurPrix(new AssemblagePrix()).calculerPrixHT(colis, 100,
                ModeTransport.ROUTE, false, false, TypeClient.PARTICULIER), 0.001);
    }

    @Test
    void expeditionFluvialeAvecHorsGabaritSuitLeCycleDeVieComplet() {
        ClientRepository clients = new InMemoryClientRepository();
        ExpeditionRepository expeditions = new InMemoryExpeditionRepository();
        Notificateur notificateur = new Notificateur(new ConsoleCanalNotification());
        ServiceClient serviceClient = new ServiceClient(clients, notificateur, new ValidateurClient());
        ServiceExpedition service = new ServiceExpedition(expeditions,
                new CalculateurPrix(new AssemblagePrix()), new AffectationVehicule(new Flotte()),
                new ValidateurExpedition(), new EstimateurDelai(), notificateur);
        Client client = serviceClient.creerClient("Test", "test@example.com", "0600000000",
                "Paris", TypeClient.PARTICULIER, null);
        Expedition expedition = service.creerExpedition(client,
                List.of(colis(TypeMarchandise.STANDARD, 100, 201)), "Paris", "Le Havre",
                FLUVIAL, false, false);
        assertEquals(330.0, expedition.getPrixHT(), 0.001);
        assertEquals(396.0, expedition.getPrixTTC(), 0.001);
        org.junit.jupiter.api.Assertions.assertTrue(service.demarrerExpedition(expedition.getId()));
        org.junit.jupiter.api.Assertions.assertTrue(service.livrerExpedition(expedition.getId()));
        assertEquals(StatutExpedition.LIVREE, expedition.getStatut());
        org.junit.jupiter.api.Assertions.assertTrue(expedition.getVehicule().estDisponible());
        assertEquals(0.0, expedition.getVehicule().getChargeActuelle(), 0.001);
    }

    private static Colis colis(TypeMarchandise type, double poids) {
        return colis(type, poids, 10);
    }

    private static Colis colis(TypeMarchandise type, double poids, double longueur) {
        return new Colis("Test", poids, longueur, 10, 10, type, 100);
    }
}
