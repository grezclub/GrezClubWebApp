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

	// =========================================================================
	// CONSTRUCTEURS
	// =========================================================================

	public CreationEducateurBean() {

	}

	// =========================================================================
	// METHODS
	// =========================================================================
		public String creerEducateur(){
			manager.creerEducateur(nomEduc, prenomEduc, telEduc, mailEduc, loginEduc, mdpEduc, categorieEduc, fonctionEduc);
		 return "pretty:home";
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
	
	@Autowired
    public void setManager(EducateurManager manager) {
        this.manager = manager;
    }

}
