package com.paloit.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope
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
		return "pretty:rechercheJoueur";
	}
	
	public String rechercheEducateur(){
		return "pretty:rechercheEducateur";
	}
    // =========================================================================
    // OVERRIDES
    // =========================================================================

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================

}
