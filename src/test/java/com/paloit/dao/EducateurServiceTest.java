package com.paloit.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.paloit.entities.Educateur;

public class EducateurServiceTest {
    
    
    private static ClassPathXmlApplicationContext context;
    private static EducateurService educateurService;
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
      educateurService = (EducateurService) context.getBean( "educateurService" );

    }


    /**
     * Dans le AfterClass on referme le context ouvert dans le BeforeClass
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        context.close();
    }

  
    
    @Test
    public void testFindAll() {
      List<Educateur> allEducateur =  educateurService.findAll();
      
      /**
       * Le premier test verifie la liste n'est pas null
       * Le second qu'il existe des lignes dans cette liste
       */
      assertNotNull(allEducateur);
      assertTrue(allEducateur.size() > 0);
      
      //Affichage des elements pour verification
      System.out.println(allEducateur.toString());
      
    }

    @Test
    public void testFindById() {
        Educateur educ = educateurService.findById( 2 );
        assertNotNull(educ);
        assertEquals(2, educ.getIdEducateur());
        System.out.println(educ);
    }
   

    @Test
    public void testSave() {
        Educateur educ = educateurService.findById( 2 );
        educateurService.save( educ );
    }
    
    @Test
    public void testDelete(){
        Educateur educ = educateurService.findById( 4 );
        System.out.println(educ);
        educateurService.deleteEducateur( educ );
    }
    
    @Test
    public void testGetEducateurByLog(){
    	Educateur educ = educateurService.getEducateurByLog("rachid", "rachid");
    	assertNotNull(educ);
    }
    
    @Test
    public void testGetEducateurByLogin(){
    	Educateur educ = educateurService.findByLogin("red");
    	assertNotNull(educ);
    }

}
