package fr.efrei.transfret;

public class ServiceClient {

    private final ValidateurClient validateurClient;
    private final ClientRepository clientRepository;
    private final Notificateur notificateur;

    public ServiceClient(ClientRepository clientRepository, Notificateur notificateur,
                         ValidateurClient validateurClient) {
        this.clientRepository = clientRepository;
        this.notificateur = notificateur;
        this.validateurClient = validateurClient;
    }

    public Client creerClient(String nom, String email, String telephone, String adresse, TypeClient type, String siret) {
        if (!validateurClient.valider(nom, email, type, siret)) return null;
        Client c = new Client(nom, email, telephone, adresse, type, siret);
        clientRepository.sauvegarder(c);
        notificateur.notifierBienvenue(c);
        return c;
    }
}
