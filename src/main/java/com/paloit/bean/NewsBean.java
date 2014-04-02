package com.paloit.bean;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paloit.entities.News;
import com.paloit.entities.NewsAffiche;
import com.paloit.manager.NewsManager;

@Component
@RequestScoped
public class NewsBean {

	// =========================================================================
	// ATTRIBUTS
	// =========================================================================
	private News news;
	private News news1;
	
	private List<News> listeNews;
	private List<NewsAffiche> listeNewsAffiche;
	private NewsManager manager;
	private File image;
	
	private String titre;
	private String article;
	private String id;
	private Integer i = 0;
	
	private StreamedContent imageStreaming;
	private  StreamedContent fileContent;
	

	// =========================================================================
	// CONSTRUCTEURS
	// =========================================================================

	public NewsBean() {

	}

	// =========================================================================
	// METHODS
	// =========================================================================
	public List<News> getRecupNews() {

		news1= new News();
		listeNews = manager.recup5News();
		return listeNews;		

	}
	
	

	public StreamedContent getBytesToStreamedContent() {	
		
		 FacesContext context = FacesContext.getCurrentInstance();
		 if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {

			 	//Rendu de la page HTML. Retour d'un streamCentent par defaut pour generer une URL correct
	            return new DefaultStreamedContent();
	        }
	        else {
	            // Le navigateur a besoin de l'image donc renvoie de l'image.
	            String idNews = context.getExternalContext().getRequestParameterMap().get("newsId");
	            int newsId = Integer.parseInt(idNews);
	            this.news1 = manager.recupNewsId(newsId);
	            this.imageStreaming = convertFichier(news1.getImageNews());     
	            return imageStreaming;
	        }
	
			
		
	}


	public StreamedContent convertFichier(byte[] bytes) {

		InputStream is = new ByteArrayInputStream(bytes);
		System.out.println("size file : " + bytes.length);
		StreamedContent image = new DefaultStreamedContent(is, "image/jpeg",
				"image.jpg");
		System.out.println("getContentType : " + image.getContentType());
		System.out.println("getName : " + image.getName());
		System.out.println("getContentEncoding :" + image.getContentEncoding());
		System.out.println("getStream : " + image.getStream());
		is = null;
		bytes = null;

		return image;
	}

	// Methode permettant la recuperation d'une image et la converstion en jpeg
	public static Image byteToImage(byte[] bytes, File imageFile)
			throws IOException {

		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		Iterator<?> readers = ImageIO.getImageReadersByFormatName("jpeg");
		ImageReader reader = (ImageReader) readers.next();
		Object source = bis;
		ImageInputStream iis = ImageIO.createImageInputStream(source);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Image image = reader.read(0, param);
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawImage(image, null, null);
		ImageIO.write(bufferedImage, "jpeg", imageFile);
		System.out.println(imageFile.getPath());
		return bufferedImage;

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
	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public List<News> getListeNews() {
		return listeNews;
	}

	public void setListeNews(List<News> listeNews) {
		this.listeNews = listeNews;
	}

	public List<NewsAffiche> getListeNewsAffiche() {
		return listeNewsAffiche;
	}

	public void setListeNewsAffiche(List<NewsAffiche> listeNewsAffiche) {
		this.listeNewsAffiche = listeNewsAffiche;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public News getNews1() {
		return news1;
	}

	public void setNews1(News news1) {
		this.news1 = news1;
	}


	public String getName() {
		return fileContent.getName();
	}

	public InputStream getStream() {
		return fileContent.getStream();
	}

	public String getContentType() {
		return fileContent.getContentType();
	}

	public String getContentEncoding() {
		return fileContent.getContentEncoding();
	}
	
	

}
