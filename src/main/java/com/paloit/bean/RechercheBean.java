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
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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

import com.paloit.entities.Equipe;
import com.paloit.entities.Joueur;
import com.paloit.entities.News;
import com.paloit.manager.EquipeManager;
import com.paloit.manager.JoueurManager;
import com.paloit.manager.NewsManager;

@Component
@RequestScoped
public class RechercheBean {

	/*
	 * Classe permettant la recherche, la modification et la suppresiion de
	 * joueur et de news
	 */
	// =========================================================================
	// ATTRIBUTS
	// =========================================================================

	// Attributs nécessaires aux opérations joueur
	private Joueur joueur;
	private JoueurManager joueurManager;
	private EquipeManager equipeManager;
	private List<Joueur> filtreJoueur;

	private String nomJoueur;
	private String prenomJoueur;
	private String adresseJoueur;
	private String telJoueur;
	private String tel2Joueur;
	private String categorieJoueur;
	private Date dateNaissanceJoueur;
	private String mailJoueur;
	private Equipe equipe;
	private int id;
	private int idNews;
	private int number;

	// Attributs nécessaires aux opérations news
	private News news;
	private NewsManager newsManager;

	private StreamedContent imageStreaming;

	private String titreNews;
	private String texteNews;
	private byte[] imageNews;

	// =========================================================================
	// CONSTRUCTEURS
	// =========================================================================

	public RechercheBean() {

	}

	// =========================================================================
	// METHODS
	// =========================================================================
	public String editJoueur() {

		// Mise en forme de la date recuperer pour interaction avec la BDD
		this.nomJoueur = joueur.getNomJoueur();
		this.prenomJoueur = joueur.getPrenomJoueur();
		this.adresseJoueur = joueur.getAdresseJoueur();
		this.dateNaissanceJoueur = joueur.getDatenaissanceJoueur();
		this.telJoueur = joueur.getTelJoueur();
		this.tel2Joueur = joueur.getTel2Joueur();
		this.mailJoueur = joueur.getMailJoueur();
		this.equipe = joueur.getEquipe();
		return "editJoueur.jsf";
	}

	public String saveEditedJoueur() {
		equipe = new Equipe();
		equipe = joueur.getEquipe();
		id = joueur.getIdJoueur();

		reinit();
		joueur.setNomJoueur(nomJoueur);
		joueur.setAdresseJoueur(adresseJoueur);
		switch (number) {
		case 1:
			joueur.setEquipe(equipe);
			break;
		case 2:
			joueur.setEquipe(equipeManager.findEquipeById(1));
			break;
		case 3:
			joueur.setEquipe(equipeManager.findEquipeById(2));
			break;
		case 4:
			joueur.setEquipe(equipeManager.findEquipeById(3));
			break;
		case 5:
			joueur.setEquipe(equipeManager.findEquipeById(4));
			break;
		case 6:
			joueur.setEquipe(equipeManager.findEquipeById(5));
			break;
		case 7:
			joueur.setEquipe(equipeManager.findEquipeById(6));
			break;
		case 8:
			joueur.setEquipe(equipeManager.findEquipeById(7));
			break;
		default:
			this.equipe = null;
			break;

		}
		joueur.setIdJoueur(id);
		joueur.setPrenomJoueur(prenomJoueur);
		joueur.setTelJoueur(telJoueur);
		joueur.setTel2Joueur(tel2Joueur);
		joueur.setMailJoueur(mailJoueur);
		joueur.setDatenaissanceJoueur(dateNaissanceJoueur);
		joueurManager.updateJoueur(joueur);
		equipe = null;
		number = 0;
		
		return "rechercheJoueur.jsf";

	}

	public String deleteJoueur() {
		this.id = joueur.getIdJoueur();
		joueurManager.supprimerJoueur(this.id);
		return "home.jsf";
	}

	public void reinit() {
		joueur = new Joueur();
	}

	public String editNews() {

		this.titreNews = news.getTitreNews();
		this.texteNews = news.getTexteNews();
		this.idNews = news.getIdNews();
		this.imageNews = news.getImageNews();

		reinitNews();
		return "editNews.xhtml";
	}

	public String saveEditedNews() {

		this.news.setIdNews(this.idNews);
		this.news.setTexteNews(this.texteNews);
		this.news.setTitreNews(this.titreNews);
		this.news.setImageNews(this.imageNews);
		newsManager.modifierNews(this.news);

		return "afficheNews.jsf";

	}

	public String deleteNews() {

		newsManager.supprimeNews(this.idNews);
		return "afficheNews.jsf";
	}

	public void reinitNews() {
		news = new News();
	}

	// Listener pour l'image sauvegardé
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

		FacesMessage msg = new FacesMessage("L' image ", event.getFile()
				.getFileName() + " à été sauvegarder");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public StreamedContent getBytesToStreamedContent() {

		this.imageStreaming = convertFichier(this.imageNews);
		return imageStreaming;

	}

	public StreamedContent convertFichier(byte[] bytes) {

		StreamedContent image;
		if (bytes != null) {
			InputStream is = new ByteArrayInputStream(bytes);
			System.out.println("size file : " + bytes.length);
			image = new DefaultStreamedContent(is, "image/jpeg", "image.jpg");
			System.out.println("getContentType : " + image.getContentType());
			System.out.println("getName : " + image.getName());
			System.out.println("getContentEncoding :"
					+ image.getContentEncoding());
			System.out.println("getStream : " + image.getStream());
			is = null;
			bytes = null;
		} else {
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

	public static byte[] ImageToByte(File filePath)
			throws FileNotFoundException {

		FileInputStream fis = new FileInputStream(filePath);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		try {
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
				System.out.println("read " + readNum + " bytes,");
			}
		} catch (IOException ex) {

		}
		byte[] bytes = bos.toByteArray();
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;

	}

	// =========================================================================
	// AUTOWIRED
	// =========================================================================
	@Autowired
	public void setManager(JoueurManager manager) {
		this.joueurManager = manager;
	}

	@Autowired
	public void setManager(NewsManager manager) {
		this.newsManager = manager;
	}

	@Autowired
	public void setEquipeManager(EquipeManager equipeManager) {
		this.equipeManager = equipeManager;

	}

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

	
	public String getTel2Joueur() {
		return tel2Joueur;
	}

	public void setTel2Joueur(String tel2Joueur) {
		this.tel2Joueur = tel2Joueur;
	}

	public String getCategorieJoueur() {
		return categorieJoueur;
	}

	public void setCategorieJoueur(String categorieJoueur) {
		this.categorieJoueur = categorieJoueur;
	}

	public String getMailJoueur() {
		return mailJoueur;
	}

	public Date getDateNaissanceJoueur() {
		return dateNaissanceJoueur;
	}

	public void setDateNaissanceJoueur(Date dateNaissanceJoueur) {
		this.dateNaissanceJoueur = dateNaissanceJoueur;
	}

	public void setMailJoueur(String mailJoueur) {
		this.mailJoueur = mailJoueur;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
