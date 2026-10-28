package fr.efrei.transfret;

import java.util.List;

public class CalculateurPrix {

    private static final double COEF_FRAGILE = 1.15;
    private static final double COEF_DANGEREUX = 1.30;
    private static final double COEF_REFRIGERE = 1.20;
    private static final double COEF_URGENCE = 1.50;

    private static final double TAUX_ASSURANCE = 0.02;
    private static final double ASSURANCE_MINIMUM = 15.0;

    public double calculerPrixHT(List<Colis> colis, int distance, ModeTransport mode,
                                 boolean urgent, boolean assurance, TypeClient typeClient) {
        double poids = 0;
        double volume = 0;
        double valeur = 0;
        boolean fragile = false;
        boolean dangereux = false;
        boolean refrigere = false;
        for (Colis c : colis) {
            poids += c.getPoids();
            volume += c.getVolume();
            valeur += c.getValeurDeclaree();
            if (c.getType() == TypeMarchandise.FRAGILE) fragile = true;
            if (c.getType() == TypeMarchandise.DANGEREUX) dangereux = true;
            if (c.getType() == TypeMarchandise.REFRIGERE) refrigere = true;
        }

        double prix = mode.calculerPrixBase(distance, poids, volume);

        if (fragile) prix *= COEF_FRAGILE;
        if (dangereux) prix *= COEF_DANGEREUX;
        if (refrigere) prix *= COEF_REFRIGERE;
        if (urgent) prix *= COEF_URGENCE;

        prix = typeClient.appliquerRemise(prix);

        if (assurance) {
            double montantAssurance = Math.max(valeur * TAUX_ASSURANCE, ASSURANCE_MINIMUM);
            prix += montantAssurance;
        }
        return Montants.arrondir(prix);
    }
}
