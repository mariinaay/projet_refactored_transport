package fr.efrei.transfret;

public class EstimateurDelai {

    private static final double VITESSE_ROUTE_KM_PAR_HEURE = 70.0;
    private static final int HEURES_ROUTE_PAR_JOUR = 9;
    private static final int HEURES_ROUTE_URGENTE_PAR_JOUR = 18;
    private static final double KM_RAIL_PAR_JOUR = 1440.0;
    private static final double KM_MER_PAR_JOUR = 720.0;
    private static final int JOUR_SUPPLEMENTAIRE_RAIL = 1;
    private static final int JOURS_SUPPLEMENTAIRES_MER = 2;
    private static final int DISTANCE_AIR_LONGUE = 3000;
    private static final int JOURS_AIR_STANDARD = 1;
    private static final int JOURS_AIR_LONGUE_DISTANCE = 2;

    public int calculerDelai(int distance, ModeTransport mode, boolean urgent) {
        int jours = 0;
        switch (mode) {
            case ROUTE:
                double heures = distance / VITESSE_ROUTE_KM_PAR_HEURE;
                if (urgent) {
                    jours = (int) Math.ceil(heures / HEURES_ROUTE_URGENTE_PAR_JOUR);
                } else {
                    jours = (int) Math.ceil(heures / HEURES_ROUTE_PAR_JOUR);
                }
                break;
            case RAIL:
                jours = (int) Math.ceil(distance / KM_RAIL_PAR_JOUR) + JOUR_SUPPLEMENTAIRE_RAIL;
                if (urgent) jours = jours - 1;
                break;
            case MER:
                jours = (int) Math.ceil(distance / KM_MER_PAR_JOUR) + JOURS_SUPPLEMENTAIRES_MER;
                break;
            case AIR:
                jours = JOURS_AIR_STANDARD;
                if (distance > DISTANCE_AIR_LONGUE) jours = JOURS_AIR_LONGUE_DISTANCE;
                if (urgent) jours = JOURS_AIR_STANDARD;
                break;
        }
        if (jours < 1) jours = 1;
        return jours;
    }
}
