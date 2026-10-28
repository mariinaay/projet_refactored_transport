package fr.efrei.transfret;

public final class ModeMer implements StrategieModeTransport {

    private static final double TARIF_KM = 0.5;
    private static final double TARIF_KG = 0.03;
    private static final double PRIX_MINIMUM = 500.0;
    private static final double KM_PAR_JOUR = 720.0;
    private static final int JOURS_SUPPLEMENTAIRES = 2;

    @Override
    public double calculerPrixBase(int distance, double poids, double volume) {
        return Math.max(distance * TARIF_KM + poids * TARIF_KG, PRIX_MINIMUM);
    }

    @Override
    public int calculerDelai(int distance, boolean urgent) {
        return (int) Math.ceil(distance / KM_PAR_JOUR) + JOURS_SUPPLEMENTAIRES;
    }

    @Override
    public boolean autoriseUrgence() {
        return false;
    }
}
