package com.paloit.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import util.SendMailTLS;

import com.paloit.entities.Educateur;
import com.paloit.entities.Joueur;
import com.paloit.manager.ConnexionManager;
import com.paloit.manager.JoueurManager;

@Component
@RequestScoped
public class MailBean {

	// =========================================================================
	// ATTRIBUTS
	// =========================================================================
	
	private JoueurManager manager;

	private Integer progress = 0;
	private Integer progressMax = 100;
	
	private ConnexionManager connexionManager;

	private String categorieJoueur;
	private String contenueMail = null;
	private String objetMail = null;
	private List<Joueur> listeDestinataire;
	private int number;
	private Educateur educateur;

	// =========================================================================
	// CONSTRUCTEURS
	// =========================================================================
	public MailBean() {

	}

	// =========================================================================
	// METHODS
	// =========================================================================
	public String envoieMail() {

		switch (number) {
		case 1:
			this.categorieJoueur = "U7";
			break;
		case 2:
			this.categorieJoueur = "U9";
			break;
		case 3:
			this.categorieJoueur = "U11";
			break;
		case 4:
			this.categorieJoueur = "U13";
			break;
		case 5:
			this.categorieJoueur = "U15";
			break;
		case 6:
			this.categorieJoueur = "U18";
			break;
		case 7:
			this.categorieJoueur = "Senior";
			break;
		case 8:
			this.categorieJoueur = "Tous";
			break;
		default:
			this.categorieJoueur = "Tous";
			break;
		}
		
		progress = 0;
		listeDestinataire = new ArrayList<Joueur>();
		
		if (this.categorieJoueur == "Tous" ){
			listeDestinataire = manager.getAllJoueur();
		}
		else {
		listeDestinataire = manager.listeJoueurCategorie(categorieJoueur);
		}
		
		progressMax = listeDestinataire.size();

		for (int i = 0; i < listeDestinataire.size(); i++) {
			this.progress = 1 + i;
			this.progress = this.progress * 100;
			this.progress = this.progress / progressMax;

			if (this.progress >= 100) {
				this.progress = 100;
			}
			SendMailTLS.main(listeDestinataire.get(i).getMailJoueur(), objetMail, contenueMail);

		}

		this.objetMail = null;
		this.contenueMail = null;
		progressMax = 100;
		progress = 0;

		return "envoieMail.jsf";
	}

	
	public String envoieMailEduc(){
		
		//Recuperation des informations concernant l'educateur connecte
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				UserDetails userDatails = (UserDetails) auth.getPrincipal();
				this.educateur = connexionManager.educParLogin(userDatails.getUsername());
				
		progress = 0;
		listeDestinataire = new ArrayList<Joueur>();
		
		
		listeDestinataire = manager.listeJoueurEquipe(this.educateur.getEquipe().getIdEquipe());
		
		
		progressMax = listeDestinataire.size();

		for (int i = 0; i < listeDestinataire.size(); i++) {
			this.progress = 1 + i;
			this.progress = this.progress * 100;
			this.progress = this.progress / progressMax;

			if (this.progress >= 100) {
				this.progress = 100;
			}
			SendMailTLS.main(listeDestinataire.get(i).getMailJoueur(), objetMail, contenueMail);

		}

		this.objetMail = null;
		this.contenueMail = null;
		progressMax = 100;
		progress = 0;

		return "envoieMail.jsf";
		
	}
	//
	// =========================================================================
	// @Autowired
	// =========================================================================

	@Autowired
	public void setManager(JoueurManager manager) {
		this.manager = manager;
	}

	@Autowired
	public void setConnexionManager(ConnexionManager connexionManager) {
		this.connexionManager = connexionManager;

	}
	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

	public String getContenueMail() {
		return contenueMail;
	}

	public void setContenueMail(String contenueMail) {
		this.contenueMail = contenueMail;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getObjetMail() {
		return objetMail;
	}

	public void setObjetMail(String objetMail) {
		this.objetMail = objetMail;
	}

	public Integer getProgress() {
		if (progress == null) {
			return progress = 0;
		} else {
			return progress;
		}

	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public void onComplete() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Mails envoyé",
						"Les mail ont été envoyé avec succès"));
	}

	public void cancel() {
		progress = null;
	}

	public Integer getProgressMax() {
		return progressMax;
	}

	public void setProgressMax(Integer progressMax) {
		this.progressMax = progressMax;
	}

}
