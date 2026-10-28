package fr.efrei.transfret;

/** Enregistrement des modes et assemblage de leurs strategies. */
public enum ModeTransport implements StrategieModeTransport {
    ROUTE(new ModeRoute()),
    RAIL(new ModeRail()),
    MER(new ModeMer()),
    AIR(new ModeAir());

    private final StrategieModeTransport strategie;

    ModeTransport(StrategieModeTransport strategie) {
        this.strategie = strategie;
    }

    @Override
    public double calculerPrixBase(int distance, double poids, double volume) {
        return strategie.calculerPrixBase(distance, poids, volume);
    }

    @Override
    public int calculerDelai(int distance, boolean urgent) {
        return strategie.calculerDelai(distance, urgent);
    }

    @Override
    public boolean autoriseMarchandise(TypeMarchandise type) {
        return strategie.autoriseMarchandise(type);
    }

    @Override
    public boolean autoriseUrgence() {
        return strategie.autoriseUrgence();
    }
}
