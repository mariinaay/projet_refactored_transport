package fr.efrei.transfret;

public final class ModeRoute implements StrategieModeTransport {

    private static final double TARIF_KM = 1.2;
    private static final double TARIF_KG = 0.1;
    private static final double VITESSE_KM_PAR_HEURE = 70.0;
    private static final int HEURES_PAR_JOUR = 9;
    private static final int HEURES_URGENTES_PAR_JOUR = 18;

    @Override
    public double calculerPrixBase(int distance, double poids, double volume) {
        return distance * TARIF_KM + poids * TARIF_KG;
    }

    @Override
    public int calculerDelai(int distance, boolean urgent) {
        double heures = distance / VITESSE_KM_PAR_HEURE;
        int heuresParJour = urgent ? HEURES_URGENTES_PAR_JOUR : HEURES_PAR_JOUR;
        return (int) Math.ceil(heures / heuresParJour);
    }
}
