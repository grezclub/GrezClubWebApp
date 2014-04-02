package com.paloit.dao;

import java.util.List;

import com.paloit.entities.Joueur;
import com.paloit.entities.Match;

public interface ConvocationService {
	
	public void enregistreConvocation(Match match, Joueur joueur);

}
