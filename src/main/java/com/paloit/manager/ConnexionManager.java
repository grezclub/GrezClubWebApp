package com.paloit.manager;

import java.util.List;

import com.paloit.entities.Educateur;
import com.paloit.entities.Entrainement;
import com.paloit.entities.Joueur;
import com.paloit.entities.Match;

public interface ConnexionManager {
	
	//Methode de vérification des identifiants
	public Educateur testConnection(String login, String mdp);
	
	//Recuperer un educateur par son login
	public Educateur educParLogin (String login);
	

	


}
