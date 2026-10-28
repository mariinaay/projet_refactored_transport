package fr.efrei.transfret;

public class SupplementUrgence extends DecorateurPrix {

    private static final double COEFFICIENT = 1.50;

    public SupplementUrgence(Prix prix) {
        super(prix);
    }

    @Override
    public double calculer() {
        return prix.calculer() * COEFFICIENT;
    }
}
