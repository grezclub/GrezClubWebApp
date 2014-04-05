package com.paloit.bean;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.paloit.manager.NewsManager;

@Component
@Scope
public class CreationNewsBean {

	// =========================================================================
	// ATTRIBUTS
	// =========================================================================
	private String titreNews;
	private String contenueNews;
	private byte[] imageNews;
	
	private NewsManager manager;

	// =========================================================================
	// CONSTRUCTEURS
	// =========================================================================
	public CreationNewsBean() {

	}

	// =========================================================================
	// METHODS
	// =========================================================================
	
	public void handleFileUpload(FileUploadEvent event) {  
		
		
		/**/
		InputStream is;
		try {
			is = event.getFile().getInputstream();
			imageNews = IOUtils.toByteArray(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FacesMessage msg = new FacesMessage("L' image ", event.getFile().getFileName() + " à été sauvegarder");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	public String creerNews(){
		
	
		manager.creerNews(imageNews, titreNews, contenueNews);
		  
			
	        return "afficheNews.jsf";
	    
	}
        
    

	//
	// =========================================================================
	// @Autowired
	// =========================================================================
	@Autowired
    public void setManager(NewsManager manager) {
        this.manager = manager;
    }

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================
	public String getTitreNews() {
		return titreNews;
	}

	public void setTitreNews(String titreNews) {
		this.titreNews = titreNews;
	}

	public String getContenueNews() {
		return contenueNews;
	}

	public void setContenueNews(String contenueNews) {
		this.contenueNews = contenueNews;
	}

	public byte[] getImageNews() {
		return imageNews;
	}

	public void setImageNews(byte[] imageNews) {
		this.imageNews = imageNews;
	}  
	
	

}
