package fr.efrei.transfret;

public class RemisePrix extends DecorateurPrix {

    private final PolitiqueRemise politiqueRemise;

    public RemisePrix(Prix prix, PolitiqueRemise politiqueRemise) {
        super(prix);
        this.politiqueRemise = politiqueRemise;
    }

    @Override
    public double calculer() {
        return politiqueRemise.appliquerRemise(prix.calculer());
    }
}
