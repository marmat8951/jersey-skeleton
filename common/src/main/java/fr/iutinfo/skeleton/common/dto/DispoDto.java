package fr.iutinfo.skeleton.common.dto;

public class DispoDto {
	private int id = 0;
	private String jour;
	private boolean matin;
	private boolean aprem;
	private boolean soir;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
	
}
