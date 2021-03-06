package com.paloit.bean;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

@Component
@RequestScoped
public class CreationEvenementBean {

		
		// =========================================================================
	    // CONSTRUCTEURS
	    // =========================================================================	
		public CreationEvenementBean(){
			
		}
		// =========================================================================
	    // METHODS
	    // =========================================================================
		public String creerEntrainement(){
			return "creationEntrainement.jsf";
		}
		
		public String afficherEntrainement(){
			return "presenceEntrainement1.jsf";
		}
		
		public String creerConvocation(){
			return "creationConvocation.jsf";
		}
		
		public String afficherConvocation(){
			return "presenceMatch1.jsf";
		}
	    // =========================================================================
	    // OVERRIDES
	    // =========================================================================

	    // =========================================================================
	    // GETTERS & SETTERS
	    // =========================================================================

	}



