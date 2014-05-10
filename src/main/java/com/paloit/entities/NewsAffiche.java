package com.paloit.entities;

import java.awt.Image;

public class NewsAffiche {
	
	private int idNews;
	private Image imageNews;
	private String texteNews;
	private String titreNews;
	
	public NewsAffiche(){
		
	}

	public int getIdNews() {
		return idNews;
	}

	public void setIdNews(int idNews) {
		this.idNews = idNews;
	}

	public Image getImageNews() {
		return imageNews;
	}

	public void setImageNews(Image imageNews) {
		this.imageNews = imageNews;
	}

	public String getTexteNews() {
		return texteNews;
	}

	public void setTexteNews(String texteNews) {
		this.texteNews = texteNews;
	}

	public String getTitreNews() {
		return titreNews;
	}

	public void setTitreNews(String titreNews) {
		this.titreNews = titreNews;
	}
	
	

}
