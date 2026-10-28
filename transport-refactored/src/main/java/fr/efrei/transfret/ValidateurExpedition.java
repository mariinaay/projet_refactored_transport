package fr.efrei.transfret;

import java.util.List;
import java.util.Optional;

public class ValidateurExpedition {

    public boolean valider(Client client, List<Colis> colis, String villeDepart,
                           String villeArrivee, ModeTransport mode, boolean urgent) {
        if (client == null) {
            System.out.println("ERREUR : client obligatoire");
            return false;
        }
        if (colis == null || colis.isEmpty()) {
            System.out.println("ERREUR : au moins un colis est nécessaire");
            return false;
        }
        Optional<Colis> poidsInvalide = colis.stream().filter(c -> c.getPoids() <= 0).findFirst();
        if (poidsInvalide.isPresent()) {
            System.out.println("ERREUR : poids invalide pour " + poidsInvalide.get().getDescription());
            return false;
        }
        Optional<Colis> marchandiseInterdite = colis.stream()
                .filter(c -> !mode.autoriseMarchandise(c.getType())).findFirst();
        if (marchandiseInterdite.isPresent()) {
            System.out.println("ERREUR : le mode " + mode + " n'accepte pas les marchandises "
                    + marchandiseInterdite.get().getLibelleType());
            return false;
        }
        if (urgent && !mode.autoriseUrgence()) {
            System.out.println("ERREUR : l'option urgente n'est pas disponible pour ce mode de transport");
            return false;
        }
        if (Distances.getDistance(villeDepart, villeArrivee) == Distances.DISTANCE_INCONNUE) {
            System.out.println("ERREUR : trajet inconnu " + villeDepart + " -> " + villeArrivee);
            return false;
        }
        return true;
    }
}
