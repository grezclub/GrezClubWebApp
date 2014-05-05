package com.paloit.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.dao.EducateurService;
import com.paloit.dao.EquipeService;
import com.paloit.dao.MatchService;
import com.paloit.entities.Educateur;
import com.paloit.entities.Equipe;
import com.paloit.entities.Match;

@Service
@Transactional
public class EquipeManagerImpl implements EquipeManager {
	
	
	private EquipeService equipeService;
	private EducateurService educateurService;
	private MatchService matchService;
	private Equipe equipe;

	
	@Autowired
	public void setEquipeService(EquipeService equipeService) {
		this.equipeService = equipeService;
	}
	@Autowired
	public void setEducateurService(EducateurService educateurService) {
		this.educateurService = educateurService;
	}
	@Autowired
	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}
	
	
	
	
	@Override
	public Equipe findEquipeById(int idEquipe) {
		equipe = equipeService.findById(idEquipe);
		return equipe;
	}



	@Override
	public Equipe findByCategorie(String categorie) {
		return equipe = equipeService.findByCategorie(categorie);
		
	}


}
