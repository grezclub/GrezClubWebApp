package com.paloit.dao;

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

}
