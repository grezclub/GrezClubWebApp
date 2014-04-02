package com.paloit.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.entities.Educateur;
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


}
