package com.paloit.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.dao.ConvocationService;
import com.paloit.dao.EducateurService;
import com.paloit.dao.EquipeService;
import com.paloit.dao.MatchService;
import com.paloit.dao.PresenceService;
import com.paloit.entities.Convocation;
import com.paloit.entities.Educateur;
import com.paloit.entities.Equipe;
import com.paloit.entities.Joueur;
import com.paloit.entities.Match;

@Service
@Transactional
public class MatchManagerImpl implements MatchManager {
	
	//ATTRIBUTS
		private MatchService 	matchService ;
		private PresenceService presenceService;
		private EquipeService equipeService;
		private EducateurService educateurService;
		private ConvocationService 		convocationService;
		private Match 			match;
		private Equipe equipe;
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
			@Autowired
			public void setEquipeService(EquipeService equipeService) {
				this.equipeService = equipeService;
			}
			@Autowired
			public void setEducateurService(EducateurService educateurService) {
				this.educateurService = educateurService;
			}

	public void creerConvocation(List<Joueur> joueurListe, Educateur educateur,
			Date date, String lieu, String adversaire, String heure,
			String commentaire, String classe) {
	
	match = new Match();
	
    
    //On affecte le lieu
	match.setDateMatch(date);
    match.setLieuMatch(lieu);
    match.setHeure(heure);
    match.setAdversaire(adversaire);
    match.setCommentaire(commentaire);
    match.setClasse(classe);
    match.setEducateur(educateur);
    match.setIdEquipe(educateur.getEquipe().getIdEquipe());
    
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
	
	@Override
	public Match findById(Integer id) {
		return matchService.findById(id);
	}
	@Override
	public List<Match> listMatchEducateur(int idEducateur) {
		return  matchService.findMatchEduc(idEducateur);
	}
	@Override
	public List<Match> listConvocation(int idEducateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Match> dernierMatch(String categorie) {
		
		this.equipe = equipeService.findByCategorie(categorie);
		match = new Match();
		
		List<Match> listeMatch= new ArrayList<Match>();
		listeMatch= matchService.findLastMatchEquipe(this.equipe.getIdEquipe());
//		List<Educateur> listeEduc = new ArrayList<Educateur>();
//		listeEduc = educateurService.findAll();
//
//		List<Educateur> listeEducChoisi = new ArrayList<Educateur>();
//		for (int i =0; i<listeEduc.size(); i++){
//			if (listeEduc.get(i).getEquipe() == this.equipe){
//				listeEducChoisi.add(listeEduc.get(i));
//			}
//		}
//
//		List<Match> listeMatch= new ArrayList<Match>();
//		for (int i =0; i < listeEducChoisi.size();i++){
//		listeMatch.add((Match) matchService.findLastMatchEduc(listeEducChoisi.get(i).getIdEducateur()));
//		}


		return listeMatch;
	}
}
