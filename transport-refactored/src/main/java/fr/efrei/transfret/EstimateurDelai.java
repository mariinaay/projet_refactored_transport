package fr.efrei.transfret;

public class EstimateurDelai {

    private static final int DELAI_MINIMUM_JOURS = 1;

    public int calculerDelai(int distance, ModeTransport mode, boolean urgent) {
        return Math.max(DELAI_MINIMUM_JOURS, mode.calculerDelai(distance, urgent));
    }
}
