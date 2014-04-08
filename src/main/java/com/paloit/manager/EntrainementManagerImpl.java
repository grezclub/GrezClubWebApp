package com.paloit.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.dao.EntrainementService;
import com.paloit.dao.PresenceService;
import com.paloit.entities.Educateur;
import com.paloit.entities.Entrainement;
import com.paloit.entities.Joueur;
import com.paloit.entities.Presence;

@Service
@Transactional
public class EntrainementManagerImpl implements EntrainementManager {
	
	//ATTRIBUTS
	private EntrainementService 	entrainementService ;
	private PresenceService 		presenceService;
	private Entrainement 			entrainement;
	private Presence 				presence;
	
	//AUTOWIRED
	@Autowired
	public void setEntrainementService(EntrainementService entrainementService) {
		this.entrainementService = entrainementService;
	}
	@Autowired
	public void setPresenceService(PresenceService presenceService) {
		this.presenceService = presenceService;
	}
	
	//METHODES
	public void creerEntrainement(
			List<Joueur> joueurListe, Educateur educateur, Date date,
			String lieu) {
		
		entrainement = new Entrainement();
        
        //On affecte le lieu
		entrainement.setDateEntrainement(date);
        entrainement.setLieuEntrainement(lieu);
        entrainement.setEducateur(educateur);
        
        entrainementService.creerEntrainemnt(entrainement);
        
        //On enregistre les joueurs present a l'entrainement
        for (int i =0; i < joueurListe.size(); i++){
        	
        presenceService.enregistrerPresence(entrainement, joueurListe.get(i));
        }
        
        

	}

	public List<Entrainement> getAllEntrainement() {
		return entrainementService.findall();
	}
	
	public List<Entrainement> getEntrainementByEduc(Educateur educ) {
		List<Entrainement> listeEntrainement = new ArrayList<Entrainement>();
		listeEntrainement = entrainementService.listeEntrainementParEducateur(educ.getIdEducateur());
		return listeEntrainement;
	}
	@Override
	public void updateEntrainement(Entrainement entrainement) {
		entrainementService.updateEntrainement(entrainement);
		
	}
	 

}
