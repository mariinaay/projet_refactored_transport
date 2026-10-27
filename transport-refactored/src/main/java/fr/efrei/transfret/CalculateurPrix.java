package fr.efrei.transfret;

import java.util.List;

public class CalculateurPrix {

    private static final double TARIF_ROUTE_KM = 1.2;
    private static final double TARIF_ROUTE_KG = 0.1;

    private static final double TARIF_RAIL_KM = 0.8;
    private static final double TARIF_RAIL_KG = 0.07;
    private static final double PRIX_MIN_RAIL = 150.0;

    private static final double TARIF_MER_KM = 0.5;
    private static final double TARIF_MER_KG = 0.03;
    private static final double PRIX_MIN_MER = 500.0;

    private static final double TARIF_AIR_KM = 0.6;
    private static final double TARIF_AIR_KG = 4.5;
    private static final double COEFFICIENT_POIDS_VOLUMETRIQUE = 167.0;

    private static final double COEF_FRAGILE = 1.15;
    private static final double COEF_DANGEREUX = 1.30;
    private static final double COEF_REFRIGERE = 1.20;
    private static final double COEF_URGENCE = 1.50;

    private static final double REMISE_ENTREPRISE = 0.10;
    private static final double REMISE_ENTREPRISE_GRANDE_EXPEDITION = 0.15;
    private static final double REMISE_PREMIUM = 0.20;
    private static final double TAUX_ASSURANCE = 0.02;
    private static final double ASSURANCE_MINIMUM = 15.0;
    private static final double SEUIL_REMISE_ENTREPRISE = 5000.0;

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

        double prix;
        if (mode == ModeTransport.ROUTE) {
            prix = distance * TARIF_ROUTE_KM + poids * TARIF_ROUTE_KG;
        } else if (mode == ModeTransport.RAIL) {
            prix = distance * TARIF_RAIL_KM + poids * TARIF_RAIL_KG;
            if (prix < PRIX_MIN_RAIL) prix = PRIX_MIN_RAIL;
        } else if (mode == ModeTransport.MER) {
            prix = distance * TARIF_MER_KM + poids * TARIF_MER_KG;
            if (prix < PRIX_MIN_MER) prix = PRIX_MIN_MER;
        } else if (mode == ModeTransport.AIR) {
            double poidsTaxable = poids;
            if (volume * COEFFICIENT_POIDS_VOLUMETRIQUE > poids) {
                poidsTaxable = volume * COEFFICIENT_POIDS_VOLUMETRIQUE;
            }
            prix = distance * TARIF_AIR_KM + poidsTaxable * TARIF_AIR_KG;
        } else {
            throw new IllegalArgumentException("Mode inconnu : " + mode);
        }

        if (fragile) prix *= COEF_FRAGILE;
        if (dangereux) prix *= COEF_DANGEREUX;
        if (refrigere) prix *= COEF_REFRIGERE;
        if (urgent) prix *= COEF_URGENCE;

        if (typeClient == TypeClient.ENTREPRISE) {
            if (prix > SEUIL_REMISE_ENTREPRISE) {
                prix *= 1 - REMISE_ENTREPRISE_GRANDE_EXPEDITION;
            } else {
                prix *= 1 - REMISE_ENTREPRISE;
            }
        } else if (typeClient == TypeClient.PREMIUM) {
            prix *= 1 - REMISE_PREMIUM;
        }

        if (assurance) {
            double montantAssurance = Math.max(valeur * TAUX_ASSURANCE, ASSURANCE_MINIMUM);
            prix += montantAssurance;
        }
        return Montants.arrondir(prix);
    }
}
