package com.paloit.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.paloit.entities.Joueur;

public class JoueurServiceTest {
    
    private static ClassPathXmlApplicationContext context;

    private static JoueurService joueurService;
    private static EquipeService equipeService;
    static Session  session;

    @Autowired
	public void setFactory(SessionFactory factory) {
		session = factory.openSession();
	}
    
    /**
     * Ici on charge Spring (chargement de l'application contexte en specifiant le fichier de configuration) 
     * Et on charge la Classe de service EducateurService � partir du contexte
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        joueurService = (JoueurService) context.getBean( "joueurService" );
        equipeService = (EquipeService) context.getBean( "equipeService" );
    }
    

    /**
     *On referme le contexte ouvert dans le BeforeClass
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        context.close();
    }

    @Test
    public void testFindAll() {	
    	List<Joueur> liste = joueurService.findAll();
    	assertNotNull(liste);
    	assertTrue(liste.size() > 0);
    }

    @Test
    public void testFindById() {
        Joueur joueur = joueurService.findById( 1 );
        assertNotNull(joueur);
        System.out.println(joueur);
    }

    @Test
    public void testFindByName(){
        List<Joueur> listeJoueur = joueurService.findByName( "LANISTER" );
        assertNotNull(listeJoueur);
        assertTrue(listeJoueur.size() > 0);
        System.out.println(listeJoueur);
    }
    
    @Test
    public void testSave() {
        Joueur joueur = joueurService.findById( 2 );
        joueurService.save( joueur );
    }
    
    @Test
    public void testFindByCategorie(){
        List<Joueur> listeJoueur = joueurService.findByCategorie( "U9" );
        System.out.println(listeJoueur);
       
    }
    
    @Test
    public void testDeleteJoueur(){
        Joueur joueur = joueurService.findById( 3);
        joueurService.deleteJoueur( joueur );
    }
    
    @Test
    public void testCreerJoueur(){
      //Mise en forme de la date recuperer pour interaction avec la BDD 
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = null;
        try {
            date1 = df.parse( "01/01/2013" );
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
       
        Joueur joueur = new Joueur();
        joueur.setNomJoueur( "Stark" );
        joueur.setPrenomJoueur( "John" );
        joueur.setDatenaissanceJoueur( date1 );
        joueur.setAdresseJoueur( "12 rue de Winterfell" );
        joueur.setEquipe( equipeService.findById( 1 ) );
        joueur.setTelJoueur( "0649801006" );
        
        joueurService.save( joueur );
    }

}
