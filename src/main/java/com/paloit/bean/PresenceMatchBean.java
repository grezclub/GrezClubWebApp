package com.paloit.bean;

import java.util.Date;
import java.util.List;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.paloit.entities.Educateur;
import com.paloit.entities.Joueur;
import com.paloit.entities.Match;
import com.paloit.manager.ConnexionManager;
import com.paloit.manager.EducateurManager;
import com.paloit.manager.JoueurManager;
import com.paloit.manager.MatchManager;
import com.paloit.manager.PresenceManager;

@Component
@Scope
public class PresenceMatchBean {
	
	private Date date;
	private String dateAffiche;
	private String lieu;
	private List<Match> filtreMatch;
	private Match match;

	private DualListModel<Joueur> listeJoueurs;
	
	List<Joueur> listeSelection;
	List<Joueur> listeJoueurPresent;
	List<Educateur> listeEducateur;
	List<Match> listeMatch;
	private Joueur joueur;
	private Educateur educateurSelectionner;
	private Educateur educateur;

	private JoueurManager joueurManager;
	private EducateurManager educateurManager;
	private ConnexionManager connexionManager;
	private MatchManager matchManager;
	private PresenceManager presenceManager;
	

	// =========================================================================
	// CONSTRUCTEURS
	// =========================================================================

	public PresenceMatchBean() {
		// TODO Auto-generated constructor stub
	}
	
	// =========================================================================
		// METHODS
		// =========================================================================
		
	//Récupère la liste des entrainements
		public List<Match> getListeMatch(){
			
			return matchManager.getAllMatch();		
		}
		
		//Affiche les joueurs present a l'entrainement
		
		
		// =========================================================================
		// @Autowired
		// =========================================================================

		@Autowired
		public void setManager(JoueurManager manager) {
			this.joueurManager = manager;
		}

		@Autowired
		public void setManager(EducateurManager manager) {
			this.educateurManager = manager;
		}
		
		@Autowired
	    public void setManager(ConnexionManager manager) {
	        this.connexionManager = manager;
	    }
		
		@Autowired
	    public void setManager(MatchManager manager) {
	        this.matchManager = manager;
	    }
		@Autowired
	    public void setManager(PresenceManager manager) {
	        this.presenceManager = manager;
	    }
		
		// =========================================================================
		// GETTERS & SETTERS
		// =========================================================================
		
		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public String getDateAffiche() {
			return dateAffiche;
		}

		public void setDateAffiche(String dateAffiche) {
			this.dateAffiche = dateAffiche;
		}

		public String getLieu() {
			return lieu;
		}

		public void setLieu(String lieu) {
			this.lieu = lieu;
		}

		public Match getMatch() {
			return match;
		}

		public void setMatch(Match match) {
			this.match = match;
		}

		public DualListModel<Joueur> getListeJoueurs() {
			return listeJoueurs;
		}

		public void setListeJoueurs(DualListModel<Joueur> listeJoueurs) {
			this.listeJoueurs = listeJoueurs;
		}

		public List<Joueur> getListeSelection() {
			return listeSelection;
		}

		public void setListeSelection(List<Joueur> listeSelection) {
			this.listeSelection = listeSelection;
		}

		public List<Joueur> getListeJoueurPresent() {
			return listeJoueurPresent;
		}

		public void setListeJoueurPresent(List<Joueur> listeJoueurPresent) {
			this.listeJoueurPresent = listeJoueurPresent;
		}

		public List<Educateur> getListeEducateur() {
			return listeEducateur;
		}

		public void setListeEducateur(List<Educateur> listeEducateur) {
			this.listeEducateur = listeEducateur;
		}

		public Joueur getJoueur() {
			return joueur;
		}

		public void setJoueur(Joueur joueur) {
			this.joueur = joueur;
		}

		public Educateur getEducateurSelectionner() {
			return educateurSelectionner;
		}

		public void setEducateurSelectionner(Educateur educateurSelectionner) {
			this.educateurSelectionner = educateurSelectionner;
		}

		public Educateur getEducateur() {
			return educateur;
		}

		public void setEducateur(Educateur educateur) {
			this.educateur = educateur;
		}

		public EducateurManager getEducateurManager() {
			return educateurManager;
		}

		public void setEducateurManager(EducateurManager educateurManager) {
			this.educateurManager = educateurManager;
		}

		public void setListeMatch(List<Match> listeMatch) {
			this.listeMatch = listeMatch;
		}

		public List<Match> getFiltreMatch() {
			return filtreMatch;
		}

		public void setFiltreMatch(List<Match> filtreMatch) {
			this.filtreMatch = filtreMatch;
		}
		
		

}
