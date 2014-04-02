package com.paloit.bean;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.paloit.entities.Educateur;
import com.paloit.manager.ConnexionManager;

@Component
@Scope
public class ConnexionBean extends HttpServlet {

	// =========================================================================
    // ATTRIBUTS
    // =========================================================================
	private static final long serialVersionUID = 4496343151122047367L;
	
	private String login;
	private String mdp;
	private String erreur = "";
	private ConnexionManager manager;
	private Educateur educateurSession ;

	
	
	// =========================================================================
    // CONSTRUCTEURS
    // =========================================================================	
	public ConnexionBean(){
		
	}
	
	
	// =========================================================================
    // METHODS
    // =========================================================================

	
	//Methode qui renvoie les infos sur l'educateur connécter
	public String getUserName(){
		/*Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		return userDetails.toString();*/
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDatails = (UserDetails) auth.getPrincipal();
		this.educateurSession = manager.educParLogin(userDatails.getUsername());
		return this.educateurSession.getPrenomEducateur();

	}
	
    // =========================================================================
    // AUTOWIRED
    // =========================================================================
	@Autowired
    public void setManager(ConnexionManager manager) {
        this.manager = manager;
    }
	
    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getErreur() {
		return erreur;
	}
	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

	public Educateur getEducateurSession() {
		return educateurSession;
	}
	public void setEducateurSession(Educateur educateurSession) {
		this.educateurSession = educateurSession;
	}

	
}
