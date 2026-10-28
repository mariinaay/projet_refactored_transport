package fr.efrei.transfret;

import java.util.List;

@FunctionalInterface
public interface ReglePrix {

    Prix appliquer(Prix prix, List<Colis> colis);
}
