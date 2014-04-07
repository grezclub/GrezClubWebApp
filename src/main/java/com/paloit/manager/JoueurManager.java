package com.paloit.manager;

import java.util.List;

import com.paloit.entities.Joueur;

public interface JoueurManager {
	
	public List<Joueur> getAllJoueur();
	
	public void creerJoueur1(String nomJoueur, String prenomJoueur, String dateNaissance
							, String adresse, String tel, String categorie, String mailJoueur);
	

	public void modifierJoueur(Joueur joueur, String dateNaissance);
	
	public void supprimerJoueur(int id);
	
	public List<Joueur> listeJoueurCategorie (String categorie);
	
	public Joueur joueurParId (String id);
	
	
}
