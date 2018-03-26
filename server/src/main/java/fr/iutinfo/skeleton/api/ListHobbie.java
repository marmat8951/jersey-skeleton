package fr.iutinfo.skeleton.api;
import fr.iutinfo.skeleton.common.dto.ListHobbieDto;
import fr.iutinfo.skeleton.common.dto.RDVDto;

public class ListHobbie {
	//Le login est l'id user
	private String login=""; 
	//id_hob est le libelle du hobbie contenu dans la table Hobbie
	private String id_hob=""; 
	
	public ListHobbie(String login, String id_hob) {
		
		this.login = login;
		this.id_hob = id_hob;
	}
	
	public ListHobbie() {}

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
	
	public void initFromDto(ListHobbieDto dto) {
		this.setId_hob(dto.getId_hob());
		this.setLogin(dto.getLogin());
    }
    
  

    public ListHobbieDto convertToDto() {
        ListHobbieDto dto = new ListHobbieDto();
        dto.setId_hob(this.getId_hob());
        dto.setLogin(this.getLogin());
        return dto;
    }
	

	
	
}
