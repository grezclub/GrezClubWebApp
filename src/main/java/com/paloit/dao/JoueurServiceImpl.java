package com.paloit.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.entities.Equipe;
import com.paloit.entities.Joueur;

@Service ("joueurService") 
@Transactional

/**
 * @Service Annotation permettant de preciser que cette classe est un Bean de service de Spring
 * @Transactional On precise �galement qu'il s'agit d'un Bean Transactionnel
 */
public class JoueurServiceImpl implements JoueurService {
    
    /**
     * Pour commencer on a besoin de creer un attribus SesionFactory pour
     * pouvoir communiquer avec Hibernate. On ajoute une annotation Autowired
     * car avec Spring on � d�j� configurer une sessionFactory Ainsi
     * automatiquement il reprendra cette configuration.
     */
    @Autowired
    private SessionFactory sessionFactory;

    //Recuperation de la liste de tous les joueurs
    @SuppressWarnings( "unchecked" )
    
    public List<Joueur> findAll() {
        return sessionFactory.getCurrentSession().createQuery( "from Joueur" ).list();
    }
    
    //Recup�ration de la liste de tous les joueurs d'un cat�gorie
    @SuppressWarnings( "unchecked" )
    
    public List<Joueur> findByCategorie (String categorie){
        Equipe equipe  =(Equipe) sessionFactory.getCurrentSession().createQuery( "from Equipe where categorie = '"+categorie+"'").uniqueResult();
        return (List<Joueur>) sessionFactory.getCurrentSession().createQuery( "from Joueur where equipe.idEquipe = "+equipe.getIdEquipe() ).list();
        
        
    }

    //Recherche un joueur par son id
    public Joueur findById( Integer id ) {
       
        return (Joueur) sessionFactory.getCurrentSession().get( Joueur.class, id );
    }

    //Recherche un joueur par son nom
    @SuppressWarnings( "unchecked" )
    
    public List<Joueur> findByName( String name ) {
        List<Joueur> listeJoueur = sessionFactory.getCurrentSession().createQuery
                            ( "from Joueur where nomJoueur = '"+name+"'" ).list();
        return listeJoueur;
    }
    
    //Creer un joueur
    public void creerJoueur( Joueur joueur ) {
        sessionFactory.getCurrentSession().save( joueur );
        
    }
    
    //Sauvegarde ou met a jour un joueur
    
    public void save( Joueur joueur ) {
        sessionFactory.getCurrentSession().save( joueur );
    }
    
    //Supprimer un joueur
    
    public void deleteJoueur (Joueur joueur){
        sessionFactory.getCurrentSession().delete( joueur );
    }

    @SuppressWarnings( "unchecked" )
    
    public List<Joueur> findAllLight() {
        return sessionFactory.getCurrentSession().createQuery( "select nomJoueur, prenomJoueur, idJoueur from Joueur" ).list();

    }

    public void update(Joueur joueur){
    	sessionFactory.getCurrentSession().update(joueur);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Joueur> findByEquipe(int idEquipe) {
		
		
	 return sessionFactory.getCurrentSession().createQuery( "from Joueur where id_equipe = '"+idEquipe+"'" ).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Joueur> listeJoueurAnniversaire() {
		
		return sessionFactory.getCurrentSession().createQuery( "from Joueur  where to_char(datenaissance_joueur, 'MM') = '07'" ).list();
	}
   

    

}
