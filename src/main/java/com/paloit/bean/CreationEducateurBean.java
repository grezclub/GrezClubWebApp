package com.paloit.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.paloit.entities.Educateur;
import com.paloit.entities.Equipe;
import com.paloit.manager.EducateurManager;

@Component
@Scope
public class CreationEducateurBean {

	// =========================================================================
	// ATTRIBUTS
	// =========================================================================
	private Educateur educateur;
	private Equipe equipe;
	private EducateurManager manager;

	private String nomEduc;
	private String prenomEduc;
	private String telEduc;
	private String mailEduc;
	private String loginEduc;
	private String mdpEduc;
	private String categorieEduc;
	private String fonctionEduc;
	private boolean actif;
	private int number;
	private int numberRole;
	private int numberActive;

	// =========================================================================
	// CONSTRUCTEURS
	// =========================================================================
	public CreationEducateurBean() {

	}

	// =========================================================================
	// METHODS
	// =========================================================================
	public String creerEducateur(){
			switch(number)
	        {
	            case 1:
	                this.categorieEduc = "U7";
	            break;
	            case 2:
	            	this.categorieEduc = "U9";
	            break;
	            case 3:
	            	this.categorieEduc = "U11";
	            break;
	            case 4:
	            	this.categorieEduc = "U13";
	            break;
	            case 5:
	            	this.categorieEduc = "U15";
	            break;
	            case 6:
	            	this.categorieEduc = "U18";
	            break;
	            case 7:
	            	this.categorieEduc = "Senior";
	            break;
	            default: this.categorieEduc = "";
                break;
	           
	            
	        }
			switch(numberRole)
	        {
	            case 1:
	                this.fonctionEduc = "ROLE_USER";
	            break;
	            case 2:
	            	this.fonctionEduc = "ROLE_ADMIN";
	            break;
	           
	            default: this.fonctionEduc = "ROLE_USER";
                break;
	           
	            
	        }
			switch(numberActive)
	        {
	            case 1:
	                this.actif = true;
	            break;
	            case 2:
	            	this.actif = false;
	            break;
	           
	            default: this.actif = true;
                break;
	           
	            
	        }
			
			manager.creerEducateur(nomEduc, prenomEduc, telEduc, mailEduc, loginEduc, mdpEduc, categorieEduc, fonctionEduc, actif);
			this.categorieEduc = null;
			this.nomEduc = null;
			this.prenomEduc = null;
			this.telEduc = null;
			this.mailEduc = null;
			this.loginEduc = null;
			this.mdpEduc = null;
			this.fonctionEduc = null;
			this.actif = true;
			this.number = 0;
			
		 return "home.jsf";
		}

	//
	// =========================================================================
	// OVERRIDES
	// =========================================================================

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

	public Educateur getEducateur() {
		return educateur;
	}

	public void setEducateur(Educateur educateur) {
		this.educateur = educateur;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public String getNomEduc() {
		return nomEduc;
	}

	public void setNomEduc(String nomEduc) {
		this.nomEduc = nomEduc;
	}

	public String getPrenomEduc() {
		return prenomEduc;
	}

	public void setPrenomEduc(String prenomEduc) {
		this.prenomEduc = prenomEduc;
	}

	public String getTelEduc() {
		return telEduc;
	}

	public void setTelEduc(String telEduc) {
		this.telEduc = telEduc;
	}

	public String getMailEduc() {
		return mailEduc;
	}

	public void setMailEduc(String mailEduc) {
		this.mailEduc = mailEduc;
	}

	public String getLoginEduc() {
		return loginEduc;
	}

	public void setLoginEduc(String loginEduc) {
		this.loginEduc = loginEduc;
	}

	public String getMdpEduc() {
		return mdpEduc;
	}

	public void setMdpEduc(String mdpEduc) {
		this.mdpEduc = mdpEduc;
	}

	public String getCategorieEduc() {
		return categorieEduc;
	}

	public void setCategorieEduc(String categorieEduc) {
		this.categorieEduc = categorieEduc;
	}

	public String getFonctionEduc() {
		return fonctionEduc;
	}

	public void setFonctionEduc(String fonctionEduc) {
		this.fonctionEduc = fonctionEduc;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	
	public int getNumberRole() {
		return numberRole;
	}

	public void setNumberRole(int numberRole) {
		this.numberRole = numberRole;
	}

	@Autowired
	public void setManager(EducateurManager manager) {
		this.manager = manager;
	}

	public int getNumberActive() {
		return numberActive;
	}

	public void setNumberActive(int numberActive) {
		this.numberActive = numberActive;
	}
	
	
}
