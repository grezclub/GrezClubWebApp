package com.paloit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.entities.Convocation;
import com.paloit.entities.ConvocationId;
import com.paloit.entities.Joueur;
import com.paloit.entities.Match;

@Service ("convocationService") 
@Transactional
public class ConvocationServiceImpl implements ConvocationService {

	@Autowired
    private SessionFactory sessionFactory;
	
	//methode qui sauvegarde une liste de joueur convoqué
	public void enregistreConvocation(Match match, Joueur joueur) {
		 Convocation convocation = new Convocation();
	        ConvocationId convocationId = new ConvocationId();
	        
	        convocationId.setIdMatch( match.getIdMatch() );
	        convocationId.setIdJoueur( joueur.getIdJoueur() );
	        
	        convocation.setId( convocationId );
	        
	        sessionFactory.getCurrentSession().save( convocation );

		
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Joueur> listeJoueurConvoque(Match match) {
		 /**
         * Creation de 2 listes, une contiendra la liste des id joueur qui
         * �taient present a l'entrainement. L'autre sera creer � partir de la
         * premi�re (avec les id recupere) et contiendra les joueurs present
         */
        ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();
        ArrayList<Convocation> listePresence = new ArrayList<Convocation>();

        //Creation premi�re liste
        listePresence = (ArrayList<Convocation>) sessionFactory.getCurrentSession().createQuery
                ( "from Convocation where id.idMatch = " + match.getIdMatch() ).list();
        
        //Creation seconde liste a partir des infos de la premi�re
        for ( int i = 0; i < listePresence.size(); i++ ) {
            listeJoueur.add( (Joueur) sessionFactory.getCurrentSession()
                    .createQuery( "from Joueur where idJoueur = " + listePresence.get( i ).getId().getIdJoueur() )
                    .uniqueResult() );
        }
        
        return listeJoueur;
		
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Convocation> listePresenceParMatch(Match match) {
		List <Convocation>listePresence = new ArrayList<Convocation>();
		listePresence = (ArrayList<Convocation>) sessionFactory.getCurrentSession().createQuery
                ( "from Convocation where id.idMatch = " + match.getIdMatch() ).list();
		System.out.println("c'est bon pour la recuperation de la liste de presence");
		return listePresence;
	}



	@Override
	public void deleteConvocation(Convocation convocation) {
		sessionFactory.getCurrentSession().delete(convocation);
		
	}



	@Override
	public void updateConvocation(Match match, Joueur joueur) {
		 Convocation convocation = new Convocation();
	        ConvocationId convocationId = new ConvocationId();
	        
	        convocationId.setIdMatch(match.getIdMatch());
	        convocationId.setIdJoueur( joueur.getIdJoueur() );
	        
	        convocation.setId(convocationId);
	        
	        sessionFactory.getCurrentSession().save( convocation );
		
	}



	
	@SuppressWarnings("unchecked")
	@Override
	public List<Convocation> statPresenceJoueur(Joueur joueur) {
		 return sessionFactory.getCurrentSession().createQuery("from Convocation where id_joueur='"+joueur.getIdJoueur()+"'").list();
	}

}
