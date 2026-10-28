package fr.efrei.transfret;

public class Avion extends Vehicule {

    private static final int ALTITUDE_CROISIERE_PIEDS = 35_000;

    public Avion(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax, ModeTransport.AIR);
    }

    @Override
    public void partir(int distanceKm) {
        faireLePlein();
        System.out.println("L'avion " + immatriculation + " roule sur la piste");
        System.out.println("L'avion " + immatriculation + " vole à " + ALTITUDE_CROISIERE_PIEDS + " pieds");
    }
}
