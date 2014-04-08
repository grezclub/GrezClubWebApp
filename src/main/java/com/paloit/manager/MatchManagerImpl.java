package com.paloit.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.dao.ConvocationService;
import com.paloit.dao.MatchService;
import com.paloit.dao.PresenceService;
import com.paloit.entities.Convocation;
import com.paloit.entities.Educateur;
import com.paloit.entities.Entrainement;
import com.paloit.entities.Joueur;
import com.paloit.entities.Match;

@Service
@Transactional
public class MatchManagerImpl implements MatchManager {
	
	//ATTRIBUTS
		private MatchService 	matchService ;
		private PresenceService presenceService;
		private ConvocationService 		convocationService;
		private Match 			match;
		private Convocation 				convocation;
	
	//AUTOWIRED
			@Autowired
			public void setMatchService(MatchService matchService) {
				this.matchService = matchService;
			}
			@Autowired
			public void setConvocationService(ConvocationService convocationService) {
				this.convocationService = convocationService;
			}

	public void creerConvocation(List<Joueur> joueurListe, Educateur educateur,
			Date date, String lieu, String adversaire, String heure,
			String commentaire) {
	
	match = new Match();
	
    
    //On affecte le lieu
	match.setDateMatch(date);
    match.setLieuMatch(lieu);
    match.setHeure(heure);
    match.setAdversaire(adversaire);
    match.setCommentaire(commentaire);
    match.setEducateur(educateur);
    
    matchService.enregistrerMatch(match);
    
    //On enregistre les joueurs present a l'entrainement
    for (int i =0; i < joueurListe.size(); i++){
    	
    convocationService.enregistreConvocation(match, joueurListe.get(i));
    }

	}
	
	public List<Match> getAllMatch() {
			return matchService.findAll();
	}
	@Override
	public void deleteMatch(Match match) {
		matchService.deleteMatch(match);
		
	}

}
