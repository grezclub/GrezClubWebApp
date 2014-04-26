package com.paloit.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.dao.EntrainementService;
import com.paloit.dao.PresenceService;
import com.paloit.entities.Entrainement;
import com.paloit.entities.Joueur;
import com.paloit.entities.Presence;

@Service
@Transactional
public class PresenceManagerImpl implements PresenceManager {
	
	//ATTRIBUTS

		
		private PresenceService 		presenceService;
		private EntrainementService    	entrainementService;
		private List<Joueur> listePresence;
		
		//AUTOWIRED
		@Autowired
		public void setEntrainementService (EntrainementService  entrainementService) {
			this.entrainementService = entrainementService;
		}
		@Autowired
		public void setPresenceService(PresenceService presenceService) {
			this.presenceService = presenceService;
		}

	public List<Joueur> listePresence(Entrainement entrainement) {
		
		listePresence = new ArrayList<Joueur>();
		listePresence = presenceService.listeJoueurPresent(entrainement);
		return listePresence;
	}

	
	public void updatePresence(Entrainement entrainement, List<Joueur> joueurListe) {
		List<Presence> listeTemporaire = new ArrayList <Presence>();
		listeTemporaire = presenceService.listePresenceParEntrainement(entrainement);
		
		entrainementService.updateEntrainement(entrainement);
		
		for (int i = 0; i < listeTemporaire.size(); i++){
		presenceService.deletePresence(listeTemporaire.get(i));
		}
		//On enregistre les joueurs present a l'entrainement
        for (int i =0; i < joueurListe.size(); i++){
        presenceService.updatePresence(entrainement, joueurListe.get(i));
        }
        
	}
	
	public void deletePresence(Entrainement entrainement) {
		
		List<Presence> listePresence = new ArrayList<Presence>();
		listePresence = presenceService.listePresenceParEntrainement(entrainement);
		for (int i = 0; i <listePresence.size(); i++){
			presenceService.deletePresence(listePresence.get(i));
		}
		
	}
	@Override
	
	public List<Presence> statPresenceJoueur(Joueur joueur) {
		// TODO Auto-generated method stub
		return presenceService.statPresenceJoueur(joueur);
	}

	
}
