package com.paloit.dao;

import java.util.List;

import com.paloit.entities.Entrainement;
import com.paloit.entities.Match;

public interface MatchService {
	
	//Méthode qui vas creer une convocation
	public void enregistrerMatch(Match match);
	
	//Methode qui vas recupérer la liste de tous les matchs
	public List<Match> findAll();
	
	 //Methode permettant de modifier un match
    public void updateMatch (Match match);
    
  //Methode permettant de supprimer un match
    public void deleteMatch (Match match);

}
