package fr.efrei.transfret;

public final class Montants {

    private static final double FACTEUR_ARRONDI = 100.0;

    private Montants() {
    }

    public static double arrondir(double valeur) {
        return Math.round(valeur * FACTEUR_ARRONDI) / FACTEUR_ARRONDI;
    }
}
