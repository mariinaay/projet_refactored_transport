package fr.efrei.transfret;

public class Assurance extends DecorateurPrix {

    private static final double TAUX = 0.02;
    private static final double MONTANT_MINIMUM = 15.0;

    private final double valeurDeclaree;

    public Assurance(Prix prix, double valeurDeclaree) {
        super(prix);
        this.valeurDeclaree = valeurDeclaree;
    }

    @Override
    public double calculer() {
        double montantAssurance = Math.max(valeurDeclaree * TAUX, MONTANT_MINIMUM);
        return prix.calculer() + montantAssurance;
    }
}
