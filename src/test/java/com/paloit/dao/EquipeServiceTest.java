package com.paloit.dao;

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

import com.paloit.entities.Equipe;

public class EquipeServiceTest {
    
    private static ClassPathXmlApplicationContext context;
    private static EquipeService equipeService;
    static Session  session;

    @Autowired
   	public void setFactory(SessionFactory factory) {
   		session = factory.openSession();
   	}
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
        context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        equipeService = (EquipeService) context.getBean( "equipeService" );
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        context.close();
    }

    @Test
    public void testFindAll() {
        List<Equipe> allEquipe = equipeService.findAll();
        assertNotNull(allEquipe);
        assertTrue(allEquipe.size() > 0);
        System.out.println(allEquipe);
    }

    @Test
    public void testFindById() {
        Equipe equipe = equipeService.findById( 2 );
        assertNotNull(equipe);
        System.out.println(equipe);
    }

    @Test
    public void testSave() {
        Equipe equipe = equipeService.findById( 2 );
        equipeService.save( equipe );
        
    }
    
    @Test
    public void testDeleteEquipe(){
        Equipe equipe = equipeService.findById( 3 );
        equipeService.deleteEquipe( equipe );
    }
    
    @Test
    public void findByCategorie(){
    	Equipe equipe = equipeService.findByCategorie("U9");
    	assertNotNull(equipe);
    	System.out.println(equipe.getCategorie()+" "+equipe.getIdEquipe());
    }

}
