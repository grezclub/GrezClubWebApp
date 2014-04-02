package com.paloit.manager;

import java.util.List;

import com.paloit.entities.Entrainement;
import com.paloit.entities.Joueur;

public interface PresenceManager {
	
	public List<Joueur> listePresence(Entrainement entrainement);

}
