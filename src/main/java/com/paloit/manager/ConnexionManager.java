package com.paloit.manager;

import java.util.List;

import com.paloit.entities.Convocation;
import com.paloit.entities.Educateur;
import com.paloit.entities.Joueur;

public interface ConnexionManager {
	
	//Methode de vérification des identifiants
	public Educateur testConnection(String login, String mdp);
	
	//Recuperer un educateur par son login
	public Educateur educParLogin (String login);
	
	 

}
