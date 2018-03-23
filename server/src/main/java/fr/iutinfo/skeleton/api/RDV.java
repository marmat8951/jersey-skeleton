package fr.iutinfo.skeleton.api;


import fr.iutinfo.skeleton.common.dto.RDVDto;

public class RDV {
	private int id_senior ;
	private String jour;
	private boolean matin;
	private boolean aprem;
	private boolean soir;
	private String libelle;
	private int id_etu=-1;
	
	public RDV() {
	}
	

	public RDV(int id_senior, String jour, boolean matin, boolean aprem, boolean soir, String libelle) {
		super();
		this.id_senior = id_senior;
		this.jour = jour;
		this.matin = matin;
		this.aprem = aprem;
		this.soir = soir;
		this.libelle = libelle;
	}


	public int getId_senior() {
		return id_senior;
	}
	public void setId_senior(int id_senior) {
		this.id_senior = id_senior;
	}
	public String getJour() {
		return jour;
	}
	public void setJour(String jour) {
		this.jour = jour;
	}
	public boolean getMatin() {
		return matin;
	}
	public void setMatin(boolean matin) {
		this.matin = matin;
	}
	public boolean getAprem() {
		return aprem;
	}
	public void setAprem(boolean aprem) {
		this.aprem = aprem;
	}
	public boolean getSoir() {
		return soir;
	}
	public void setSoir(boolean soir) {
		this.soir = soir;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public int getId_etu() {
		return id_etu;
	}


	public void setId_etu(int id_etu) {
		this.id_etu = id_etu;
	}
	
    public void initFromDto(RDVDto dto) {
        this.setAprem(dto.getAprem());
        this.setSoir(dto.getSoir());
        this.setMatin(dto.getMatin());
        this.setId_senior(dto.getId_senior());
        this.setJour(dto.getJour());
        this.setId_etu(dto.getId_etu());
        this.setLibelle(dto.getLibelle());
    }
    
    public boolean isValide() {
    	return id_etu>0;
    }

    public RDVDto convertToDto() {
        RDVDto dto = new RDVDto();
        dto.setAprem(this.getAprem());
        dto.setMatin(this.getMatin());
        dto.setSoir(this.getSoir());
        dto.setId_etu(this.getId_etu());
        dto.setId_senior(this.getId_senior());
        dto.setLibelle(this.getLibelle());
        dto.setJour(this.getJour());
        return dto;
    }
	
}
