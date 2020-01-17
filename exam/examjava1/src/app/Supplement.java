package app;

public class Supplement {

	private String code;
	private String nom;
	private double prix;

	public Supplement(String code, String nom, double prix) {
		this.code = code;
		this.nom = nom;
		this.prix = prix;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Supplement [code=" + code + ", nom=" + nom + ", prix=" + prix + "]";
	}


	
}
