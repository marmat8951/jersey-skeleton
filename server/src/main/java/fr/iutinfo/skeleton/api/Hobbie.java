package fr.iutinfo.skeleton.api;
import fr.iutinfo.skeleton.api.Service;
import fr.iutinfo.skeleton.common.dto.HobbieDto;
import fr.iutinfo.skeleton.common.dto.ServiceDto;

public class Hobbie {
	
	private static Hobbie poney = new Hobbie("poney");
    private String libelle ="";
    
    public Hobbie(String libelle) {
		this.libelle=libelle; 
	}
    
    public Hobbie() {
    	
    }

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
    
	public String toString() {
    	return "hobbie : "+ libelle;
    }
	
	public void initFromDto(HobbieDto dto) {
        this.setLibelle(dto.getLibelle());
    }
    
    public HobbieDto convertToDto() {
        HobbieDto dto = new HobbieDto();
        dto.setLibelle(this.getLibelle());
        return dto;
    }
}
