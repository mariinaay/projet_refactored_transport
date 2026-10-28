package fr.efrei.transfret;

public class SupplementHorsGabarit extends DecorateurPrix {

    private static final double COEFFICIENT = 1.10;

    public SupplementHorsGabarit(Prix prix) {
        super(prix);
    }

    @Override
    public double calculer() {
        return prix.calculer() * COEFFICIENT;
    }
}
