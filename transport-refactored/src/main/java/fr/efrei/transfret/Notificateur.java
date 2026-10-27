package fr.efrei.transfret;

public class Notificateur {

    private final CanalNotification canalNotification;

    public Notificateur(CanalNotification canalNotification) {
        this.canalNotification = canalNotification;
    }

    public void notifier(Client client, String sujet, String corps, String sms) {
        canalNotification.envoyerEmail(client.getEmail(), sujet, corps);
        if (client.getType().recoitSms()) {
            canalNotification.envoyerSms(client.getTelephone(), sms);
        }
    }

    public void notifierBienvenue(Client client) {
        canalNotification.envoyerEmail(client.getEmail(), "Bienvenue chez TransFret", "Bonjour " + client.getNom()
                + ", votre compte " + client.getType().name().toLowerCase() + " a été créé.");
    }
}
