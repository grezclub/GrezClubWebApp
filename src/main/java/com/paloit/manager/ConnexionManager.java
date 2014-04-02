package com.paloit.manager;

import com.paloit.entities.Educateur;

public interface ConnexionManager {
	
	//Methode de vérification des identifiants
	public Educateur testConnection(String login, String mdp);
	
	//Recuperer un educateur par son login
	public Educateur educParLogin (String login);

}
