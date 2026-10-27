package fr.efrei.transfret;

public interface ClientRepository {

    void sauvegarder(Client client);

    Client trouverParEmail(String email);
}
