package com.paloit.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.dao.PresenceService;
import com.paloit.entities.Entrainement;
import com.paloit.entities.Joueur;
import com.paloit.entities.Presence;

@Service
@Transactional
public class PresenceManagerImpl implements PresenceManager {
	
	//ATTRIBUTS

		private PresenceService 		presenceService;
		private List<Joueur> listePresence;
		
		//AUTOWIRED
		@Autowired
		public void setPresenceService(PresenceService presenceService) {
			this.presenceService = presenceService;
		}

	public List<Joueur> listePresence(Entrainement entrainement) {
		
		listePresence = new ArrayList<Joueur>();
		listePresence = presenceService.listeJoueurPresent(entrainement);
		return listePresence;
	}

}
