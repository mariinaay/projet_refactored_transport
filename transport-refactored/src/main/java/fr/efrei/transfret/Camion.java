package fr.efrei.transfret;

public class Camion extends Vehicule {

    protected boolean adr; // certification matières dangereuses

    public Camion(String immatriculation, double capaciteMax, boolean adr) {
        super(immatriculation, capaciteMax, ModeTransport.ROUTE);
        this.adr = adr;
    }

    @Override
    protected boolean peutTransporter(Colis colis) {
        return colis.getType().autoriseCamion(adr);
    }

    @Override
    public void partir(int distanceKm) {
        faireLePlein();
        System.out.println("Le camion " + immatriculation + " roule sur " + distanceKm + " km");
    }
}
