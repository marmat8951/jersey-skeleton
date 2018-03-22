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
	private static User anonymous = new User(-1, "Anonymous");
	private int id = 0;
	private String login;
	private String password;
	private String passwdHash;
	private String salt;

	public User(int id, String login) {
		this.id = id;
		this.login = login;
	}

	public User() {
	}

	public static User getAnonymousUser() {
		return anonymous;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
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

	public String getPasswdHash() {
		return passwdHash;
	}

	public void setPasswdHash(String passwdHash) {
		this.passwdHash = passwdHash;
	}

	@Override
	public boolean equals(Object arg) {
		if (getClass() != arg.getClass())
			return false;
		User user = (User) arg;
		return login.equals(user.login) && passwdHash.equals(user.getPasswdHash()) && salt.equals((user.getSalt()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((passwdHash == null) ? 0 : passwdHash.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return id + ": " + login;
	}

	public String getSalt() {
		if (salt == null) {
			salt = generateSalt();
		}
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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
		return !(id == anonymous.getId());
	}

	public boolean isAnonymous() {
		return this.getId() == getAnonymousUser().getId();
	}

	public void initFromDto(UserDto dto) {
		this.setLogin(dto.getLogin());
		this.setId(dto.getId());
		this.setPassword(dto.getPassword());
	}

	public UserDto convertToDto() {
		UserDto dto = new UserDto();
		dto.setLogin(this.getLogin());
		dto.setId(this.getId());
		dto.setPassword(this.getPassword());
		return dto;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
