package com.paloit.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.dao.EquipeService;
import com.paloit.entities.Equipe;

@Service
@Transactional
public class EquipeManagerImpl implements EquipeManager {
	
	
	private EquipeService equipeService;
	private Equipe equipe;

	
	@Autowired
	public void setEquipeService(EquipeService equipeService) {
		this.equipeService = equipeService;
	}
	
	
	
	@Override
	public Equipe findEquipeById(int idEquipe) {
		equipe = equipeService.findById(idEquipe);
		return equipe;
	}

}
