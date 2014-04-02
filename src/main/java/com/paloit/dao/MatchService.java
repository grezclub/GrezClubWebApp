package com.paloit.dao;

import java.util.List;

import com.paloit.entities.Match;

public interface MatchService {
	
	//Méthode qui vas creer une convocation
	public void enregistrerMatch(Match match);
	
	//Methode qui vas recupérer la liste de tous les matchs
	public List<Match> findAll();

}
