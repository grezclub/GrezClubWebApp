package com.paloit.dao;

import java.util.List;

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
    
    //Methode permettant de recuperer un match avec son id
    public Match findById (Integer id);
    
    //Recupérer les match d'un educateur a partir de l'id educateur
    public List<Match> findMatchEduc(int idEducateur);
    
    //Recupérer le dernier match d'un educateur
    public List<Match> findLastMatchEduc(int idEducateur);
    
    //Récupérer les dernières convocation d'une catégorie
    public List<Match> findLastMatchEquipe (int idEquipe);

}
