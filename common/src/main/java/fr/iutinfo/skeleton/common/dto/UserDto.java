package fr.iutinfo.skeleton.common.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDto {
    final static Logger logger = LoggerFactory.getLogger(UserDto.class);
    private String nom;
    private String prenom;
    private String login;
    private String numero;
    private String statut;
    private String password;
    private String valide;
    
    /*
     * {
"nom" : "tnom",
"prenom" : "tprenom",
"login" : "tlogin",
"numero" : "tnumero",
"statut" : "tstatut",
"password" : "pass"
}
     */

    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getValide() {
		return valide;
	}

	public void setValide(String valide) {
		this.valide = valide;
	}


}