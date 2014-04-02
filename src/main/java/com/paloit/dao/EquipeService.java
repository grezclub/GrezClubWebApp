package com.paloit.dao;

import java.util.List;

import com.paloit.entities.Equipe;

public interface EquipeService {

    // Methode permettant de recuperer une liste d'Equipe
    List<Equipe> findAll();

    // Methode permettant de recuperer une equipe par son identifiant
    Equipe findById( Integer id );

    // Methode permettant de sauvegarder une �quipe
    void save( Equipe equipe );

    // Methode permettant de supprimer une �quipe
    void deleteEquipe( Equipe equipe );
    
    //Methode permettant de modifier une �quipe
    Equipe findByCategorie (String categorie);

}
