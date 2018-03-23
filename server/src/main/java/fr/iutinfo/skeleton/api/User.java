package fr.iutinfo.skeleton.api;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import fr.iutinfo.skeleton.common.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.security.SecureRandom;

public class User implements Principal {
    final static Logger logger = LoggerFactory.getLogger(User.class);
    private static User anonymous = new User("anony", "Anonymous", "anonym");
    private String nom;
    private String prenom;
    private String login;
    private String numero;
    private String statut;
    private String email;
    private String password;
    private String passwdHash;
    private String salt;
    private String search;

	public User(String login, String name) {
        this.login = login;
        this.nom = name;
    }

    public User(String login, String name, String alias) {
        this.login = login;
        this.nom = name;
        this.statut = alias;
    }

    public User(String nom, String numero, String prenom, String login, String statut, int userId, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.statut = statut;
		this.email = email;
		this.numero = numero;
	}

	public User() {
    }

	public String getPasswdHash() {
		return passwdHash;
	}

	public void setPasswdHash(String passwdHash) {
		this.passwdHash = passwdHash;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalt() {
		if(salt == null) {
			salt = generateSalt();
		}
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getPassword() {
		return password;
	}

    public static User getAnonymousUser() {
        return anonymous;
    }

    public void setPassword(String password) {
        passwdHash = buildHash(password, getSalt());
        this.password = password;
    }

    private String buildHash(String password, String s) {
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(password + s, Charsets.UTF_8);
        return hasher.hash().toString();
    }

    public boolean isGoodPassword(String password) {
        if (isAnonymous()) {
            return false;
        }
        String hash = buildHash(password, getSalt());
        return hash.equals(getPasswdHash());
    }

    @Override
    public boolean equals(Object arg) {
        if (getClass() != arg.getClass())
            return false;
        User user = (User) arg;
        return nom.equals(user.nom) && statut.equals(user.statut) && email.equals(user.email) && passwdHash.equals(user.getPasswdHash()) && salt.equals((user.getSalt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((statut == null) ? 0 : statut.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result + ((passwdHash == null) ? 0 : passwdHash.hashCode());
        result = prime * result + ((salt == null) ? 0 : salt.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return login + ": " + statut + ", " + nom + " <" + email + ">";
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putLong(random.nextLong());
        return hasher.hash().toString();
    }

    public void resetPasswordHash() {
        if (password != null && !password.isEmpty()) {
            setPassword(getPassword());
        }
    }

    public boolean isInUserGroup() {
        return !(login == anonymous.getLogin());
    }

    public boolean isAnonymous() {
        return this.getLogin() == getAnonymousUser().getLogin();
    }

    public void initFromDto(UserDto dto) {
    	this.setNom(dto.getNom());
    	this.setPrenom(dto.getPrenom());
    	this.setLogin(dto.getLogin());
    	this.setNumero(dto.getNumero());
    	this.setStatut(dto.getStatut());
    	this.setEmail(dto.getEmail());
    	this.setPassword(dto.getPassword());
    }

    public UserDto convertToDto() {
        UserDto dto = new UserDto();
    	dto.setNom(this.getNom());
    	dto.setPrenom(this.getPrenom());
    	dto.setLogin(this.getLogin());
    	dto.setNumero(this.getNumero());
    	dto.setStatut(this.getStatut());
    	dto.setEmail(this.getEmail());
    	dto.setPassword(this.getPassword());
        return dto;
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return nom;
	}
}