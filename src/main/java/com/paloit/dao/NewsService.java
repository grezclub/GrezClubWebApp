package com.paloit.dao;

import java.util.List;

import com.paloit.entities.News;

public interface NewsService {

    // Methode permettant de creer une News
    public void creerNews(String titre, String texte );
    
    //Methode permettant de creer une news depuis une fenetre JSF
    public void creerNews1(News news);

    // Methode permettant de supprimer une News
    public void deleteNews( News news );

    // M�thode permettant de lister toutes les News
    public List<News> listeNews();
    
 // M�thode permettant de recuperer les 5 dernieres News
    public List<News> liste5News();

    // Methode permettant de rechercher une News par son id
    public News recupererNewsId( Integer id );
    
    

    // Methode permettant de rechercher une News par son titre
    public News recupererNewsTitre( String titre );
    
    // Methode permettant de modifier une News
    public void modifierNews (News news);
}
