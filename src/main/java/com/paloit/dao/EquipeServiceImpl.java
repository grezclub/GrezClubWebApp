package com.paloit.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.entities.Equipe;

@Service ("equipeService") 
@Transactional
/**
 * @Service Annotation permettant de preciser que cette classe est un Bean de service de Spring
 * @Transactional On precise �galement qu'il s'agit d'un Bean Transactionnel
 */
public class EquipeServiceImpl implements EquipeService {
    
    /**
     * Pour commencer on a besoin de creer un attribus SesionFactory pour
     * pouvoir communiquer avec Hibernate. On ajoute une annotation Autowired
     * car avec Spring on � d�j� configurer une sessionFactory Ainsi
     * automatiquement il reprendra cette configuration
     */
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings( "unchecked" )
    
    public List<Equipe> findAll() {
        return sessionFactory.getCurrentSession().createQuery( " from Equipe" ).list();
    }

    
    public Equipe findById( Integer id ) {
        return (Equipe) sessionFactory.getCurrentSession().get( Equipe.class, id );
    }

    
    public void save( Equipe equipe ) {
        sessionFactory.getCurrentSession().saveOrUpdate( equipe );
    }
    
    
    public void deleteEquipe (Equipe equipe){
        sessionFactory.getCurrentSession().delete( equipe );
    }


	public Equipe findByCategorie(String categorie) {
		Equipe equipe = new Equipe();
		equipe = (Equipe) sessionFactory.getCurrentSession().createQuery("from Equipe where categorie = '"+categorie+"'").uniqueResult();
		return equipe;
	}

}
