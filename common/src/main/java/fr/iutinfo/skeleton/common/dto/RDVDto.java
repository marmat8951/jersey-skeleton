package fr.iutinfo.skeleton.common.dto;

import java.sql.Time;

public class RDVDto {
	//final static Logger logger = LoggerFactory.getLogger(RDVDto.class);
	private int id_senior ;
	private int id_etu;
	private Time jour;
	private boolean matin;
	private boolean aprem;
	private boolean soir;
	private String libelle;
	
	public int getId_senior() {
		return id_senior;
	}
	public void setId_senior(int id_senior) {
		this.id_senior = id_senior;
	}
	public Time getJour() {
		return jour;
	}
	public void setJour(Time jour) {
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
	
	
}
