package com.paloit.manager;

import java.util.List;

import com.paloit.entities.Entrainement;
import com.paloit.entities.Joueur;

public interface PresenceManager {
	
	public List<Joueur> listePresence(Entrainement entrainement);
	
	 //Methode permettant de mettre a jour une liste de presence
    public void updatePresence (Entrainement entrainement, List<Joueur> joueurListe);
    
    //Methode qui supprime une liste de présence correspondant à un entrainement
    public void deletePresence (Entrainement entrainement);

}
