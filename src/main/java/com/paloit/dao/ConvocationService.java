package com.paloit.dao;

import java.util.List;

import com.paloit.entities.Convocation;
import com.paloit.entities.Entrainement;
import com.paloit.entities.Joueur;
import com.paloit.entities.Match;

public interface ConvocationService {
	
	public void enregistreConvocation(Match match, Joueur joueur);
	
	//recuperer une liste de joueur present à un entrainement
	public List<Joueur> listeJoueurConvoque(Match match);
	
	//Methode qui renvoie la liste de convocation d'un match
    public List<Convocation> listePresenceParMatch (Match match);
    
    //Methode qui supprime toute les convocations d'un match
    public void deleteConvocation (Convocation convocation);
    
  //Methode permettant de mettre a jour une liste de convocation
    public void updateConvocation (Match match, Joueur joueur);

}
