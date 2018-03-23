package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.ServiceDto;
import fr.iutinfo.skeleton.common.dto.UserDto;

public class Service {
	
	
    private static Service conduite = new Service("Conduite");
    private String libelle ="";
    private String search;
    
    public Service(String libelle) {
		this.libelle=libelle; 
	}
    
    public Service() {
    	
    }
    
    public String getLibelle() {
    	return libelle; 
    }
    
    public void setLibelle(String libelle) {
    	this.libelle=libelle; 
    }
    
    public static Service getConduiteService() {
        return conduite;
    }
    
    public String toString() {
    	return "service : "+ libelle;
    }
    
    public String getSearch() {
        search = libelle; 
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
    
    public void initFromDto(ServiceDto dto) {
        this.setLibelle(dto.getLibelle());
    }
    
    public ServiceDto convertToDto() {
        ServiceDto dto = new ServiceDto();
        dto.setLibelle(this.getLibelle());
        return dto;
    }
}
