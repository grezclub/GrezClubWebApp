package com.paloit.bean;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


@Named
@RequestScoped
public class AuthenticationBean {
	
	public AuthenticationBean(){
	
	}
	
	public String doLogin() throws IOException ,ServletException{
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();
		System.out.println("methode doLogin()");
		return "/pages/home.jsf";
	}
	
	public String doLogout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		System.out.println("Deconnexion");
		return "/login-failure.xhtml";
	}
	
	public String login(){
		return "login.xhtml";
	}

}
