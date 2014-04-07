package com.paloit.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.dao.EquipeService;
import com.paloit.dao.JoueurService;
import com.paloit.entities.Joueur;

@Service
@Transactional
public class JoueurManagerImpl implements JoueurManager {
	
	private JoueurService joueurService;
	private EquipeService equipeService;
	private Joueur joueur;
	
	@Autowired
	public void setJoueurService(JoueurService joueurService) {
		this.joueurService = joueurService;
	}
	@Autowired
	public void setEquipeService(EquipeService equipeService) {
		this.equipeService = equipeService;
	}
	
	
	public List<Joueur> getAllJoueur() {
		return joueurService.findAll();
	}

	
	public void creerJoueur1(String nomJoueur, String prenomJoueur, String dateNaissance
			, String adresse, String tel, String categorie, String mailJoueur) {
		
		//Attribution des informations récuperer pour creer le joueur
    	joueur = new Joueur();
    	joueur.setPrenomJoueur(prenomJoueur);
       	joueur.setAdresseJoueur(adresse);
    	joueur.setNomJoueur(nomJoueur);
    	joueur.setTelJoueur(tel);
    	joueur.setMailJoueur(mailJoueur);
    	
    	//Mise en forme de la date recuperer pour interaction avec la BDD 
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = null;
        try {
            date1 = df.parse( dateNaissance );
        	joueur.setDatenaissanceJoueur(date1);

        } catch ( ParseException e ) {
            e.printStackTrace();
        }	
		
		//Recupération de l'equipe du joueur par l'information catégorie
		this.joueur.setEquipe(equipeService.findByCategorie(categorie));
		
		//Sauvegarde du joueur
		joueurService.save(joueur);
	}
	
	public void modifierJoueur(Joueur joueur, String dateNaissance) {
		
		//Attribution des informations récuperer pour creer le joueur
    	
    	
    	
    	//Mise en forme de la date recuperer pour interaction avec la BDD 
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = df.parse( dateNaissance );
        	joueur.setDatenaissanceJoueur(date1);

        } catch ( ParseException e ) {
            e.printStackTrace();
        }	
		
		
		
		//Sauvegarde du joueur
		joueurService.update(joueur);
	}
	public void supprimerJoueur(int id) {
		joueur = new Joueur();
		joueur = joueurService.findById(id);
		joueurService.deleteJoueur(joueur);
		
	}
	public List<Joueur> listeJoueurCategorie(String categorie) {
		
		return joueurService.findByCategorie(categorie);
	}
	
	public Joueur joueurParId(String id) {
		
		int identifiant = Integer.parseInt(id);
		Joueur joueur = joueurService.findById(identifiant);
		return joueur;
	}
	


}
