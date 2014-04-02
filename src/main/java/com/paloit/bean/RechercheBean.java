package com.paloit.bean;

import java.util.List;




import javax.persistence.PostLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.paloit.dao.JoueurService;
import com.paloit.entities.Equipe;
import com.paloit.entities.Joueur;
import com.paloit.manager.JoueurManager;

@Component
@Scope
public class RechercheBean {
	
	// =========================================================================
    // ATTRIBUTS
    // =========================================================================

		private Joueur joueur;
		private JoueurService js;
		private JoueurManager joueurManager;
		private List<Joueur> filtreJoueur; 
		
		private String nomJoueur;
		private String prenomJoueur;
		private String adresseJoueur;
		private String telJoueur;
		private String categorieJoueur;
		private String dateNaissanceJoueur;
		private Equipe equipe;
		private int id;

	
	
	// =========================================================================
    // CONSTRUCTEURS
    // =========================================================================		
	
		
		
		public RechercheBean(){
		
		
		
	}
	// =========================================================================
    // METHODS
    // =========================================================================
	public String editJoueur() {
			
		//Mise en forme de la date recuperer pour interaction avec la BDD 
        this.nomJoueur = joueur.getNomJoueur();
        this.prenomJoueur = joueur.getPrenomJoueur();
        this.adresseJoueur = joueur.getAdresseJoueur();
        this.dateNaissanceJoueur = joueur.getDatenaissanceJoueur().toString();
        this.telJoueur = joueur.getTelJoueur();
		return "pretty:editJoueur";
	}
	public String saveEditedJoueur(){
		equipe = new Equipe();
		equipe = joueur.getEquipe();
		id = joueur.getIdJoueur();
		reinit();
		joueur.setNomJoueur(nomJoueur);
		joueur.setAdresseJoueur(adresseJoueur);
		joueur.setEquipe(equipe);
		joueur.setIdJoueur(id);
		joueur.setPrenomJoueur(prenomJoueur);
		joueur.setTelJoueur(telJoueur);
		joueurManager.modifierJoueur(joueur, dateNaissanceJoueur);
		return "pretty:home";
		
	}
	
	public void reinit() {
        joueur = new Joueur();
    }
	
	public String deleteJoueur(){
		this.id = joueur.getIdJoueur();
		joueurManager.supprimerJoueur(this.id);
		return "pretty:home";
	}
    // =========================================================================
    // OVERRIDES
    // =========================================================================

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
	 public List<Joueur> getAllJoueurs() {
	        return joueurManager.getAllJoueur();
	    }

	    public Joueur getJoueur() {
	        return joueur;
	    }

	    public void setJoueur(Joueur joueur) {
	        this.joueur = joueur;
	    }

	    public List<Joueur> getFiltreJoueur() {
			return filtreJoueur;
		}

		public void setFiltreJoueur(List<Joueur> filtreJoueur) {
			this.filtreJoueur = filtreJoueur;
		}

		public String getNomJoueur() {
			return nomJoueur;
		}
		public void setNomJoueur(String nomJoueur) {
			this.nomJoueur = nomJoueur;
		}
		public String getPrenomJoueur() {
			return prenomJoueur;
		}
		public void setPrenomJoueur(String prenomJoueur) {
			this.prenomJoueur = prenomJoueur;
		}
		public String getAdresseJoueur() {
			return adresseJoueur;
		}
		public void setAdresseJoueur(String adresseJoueur) {
			this.adresseJoueur = adresseJoueur;
		}
		public String getTelJoueur() {
			return telJoueur;
		}
		public void setTelJoueur(String telJoueur) {
			this.telJoueur = telJoueur;
		}
		public String getCategorieJoueur() {
			return categorieJoueur;
		}
		public void setCategorieJoueur(String categorieJoueur) {
			this.categorieJoueur = categorieJoueur;
		}
		public String getDateNaissanceJoueur() {
			return dateNaissanceJoueur;
		}
		public void setDateNaissanceJoueur(String dateNaissanceJoueur) {
			this.dateNaissanceJoueur = dateNaissanceJoueur;
		}
		@Autowired
	    public void setManager(JoueurManager manager) {
	        this.joueurManager = manager;
	    }
	
}
