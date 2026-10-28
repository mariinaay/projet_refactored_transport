package fr.efrei.transfret;

import java.util.List;

public final class RegleHorsGabarit implements ReglePrix {

    private static final double DIMENSION_MAXIMALE_STANDARD_CM = 200.0;

    @Override
    public Prix appliquer(Prix prix, List<Colis> colis) {
        if (colis.stream().anyMatch(c -> c.getLongueur() > DIMENSION_MAXIMALE_STANDARD_CM
                || c.getLargeur() > DIMENSION_MAXIMALE_STANDARD_CM
                || c.getHauteur() > DIMENSION_MAXIMALE_STANDARD_CM)) {
            return new SupplementHorsGabarit(prix);
        }
        return prix;
    }
}
