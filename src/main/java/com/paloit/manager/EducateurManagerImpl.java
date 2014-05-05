package com.paloit.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.dao.EducateurService;
import com.paloit.dao.EquipeService;
import com.paloit.dao.RoleService;
import com.paloit.entities.Educateur;
import com.paloit.entities.Roles;

@Service
@Transactional
public class EducateurManagerImpl implements EducateurManager {
	
	private Educateur educateur;
	private Roles role;
	private EducateurService educateurService;
	private EquipeService equipeService;
	private RoleService roleService;

	
	@Autowired
	public void setEducateurService(EducateurService educateurService) {
		this.educateurService = educateurService;
	}
	@Autowired
	public void setEquipeService(EquipeService equipeService) {
		this.equipeService = equipeService;
	}
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public List<Educateur> findAll() {
		return educateurService.findAll();
	}

	public void creerEducateur(String nomEduc, String prenomEduc,
			String telEduc, String mailEduc, String loginEduc, String mdpEduc,
			String categorieEduc, String fonctionEduc, boolean actif) {
		
		educateur = new Educateur();
		educateur.setFonction(fonctionEduc);
		educateur.setLoginEducateur(loginEduc);
		educateur.setMdpEducateur(mdpEduc);
		educateur.setMailEducateur(mailEduc);
		educateur.setNomEducateur(nomEduc);
		educateur.setPrenomEducateur(prenomEduc);
		educateur.setTelEducateur(telEduc);
		educateur.setEnabled(actif);
		educateur.setEquipe(equipeService.findByCategorie(categorieEduc));
		
		educateurService.save(educateur);
		
		roleService.enregistreRole(educateur);
		
		

	}
	
	public void modifierEducateur(Educateur educateur) {
	
		educateurService.modifierEducateur(educateur);
		roleService.modifieRole(educateur);
		
	}
	
	public void supprimerEducateur(int id) {
		
		role = roleService.getById(id);
		roleService.deleteRole(role);
		
		educateur = educateurService.findById(id);
		educateurService.deleteEducateur(educateur);
		
		
		
		
	}
	

}
