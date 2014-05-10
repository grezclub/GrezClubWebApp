package com.paloit.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.dao.EducateurService;
import com.paloit.entities.Educateur;

@Service
@Transactional(readOnly = true)
public class ConnexionManagerImpl implements ConnexionManager {
	
	private Educateur educateur;
	private EducateurService educateurService; 
	
	@Autowired
	public void setEducateurService(EducateurService educateurService) {
		this.educateurService = educateurService;
	}    

	public Educateur testConnection(String login , String mdp) {
		
		Educateur educ = educateurService.getEducateurByLog(login, mdp);
		return educ;

	}
	
	public Educateur educParLogin(String login) {
		educateur = educateurService.findByLogin(login);
		return educateur;
	}



	

}
