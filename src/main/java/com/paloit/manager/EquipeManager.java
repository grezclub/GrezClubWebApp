package com.paloit.manager;

import java.util.ArrayList;
import java.util.List;

import com.paloit.entities.Educateur;
import com.paloit.entities.Equipe;
import com.paloit.entities.Match;

public interface EquipeManager {

	//Recupération d'une équipa par l'id
		public Equipe findEquipeById(int idEquipe);
		
		 //Methode permettant de modifier une �quipe
	    Equipe findByCategorie (String categorie);
	    
	    

}
