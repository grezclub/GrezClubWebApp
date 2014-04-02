package com.paloit.dao;

import java.util.List;

import com.paloit.entities.Educateur;

public interface EducateurService {
    
    //Methode permettant de recuperer une liste d'Educateur
    List<Educateur> findAll();
    
    //Methode permettant de recuperer un educateur par son identifiant
    Educateur findById(Integer id);
    
    //Methode permettant de recuperer un educateur par son login
    Educateur findByLogin (String login);
    
    public Educateur getEducateurByLog (String login, String mdp);
    
    //Methode permettant de sauvegarder un educateur
    void save (Educateur educateur);
    
    //Methode permettant de supprimer un educateur
    void deleteEducateur (Educateur educateur);
    
    //Methode permettant de modifier un educateur
    void modifierEducateur (Educateur educateur);

}
