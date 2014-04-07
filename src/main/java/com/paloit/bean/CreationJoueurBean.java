package com.paloit.bean;

import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paloit.entities.Joueur;
import com.paloit.manager.JoueurManager;

@Component
@RequestScoped
public class CreationJoueurBean {

	// =========================================================================
	// ATTRIBUTS
	// =========================================================================
	private Joueur joueur;
	private JoueurManager manager;

	private String nomJoueur;
	private String prenomJoueur;
	private String adresseJoueur;
	private String telJoueur;
	private String categorieJoueur;
	private String dateNaissanceJoueur;
	private String mailJoueur;
	private int number;

	// =========================================================================
	// CONSTRUCTEURS
	// =========================================================================
	public CreationJoueurBean() {

	}

	// =========================================================================
	// METHODS
	// =========================================================================

	public String creerJoueur() {

		switch(number)
        {
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
            default: this.categorieJoueur = "";
            break;
           
            
        }
		
		manager.creerJoueur1(nomJoueur, prenomJoueur, dateNaissanceJoueur,
				adresseJoueur, telJoueur, categorieJoueur, mailJoueur);
		this.nomJoueur = null;
		this.prenomJoueur = null;
		this.adresseJoueur = null;
		this.categorieJoueur = null;
		this.dateNaissanceJoueur = null;
		this.telJoueur = null;
		this.mailJoueur = null;
		this.number = 0;
		return "home.jsf";
	}

	//
	// =========================================================================
	// @Autowired
	// =========================================================================

	@Autowired
	public void setManager(JoueurManager manager) {
		this.manager = manager;
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
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

	public String getMailJoueur() {
		return mailJoueur;
	}

	public void setMailJoueur(String mailJoueur) {
		this.mailJoueur = mailJoueur;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	

}
