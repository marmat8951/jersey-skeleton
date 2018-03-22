package fr.iutinfo.skeleton.api;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import fr.iutinfo.skeleton.common.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.security.SecureRandom;

public class Information implements Principal {

    private int uid = 0;
    private String nom;
    private String prenom;
    private String mail;
    private String numero;
    private Type type;
    


    @Override
    public String toString() {
        return uid+": "+nom + ", " +prenom+", "+mail+", "+numero;
    }


    public void initFromDto(UserDto dto) {
    	//TODO
       
    }

    public void pushToDto(UserDto dto) {
    	//TODO
    }


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


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


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}


	@Override
	public String getName() {
		return uid+"";
	}

}
