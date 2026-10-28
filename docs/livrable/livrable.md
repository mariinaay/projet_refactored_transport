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

# Étape 5 – Choix des stratégies

Le choix retenu est de faire porter la stratégie par chaque constante de `ModeTransport` : l'association reste directe. Une map injectée permettrait de remplacer les stratégies à l'assemblage, mais demanderait de maintenir les correspondances séparément.

# Étape 8 – Bilan des extensions

## Fichiers existants modifiés

- FLUVIAL : 3 fichiers existants modifiés (`ModeTransport`, `Flotte`, `Distances`), contre 6 prévus avant refactoring.
- Hors gabarit : 1 fichier existant modifié (`AssemblagePrix`), contre 2 prévus avant refactoring.

Au total : 4 fichiers applicatifs modifiés, ainsi que 2 fichiers de tests (`DecorateursPrixTest`, `NouveauSystemeConfiguration`).

## Les 68 tests passent-ils toujours ?

Non. Dans le module refactoré, 62 tests de contrat passent et 6 échouent après l'ajout du hors gabarit, car les prix attendus n'incluent pas les 10 % supplémentaires. Les assertions restent inchangées. Jusqu'à l'étape 7, les 68 tests passaient. Les 68 tests legacy et les 38 tests supplémentaires passent toujours ; `mvn install` échoue sur les 6 écarts.

## Tests unitaires des nouvelles classes

Les tests sont dans `ExtensionFluvialeTest` et `DecorateursPrixTest` : tarif et délai fluviaux, péniche, restrictions, seuil de 200 cm et majoration hors gabarit appliquée une seule fois.
