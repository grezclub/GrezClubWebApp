package com.paloit.dao;

import java.util.List;

import com.paloit.entities.Joueur;

public interface JoueurService {
    
    //Methode permettant de recuperer une liste de tous les joueurs   
    List<Joueur> findAll();
    
  //Methode permettant de recuperer une liste de tous les joueurs light   
    List<Joueur> findAllLight();
    
    //Methode permettant de recuperer une liste de joueur d'une cat�gorie sp�cifique
    List<Joueur> findByCategorie (String categorie);
    
    //Methode permettant de recuperer un joueur par son identifiant
    Joueur findById(Integer id);
    
    //Methode permettant de recuperer une liste de  joueurs appartenant à une même équipe
    List<Joueur> findByEquipe (int idEquipe);

    
    //Methode permettant de recuperer un joueur par son nom
    List<Joueur> findByName(String name);
    
    //Methode permettant de creer un joueur
    void creerJoueur(Joueur joueur);
    
    //Methode permettant de sauvegarder un joueur
    void save (Joueur joueur);
    
    
    //Methode permettant de supprimer un joueur
    void deleteJoueur (Joueur joueur);
    
    //Methode permettant de modifier un joueur
    void update (Joueur joueur);
    
    //Récupérer liste de joueur en fonction du mois d'anniversaire
    List<Joueur> listeJoueurAnniversaire();

}
