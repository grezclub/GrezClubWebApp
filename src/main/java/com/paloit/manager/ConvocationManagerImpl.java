package com.paloit.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.dao.ConvocationService;
import com.paloit.dao.MatchService;
import com.paloit.entities.Convocation;
import com.paloit.entities.Joueur;
import com.paloit.entities.Match;

@Service
@Transactional
public class ConvocationManagerImpl implements ConvocationManager {
	
	//ATTRIBUTS

	
			private ConvocationService 		convocationService;
			private MatchService    	matchService;
			private List<Joueur> listeConvocation;
			
			//AUTOWIRED
			@Autowired
			public void setMatchService (MatchService  matchService) {
				this.matchService = matchService;
			}
			@Autowired
			public void setConvocationService(ConvocationService convocationService) {
				this.convocationService = convocationService;
			}
			
			//Methodes
			
			public List<Joueur> listeConvocation(Match match) {
				listeConvocation = new ArrayList<Joueur>();
				listeConvocation = convocationService.listeJoueurConvoque(match);
				return listeConvocation;
			}
			
			@Override
			public void updateConvocation(Match match, List<Joueur> joueurListe) {
				List<Convocation> listeTemporaire = new ArrayList <Convocation>();
				listeTemporaire = convocationService.listePresenceParMatch(match);
				
				matchService.updateMatch(match);
				
				for (int i = 0; i < listeTemporaire.size(); i++){
				convocationService.deleteConvocation(listeTemporaire.get(i));
				}
				//On enregistre les joueurs present a l'entrainement
		        for (int i =0; i < joueurListe.size(); i++){
		        	convocationService.updateConvocation(match, joueurListe.get(i));
		        }
				
			}
			@Override
			
			public void deleteConvocation(Match match) {
				List<Convocation> listePresence = new ArrayList<Convocation>();
				listePresence = convocationService.listePresenceParMatch(match);
				for (int i = 0; i <listePresence.size(); i++){
					convocationService.deleteConvocation(listePresence.get(i));
				}
				
			}
			@Override
			public List<Convocation> statConvocationJoueur(Joueur joueur) {
				// TODO Auto-generated method stub
				return convocationService.statPresenceJoueur(joueur);
			}
			
}
