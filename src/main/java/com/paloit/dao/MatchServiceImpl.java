package com.paloit.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.entities.Educateur;
import com.paloit.entities.Entrainement;
import com.paloit.entities.Match;

@Service ("matchService") 
@Transactional
public class MatchServiceImpl implements MatchService {
	
	@Autowired
    private SessionFactory sessionFactory;

	//Sauvegarde d'une convocation
	public void enregistrerMatch(Match match) {
		sessionFactory.getCurrentSession().save(match);

	}

	//recupere liste de tous mes entrainements
	@SuppressWarnings("unchecked")
	public List<Match> findAll() {	
		return sessionFactory.getCurrentSession().createQuery( "from Match" ).list();
	}

	
	public void updateMatch(Match match) {
		sessionFactory.getCurrentSession().update(match);
		
	}

	@Override
	public void deleteMatch(Match match) {
		sessionFactory.getCurrentSession().delete(match);
		
	}

	@Override
	public Match findById(Integer id) {
		return (Match) sessionFactory.getCurrentSession().get( Match.class, id );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Match> findMatchEduc(int idEducateur) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery( "from Match where id_educateur = '"+idEducateur+"'" ).list();
	}


}
