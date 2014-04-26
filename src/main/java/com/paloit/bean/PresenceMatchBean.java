package com.paloit.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.paloit.entities.Educateur;
import com.paloit.entities.Joueur;
import com.paloit.entities.Match;
import com.paloit.manager.ConnexionManager;
import com.paloit.manager.ConvocationManager;
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
	private String commentaire;
	public String adversaire;
	public String heure;
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
	private Integer number;
	private String classe;
	
	private List<Joueur> source;
	private List<Joueur> target;
	private List<String> targetEssai;

	private JoueurManager joueurManager;
	private EducateurManager educateurManager;
	private ConnexionManager connexionManager;
	private MatchManager matchManager;
	private ConvocationManager convocationManager;
	
	

	// =========================================================================
	// CONSTRUCTEURS
	// =========================================================================

	public PresenceMatchBean() {
		// TODO Auto-generated constructor stub
	}
	
	// =========================================================================
		// METHODS
		// =========================================================================
		
	//Listener de la PickList
		public void onTransfer(TransferEvent event) {

			StringBuilder builder = new StringBuilder();
			for (Object item : event.getItems()) {

				// Joueur joueur = (Joueur) event.getItems().get(0);

				if (targetEssai.contains(item)) {
					targetEssai.remove(item);
					System.out.println("sortie de la liste = " + item);

				} else
					targetEssai.add((String) item);
				for (int j = 0; j < targetEssai.size(); j++) {
					System.out.println("Dans la liste = " + targetEssai.get(j));}}

				} 


		public DualListModel<Joueur> getJoueursListe() {
			// listes source et cible 
			source = new ArrayList<Joueur>();
			target = new ArrayList<Joueur>();
			
			
			source = joueurManager.getAllJoueur();

			this.listeJoueurs = new DualListModel<Joueur>(source, target);

			return listeJoueurs;

		}
		
		
		//Methode qui renvoie les infos sur l'educateur connécter
		public Educateur getUserName(){
			/*Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
			return userDetails.toString();*/
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDatails = (UserDetails) auth.getPrincipal();
			this.educateur = connexionManager.educParLogin(userDatails.getUsername());
			return educateur;

		}
		
	
	//Récupère la liste des entrainements
		public List<Match> getListeMatch(){
			
			//Recuperation des informations concernant l'educateur connecte
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDatails = (UserDetails) auth.getPrincipal();
			this.educateur = connexionManager.educParLogin(userDatails.getUsername());
			if (this.educateur.getFonction().contentEquals("ROLE_ADMIN") ){
				return matchManager.getAllMatch();
			}
			else
			return matchManager.listMatchEducateur(this.educateur.getIdEducateur());
			
					
		}
		
		//Affiche les joueurs convoqué au match selectionné
		public String detailMatch(){
			
			listeJoueurPresent = new ArrayList<Joueur>();
			listeJoueurPresent =  convocationManager.listeConvocation(match);
			System.out.println(listeJoueurPresent.size());
			return "presenceMatch2.jsf" ;
		}
		
		//Modifie liste joueur dans un match existant
		public String modifieListeJoueurExistant(){
			
//			if (match.getClasse().equals("A")){
//				number = 1;
//			}
//			else if (match.getClasse().equals("B")) {
//				number = 2;
//			}
//			else if (match.getClasse().equals("C")) {
//				number = 3;
//			}
//			else {
//				number = 0;
//			}
//			
			return "modifierMatch.jsf";
		}

		//modification de la modif
		public String modifieListeJoueur(){
			return "modifierMatch.jsf";
		}
		//Recap de l'entrainement modifie
		public String recapMatchModifie() {

			listeSelection = new ArrayList<Joueur>();
			for (int i = 0; i < this.targetEssai.size(); i++) {

				listeSelection.add(joueurManager.joueurParId(targetEssai.get(i)));
			}
			return "modifierMatch3.jsf";

		}
		
		public String updateMatch(){
			
			switch (number) {
			case 0:
				match.setClasse(match.getClasse());
				break;
			case 1:
				match.setClasse("A");
				break;
			case 2:
				match.setClasse("B");
				break;
			case 3:
				match.setClasse("C");
				break;
			
			default:
				match.setClasse("A");
				break;
			}
			// Joueurs
					source = new ArrayList<Joueur>();
					target = new ArrayList<Joueur>();
					
					//Recuperation des informations concernant l'educateur connecte
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					UserDetails userDatails = (UserDetails) auth.getPrincipal();
					this.educateur = connexionManager.educParLogin(userDatails.getUsername());
					
					if (this.educateur.getFonction().contentEquals("ROLE_ADMIN") ){
						source = joueurManager.getAllJoueur();
						this.listeJoueurs = new DualListModel<Joueur>(source, target);
						targetEssai = new ArrayList<String>();
						
						educateur = this.getUserName();

						return "modifierMatch2.jsf";
					}
					else
					source = joueurManager.listeJoueurCategorie(this.educateur.getEquipe().getCategorie());

					this.listeJoueurs = new DualListModel<Joueur>(source, target);
					targetEssai = new ArrayList<String>();
					
					educateur = this.getUserName();
					

					return "modifierMatch2.jsf";
		}
		
		//Methode qui met a jour un entrainement
		public String update(){
			
			convocationManager.updateConvocation(match, listeSelection);
			this.date = null;
			this.lieu = null;
			this.classe = null;
			this.number = 0;
			
			return "presenceMatch1.jsf";
		}

		//Methode qui suprime un match et sa liste de convocation
		public String delete(){
			
			convocationManager.deleteConvocation(match);
			matchManager.deleteMatch(match);
			return "presenceMatch1.jsf";
		}
		
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
	    public void setManager(ConvocationManager convocationManager) {
	        this.convocationManager = convocationManager;
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

		public List<Joueur> getSource() {
			return source;
		}

		public void setSource(List<Joueur> source) {
			this.source = source;
		}
	
		public List<Joueur> getTarget() {
			return target;
		}

		public void setTarget(List<Joueur> target) {
			this.target = target;
		}

		public List<String> getTargetEssai() {
			return targetEssai;
		}

		public void setTargetEssai(List<String> targetEssai) {
			this.targetEssai = targetEssai;
		}

		public Integer getNumber() {
			return number;
		}

		public void setNumber(Integer number) {
			this.number = number;
		}

		public String getCommentaire() {
			return commentaire;
		}

		public void setCommentaire(String commentaire) {
			this.commentaire = commentaire;
		}

		public String getAdversaire() {
			return adversaire;
		}

		public void setAdversaire(String adversaire) {
			this.adversaire = adversaire;
		}

		public String getHeure() {
			return heure;
		}

		public void setHeure(String heure) {
			this.heure = heure;
		}

		public String getClasse() {
			return classe;
		}

		public void setClasse(String classe) {
			this.classe = classe;
		}
		
		
		
		

}
