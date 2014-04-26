package com.paloit.manager;

import java.util.Date;
import java.util.List;

import com.paloit.entities.Educateur;
import com.paloit.entities.Entrainement;
import com.paloit.entities.Joueur;

public interface EntrainementManager {

	public void creerEntrainement ( List<Joueur> joueurListe,Educateur educateur, Date date, String lieu);
	
	public List<Entrainement> getAllEntrainement();
	
	//Recuperer la liste des entrainements d'un educateur
	public List<Entrainement> getEntrainementByEduc(Educateur educ);
	
	//Modifie un entrainement
	public void updateEntrainement(Entrainement entrainement);
	
	//Suprime un entrainement
	public void deleteEntrainement(Entrainement entrainement);
	
	//find by id
	public Entrainement findById (Integer id);
}
