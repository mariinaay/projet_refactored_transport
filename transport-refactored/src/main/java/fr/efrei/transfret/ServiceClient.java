package fr.efrei.transfret;

public class ServiceClient {

    private final Notificateur notificateur = new Notificateur();

    private final ValidateurClient validateurClient = new ValidateurClient();

    public Client creerClient(String nom, String email, String telephone, String adresse, TypeClient type, String siret) {
        if (!validateurClient.valider(nom, email, type, siret)) return null;
        Client c = new Client(nom, email, telephone, adresse, type, siret);
        Database.getInstance().sauvegarderClient(c);
        notificateur.notifierBienvenue(c);
        return c;
    }
}
