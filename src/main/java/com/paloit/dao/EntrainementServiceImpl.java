package com.paloit.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.entities.Entrainement;

@Service ("entrainementService") 
@Transactional
public class EntrainementServiceImpl implements EntrainementService {
    
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings( "unchecked" )
    public List<Entrainement> findall() {
        return sessionFactory.getCurrentSession().createQuery( "from Entrainement" ).list();
    }

    @SuppressWarnings( "unchecked" )
    public List<Entrainement> listeEntrainementParEducateur( Integer idEduc ) {
        return  sessionFactory.getCurrentSession().createQuery( "from Entrainement where educateur.idEducateur = "+idEduc ).list();
    }

    @SuppressWarnings( "unchecked" )
    public List<Entrainement> listeEntrainementParLieu( String lieu ) {
        return  sessionFactory.getCurrentSession().createQuery( "from Entrainement where lieuEntrainement = '"+lieu+"'" ).list();
    }

    @SuppressWarnings( "unchecked" )
    public List<Entrainement> listeEntrainementParDate( Date date ) {
        return  sessionFactory.getCurrentSession().createQuery( "from Entrainement where dateEntrainement = '"+date+"'" ).list();
    }

    
    public void creerEntrainemnt( Entrainement entrainemnt ) {
        
        sessionFactory.getCurrentSession().save( entrainemnt );
    }

    
    public Entrainement findById( Integer id ) {
        return (Entrainement) sessionFactory.getCurrentSession().get( Entrainement.class, id );
    }
    
    
    public void deleteEntrainement (Entrainement entrainement){
       sessionFactory.getCurrentSession().delete( entrainement );
    }

	
	public void updateEntrainement(Entrainement entrainement) {
		sessionFactory.getCurrentSession().update(entrainement);
		
	}


}
