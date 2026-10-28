package fr.efrei.transfret;

import java.util.List;
import java.util.Set;

public class AssemblagePrix {

    private final List<ReglePrix> reglesPrix;

    public AssemblagePrix() {
        this(List.of(new RegleHorsGabarit()));
    }

    public AssemblagePrix(List<ReglePrix> reglesPrix) {
        this.reglesPrix = List.copyOf(reglesPrix);
    }

    public double calculer(Prix tarifBase, List<Colis> colis, Set<TypeMarchandise> types, boolean urgent,
                           PolitiqueRemise politiqueRemise, boolean assurance, double valeurDeclaree) {
        Prix prix = tarifBase;
        for (TypeMarchandise type : types) {
            prix = type.appliquerSupplement(prix);
        }
        for (ReglePrix regle : reglesPrix) {
            prix = regle.appliquer(prix, colis);
        }
        if (urgent) {
            prix = new SupplementUrgence(prix);
        }

        prix = new RemisePrix(prix, politiqueRemise);
        if (assurance) {
            prix = new Assurance(prix, valeurDeclaree);
        }
        return Montants.arrondir(prix.calculer());
    }
}
