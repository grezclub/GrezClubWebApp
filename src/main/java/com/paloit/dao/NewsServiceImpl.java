package com.paloit.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.entities.News;

@Service ("newsService") 
@Transactional
public class NewsServiceImpl implements NewsService {
    
    @Autowired
    private SessionFactory sessionFactory;
   
    
    //Methode permettant la conversion d'une image en byte[]
    public static byte [] ImageToByte(File filePath) throws FileNotFoundException{
        
        FileInputStream fis = new FileInputStream(filePath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];       
        try {
        for (int readNum; (readNum = fis.read(buf)) != -1;) {
        bos.write(buf, 0, readNum); 
        System.out.println("read " + readNum + " bytes,");}
        } catch (IOException ex) {
             
        }
            byte[] bytes = bos.toByteArray();
           try {
            fis.close();
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            return bytes;    

            }
    
    //Methode permettant la recuperation d'une image et la converstion en jpeg
/*    public static void byteToImage(byte [] bytes,File imageFile) throws IOException{

        ByteArrayInputStream bis =new ByteArrayInputStream(bytes);
        Iterator<?> readers =ImageIO.getImageReadersByFormatName("jpeg");
        ImageReader reader =(ImageReader) readers.next();
        Object source = bis; 
        ImageInputStream iis =ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param =reader.getDefaultReadParam();
        Image image = reader.read(0,param);
        BufferedImage bufferedImage= new BufferedImage(image.getWidth(null), image.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 =bufferedImage.createGraphics();
        g2.drawImage(image, null,null);
        ImageIO.write(bufferedImage,"jpeg", imageFile);
        System.out.println(imageFile.getPath());
            }*/
    
    public void creerNews1(News news) {

    	sessionFactory.getCurrentSession().save( news );
	}
    
    public void creerNews(  String titre, String texte ) {
        News news = new News();
        news.setTitreNews( titre );
        news.setTexteNews( texte );
        
        byte[] imageNumerise = null;
        
        File img = new File("./resources/images/dock/recherche.png");
        try {
           imageNumerise = ImageToByte( img );
        } catch ( FileNotFoundException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        news.setImageNews( imageNumerise );
        
        sessionFactory.getCurrentSession().save( news );
}

    
    public News recupererNewsId(Integer id){
        
        News news = (News) sessionFactory.getCurrentSession().get( News.class, id );
/*        File image = new File("ressources/retour.jpeg");
        try {
            byteToImage(news.getImageNews(), image);
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        return news;
        
    }
 
    
    public void deleteNews( News news ) {
        sessionFactory.getCurrentSession().delete( news );
    }
  
    
    public News recupererNewsTitre( String titre ) {
     
    /*News news = (News) sessionFactory.getCurrentSession().createQuery
                        ( "from News where titreNews = '"+titre+"'" ).uniqueResult();
        File image = new File("ressources/retour1.jpeg");
        try {
            byteToImage(news.getImageNews(), image);
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        return null;
    }
   
    @SuppressWarnings( "unchecked" )
    
    public List<News> listeNews() {
    	Query query = sessionFactory.getCurrentSession().createQuery("from News order by idNews desc 3");
    	List<News> listeNews = query.list();
       return listeNews;
               
    }

	@Override
	@SuppressWarnings("unchecked")
	public List<News> liste5News() {
		Query query = sessionFactory.getCurrentSession().createQuery("from News order by idNews desc 3");
    	query.setMaxResults(5);
		List<News> listeNews = query.list();
       return listeNews;
	}

	

	

    
}
