package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Service {
	
	final static Logger logger = LoggerFactory.getLogger(User.class);
    private static Service conduite = new Service("Conduite");
    private String libelle ="";
    
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
}
