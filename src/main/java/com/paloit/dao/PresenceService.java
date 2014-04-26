package com.paloit.dao;

import java.util.ArrayList;
import java.util.List;

import com.paloit.entities.Educateur;
import com.paloit.entities.Entrainement;
import com.paloit.entities.Joueur;
import com.paloit.entities.Presence;

public interface PresenceService {
    
    //M�thode permettant l'enregistrement d'une pr�sence
    public void enregistrerPresence (Entrainement entrainement, Joueur joueur);
    
    //M�thode permettant l'affichage de tous les joueurs pr�sent lors d'un entrainement
    public ArrayList<Joueur> listeJoueurPresent (Entrainement entrainemnt);
    
    //M�thode permettant de savoir qui a encadrer un entrainement
    public Educateur encadreEntrainement (Entrainement entrainemnt);

    //Methode permettant de mettre a jour une liste de presence
    public void updatePresence (Entrainement entrainement, Joueur joueur);
    
    //Methode qui supprime toute les presences d'un entrainement
    public void deletePresence (Presence presence);
    
    //Methode qui renvoie la liste de presence d'un entrainement
    public List<Presence> listePresenceParEntrainement (Entrainement entrainement);
    
    //Methode permettant de recuperer une liste de présence d'un joueur
    public List<Presence> statPresenceJoueur (Joueur joueur);
}
