package com.paloit.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.paloit.entities.Joueur;
import com.paloit.entities.Match;
import com.paloit.manager.ConvocationManager;
import com.paloit.manager.MatchManager;

@Component
@Scope
public class ConvocationBean {
	
	// =========================================================================
		// ATTRIBUTS
		// =========================================================================
	private MatchManager matchManager;
	private ConvocationManager convocationManager;
	private String categorie;
	private int number;
	private List<Match> listeMatch;
	private List<Joueur> listejoueurConvoqueA;
	private List<Joueur> listejoueurConvoqueB;
	private List<Joueur> listejoueurConvoqueC;
	private Match match;
	private Match matchA;
	private Match matchB;
	private Match matchC;
	private String page;

			// =========================================================================
			// CONSTRUCTEURS
			// =========================================================================

			public ConvocationBean() {

			}

			// =========================================================================
			// METHODES
			// =========================================================================
			//Récupère la liste des entrainements
			public String listeMatchConvocation(){
				
				switch(number)
		        {
		            case 1:
		                this.categorie = "U7";
		            break;
		            case 2:
		            	this.categorie = "U9";
		            break;
		            case 3:
		            	this.categorie = "U11";
		            break;
		            case 4:
		            	this.categorie = "U13";
		            break;
		            case 5:
		            	this.categorie = "U15";
		            break;
		            case 6:
		            	this.categorie = "U18";
		            break;
		            case 7:
		            	this.categorie = "Senior";
		            break;
		            default: this.categorie = "";
	                break;
		        }
		            
		       listeMatch = new ArrayList<Match>();
		       listejoueurConvoqueA = new ArrayList<Joueur>();
		       listejoueurConvoqueB = new ArrayList<Joueur>();
		       listejoueurConvoqueC = new ArrayList<Joueur>();
		       matchA = new Match();
		       matchB = new Match();
		       matchC = new Match();

			listeMatch = matchManager.dernierMatch(this.categorie);
			
			switch (listeMatch.size()) {
			
			case 3:
				listejoueurConvoqueA = convocationManager.listeConvocation(listeMatch.get(0));
				listejoueurConvoqueB = convocationManager.listeConvocation(listeMatch.get(1));
				listejoueurConvoqueC = convocationManager.listeConvocation(listeMatch.get(2));
				matchA = listeMatch.get(0);
				matchB.equals(listeMatch.get(1)) ;
				matchC.equals(listeMatch.get(2)) ;
				System.out.println("if numero 3 "+matchA.getAdversaire());
				  page = "convocation1.xhtml";
				  break;
				
			case 2:
				listejoueurConvoqueA = convocationManager.listeConvocation(listeMatch.get(0));
				listejoueurConvoqueB = convocationManager.listeConvocation(listeMatch.get(1));
				matchA = listeMatch.get(0);
				matchB = listeMatch.get(1);
				System.out.println("if numero 2 "+matchA.getAdversaire()+" <<< "+matchB.getAdversaire());
				page = "convocation2.xhtml";
				  break;
			
			case 1:
				listejoueurConvoqueA = convocationManager.listeConvocation(listeMatch.get(0));
				matchA = listeMatch.get(0);
				System.out.println("if numero 1 "+matchA.getAdversaire());
				page = "convocation3.xhtml";
				  break;
			default:
				break;
			}

			return page;
			
						
			}
		
			// =========================================================================
			// AUTOWIRED
			// =========================================================================
			@Autowired
		    public void setManager(MatchManager manager) {
		        this.matchManager = manager;
		    }
			@Autowired
		    public void setManager(ConvocationManager convocationManager) {
		        this.convocationManager = convocationManager;
		    }

			
		
			// =========================================================================
			// GETTERS & SETTERS
			// =========================================================================
			public List<Match> getListeMatch() {
				return listeMatch;
			}

			public void setListeMatch(List<Match> listeMatch) {
				this.listeMatch = listeMatch;
			}

			public int getNumber() {
				return number;
			}

			public void setNumber(int number) {
				this.number = number;
			}

			public Match getMatch() {
				return match;
			}

			public void setMatch(Match match) {
				this.match = match;
			}

			public List<Joueur> getListejoueurConvoqueA() {
				return listejoueurConvoqueA;
			}

			public void setListejoueurConvoqueA(List<Joueur> listejoueurConvoqueA) {
				this.listejoueurConvoqueA = listejoueurConvoqueA;
			}

			public List<Joueur> getListejoueurConvoqueB() {
				return listejoueurConvoqueB;
			}

			public void setListejoueurConvoqueB(List<Joueur> listejoueurConvoqueB) {
				this.listejoueurConvoqueB = listejoueurConvoqueB;
			}

			public List<Joueur> getListejoueurConvoqueC() {
				return listejoueurConvoqueC;
			}

			public void setListejoueurConvoqueC(List<Joueur> listejoueurConvoqueC) {
				this.listejoueurConvoqueC = listejoueurConvoqueC;
			}

			public Match getMatchA() {
				return matchA;
			}

			public void setMatchA(Match matchA) {
				this.matchA = matchA;
			}

			public Match getMatchB() {
				return matchB;
			}

			public void setMatchB(Match matchB) {
				this.matchB = matchB;
			}

			public Match getMatchC() {
				return matchC;
			}

			public void setMatchC(Match matchC) {
				this.matchC = matchC;
			}
			
				
}
