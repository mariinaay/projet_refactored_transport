package fr.efrei.transfret;

record NouveauSystemeConfiguration(ServiceClient serviceClient, ServiceExpedition serviceExpedition,
                                   ClientRepository clients, ExpeditionRepository expeditions,
                                   CanalNotification canalNotification, GenerateurFacture generateurFacture) {

    static NouveauSystemeConfiguration creer() {
        ClientRepository clients = new InMemoryClientRepository();
        ExpeditionRepository expeditions = new InMemoryExpeditionRepository();
        CanalNotification canalNotification = new ConsoleCanalNotification();
        Flotte flotte = new Flotte();
        GenerateurFacture generateurFacture = new GenerateurFacture();
        Notificateur notificateur = new Notificateur(canalNotification);
        ServiceClient serviceClient = new ServiceClient(clients, notificateur, new ValidateurClient());
        ServiceExpedition serviceExpedition = new ServiceExpedition(expeditions,
                new CalculateurPrix(new AssemblagePrix(java.util.List.of())),
                new AffectationVehicule(flotte),
                new ValidateurExpedition(), new EstimateurDelai(), notificateur);
        return new NouveauSystemeConfiguration(serviceClient, serviceExpedition, clients, expeditions,
                canalNotification, generateurFacture);
    }
}
