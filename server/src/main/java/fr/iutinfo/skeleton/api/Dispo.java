package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.DispoDto;


public class Dispo {
	private static Dispo disponibilité = new Dispo();
	
	private  int id=0;  
	private String jour; 
	private boolean matin;
	private boolean aprem;
	private boolean soir;
	
	public Dispo(int id, String jour) {
		this.id=id;
		this.jour=jour; 
	}
    
   

	public Dispo(int id, String jour, boolean matin, boolean aprem, boolean soir) {
		this.id=id; 
		this.jour = jour;
		//this.matin = matin;
		//this.aprem = aprem;
		//this.soir = soir;
	}



	public Dispo() {
    }
	
	public static Dispo getAnonymous() {
		return disponibilité;
	}
	
	public  int getId() {
		return id;
	}
	public  void setId(int id) {
		this.id = id;
	}
	public String getJour() {
		return jour;
	}
	public void setJour(String jour) {
		this.jour = jour;
	}
	public boolean isMatin() {
		return matin;
	}
	public void setMatin(boolean matin) {
		this.matin = matin;
	}
	public boolean isAprem() {
		return aprem;
	}
	public void setAprem(boolean aprem) {
		this.aprem = aprem;
	}
	public boolean isSoir() {
		return soir;
	}
	public void setSoir(boolean soir) {
		this.soir = soir;
	}
	
	public void initFromDto(DispoDto dto) {
		this.setJour(dto.getJour());
		this.setId(dto.getId());
		/*this.setMatin(dto.isMatin());
		this.setAprem(dto.isAprem());
		this.setSoir(dto.isSoir());*/
	}

	public DispoDto convertToDto() {
		DispoDto dto = new DispoDto();
		dto.setJour(this.getJour());
		dto.setId(this.getId());
		/*this.setMatin(dto.isMatin());
		this.setAprem(dto.isAprem());
		this.setSoir(dto.isSoir());*/
		return dto;
	}



	@Override
	public String toString() {
		return "Dispo [id=" + id + ", jour=" + jour + ", matin=" + matin + ", aprem=" + aprem + ", soir=" + soir + "]";
	}
	
	
	
}
