package fr.efrei.transfret;

public final class ModeRail implements StrategieModeTransport {

    private static final double TARIF_KM = 0.8;
    private static final double TARIF_KG = 0.07;
    private static final double PRIX_MINIMUM = 150.0;
    private static final double KM_PAR_JOUR = 1440.0;
    private static final int JOUR_SUPPLEMENTAIRE = 1;
    private static final int RETRAIT_JOUR_URGENCE = 1;

    @Override
    public double calculerPrixBase(int distance, double poids, double volume) {
        return Math.max(distance * TARIF_KM + poids * TARIF_KG, PRIX_MINIMUM);
    }

    @Override
    public int calculerDelai(int distance, boolean urgent) {
        int jours = (int) Math.ceil(distance / KM_PAR_JOUR) + JOUR_SUPPLEMENTAIRE;
        return urgent ? jours - RETRAIT_JOUR_URGENCE : jours;
    }

    private static final java.util.Set<TypeMarchandise> MARCHANDISES_INTERDITES = java.util.Set.of(TypeMarchandise.REFRIGERE);

    @Override
    public boolean autoriseMarchandise(TypeMarchandise type) {
        return !MARCHANDISES_INTERDITES.contains(type);
    }
}
