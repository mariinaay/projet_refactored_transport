package fr.efrei.transfret;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class CalculateurPrix {

    private final AssemblagePrix assemblagePrix;

    public CalculateurPrix(AssemblagePrix assemblagePrix) {
        this.assemblagePrix = assemblagePrix;
    }

    public double calculerPrixHT(List<Colis> colis, int distance, ModeTransport mode,
                                 boolean urgent, boolean assurance, TypeClient typeClient) {
        double poids = 0;
        double volume = 0;
        double valeur = 0;
        Set<TypeMarchandise> types = EnumSet.noneOf(TypeMarchandise.class);
        for (Colis c : colis) {
            poids += c.getPoids();
            volume += c.getVolume();
            valeur += c.getValeurDeclaree();
            types.add(c.getType());
        }

        Prix tarifBase = new TarifBase(mode, distance, poids, volume);
        return assemblagePrix.calculer(tarifBase, colis, types, urgent, typeClient, assurance, valeur);
    }
}
