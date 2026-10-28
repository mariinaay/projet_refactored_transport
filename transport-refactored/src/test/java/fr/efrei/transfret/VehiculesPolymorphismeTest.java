package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VehiculesPolymorphismeTest {

    @Test
    void camionFrigorifiquePeutChargerUnColisStandardCommeUnCamion() {
        CamionFrigorifique camion = new CamionFrigorifique("FR-001", 1000);
        Colis colis = colis(TypeMarchandise.STANDARD);

        assertDoesNotThrow(() -> camion.charger(colis));
        assertEquals(colis.getPoids(), camion.getChargeActuelle(), 0.001);
    }

    @Test
    void chaqueVehiculeAccepteSeulementSonModeDeTransport() {
        List<Colis> colis = List.of(colis(TypeMarchandise.STANDARD));

        assertTrue(new Camion("RO-001", 1000, false).peutPrendreEnCharge(colis, ModeTransport.ROUTE));
        assertFalse(new Camion("RO-001", 1000, false).peutPrendreEnCharge(colis, ModeTransport.RAIL));
        assertTrue(new Train("TR-001", 1000).peutPrendreEnCharge(colis, ModeTransport.RAIL));
        assertTrue(new Bateau("ME-001", 1000, false).peutPrendreEnCharge(colis, ModeTransport.MER));
        assertTrue(new Avion("AI-001", 1000).peutPrendreEnCharge(colis, ModeTransport.AIR));
    }

    @Test
    void incompatibilitesDesColisSontGereesParLesVehicules() {
        List<Colis> refrigere = List.of(colis(TypeMarchandise.REFRIGERE));
        List<Colis> dangereux = List.of(colis(TypeMarchandise.DANGEREUX));

        assertTrue(new CamionFrigorifique("FR-001", 1000)
                .peutPrendreEnCharge(refrigere, ModeTransport.ROUTE));
        assertFalse(new CamionFrigorifique("FR-001", 1000)
                .peutPrendreEnCharge(List.of(colis(TypeMarchandise.STANDARD)), ModeTransport.ROUTE));
        assertFalse(new Camion("RO-001", 1000, false)
                .peutPrendreEnCharge(dangereux, ModeTransport.ROUTE));
        assertTrue(new Camion("RO-002", 1000, true)
                .peutPrendreEnCharge(dangereux, ModeTransport.ROUTE));
        assertFalse(new Bateau("ME-001", 1000, false)
                .peutPrendreEnCharge(refrigere, ModeTransport.MER));
        assertTrue(new Bateau("ME-002", 1000, true)
                .peutPrendreEnCharge(refrigere, ModeTransport.MER));
    }

    @Test
    void partirEstDisponibleSansMethodesDeplacementIncompatibles() {
        assertDoesNotThrow(() -> new Camion("RO-001", 1000, false).partir(100));
        assertDoesNotThrow(() -> new CamionFrigorifique("FR-001", 1000).partir(100));
        assertDoesNotThrow(() -> new Train("TR-001", 1000).partir(100));
        assertDoesNotThrow(() -> new Bateau("ME-001", 1000, true).partir(100));
        assertDoesNotThrow(() -> new Avion("AI-001", 1000).partir(100));
    }

    private static Colis colis(TypeMarchandise type) {
        return new Colis("Test", 10, 10, 10, 10, type, 100);
    }
}
