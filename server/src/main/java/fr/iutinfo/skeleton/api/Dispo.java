package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.DispoDto;


public class Dispo {
	private static Dispo disponibilité = new Dispo();
	
	private  int iduser=0;  
	private String jour; 
	private boolean matin;
	private boolean aprem;
	private boolean soir;
	
	public Dispo() {
    }
	
	public Dispo(int id, String jour) {
		this.iduser=id;
		this.jour=jour; 
	}
    
   

	public Dispo(int id, String jour, boolean matin, boolean aprem, boolean soir) {
		this.iduser=id; 
		this.jour = jour;
		this.matin = matin;
		this.aprem = aprem;
		this.soir = soir;
	}
	
	public static Dispo getAnonymous() {
		return disponibilité;
	}
	
	
	
	public static Dispo getDisponibilité() {
		return disponibilité;
	}

	public static void setDisponibilité(Dispo disponibilité) {
		Dispo.disponibilité = disponibilité;
	}



	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
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
		this.setIduser(dto.getIduser());
		this.setMatin(dto.isMatin());
		this.setAprem(dto.isAprem());
		this.setSoir(dto.isSoir());
	}

	public DispoDto convertToDto() {
		DispoDto dto = new DispoDto();
		dto.setJour(this.getJour());
		dto.setIduser(this.getIduser());
		dto.setMatin(isMatin());
		dto.setAprem(isAprem());
		dto.setSoir(isSoir());
		return dto;
	}



	@Override
	public String toString() {
		return "Dispo [id=" + iduser + ", jour=" + jour + ", matin=" + matin + ", aprem=" + aprem + ", soir=" + soir + "]";
	}



	
	
	
}
