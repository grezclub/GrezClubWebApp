package com.paloit.dao;

import java.util.ArrayList;

import com.paloit.entities.Educateur;
import com.paloit.entities.Entrainement;
import com.paloit.entities.Joueur;

public interface PresenceService {
    
    //M�thode permettant l'enregistrement d'une pr�sence
    public void enregistrerPresence (Entrainement entrainement, Joueur joueur);
    
    //M�thode permettant l'affichage de tous les joueurs pr�sent lors d'un entrainement
    public ArrayList<Joueur> listeJoueurPresent (Entrainement entrainemnt);
    
    //M�thode permettant de savoir qui a encadrer un entrainement
    public Educateur encadreEntrainement (Entrainement entrainemnt);

}
