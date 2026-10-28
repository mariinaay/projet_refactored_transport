package fr.efrei.transfret;

public class CamionFrigorifique extends Camion {

    public CamionFrigorifique(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax, false);
    }

    @Override
    protected boolean peutTransporter(Colis colis) {
        return colis.getType().autoriseCamionFrigorifique();
    }

    @Override
    protected boolean accepteListeVide() {
        return false;
    }

    @Override
    public void partir(int distanceKm) {
        super.partir(distanceKm);
        System.out.println("Réfrigération activée à " + TEMPERATURE_REFRIGERATION_CELSIUS + "°C sur "
                + immatriculation);
    }
}
