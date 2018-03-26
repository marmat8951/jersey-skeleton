package fr.iutinfo.skeleton.common.dto;

public class ListHobbieDto {
	//Id de l'utilisateur qui est le login
	private String login; 
	//id_hob est le libelle du hobbie contenu dans la table Hobbie
	private String id_hob;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getId_hob() {
		return id_hob;
	}
	public void setId_hob(String id_hob) {
		this.id_hob = id_hob;
	}
	
	
	
}
