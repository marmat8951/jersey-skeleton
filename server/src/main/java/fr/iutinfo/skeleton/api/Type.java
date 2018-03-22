package fr.iutinfo.skeleton.api;
public enum Type {
	ETUDIANT,SENIOR,ADMIN;
	
	public String toString() {
		String res;
		if(this.equals(ADMIN)) {
			return "admin";
		}else if(this.equals(ETUDIANT)) {
			return "etudiant";
		}else {
			return "senior";
		}
	}
	
	
	public Type getString(String role) {
		if(role.equalsIgnoreCase("admin")) {
			return ADMIN;
		}else if(role.equalsIgnoreCase("etudiant")) {
			return Type.ETUDIANT;
		}else {
			return Type.SENIOR;
		}
	}
	
}