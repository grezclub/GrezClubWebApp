package com.paloit.manager;

import java.util.List;

import com.paloit.entities.News;


public interface NewsManager {
	
	//Methode permettant la creation d'une News
	public void creerNews (byte[] imageNews, String titre, String contenue);
	
	//Methide permettant la recuperation de toutes les news
	public List<News> recupNews ();
	
	//Recuperer une News par son id
	public News recupNewsId (Integer id);
	
	//Recupere les 5 dernières News
	public List<News> recup5News();
	
	

}
