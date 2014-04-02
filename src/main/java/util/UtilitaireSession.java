package util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.paloit.entities.Educateur;


public class UtilitaireSession {



	private static final UtilitaireSession instance = new UtilitaireSession();

	private  Educateur educateur ;


 //Constructeur prive du singleton
	private UtilitaireSession(){
		super();
	}


	  
// Fonction permettant de recuperer l'instance de Session


	public static UtilitaireSession getInstance() {
		return instance;
	}
	
	

	  
// Fonction qui vérifie le contexte


	private boolean isContextOk(FacesContext fc){
		boolean res = (
			fc!=null 
			&& fc.getExternalContext()!=null 
			&& fc.getExternalContext().getSession(false)!=null
		);
		return res;
	}
	
	

	  
// Fonction qui recupere une session à partir du faces context


	private HttpSession getSession(FacesContext fc){
		return (HttpSession) fc.getExternalContext().getSession(false);
	}
	
	

	  
// Fonction qui permet de recuperer un objet dans la session


	private Object get(String cle){
		FacesContext fc = FacesContext.getCurrentInstance();
		Object res = null;
		if(isContextOk(fc)){
			res = getSession(fc).getAttribute(cle);
		}
		return res;
	}
	


	  
// Procedure qui permet d'enregistrer une variable dans la session
// 	La cle qui permet d'identifier la varaible dans la session


	private void set(String cle, Object valeur){
		FacesContext fc = FacesContext.getCurrentInstance();
		if(fc !=null && fc.getExternalContext()!=null){
			getSession(fc).setAttribute(cle, valeur);
		}
	}



	public Educateur getEducateur() {
		return (Educateur) (get("nom"));

	}


	public void setEducateur(Educateur educateur) {
		set("nom", educateur);
		
	}

	
	
}
