package com.paloit.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.paloit.entities.Joueur;
import com.paloit.manager.JoueurManager;

@Component
@Scope
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

	// =========================================================================
	// CONSTRUCTEURS
	// =========================================================================
	public CreationJoueurBean() {

	}

	// =========================================================================
	// METHODS
	// =========================================================================

	public String creerJoueur() {

		manager.creerJoueur1(nomJoueur, prenomJoueur, dateNaissanceJoueur,
				adresseJoueur, telJoueur, categorieJoueur);

		return "pretty:home";
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

}
