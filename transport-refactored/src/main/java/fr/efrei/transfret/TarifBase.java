package fr.efrei.transfret;

public class TarifBase implements Prix {

    private final TarifTransport tarif;
    private final int distance;
    private final double poids;
    private final double volume;

    public TarifBase(TarifTransport tarif, int distance, double poids, double volume) {
        this.tarif = tarif;
        this.distance = distance;
        this.poids = poids;
        this.volume = volume;
    }

    @Override
    public double calculer() {
        return tarif.calculerPrixBase(distance, poids, volume);
    }
}
