package fr.efrei.transfret;

public final class ModeAir implements StrategieModeTransport {

    private static final double TARIF_KM = 0.6;
    private static final double TARIF_KG = 4.5;
    private static final double COEFFICIENT_POIDS_VOLUMETRIQUE = 167.0;
    private static final int DISTANCE_LONGUE = 3000;
    private static final int DELAI_STANDARD_JOURS = 1;
    private static final int DELAI_LONGUE_DISTANCE_JOURS = 2;

    @Override
    public double calculerPrixBase(int distance, double poids, double volume) {
        double poidsTaxable = Math.max(poids, volume * COEFFICIENT_POIDS_VOLUMETRIQUE);
        return distance * TARIF_KM + poidsTaxable * TARIF_KG;
    }

    @Override
    public int calculerDelai(int distance, boolean urgent) {
        return !urgent && distance > DISTANCE_LONGUE
                ? DELAI_LONGUE_DISTANCE_JOURS : DELAI_STANDARD_JOURS;
    }

    private static final java.util.Set<TypeMarchandise> MARCHANDISES_INTERDITES = java.util.Set.of(TypeMarchandise.DANGEREUX, TypeMarchandise.REFRIGERE);

    @Override
    public boolean autoriseMarchandise(TypeMarchandise type) {
        return !MARCHANDISES_INTERDITES.contains(type);
    }
}
