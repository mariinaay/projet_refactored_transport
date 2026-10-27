package fr.efrei.transfret;

import java.util.ArrayList;
import java.util.List;

public class ConsoleCanalNotification implements CanalNotification {

    private static final int LONGUEUR_MAXIMALE_SMS = 160;
    private static final int LONGUEUR_SUFFIXE_ELLIPSIS = 3;

    private static final String SMTP_HOST = "smtp.transfret.fr";
    private static final String EXPEDITEUR = "noreply@transfret.fr";

    private final List<String[]> emails = new ArrayList<>();
    private final List<String[]> sms = new ArrayList<>();

    @Override
    public boolean envoyerEmail(String destinataire, String sujet, String corps) {
        if (destinataire == null || !destinataire.contains("@")) {
            return false;
        }
        System.out.println("[EMAIL via " + SMTP_HOST + "] " + EXPEDITEUR + " -> " + destinataire + " | " + sujet);
        System.out.println("        " + corps);
        emails.add(new String[]{destinataire, sujet, corps});
        return true;
    }

    @Override
    public void envoyerSms(String numero, String message) {
        if (message.length() > LONGUEUR_MAXIMALE_SMS) {
            message = message.substring(0, LONGUEUR_MAXIMALE_SMS - LONGUEUR_SUFFIXE_ELLIPSIS) + "...";
        }
        System.out.println("[SMS] -> " + numero + " : " + message);
        sms.add(new String[]{numero, message});
    }

    @Override
    public List<String[]> emailsEnvoyes() {
        return new ArrayList<>(emails);
    }

    @Override
    public List<String[]> smsEnvoyes() {
        return new ArrayList<>(sms);
    }
}
