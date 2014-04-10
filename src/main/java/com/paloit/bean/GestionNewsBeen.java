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
			return "creationNews.xhtml";
		}
		
		public String rechercheNews(){
			return "rechercheNews.jsf";
		}
		
		public String afficheNews(){
			return "afficheNews.jsf";
		}
	    // =========================================================================
	    // OVERRIDES
	    // =========================================================================

	    // =========================================================================
	    // GETTERS & SETTERS
	    // =========================================================================

	}


