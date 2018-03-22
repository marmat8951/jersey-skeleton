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
    	setMail(dto.getMail());
    	setNom(dto.getNom());
    	setPrenom(dto.getPrenom());
    	setNumero(dto.getNumero());
    	setType(Type.getRole(dto.getStatut()));
    }

    public void pushToDto(UserDto dto) {
    	dto.setMail(getMail());
    	dto.setNom(getNom());
    	dto.setPrenom(getPrenom());
    	dto.setNumero(getNumero());
    	dto.setStatut(getType().toString());
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


	public Type getType2() {
		return type;
	}
	
	public String getType() {
		return type.toString();
	}

	public void setType(String type) {
		this.type = Type.getRole(type);
	}
	
	
	public void setType(Type type) {
		this.type = type;
	}


	@Override
	public String getName() {
		return uid+"";
	}

}
