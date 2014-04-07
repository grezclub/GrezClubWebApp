package com.paloit.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.entities.Educateur;


@Service ("educateurService") 
@Transactional
/**
 * @Service Annotation permettant de preciser que cette classe est un Bean de service de Spring
 * @Transactional On precise �galement qu'il s'agit d'un Bean Transactionnel
 */
public class EducateurServiceImpl implements EducateurService {

    /**
     * Pour commencer on a besoin de creer un attribus SesionFactory pour
     * pouvoir communiquer avec Hibernate. On ajoute une annotation Autowired
     * car avec Spring on � d�j� configurer une sessionFactory Ainsi
     * automatiquement il reprendra cette configuration
     */
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings( "unchecked" )
    /**
     * Cette anotation dis � eclipse de ne pas prevenir le warning erreur normal
     * du � Hibernate qui renvoi une liste simple non typ�
     */
    public List<Educateur> findAll() {
        /**
         * On utilise le sessionFactory declar� plus haut pour r�cuperer la
         * session courante, et ensuite avec la requ�te HQL, recuperer une liste
         * depuis la table Educateur
         */
        return sessionFactory.getCurrentSession().createQuery( "from Educateur" ).list();
    }

    public Educateur findById( Integer id ) {
        /**
         * De la m�me mani�re, je recupere a partir du sessionFactory la session
         * courante et je lui demande de charger depuis la classe/Table
         * Educateur l'attribu qui � l'id voulu
         * Ici on utilise la methode get() qui retourne l'objet avec tous ses attribus.
         * On pourrai utiliser load() mais l'objet charger n'aura pas tous ses attribus
         */
        return (Educateur) sessionFactory.getCurrentSession().get( Educateur.class, id );
    }

    public void deleteEducateur (Educateur educateur){
        //Suppression d'un educateur
        sessionFactory.getCurrentSession().delete( educateur );
    }

   
    public void save( Educateur educateur ) {
        // Sauvegarde de l'educateur toujours de la m�me mani�re
    	educateur.setEnabled(true);
        sessionFactory.getCurrentSession().saveOrUpdate( educateur );
    }

	
    public Educateur getEducateurByLog(String login, String mdp) {
		
    	Educateur educ = new Educateur();
    	educ = (Educateur) sessionFactory.getCurrentSession().createQuery
    			("from Educateur where loginEducateur ='"+login+ "' and mdpEducateur='" +mdp+"'").uniqueResult();
		return educ;
	}

	
    public void modifierEducateur(Educateur educateur) {
		sessionFactory.getCurrentSession().update(educateur);
		
	}

	public Educateur findByLogin(String login) {
		Educateur educ = new Educateur();
		educ = (Educateur) sessionFactory.getCurrentSession().createQuery
    			("from Educateur where loginEducateur ='"+login+ "'" ).uniqueResult();
		return educ;
	}

}
