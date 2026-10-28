package fr.efrei.transfret;

public final class ModeFluvial implements StrategieModeTransport {

    private static final double TARIF_KM = 0.40;
    private static final double TARIF_KG = 0.02;
    private static final double PRIX_MINIMUM = 300.0;
    private static final double KM_PAR_JOUR = 360.0;
    private static final int JOUR_SUPPLEMENTAIRE = 1;

    @Override
    public double calculerPrixBase(int distance, double poids, double volume) {
        return Math.max(distance * TARIF_KM + poids * TARIF_KG, PRIX_MINIMUM);
    }

    @Override
    public int calculerDelai(int distance, boolean urgent) {
        return (int) Math.ceil(distance / KM_PAR_JOUR) + JOUR_SUPPLEMENTAIRE;
    }

    private static final java.util.Set<TypeMarchandise> MARCHANDISES_INTERDITES = java.util.Set.of(TypeMarchandise.REFRIGERE);

    @Override
    public boolean autoriseMarchandise(TypeMarchandise type) {
        return !MARCHANDISES_INTERDITES.contains(type);
    }

    @Override
    public boolean autoriseUrgence() {
        return false;
    }
}
