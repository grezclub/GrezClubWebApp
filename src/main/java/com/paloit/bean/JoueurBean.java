package com.paloit.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.paloit.entities.Joueur;
import com.paloit.manager.JoueurManager;

@Component
@Scope
public class JoueurBean {
	
	private Joueur joueur;
	private JoueurManager manager;
	
	
	public JoueurBean(){
		
	}
	
	// =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
    public List<Joueur> getAllJoueurs() {
        return manager.getAllJoueur();
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    @Autowired
    public void setManager(JoueurManager manager) {
        this.manager = manager;
    }
	
	
	

}
