package com.paloit.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope
public class GestionNewsBeen {

	
		// =========================================================================
	    // CONSTRUCTEURS
	    // =========================================================================	
		public GestionNewsBeen(){
			
		}
		// =========================================================================
	    // METHODS
	    // =========================================================================
		public String creerNews(){
			return "pretty:creationNews";
		}
		
		public String rechercheNews(){
			return "pretty:rechercheNews";
		}
	    // =========================================================================
	    // OVERRIDES
	    // =========================================================================

	    // =========================================================================
	    // GETTERS & SETTERS
	    // =========================================================================

	}


