package fr.efrei.transfret;

import java.util.List;

public interface CanalNotification {

    boolean envoyerEmail(String destinataire, String sujet, String corps);

    void envoyerSms(String numero, String message);

    List<String[]> emailsEnvoyes();

    List<String[]> smsEnvoyes();
}
