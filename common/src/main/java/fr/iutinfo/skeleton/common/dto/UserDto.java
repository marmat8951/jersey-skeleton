package fr.iutinfo.skeleton.common.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDto {
	final static Logger logger = LoggerFactory.getLogger(UserDto.class);
	private int id = 0;
	private String password;
	private String login;
	private String nom;
	private String prenom;
	private String mail;
	private String numero;
	private String statut;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getMail() {
		return mail;
	}

	public String getNumero() {
		return numero;
	}

	public String getStatut() {
		return statut;
	}
	
}
