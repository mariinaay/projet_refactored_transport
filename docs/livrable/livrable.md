# Étape 1 – Diagnostic

## Défauts et principes SOLID

| Défaut | Emplacement | Principe violé |
|---|---|---|
| Validation, délais et notifications regroupés dans un service | `ServiceExpedition` | S — responsabilité unique |
| Facture générée par une entité métier | `Expedition.genererFacture()` | S — responsabilité unique |
| Ajouter un mode impose de modifier les conditions | `CalculateurPrix`, `ServiceExpedition`, `AffectationVehicule` | O — ouvert/fermé |
| Le camion frigorifique refuse des colis acceptés par son parent | `CamionFrigorifique.charger()` | L — substitution de Liskov |
| Des méthodes inutilisables sont imposées aux véhicules | `IVehicule` et ses implémentations | I — ségrégation des interfaces |
| Dépendances techniques imposées, singleton et état global | `Database`, `Flotte`, `ServiceClient`, `ServiceExpedition`, services email/SMS | D — inversion des dépendances |

## Emplacements à modifier avant refactoring

| Extension | Emplacement | Modification prévue |
|---|---|---|
| FLUVIAL | `CalculateurPrix.calculerPrixHT()` | Ajouter le tarif |
| FLUVIAL | `ServiceExpedition.calculerDelai()` | Ajouter le délai |
| FLUVIAL | `ServiceExpedition.creerExpedition()` | Interdire le réfrigéré et l'urgence |
| FLUVIAL | `ServiceExpedition.demarrerExpedition()` | Ajouter le départ fluvial |
| FLUVIAL | `AffectationVehicule.trouverVehicule()` | Sélectionner une péniche |
| FLUVIAL | `Flotte.initialiser()` | Ajouter la péniche |
| FLUVIAL | `Utils.getDistance()` | Ajouter Paris–Le Havre |
| FLUVIAL | `IVehicule` et nouvelle classe `Peniche` | Définir les opérations de la péniche |
| Hors gabarit | `Colis` | Détecter une dimension supérieure à 200 cm |
| Hors gabarit | `CalculateurPrix.calculerPrixHT()` | Appliquer une majoration unique de 10 % |
