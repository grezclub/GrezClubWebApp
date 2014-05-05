package com.paloit.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.entities.Educateur;
import com.paloit.entities.Roles;

@Service ("roleService") 
@Transactional
public class RoleServiceImpl implements RoleService {
	
	/* Pour commencer on a besoin de creer un attribus SesionFactory pour
    * pouvoir communiquer avec Hibernate. On ajoute une annotation Autowired
    * car avec Spring on � d�j� configurer une sessionFactory Ainsi
    * automatiquement il reprendra cette configuration
    */
   @Autowired
   private SessionFactory sessionFactory;

	@Override
	public void enregistreRole(Educateur educateur) {
		Roles role = new Roles();
		
		role.setAuthority(educateur.getFonction());
		role.setEducateur(educateur);
		
		sessionFactory.getCurrentSession().save(role);
		
		role = null;

	}

	@Override
	public void modifieRole(Educateur educateur) {
		Roles role= new Roles();
		role = (Roles) sessionFactory.getCurrentSession().createQuery("from Roles where educateur.idEducateur = '"+educateur.getIdEducateur()+"'").uniqueResult();
		
		role.setAuthority(educateur.getFonction());
		
		sessionFactory.getCurrentSession().update(role);
		
	}

	@Override
	public Roles getById(int id) {
		Roles role = new Roles();
		role = (Roles) sessionFactory.getCurrentSession().createQuery("from Roles where educateur.idEducateur = '"+id+"'").uniqueResult();
		return role;
	}

	@Override
	public void deleteRole(Roles role) {
		sessionFactory.getCurrentSession().delete(role);
		
	}

}
