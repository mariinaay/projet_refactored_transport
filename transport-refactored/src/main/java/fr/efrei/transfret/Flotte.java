package fr.efrei.transfret;

import java.util.List;

public class Flotte {

    private static final String IMMATRICULATION_CAMION_STANDARD = "AB-123-CD";
    private static final double CAPACITE_CAMION_STANDARD_KG = 19000;
    private static final String IMMATRICULATION_CAMION_ADR = "EF-456-GH";
    private static final double CAPACITE_CAMION_ADR_KG = 12000;
    private static final String IMMATRICULATION_CAMION_FRIGORIFIQUE = "IJ-789-KL";
    private static final double CAPACITE_CAMION_FRIGORIFIQUE_KG = 8000;
    private static final String IMMATRICULATION_TRAIN = "TR-0001";
    private static final double CAPACITE_TRAIN_KG = 600000;
    private static final String IMMATRICULATION_BATEAU = "CMA-CGM-01";
    private static final double CAPACITE_BATEAU_KG = 20000000;
    private static final String IMMATRICULATION_AVION = "F-GTXA";
    private static final double CAPACITE_AVION_KG = 100000;

    private final List<Vehicule> vehicules;

    public Flotte() {
        vehicules = List.of(
                new Camion(IMMATRICULATION_CAMION_STANDARD, CAPACITE_CAMION_STANDARD_KG, false),
                new Camion(IMMATRICULATION_CAMION_ADR, CAPACITE_CAMION_ADR_KG, true),
                new CamionFrigorifique(IMMATRICULATION_CAMION_FRIGORIFIQUE, CAPACITE_CAMION_FRIGORIFIQUE_KG),
                new Train(IMMATRICULATION_TRAIN, CAPACITE_TRAIN_KG),
                new Bateau(IMMATRICULATION_BATEAU, CAPACITE_BATEAU_KG, true),
                new Avion(IMMATRICULATION_AVION, CAPACITE_AVION_KG),
                new Peniche(ModeTransport.FLUVIAL));
    }

    public List<Vehicule> getVehicules() {
        return List.copyOf(vehicules);
    }
}
