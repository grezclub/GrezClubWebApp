package com.paloit.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope
public class CreationPersonne {
	
	// =========================================================================
    // CONSTRUCTEURS
    // =========================================================================	
	public CreationPersonne(){
		
	}
	// =========================================================================
    // METHODS
    // =========================================================================
	public String creerJoueur(){
		return "creationJoueur.jsf";
	}
	
	public String creerEducateur(){
		return "creationEducateur.jsf";
	}
    // =========================================================================
    // OVERRIDES
    // =========================================================================

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================

}
