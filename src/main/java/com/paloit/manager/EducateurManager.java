package com.paloit.manager;

import java.util.List;

import com.paloit.entities.Educateur;

public interface EducateurManager {
	
	//Liste de tous les educateurs
	public List<Educateur> findAll ();

	public void creerEducateur(String nomEduc,String prenomEduc, String telEduc, String mailEduc,
									String loginEduc, String mdpEduc, String categorieEduc,
										String fonctionEduc );
	
	public void modifierEducateur (Educateur educateur);
	
	public void supprimerEducateur (int id);
	
	
}
