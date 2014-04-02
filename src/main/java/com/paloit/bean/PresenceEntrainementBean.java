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

import util.UtilitaireSession;

import com.paloit.entities.Educateur;
import com.paloit.entities.Entrainement;
import com.paloit.entities.Joueur;
import com.paloit.manager.ConnexionManager;
import com.paloit.manager.EducateurManager;
import com.paloit.manager.EntrainementManager;
import com.paloit.manager.JoueurManager;
import com.paloit.manager.PresenceManager;

@Component
@Scope
public class PresenceEntrainementBean {

	private Date date;
	private String dateAffiche;
	private String lieu;
	private List<Entrainement> filtreEntrainement;
	private Entrainement entrainement;

	private DualListModel<Joueur> listeJoueurs;
	private List<Joueur> source;
	private List<Joueur> target;
	private List<String> targetEssai;
	
	List<Joueur> listeSelection;
	List<Joueur> listeJoueurPresent;
	List<Educateur> listeEducateur;
	List<Entrainement> listeEntrainement;
	private Joueur joueur;
	private Educateur educateurSelectionner;
	private Educateur educateur;

	private JoueurManager joueurManager;
	private EducateurManager educateurManager;
	private ConnexionManager connexionManager;
	private EntrainementManager entrainementManager;
	private PresenceManager presenceManager;
	

	// =========================================================================
	// CONSTRUCTEURS
	// =========================================================================
	public PresenceEntrainementBean() {

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

	public List<Joueur> getJoueursSelectionne() {

		List<Joueur> listeSelection = new ArrayList();

		for (int i = 0; i < this.targetEssai.size(); i++) {

			listeSelection.add(joueurManager.joueurParId(targetEssai.get(i)));
		}

		return listeSelection;

	}

	public String creerEntrainement() {

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

			return "pretty:creerEntrainement";
		}
		else
		source = joueurManager.listeJoueurCategorie(this.educateur.getEquipe().getCategorie());

		this.listeJoueurs = new DualListModel<Joueur>(source, target);
		targetEssai = new ArrayList<String>();
		
		educateur = this.getUserName();

		return "pretty:creerEntrainement";
	}

	public String recapEntrainement() {

		listeSelection = new ArrayList<Joueur>();
		for (int i = 0; i < this.targetEssai.size(); i++) {

			listeSelection.add(joueurManager.joueurParId(targetEssai.get(i)));
		}
		return "pretty:recapEntrainement";

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
	
	//Methode qui enregistre un Entrainement
	public String enregistre(){
		
		entrainementManager.creerEntrainement(listeSelection, educateur, date, lieu);
		
		return "pretty:home";
	}
	
	//Modifier la liste de joueur
	public String modifieListeJoueur(){
		
		return "pretty:creerEntrainement1";
	}
	
	//Recupère la liste des educateur
	public List<Educateur> getListeEducateur(){
		
		listeEducateur = new ArrayList<Educateur> ();
		listeEducateur = educateurManager.findAll();
			return listeEducateur;
	}
	
	//Récupère la liste des entrainements
	public List<Entrainement> getListeEntrainement(){
		
		return entrainementManager.getAllEntrainement();		
	}
	
	//Affiche les joueurs present a l'entrainement
	public String detailEntrainement(){
		
		listeJoueurPresent = new ArrayList<Joueur>();
		listeJoueurPresent =  presenceManager.listePresence(entrainement);
		return "pretty:presenceEntrainement2";
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
    public void setManager(EntrainementManager manager) {
        this.entrainementManager = manager;
    }
	@Autowired
    public void setManager(PresenceManager manager) {
        this.presenceManager = manager;
    }
	

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

	public DualListModel<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}

	public void setListeJoueurs(DualListModel<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	public Date getDate() {
		return this.date;
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

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public List<Joueur> getListeSelection() {
		return listeSelection;
	}

	public void setListeSelection(List<Joueur> listeSelection) {
		this.listeSelection = listeSelection;
	}

	
	public Educateur getEducateur() {
		return educateur;
	}

	
	public void setEducateur(Educateur educateur) {
		this.educateur = educateur;
	}

	public String getDateAffiche() {
			SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("dd/MM/yy");
		return formater.format(this.date);
		
	}

	public void setDateAffiche(String dateAffiche) {
		this.dateAffiche = dateAffiche;
	}

	public void setListeEducateur(List<Educateur> listeEducateur) {
		this.listeEducateur = listeEducateur;
	}

	

	public void setListeEntrainement(List<Entrainement> listeEntrainement) {
		this.listeEntrainement = listeEntrainement;
	}

	public Educateur getEducateurSelectionner() {
		return educateurSelectionner;
	}

	public void setEducateurSelectionner(Educateur educateurSelectionner) {
		this.educateurSelectionner = educateurSelectionner;
	}

	public List<Entrainement> getFiltreEntrainement() {
		return filtreEntrainement;
	}

	public void setFiltreEntrainement(List<Entrainement> filtreEntrainement) {
		this.filtreEntrainement = filtreEntrainement;
	}

	public Entrainement getEntrainement() {
		return entrainement;
	}

	public void setEntrainement(Entrainement entrainement) {
		this.entrainement = entrainement;
	}

	public List<Joueur> getListeJoueurPresent() {
		return listeJoueurPresent;
	}

	public void setListeJoueurPresent(List<Joueur> listeJoueurPresent) {
		this.listeJoueurPresent = listeJoueurPresent;
	}
	
	
	
	
	
	

	
}
