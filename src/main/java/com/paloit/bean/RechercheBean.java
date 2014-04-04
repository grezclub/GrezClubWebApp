package com.paloit.bean;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paloit.dao.JoueurService;
import com.paloit.dao.NewsService;
import com.paloit.entities.Equipe;
import com.paloit.entities.Joueur;
import com.paloit.entities.News;
import com.paloit.manager.JoueurManager;
import com.paloit.manager.NewsManager;

@Component
@RequestScoped
public class RechercheBean {
	
	// =========================================================================
    // ATTRIBUTS
    // =========================================================================

		private Joueur joueur;
		private JoueurService js;
		private JoueurManager joueurManager;
		private List<Joueur> filtreJoueur; 
		
		private News news;
		private News news1;
		private NewsService ns;
		private NewsManager newsManager;
	
		
		private String nomJoueur;
		private String prenomJoueur;
		private String adresseJoueur;
		private String telJoueur;
		private String categorieJoueur;
		private String dateNaissanceJoueur;
		private Equipe equipe;
		private int id;
		private int idNews;
		
		private StreamedContent imageStreaming;
		private  StreamedContent fileContent;
		
		private String titreNews;
		private String texteNews;
		private byte[] imageNews;
		private byte[] imageNewsModifie;
		

	
	
	// =========================================================================
    // CONSTRUCTEURS
    // =========================================================================		

		public RechercheBean(){
		
			}
	// =========================================================================
    // METHODS
    // =========================================================================
	public String editJoueur() {
			
		//Mise en forme de la date recuperer pour interaction avec la BDD 
        this.nomJoueur = joueur.getNomJoueur();
        this.prenomJoueur = joueur.getPrenomJoueur();
        this.adresseJoueur = joueur.getAdresseJoueur();
        this.dateNaissanceJoueur = joueur.getDatenaissanceJoueur().toString();
        this.telJoueur = joueur.getTelJoueur();
		return "pretty:editJoueur";
	}
	public String saveEditedJoueur(){
		equipe = new Equipe();
		equipe = joueur.getEquipe();
		id = joueur.getIdJoueur();
		reinit();
		joueur.setNomJoueur(nomJoueur);
		joueur.setAdresseJoueur(adresseJoueur);
		joueur.setEquipe(equipe);
		joueur.setIdJoueur(id);
		joueur.setPrenomJoueur(prenomJoueur);
		joueur.setTelJoueur(telJoueur);
		joueurManager.modifierJoueur(joueur, dateNaissanceJoueur);
		return "pretty:home";
		
	}
	
	public void reinit() {
        joueur = new Joueur();
    }
	public void reinitNews() {
        news = new News();
    }
	
	public String deleteJoueur(){
		this.id = joueur.getIdJoueur();
		joueurManager.supprimerJoueur(this.id);
		return "pretty:home";
	}
	
	public String editNews() {
		
        this.titreNews = news.getTitreNews();
        this.texteNews = news.getTexteNews();
        this.imageNews = null;
        this.idNews = news.getIdNews();
		return "pretty:editNews";
	}
	
	public String saveEditedNews(){
		
		
		this.news.setIdNews(this.idNews);
		this.news.setTexteNews(this.texteNews);
		this.news.setTitreNews(this.titreNews);
		
		this.news.setImageNews(this.imageNews);
		newsManager.modifierNews(this.news);
		
		reinitNews();
	
		  
		
     
		return "pretty:home";
		
	}
	 
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
			
			FacesMessage msg = new FacesMessage("L' image " , event.getFile().getFileName() + " à été sauvegarder");  
			
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			
		}
	
	public StreamedContent getBytesToStreamedContent() {	
		
		
	        	news1 = new News();

	            this.news1 = newsManager.recupNewsId(this.news.getIdNews());
	            this.imageStreaming = convertFichier(news1.getImageNews());     
	            return imageStreaming;
	        

	}
	
	public StreamedContent convertFichier(byte[] bytes) {
		
		StreamedContent image;
		if(bytes != null){
		InputStream is = new ByteArrayInputStream(bytes);
		System.out.println("size file : " + bytes.length);
		image = new DefaultStreamedContent(is, "image/jpeg",
				"image.jpg");
		System.out.println("getContentType : " + image.getContentType());
		System.out.println("getName : " + image.getName());
		System.out.println("getContentEncoding :" + image.getContentEncoding());
		System.out.println("getStream : " + image.getStream());
		is = null;
		bytes = null;
		}
		else {
			image = null;
		}

		return image;
	}
	
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
    // =========================================================================
    // OVERRIDES
    // =========================================================================

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
	 public List<Joueur> getAllJoueurs() {
	        return joueurManager.getAllJoueur();
	    }

	    public Joueur getJoueur() {
	        return joueur;
	    }

	    public void setJoueur(Joueur joueur) {
	        this.joueur = joueur;
	    }

	    public List<Joueur> getFiltreJoueur() {
			return filtreJoueur;
		}

		public void setFiltreJoueur(List<Joueur> filtreJoueur) {
			this.filtreJoueur = filtreJoueur;
		}

		public String getNomJoueur() {
			return nomJoueur;
		}
		public void setNomJoueur(String nomJoueur) {
			this.nomJoueur = nomJoueur;
		}
		public String getPrenomJoueur() {
			return prenomJoueur;
		}
		public void setPrenomJoueur(String prenomJoueur) {
			this.prenomJoueur = prenomJoueur;
		}
		public String getAdresseJoueur() {
			return adresseJoueur;
		}
		public void setAdresseJoueur(String adresseJoueur) {
			this.adresseJoueur = adresseJoueur;
		}
		public String getTelJoueur() {
			return telJoueur;
		}
		public void setTelJoueur(String telJoueur) {
			this.telJoueur = telJoueur;
		}
		public String getCategorieJoueur() {
			return categorieJoueur;
		}
		public void setCategorieJoueur(String categorieJoueur) {
			this.categorieJoueur = categorieJoueur;
		}
		public String getDateNaissanceJoueur() {
			return dateNaissanceJoueur;
		}
		public void setDateNaissanceJoueur(String dateNaissanceJoueur) {
			this.dateNaissanceJoueur = dateNaissanceJoueur;
		}
		public String getTitreNews() {
			return titreNews;
		}
		public void setTitreNews(String titreNews) {
			this.titreNews = titreNews;
		}
		public String getTexteNews() {
			return texteNews;
		}
		public void setTexteNews(String texteNews) {
			this.texteNews = texteNews;
		}
		public byte[] getImageNews() {
			return imageNews;
		}
		public void setImageNews(byte[] imageNews) {
			this.imageNews = imageNews;
		}
		public News getNews() {
			return news;
		}
		public void setNews(News news) {
			this.news = news;
		}
		
		
		public byte[] getImageNewsModifie() {
			return imageNewsModifie;
		}
		public void setImageNewsModifie(byte[] imageNewsModifie) {
			this.imageNewsModifie = imageNewsModifie;
		}
		@Autowired
	    public void setManager(JoueurManager manager) {
	        this.joueurManager = manager;
	    }
		@Autowired
		public void setManager(NewsManager manager) {
			this.newsManager = manager;
		}
	
}
