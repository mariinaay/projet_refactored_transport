package fr.efrei.transfret;

public final class Distances {

    public static final int DISTANCE_INCONNUE = -1;
    private static final int DISTANCE_PARIS_LYON = 465;
    private static final int DISTANCE_PARIS_MARSEILLE = 775;
    private static final int DISTANCE_PARIS_LILLE = 225;
    private static final int DISTANCE_PARIS_BORDEAUX = 585;
    private static final int DISTANCE_LYON_MARSEILLE = 315;
    private static final int DISTANCE_LE_HAVRE_NEW_YORK = 5850;
    private static final int DISTANCE_MARSEILLE_ALGER = 760;
    private static final int DISTANCE_PARIS_NEW_YORK = 5840;

    private Distances() {
    }

    // retourne la distance en km entre deux villes, -1 si inconnue
    public static int getDistance(String villeA, String villeB) {
        String a = villeA.toUpperCase();
        String b = villeB.toUpperCase();
        if ((a.equals("PARIS") && b.equals("LYON")) || (a.equals("LYON") && b.equals("PARIS"))) {
            return DISTANCE_PARIS_LYON;
        } else if ((a.equals("PARIS") && b.equals("MARSEILLE")) || (a.equals("MARSEILLE") && b.equals("PARIS"))) {
            return DISTANCE_PARIS_MARSEILLE;
        } else if ((a.equals("PARIS") && b.equals("LILLE")) || (a.equals("LILLE") && b.equals("PARIS"))) {
            return DISTANCE_PARIS_LILLE;
        } else if ((a.equals("PARIS") && b.equals("BORDEAUX")) || (a.equals("BORDEAUX") && b.equals("PARIS"))) {
            return DISTANCE_PARIS_BORDEAUX;
        } else if ((a.equals("LYON") && b.equals("MARSEILLE")) || (a.equals("MARSEILLE") && b.equals("LYON"))) {
            return DISTANCE_LYON_MARSEILLE;
        } else if ((a.equals("LE HAVRE") && b.equals("NEW YORK")) || (a.equals("NEW YORK") && b.equals("LE HAVRE"))) {
            return DISTANCE_LE_HAVRE_NEW_YORK;
        } else if ((a.equals("MARSEILLE") && b.equals("ALGER")) || (a.equals("ALGER") && b.equals("MARSEILLE"))) {
            return DISTANCE_MARSEILLE_ALGER;
        } else if ((a.equals("PARIS") && b.equals("NEW YORK")) || (a.equals("NEW YORK") && b.equals("PARIS"))) {
            return DISTANCE_PARIS_NEW_YORK;
        }
        return DISTANCE_INCONNUE;
    }
}
