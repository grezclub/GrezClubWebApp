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

import com.paloit.entities.News;

public class NewsServiceTest {

    private static ClassPathXmlApplicationContext context;
    private static NewsService newsService;
    static Session  session;
    
    @Autowired
	public void setFactory(SessionFactory factory) {
		session = factory.openSession();
	}
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        newsService = (NewsService) context.getBean( "newsService" );
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        context.close();
    }

    @Test
    public void testCreerNews() {
        newsService.creerNews( "Titre3", "le grand pere Simpsons" );
        
    }
    
    @Test 
    public void recupNewsIdTest(){
        
        News news = newsService.recupererNewsId( 1 );
        System.out.println(news); 
    
    }
    
    @Test
    public void recupNewsTitreTest(){
        News news = newsService.recupererNewsTitre( "Titre2" );
        System.out.println(news.getTexteNews());
    }
    
    @Test
    public void testDeleteNews(){
        News news = newsService.recupererNewsId( 13 );
        newsService.deleteNews( news );
    }
    
    @Test
    public void testListeNews(){
        List<News> listeNews = newsService.listeNews();
        assertNotNull(listeNews);
        assertTrue(listeNews.size() > 0);
        System.out.println(listeNews.size());
    }
    

}
