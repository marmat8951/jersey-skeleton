import fr.iutinfo.skeleton.common.dto.ListHobbieDto;
import fr.iutinfo.skeleton.common.dto.RDVDto;

public class ListHobbie {
	private int id_user=-1; 
	private String id_hob;
	
	public ListHobbie(int id_user, String id_hob) {
		
		this.id_user = id_user;
		this.id_hob = id_hob;
	}
	
	public ListHobbie() {}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getId_hobbie() {
		return id_hob;
	}

	public void setId_hobbie(String id_hob) {
		this.id_hob = id_hob;
	}
	
	public void initFromDto(ListHobbieDto dto) {
		this.setId_hobbie(dto.getId_hob());
		this.setId_user(dto.getId_user());
    }
    
    public boolean isValide() {
    	return id_user>0;
    }

    public ListHobbieDto convertToDto() {
        ListHobbieDto dto = new ListHobbieDto();
        dto.setId_hob(this.getId_hobbie());
        dto.setId_user(this.getId_user());
        return dto;
    }
	

	
	
}
