package fr.iutinfo.skeleton.api;


import fr.iutinfo.skeleton.common.dto.RDVDto;

public class RDV {
	private String senior ;
	private String jour;
	private boolean matin;
	private boolean aprem;
	private boolean soir;
	private String service;
	private String etudiant=null;
	
	public RDV() {
	}
	
	public RDV(String senior, String jour, boolean matin, boolean aprem, boolean soir, String service,
			String etudiant) {
		super();
		this.senior = senior;
		this.jour = jour;
		this.matin = matin;
		this.aprem = aprem;
		this.soir = soir;
		this.service = service;
		this.etudiant = etudiant;
	}
	
    public String getSenior() {
		return senior;
	}

	public void setSenior(String senior) {
		this.senior = senior;
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

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(String etudiant) {
		this.etudiant = etudiant;
	}

	public void initFromDto(RDVDto dto) {
        this.setAprem(dto.getAprem());
        this.setSoir(dto.getSoir());
        this.setMatin(dto.getMatin());
        this.setSenior(dto.getSenior());
        this.setJour(dto.getJour());
        this.setEtudiant(dto.getEtudiant());
        this.setService(dto.getService());
    }
    
    public boolean isValide() {
    	return etudiant!=null;
    }

    public RDVDto convertToDto() {
        RDVDto dto = new RDVDto();
        dto.setAprem(this.getAprem());
        dto.setMatin(this.getMatin());
        dto.setSoir(this.getSoir());
        dto.setEtudiant(this.getEtudiant());
        dto.setSenior(this.getSenior());
        dto.setService(this.getService());
        dto.setJour(this.getJour());
        return dto;
    }
	
}
