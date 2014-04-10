package com.paloit.bean;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;


@Component
@RequestScoped
public class RechercheGeneralBean {
	
	// =========================================================================
    // CONSTRUCTEURS
    // =========================================================================	
	public RechercheGeneralBean(){
		
	}
	// =========================================================================
    // METHODS
    // =========================================================================
	public String rechercheJoueur(){
		return "rechercheJoueur.jsf";
	}
	
	public String rechercheEducateur(){
		return "rechercheEducateur.jsf";
	}
    // =========================================================================
    // OVERRIDES
    // =========================================================================

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================

}
