package com.paloit.dao;

import java.util.Date;
import java.util.List;

import com.paloit.entities.Entrainement;

public interface EntrainementService {
    
    //Methode permettant de lister tous les entrainement
    public List<Entrainement> findall ();
    
    //Methode permettant de lister tous les entrainement d'un educateur a partir de son Id
    public List<Entrainement> listeEntrainementParEducateur (Integer idEduc);
    
    //Methode permettant de recuperer tous les entrainement se d�roulant dans un lieu pr�cis (epirey, jean Marion, ...)
    public List<Entrainement> listeEntrainementParLieu (String lieu);
    
    //Methode permettant de recuperer tous les entrainement se d�roulant lors d'une date pr�cise
    public List<Entrainement> listeEntrainementParDate (Date date);
    
    //Methode permettant de recuperer un entrainement avec son id
    public Entrainement findById (Integer id);
    
    //Methode permettant de creer un entrainement
    public void creerEntrainemnt(Entrainement entrainemnt);
    
    //Methode permettant de supprimer un entrainement
    public void deleteEntrainement (Entrainement entrainement);
    
    //Methode permettant de modifier un entrainement
    public void updateEntrainement (Entrainement entrainement);

}
