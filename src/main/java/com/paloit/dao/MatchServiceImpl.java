package com.paloit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return sessionFactory.getCurrentSession().createQuery( "from Match where id_educateur = '"+idEducateur+"'" ).list();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Match> findLastMatchEduc(int idEducateur) {
		
		return sessionFactory.getCurrentSession().createQuery( "from Match where match.idMatch = (select max(idMatch) from Match) and idEducateur='"+idEducateur+"'").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Match> findLastMatchEquipe(int idEquipe) {
		
		List<Match> listeEquipeA = new ArrayList<Match>();
		List<Match> listeEquipeB = new ArrayList<Match>();
		List<Match> listeEquipeC = new ArrayList<Match>();
		List<Match> listeMatch =  new ArrayList<Match>();
		
		Match matchA;
		Match matchB;
		Match matchC;
		
		listeEquipeA = sessionFactory.getCurrentSession().createQuery("from Match where idEquipe = '"+idEquipe+"' and classe = 'A' order by idMatch desc ").list();
		listeEquipeB = sessionFactory.getCurrentSession().createQuery("from Match where idEquipe = '"+idEquipe+"' and classe = 'B' order by idMatch desc").list();
		listeEquipeC = sessionFactory.getCurrentSession().createQuery("from Match where idEquipe = '"+idEquipe+"' and classe = 'C' order by idMatch desc").list();
		
		
		
	
		if (listeEquipeA.size() > 0){
			matchA = listeEquipeA.get(0);
		listeMatch.add(matchA);
		}
		if (listeEquipeB.size() > 0){
			matchB = listeEquipeB.get(0);
			listeMatch.add(matchB);
			}
		if (listeEquipeC.size() > 0){
			matchC = listeEquipeC.get(0);
			listeMatch.add(matchC);
			}
		
		return listeMatch;
		
		
		
	}

	

	


}
