package com.paloit.manager;

import java.util.List;

import com.paloit.entities.Entrainement;
import com.paloit.entities.Joueur;
import com.paloit.entities.Match;

public interface ConvocationManager {
	
	//Recupérer la liste des joueurs convoqué à un match
	public List<Joueur> listeConvocation(Match match);
	
	 //Methode permettant de mettre a jour une liste de convocation
    public void updateConvocation (Match match, List<Joueur> joueurListe);
    
    //Methode qui supprime une liste de convocation correspondant à un match
    public void deleteConvocation (Match match);
    


}
