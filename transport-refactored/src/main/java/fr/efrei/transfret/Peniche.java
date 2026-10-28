package fr.efrei.transfret;

public class Peniche extends Vehicule {

    private static final String IMMATRICULATION = "PEN-0001";
    private static final double CAPACITE_TONNES = 2500.0;
    private static final double KILOGRAMMES_PAR_TONNE = 1000.0;

    public Peniche(ModeTransport mode) {
        super(IMMATRICULATION, CAPACITE_TONNES * KILOGRAMMES_PAR_TONNE, mode);
    }

    @Override
    public void partir(int distanceKm) {
        System.out.println("La péniche " + immatriculation + " navigue sur " + distanceKm + " km de voies fluviales");
    }
}
