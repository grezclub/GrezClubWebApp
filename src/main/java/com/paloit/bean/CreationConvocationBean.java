package com.paloit.bean;

import java.text.SimpleDateFormat;
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
import com.paloit.manager.ConnexionManager;
import com.paloit.manager.JoueurManager;
import com.paloit.manager.MatchManager;

@Component
@Scope
public class CreationConvocationBean {
	
	
	// =========================================================================
	// ATTRIBUTS
	// =========================================================================
	
	private Date date;
	private String lieu;
	private String adversaire;
	private String heure;
	private String commentaire;
	private String classe;
	private int number;
	
	private DualListModel<Joueur> listeJoueurs;
	private List<Joueur> source;
	private List<Joueur> target;
	private List<String> targetEssai;
	private List<Joueur> listeSelection;
	
	private Educateur educateur;
	
	//Manager
	private JoueurManager joueurManager;
	private ConnexionManager connexionManager;
	private MatchManager matchManager;



		// =========================================================================
		// CONSTRUCTEURS
		// =========================================================================

		public CreationConvocationBean() {

		}

		// =========================================================================
		// METHODES
		// =========================================================================
		
		//Listener de la PickList
		public void onTransfer(TransferEvent event) {

			
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

		//Dual List qui sert a la PickList
		public DualListModel<Joueur> getJoueursListe() {
			// listes source et cible 
			source = new ArrayList<Joueur>();
			target = new ArrayList<Joueur>();
			
			
			source = joueurManager.getAllJoueur();

			this.listeJoueurs = new DualListModel<Joueur>(source, target);

			return listeJoueurs;

		}

		//Recupération de la liste de joueur selectionnes
		public List<Joueur> getJoueursSelectionne() {

			List<Joueur> listeSelection = new ArrayList<Joueur>();

			for (int i = 0; i < this.targetEssai.size(); i++) {

				listeSelection.add(joueurManager.joueurParId(targetEssai.get(i)));
			}

			return listeSelection;

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
		
		/*
		 * Méthode permettant de saisir les infos et de renvoyé la page de selection 
		 * des joueurs
		 */
		public String creerEntrainement() {

			// Joueurs
			source = new ArrayList<Joueur>();
			target = new ArrayList<Joueur>();
			
		/*
		 * Recuperation des informations concernant l'educateur connecte
		 * puis en fonction, affiche la liste de joueurs qui lui correspond
		 * Admin = tous les joueurs
		 * Educ  = seulement ses joueurs
 		 */
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDatails = (UserDetails) auth.getPrincipal();
			this.educateur = connexionManager.educParLogin(userDatails.getUsername());
			
			if (this.educateur.getFonction().contentEquals("ROLE_ADMIN") ){
				source = joueurManager.getAllJoueur();
				this.listeJoueurs = new DualListModel<Joueur>(source, target);
				targetEssai = new ArrayList<String>();
				
				educateur = this.getUserName();

				return "creationConvocation2.jsf";
			}
			else
			source = joueurManager.listeJoueurCategorie(this.educateur.getEquipe().getCategorie());

			this.listeJoueurs = new DualListModel<Joueur>(source, target);
			targetEssai = new ArrayList<String>();
			
			educateur = this.getUserName();

			return "creationConvocation2.jsf";
		}
		
		//Methode renvoyant a la page recapitulative des informations saisie
		public String recapConvocation() {

			listeSelection = new ArrayList<Joueur>();
			for (int i = 0; i < this.targetEssai.size(); i++) {

				listeSelection.add(joueurManager.joueurParId(targetEssai.get(i)));
			}
			switch(number)
	        {
	            case 1:
	                this.classe = "A";
	            break;
	            case 2:
	            	this.classe = "B";
	            break;
	            case 3:
	            	this.classe = "C";
	            break;
	            default: this.classe = "A";
	            break;
	           
	            
	        }
			return "creationConvocation3.jsf";

		}
		
		//Modifier la liste de joueur
		public String modifieListeJoueur(){
			
			return "creationConvocation.jsf";
		}
		
		//Methode qui enregistre une Convocation
		public String enregistre(){
			
			matchManager.creerConvocation(listeSelection, educateur, date, lieu, adversaire, heure, commentaire, classe);
			this.listeSelection = null;
			this.educateur = null;
			this.date = null;
			this.lieu = null;
			this.adversaire = null;
			this.heure = null;
			this.commentaire = null;
			this.classe = null;
			this.number = 0;
			return "presenceMatch1.jsf";
		}
		
		
		// =========================================================================
		// AUTOWIRED
		// =========================================================================
		
		@Autowired
		public void setManager(JoueurManager manager) {
			this.joueurManager = manager;
		}
		@Autowired
		public void setManager(MatchManager manager) {
			this.matchManager = manager;
		}

		
		
		@Autowired
	    public void setManager(ConnexionManager manager) {
	        this.connexionManager = manager;
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

		public String getLieu() {
			return lieu;
		}

		public void setLieu(String lieu) {
			this.lieu = lieu;
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

		public String getCommentaire() {
			return commentaire;
		}

		public void setCommentaire(String commentaire) {
			this.commentaire = commentaire;
		}

		public String getClasse() {
			return classe;
		}

		public void setClasse(String classe) {
			this.classe = classe;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public DualListModel<Joueur> getListeJoueurs() {
			return listeJoueurs;
		}

		public void setListeJoueurs(DualListModel<Joueur> listeJoueurs) {
			this.listeJoueurs = listeJoueurs;
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

		public Educateur getEducateur() {
			return educateur;
		}

		public void setEducateur(Educateur educateur) {
			this.educateur = educateur;
		}

		public List<Joueur> getListeSelection() {
			return listeSelection;
		}

		public void setListeSelection(List<Joueur> listeSelection) {
			this.listeSelection = listeSelection;
		}
		public String getDateAffiche() {
			SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("dd/MM/yy");
		return formater.format(this.date);
		
		}

		
		
		
		
		
		
		

}
