
package com.paloit.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.paloit.entities.Educateur;
import com.paloit.entities.Equipe;
import com.paloit.manager.EducateurManager;


@Component
@Scope
public class RechercheEducateurBean {
	
	// =========================================================================
    // ATTRIBUTS
    // =========================================================================

		private Educateur educateur;
		private EducateurManager manager;
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

	
	
	// =========================================================================
    // CONSTRUCTEURS
    // =========================================================================	
	public RechercheEducateurBean(){
	
		
	}
	// =========================================================================
    // METHODS
    // =========================================================================
	public String editEducateur() {
			
		//Mise en forme de la date recuperer pour interaction avec la BDD 
        this.nomEducateur = educateur.getNomEducateur();
        this.prenomEducateur = educateur.getPrenomEducateur();
        this.mailEducateur = educateur.getMailEducateur();
        this.fonctionEducateur = educateur.getFonction();
        this.loginEducateur = educateur.getLoginEducateur();
        this.mdpEducateur = educateur.getMdpEducateur();
        this.telEducateur = educateur.getTelEducateur();
        
        
		return "pretty:editEducateur";
	}
	public String saveEditedEducateur(){
		equipe = new Equipe();
		equipe = educateur.getEquipe();
		id = educateur.getIdEducateur();
		reinit();
		educateur.setNomEducateur(nomEducateur);
		educateur.setPrenomEducateur(prenomEducateur);
		educateur.setEquipe(equipe);
		educateur.setIdEducateur(id);
		educateur.setMailEducateur(mailEducateur);
		educateur.setFonction(fonctionEducateur);
		educateur.setLoginEducateur(loginEducateur);
		educateur.setMdpEducateur(mdpEducateur);
		educateur.setTelEducateur(telEducateur);
		
		manager.modifierEducateur(educateur);
		
		return "pretty:rechercheEducateur";
		
	}
	
	public void reinit() {
        educateur = new Educateur();
    }
	
	public String deleteEducateur(){
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
	

	
}
