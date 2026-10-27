package fr.efrei.transfret;

import java.util.List;

public interface ExpeditionRepository {

    void sauvegarder(Expedition expedition);

    Expedition trouverParId(String id);

    void mettreAJourStatut(Expedition expedition);

    List<Expedition> toutes();
}
