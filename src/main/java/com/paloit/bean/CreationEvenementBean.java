package com.paloit.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope
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
			return "pretty:creerEntrainement1";
		}
		
		public String creerConvocation(){
			return "pretty:presenceEntrainement1";
		}
	    // =========================================================================
	    // OVERRIDES
	    // =========================================================================

	    // =========================================================================
	    // GETTERS & SETTERS
	    // =========================================================================

	}



