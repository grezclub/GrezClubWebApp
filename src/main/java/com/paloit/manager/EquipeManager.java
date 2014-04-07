package com.paloit.manager;

import com.paloit.entities.Equipe;

public interface EquipeManager {

	//Recupération d'une équipa par l'id
		public Equipe findEquipeById(int idEquipe);
}
