package fr.iutinfo.skeleton.common.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;

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




    public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setStatut(String statut) {
		this.statut = statut;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
