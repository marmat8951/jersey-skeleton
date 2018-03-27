package fr.iutinfo.skeleton.common.dto;

public class RDVDto {
	//final static Logger logger = LoggerFactory.getLogger(RDVDto.class);
	private int id_rdv; 
	private String senior ;
	private String etudiant;
	private String jour;
	private boolean matin;
	private boolean aprem;
	private boolean soir;
	private String service;
	
	public int getId_rdv() {
		return id_rdv;
	}
	public void setId_rdv(int id_rdv) {
		this.id_rdv = id_rdv;
	}
	public String getSenior() {
		return senior;
	}
	public void setSenior(String senior) {
		this.senior = senior;
	}
	public String getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(String etudiant) {
		this.etudiant = etudiant;
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
	
	
}
