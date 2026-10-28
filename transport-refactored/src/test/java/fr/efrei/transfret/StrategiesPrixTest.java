package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrategiesPrixTest {

    @Test
    void tarifRouteCalculePrixParDistanceEtPoids() {
        assertEquals(1210.0, new ModeRoute().calculerPrixBase(1000, 100, 1));
    }

    @Test
    void tarifRailAppliqueLeMinimum() {
        assertEquals(150.0, new ModeRail().calculerPrixBase(10, 10, 1));
    }

    @Test
    void tarifMerAppliqueLeMinimum() {
        assertEquals(500.0, new ModeMer().calculerPrixBase(10, 10, 1));
    }

    @Test
    void tarifAirUtiliseLePoidsVolumetriqueQuandIlEstSuperieur() {
        assertEquals(1563.0, new ModeAir().calculerPrixBase(100, 100, 2));
    }

    @Test
    void tarifAirUtiliseLePoidsReelQuandIlEstSuperieur() {
        assertEquals(510.0, new ModeAir().calculerPrixBase(100, 100, 0.1));
    }

    @Test
    void delaiRouteTientCompteDeUrgence() {
        ModeRoute route = new ModeRoute();

        assertEquals(2, route.calculerDelai(1260, false));
        assertEquals(1, route.calculerDelai(1260, true));
    }

    @Test
    void delaiRailRetireUnJourEnUrgence() {
        ModeRail rail = new ModeRail();

        assertEquals(2, rail.calculerDelai(1440, false));
        assertEquals(1, rail.calculerDelai(1440, true));
    }

    @Test
    void delaiMerNeChangePasAvecUrgence() {
        ModeMer mer = new ModeMer();

        assertEquals(4, mer.calculerDelai(1440, false));
        assertEquals(4, mer.calculerDelai(1440, true));
    }

    @Test
    void delaiAirEstPlusLongSeulementPourUnLongTrajetNonUrgent() {
        ModeAir air = new ModeAir();

        assertEquals(1, air.calculerDelai(3000, false));
        assertEquals(2, air.calculerDelai(3001, false));
        assertEquals(1, air.calculerDelai(3001, true));
    }

    @Test
    void particulierNeBeneficiePasDeRemise() {
        assertEquals(6000.0, TypeClient.PARTICULIER.appliquerRemise(6000.0));
    }

    @Test
    void entrepriseBeneficieDeDixPourCentJusquaCinqMilleEurosInclus() {
        assertEquals(4500.0, TypeClient.ENTREPRISE.appliquerRemise(5000.0), 0.001);
    }

    @Test
    void entrepriseBeneficieDeQuinzePourCentAuDelaDeCinqMilleEuros() {
        assertEquals(5100.0, TypeClient.ENTREPRISE.appliquerRemise(6000.0), 0.001);
    }

    @Test
    void premiumBeneficieDeVingtPourCentDeRemise() {
        assertEquals(4800.0, TypeClient.PREMIUM.appliquerRemise(6000.0), 0.001);
    }
}
