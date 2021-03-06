package com.paloit.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Match generated by hbm2java
 */
@Entity
@Table(name = "match", schema = "public")
public class Match implements java.io.Serializable {


	private static final long serialVersionUID = -7366848165795843144L;
	private int idMatch;
	private Educateur educateur;
	private Date dateMatch;
	private String lieuMatch;
	private String adversaire;
	private String heure;
	private String commentaire;
	private String classe;
	private int idEquipe;

	public Match() {
	}

	public Match(int idMatch) {
		this.idMatch = idMatch;
	}

	public Match(int idMatch, Educateur educateur, Date dateMatch,
			String lieuMatch, String adversaire, String heure) {
		this.idMatch = idMatch;
		this.educateur = educateur;
		this.dateMatch = dateMatch;
		this.lieuMatch = lieuMatch;
		this.adversaire = adversaire;
		this.heure = heure;
	}

	@Id
	@GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
	@Column(name = "id_match", unique = true, nullable = false)
	public int getIdMatch() {
		return this.idMatch;
	}

	public void setIdMatch(int idMatch) {
		this.idMatch = idMatch;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_educateur")
	public Educateur getEducateur() {
		return this.educateur;
	}

	public void setEducateur(Educateur educateur) {
		this.educateur = educateur;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_match", length = 13)
	public Date getDateMatch() {
		return this.dateMatch;
	}

	public void setDateMatch(Date dateMatch) {
		this.dateMatch = dateMatch;
	}

	@Column(name = "lieu_match")
	public String getLieuMatch() {
		return this.lieuMatch;
	}

	public void setLieuMatch(String lieuMatch) {
		this.lieuMatch = lieuMatch;
	}

	@Column(name = "adversaire")
	public String getAdversaire() {
		return this.adversaire;
	}

	public void setAdversaire(String adversaire) {
		this.adversaire = adversaire;
	}
	
	@Column(name = "heure")
	public String getHeure() {
		return this.heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}
	
	@Column(name = "commentaire")
	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	@Column(name = "classe")
	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	@Column(name = "id_equipe")
	public int getIdEquipe() {
		return this.idEquipe;
	}

	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}
	
	
	

}
