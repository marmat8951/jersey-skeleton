package fr.iutinfo.skeleton.common.dto;

public class ListHobbieDto {
	private int id_user; 
	//id_hob est le libelle du hobbie contenu dans la table Hobbie
	private String id_hob;
	
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getId_hob() {
		return id_hob;
	}
	public void setId_hob(String id_hob) {
		this.id_hob = id_hob;
	}
	
	
	
}
