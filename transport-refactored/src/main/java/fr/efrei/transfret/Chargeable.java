package fr.efrei.transfret;

public interface Chargeable {

    double getChargeActuelle();

    void charger(Colis colis);

    void decharger(Colis colis);
}
