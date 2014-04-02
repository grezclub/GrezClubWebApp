package com.paloit.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.dao.EducateurService;
import com.paloit.dao.EquipeService;
import com.paloit.entities.Educateur;

@Service
@Transactional
public class EducateurManagerImpl implements EducateurManager {
	
	private Educateur educateur;
	private EducateurService educateurService;
	private EquipeService equipeService;

	
	@Autowired
	public void setEducateurService(EducateurService educateurService) {
		this.educateurService = educateurService;
	}
	@Autowired
	public void setEquipeService(EquipeService equipeService) {
		this.equipeService = equipeService;
	}

	public List<Educateur> findAll() {
		return educateurService.findAll();
	}

	public void creerEducateur(String nomEduc, String prenomEduc,
			String telEduc, String mailEduc, String loginEduc, String mdpEduc,
			String categorieEduc, String fonctionEduc) {
		
		educateur = new Educateur();
		educateur.setFonction(fonctionEduc);
		educateur.setLoginEducateur(loginEduc);
		educateur.setMdpEducateur(mdpEduc);
		educateur.setMailEducateur(mailEduc);
		educateur.setNomEducateur(nomEduc);
		educateur.setPrenomEducateur(prenomEduc);
		educateur.setTelEducateur(telEduc);
		educateur.setEquipe(equipeService.findByCategorie(categorieEduc));
		
		educateurService.save(educateur);

	}
	
	public void modifierEducateur(Educateur educateur) {
	
		educateurService.modifierEducateur(educateur);
		
	}
	
	public void supprimerEducateur(int id) {
		educateur = educateurService.findById(id);
		educateurService.deleteEducateur(educateur);
		
	}
	

}
