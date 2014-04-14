package com.paloit.bean;

import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paloit.entities.Educateur;
import com.paloit.entities.Equipe;
import com.paloit.manager.EducateurManager;
import com.paloit.manager.EquipeManager;

@Component
@RequestScoped
public class RechercheEducateurBean {

	// =========================================================================
	// ATTRIBUTS
	// =========================================================================

	private Educateur educateur;
	private EducateurManager manager;
	private EquipeManager equipeManager;
	private List<Educateur> filtreEducateur;

	private String nomEducateur;
	private String prenomEducateur;
	private String mailEducateur;
	private String telEducateur;
	private String mdpEducateur;
	private String loginEducateur;
	private String fonctionEducateur;
	private Equipe equipe;
	private String password;
	private int id;
	private int number;

	// =========================================================================
	// CONSTRUCTEURS
	// =========================================================================
	public RechercheEducateurBean() {

	}

	// =========================================================================
	// METHODS
	// =========================================================================
	public String editEducateur() {

		// Mise en forme de la date recuperer pour interaction avec la BDD
		this.nomEducateur = educateur.getNomEducateur();
		this.prenomEducateur = educateur.getPrenomEducateur();
		this.mailEducateur = educateur.getMailEducateur();
		this.fonctionEducateur = educateur.getFonction();
		this.loginEducateur = educateur.getLoginEducateur();
		this.mdpEducateur = educateur.getMdpEducateur();
		this.telEducateur = educateur.getTelEducateur();
		this.equipe = educateur.getEquipe();
		return "editEducateur.jsf";
	}

	public String saveEditedEducateur() {
		equipe = new Equipe();
		equipe = educateur.getEquipe();
		id = educateur.getIdEducateur();
		reinit();

		educateur.setNomEducateur(nomEducateur);
		educateur.setPrenomEducateur(prenomEducateur);

		switch (number) {
		case 1:
			educateur.setEquipe(equipe);
			break;
		case 2:
			educateur.setEquipe(equipeManager.findEquipeById(1));
			break;
		case 3:
			educateur.setEquipe(equipeManager.findEquipeById(2));
			break;
		case 4:
			educateur.setEquipe(equipeManager.findEquipeById(3));
			break;
		case 5:
			educateur.setEquipe(equipeManager.findEquipeById(4));
			break;
		case 6:
			educateur.setEquipe(equipeManager.findEquipeById(5));
			break;
		case 7:
			educateur.setEquipe(equipeManager.findEquipeById(6));
			break;
		case 8:
			educateur.setEquipe(equipeManager.findEquipeById(7));
			break;
		default:
			this.equipe = null;
			break;

		}

		educateur.setIdEducateur(id);
		educateur.setMailEducateur(mailEducateur);
		educateur.setFonction(fonctionEducateur);
		educateur.setLoginEducateur(loginEducateur);
		educateur.setMdpEducateur(mdpEducateur);
		educateur.setTelEducateur(telEducateur);

		manager.modifierEducateur(educateur);

		number = 0;

		return "rechercheEducateur.jsf";

	}

	public void reinit() {
		educateur = new Educateur();
	}

	public String deleteEducateur() {
		this.id = educateur.getIdEducateur();
		manager.supprimerEducateur(this.id);
		return "pretty:rechercheEducateur";
	}

	// =========================================================================
	// AUTOWIRED
	// =========================================================================

	@Autowired
	public void setManager(EducateurManager manager) {
		this.manager = manager;

	}

	@Autowired
	public void setEquipeManager(EquipeManager equipeManager) {
		this.equipeManager = equipeManager;

	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================
	public List<Educateur> getAllEducateurs() {
		return manager.findAll();
	}

	public Educateur getEducateur() {
		return educateur;
	}

	public void setEducateur(Educateur educateur) {
		this.educateur = educateur;
	}

	public List<Educateur> getFiltreEducateur() {
		return filtreEducateur;
	}

	public void setFiltreEducateur(List<Educateur> filtreEducateur) {
		this.filtreEducateur = filtreEducateur;
	}

	public String getNomEducateur() {
		return nomEducateur;
	}

	public void setNomEducateur(String nomEducateur) {
		this.nomEducateur = nomEducateur;
	}

	public String getPrenomEducateur() {
		return prenomEducateur;
	}

	public void setPrenomEducateur(String prenomEducateur) {
		this.prenomEducateur = prenomEducateur;
	}

	public String getMailEducateur() {
		return mailEducateur;
	}

	public void setMailEducateur(String mailEducateur) {
		this.mailEducateur = mailEducateur;
	}

	public String getTelEducateur() {
		return telEducateur;
	}

	public void setTelEducateur(String telEducateur) {
		this.telEducateur = telEducateur;
	}

	public String getMdpEducateur() {
		return mdpEducateur;
	}

	public void setMdpEducateur(String mdpEducateur) {
		this.mdpEducateur = mdpEducateur;
	}

	public String getLoginEducateur() {
		return loginEducateur;
	}

	public void setLoginEducateur(String loginEducateur) {
		this.loginEducateur = loginEducateur;
	}

	public String getFonctionEducateur() {
		return fonctionEducateur;
	}

	public void setFonctionEducateur(String fonctionEducateur) {
		this.fonctionEducateur = fonctionEducateur;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
