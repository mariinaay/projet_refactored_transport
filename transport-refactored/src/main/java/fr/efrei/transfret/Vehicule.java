package fr.efrei.transfret;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class Vehicule implements Chargeable, Deplacable {

    protected static final int TEMPERATURE_REFRIGERATION_CELSIUS = 4;

    protected String immatriculation;
    protected double capaciteMax;
    protected double chargeActuelle;
    protected boolean disponible = true;
    protected List<Colis> chargement = new ArrayList<Colis>();
    private final Set<ModeTransport> modesAutorises;

    public Vehicule(String immatriculation, double capaciteMax, ModeTransport modeAutorise) {
        this.immatriculation = immatriculation;
        this.capaciteMax = capaciteMax;
        this.modesAutorises = Set.of(modeAutorise);
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public double getCapaciteMax() {
        return capaciteMax;
    }

    @Override
    public double getChargeActuelle() {
        return chargeActuelle;
    }

    public boolean estDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public void charger(Colis colis) {
        if (chargeActuelle + colis.getPoids() > capaciteMax) {
            System.out.println("ERREUR : capacité dépassée pour " + immatriculation);
            return;
        }
        chargement.add(colis);
        chargeActuelle = chargeActuelle + colis.getPoids();
    }

    @Override
    public void decharger(Colis colis) {
        if (chargement.remove(colis)) {
            chargeActuelle = chargeActuelle - colis.getPoids();
        }
    }

    public boolean peutPrendreEnCharge(List<Colis> colis, ModeTransport mode) {
        return colis != null && modesAutorises.contains(mode)
                && (!colis.isEmpty() || accepteListeVide())
                && colis.stream().allMatch(colisUnitaire ->
                mode.autoriseMarchandise(colisUnitaire.getType()) && peutTransporter(colisUnitaire));
    }

    protected boolean peutTransporter(Colis colis) {
        return true;
    }

    protected boolean accepteListeVide() {
        return true;
    }

    public abstract void partir(int distanceKm);

    protected void faireLePlein() {
        System.out.println("Plein effectué pour " + immatriculation);
    }
}
