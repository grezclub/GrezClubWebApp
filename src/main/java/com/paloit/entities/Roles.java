package com.paloit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Roles generated by hbm2java
 */
@Entity
@Table(name = "roles", schema = "public")
public class Roles implements java.io.Serializable {

	
	private static final long serialVersionUID = 5417227316979535036L;
	private int userRoleId;
	private Educateur educateur;
	private String authority;

	public Roles() {
	}

	public Roles(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Roles(int userRoleId, Educateur educateur, String authority) {
		this.userRoleId = userRoleId;
		this.educateur = educateur;
		this.authority = authority;
	}

	@Id
	@GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
	@Column(name = "user_role_id", unique = true, nullable = false)
	public int getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public Educateur getEducateur() {
		return this.educateur;
	}

	public void setEducateur(Educateur educateur) {
		this.educateur = educateur;
	}

	@Column(name = "authority")
	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
