package com.paloit.manager;

import java.util.Date;
import java.util.List;

import com.paloit.entities.Educateur;
import com.paloit.entities.Joueur;
import com.paloit.entities.Match;

public interface MatchManager {
	
	public void creerConvocation ( List<Joueur> joueurListe,Educateur educateur, Date date, String lieu, String adversaire,
										String heure, String commentaire);
	
	//Recuperation de la liste de tous les match
	public List<Match> getAllMatch();

	//Suprime un match
	public void deleteMatch(Match match);
}
