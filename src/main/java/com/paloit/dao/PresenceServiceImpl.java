package com.paloit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.entities.Educateur;
import com.paloit.entities.Entrainement;
import com.paloit.entities.Joueur;
import com.paloit.entities.Presence;
import com.paloit.entities.PresenceId;

@Service( "presenceService" )
@Transactional
public class PresenceServiceImpl implements PresenceService {

    @Autowired
    private SessionFactory sessionFactory;
  


    
    public void enregistrerPresence( Entrainement entrainement, Joueur joueur ) {
        Presence presence = new Presence();
        PresenceId presenceId = new PresenceId();
        
        presenceId.setIdEntrainement( entrainement.getIdEntrainement() );
        presenceId.setIdJoueur( joueur.getIdJoueur() );
        
        presence.setId( presenceId );
        
        sessionFactory.getCurrentSession().save( presence );

    }

    @SuppressWarnings( "unchecked" )
    
    public ArrayList<Joueur> listeJoueurPresent( Entrainement entrainement ) {
        /**
         * Creation de 2 listes, une contiendra la liste des id joueur qui
         * �taient present a l'entrainement. L'autre sera creer � partir de la
         * premi�re (avec les id recupere) et contiendra les joueurs present
         */
        ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();
        ArrayList<Presence> listePresence = new ArrayList<Presence>();

        //Creation premi�re liste
        listePresence = (ArrayList<Presence>) sessionFactory.getCurrentSession().createQuery
                ( "from Presence where id.idEntrainement = " + entrainement.getIdEntrainement() ).list();
        
        //Creation seconde liste a partir des infos de la premi�re
        for ( int i = 0; i < listePresence.size(); i++ ) {
            listeJoueur.add( (Joueur) sessionFactory.getCurrentSession()
                    .createQuery( "from Joueur where idJoueur = " + listePresence.get( i ).getId().getIdJoueur() )
                    .uniqueResult() );
        }
        
        return listeJoueur;
    }

    public Educateur encadreEntrainement( Entrainement entrainement ) {
        
        
        Educateur educateur = (Educateur) sessionFactory.getCurrentSession().createQuery
                ( "From Educateur where idEducateur = "+ entrainement.getEducateur().getIdEducateur()).uniqueResult();
        return educateur;
    }

	@Override
	public void updatePresence(Entrainement entrainement, Joueur joueur) {
		 Presence presence = new Presence();
	        PresenceId presenceId = new PresenceId();
	        
	        presenceId.setIdEntrainement( entrainement.getIdEntrainement() );
	        presenceId.setIdJoueur( joueur.getIdJoueur() );
	        
	        presence.setId( presenceId );
	        
	        sessionFactory.getCurrentSession().save( presence );
		
	}

	@Override
	public void deletePresence(Presence presence) {
		
		
		sessionFactory.getCurrentSession().delete(presence);

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Presence> listePresenceParEntrainement(Entrainement entrainement) {
		List <Presence>listePresence = new ArrayList<Presence>();
		listePresence = (ArrayList<Presence>) sessionFactory.getCurrentSession().createQuery
                ( "from Presence where id.idEntrainement = " + entrainement.getIdEntrainement() ).list();
		System.out.println("c'est bon pour la recuperation de la liste de presence");
		return listePresence;
		
	}

}
