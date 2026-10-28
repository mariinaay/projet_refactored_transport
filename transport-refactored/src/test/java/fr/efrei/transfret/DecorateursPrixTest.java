package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecorateursPrixTest {

    private final CalculateurPrix calculateur = new CalculateurPrix(new AssemblagePrix());

    @Test
    void supplementFragileAjouteQuinzePourCent() {
        assertEquals(115.0, new SupplementFragile(() -> 100.0).calculer(), 0.001);
    }

    @Test
    void supplementDangereuxAjouteTrentePourCent() {
        assertEquals(130.0, new SupplementDangereux(() -> 100.0).calculer(), 0.001);
    }

    @Test
    void supplementRefrigereAjouteVingtPourCent() {
        assertEquals(120.0, new SupplementRefrigere(() -> 100.0).calculer(), 0.001);
    }

    @Test
    void supplementHorsGabaritAjouteDixPourCent() {
        assertEquals(110.0, new SupplementHorsGabarit(() -> 100.0).calculer(), 0.001);
    }

    @Test
    void supplementUrgenceAjouteCinquantePourCent() {
        assertEquals(150.0, new SupplementUrgence(() -> 100.0).calculer(), 0.001);
    }

    @Test
    void remiseAppliqueLaPolitiqueDuClient() {
        assertEquals(90.0, new RemisePrix(() -> 100.0, TypeClient.ENTREPRISE).calculer(), 0.001);
    }

    @Test
    void assuranceAjouteDeuxPourCentDeLaValeurDeclaree() {
        assertEquals(120.0, new Assurance(() -> 100.0, 1000.0).calculer(), 0.001);
    }

    @Test
    void appliqueChaqueSupplementUneFoisPuisUrgence() {
        List<Colis> colis = List.of(
                colis(TypeMarchandise.FRAGILE, 100),
                colis(TypeMarchandise.DANGEREUX, 100),
                colis(TypeMarchandise.REFRIGERE, 100));

        double prix = calculateur.calculerPrixHT(colis, 1000, ModeTransport.ROUTE,
                true, false, TypeClient.PARTICULIER);

        assertEquals(3237.27, prix, 0.001);
    }

    @Test
    void appliqueRemiseApresSupplementEtUrgencePuisAssurance() {
        double prix = calculateur.calculerPrixHT(
                List.of(colis(TypeMarchandise.FRAGILE, 1000)),
                5000, ModeTransport.ROUTE, true, true, TypeClient.ENTREPRISE);

        assertEquals(8818.97, prix, 0.001);
    }

    @Test
    void assuranceRespecteSonMontantMinimum() {
        double prix = calculateur.calculerPrixHT(
                List.of(colis(TypeMarchandise.STANDARD, 100)),
                100, ModeTransport.ROUTE, false, true, TypeClient.PARTICULIER);

        assertEquals(136.0, prix, 0.001);
    }

    @Test
    void assemblageAccepteUneNouvelleRegleSansModifierLeCalculateur() {
        AssemblagePrix assemblage = new AssemblagePrix(List.of(
                (prix, colis) -> () -> prix.calculer() + 5.0));
        CalculateurPrix calculateurAvecRegle = new CalculateurPrix(assemblage);

        double prix = calculateurAvecRegle.calculerPrixHT(
                List.of(colis(TypeMarchandise.STANDARD, 100)),
                100, ModeTransport.ROUTE, false, false, TypeClient.PARTICULIER);

        assertEquals(126.0, prix, 0.001);
    }

    private static Colis colis(TypeMarchandise type, double valeurDeclaree) {
        return new Colis("Test", 10, 10, 10, 10, type, valeurDeclaree);
    }
}
