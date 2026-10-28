package fr.efrei.transfret;

public class Bateau extends Vehicule {

    private static final double KILOMETRES_PAR_MILLE_NAUTIQUE = 1.852;
    private boolean conteneursRefrigeres;

    public Bateau(String immatriculation, double capaciteMax, boolean conteneursRefrigeres) {
        super(immatriculation, capaciteMax, ModeTransport.MER);
        this.conteneursRefrigeres = conteneursRefrigeres;
    }

    @Override
    protected boolean peutTransporter(Colis colis) {
        return colis.getType().autoriseBateau(conteneursRefrigeres);
    }

    @Override
    public void partir(int distanceKm) {
        faireLePlein();
        int milesNautiques = (int) (distanceKm / KILOMETRES_PAR_MILLE_NAUTIQUE);
        System.out.println("Le navire " + immatriculation + " navigue sur " + milesNautiques + " milles nautiques");
        if (conteneursRefrigeres && chargement.stream()
                .anyMatch(Colis::estRefrigere)) {
            System.out.println("Conteneurs frigorifiques réglés à " + TEMPERATURE_REFRIGERATION_CELSIUS + "°C");
        }
    }
}
