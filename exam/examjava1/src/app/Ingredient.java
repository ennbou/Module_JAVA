package app;

public class Ingredient {
    private String code;
    private String nom;
    private double prixUnitaire;
    private String unite;

    public Ingredient(String code, String nom, double prixUnitaire, String unite) {
        this.code = code;
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
        this.unite = unite;
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

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
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
        return "Ingredient [code=" + code + ", nom=" + nom + ", prixUnitaire=" + prixUnitaire + ", unite=" + unite
                + "]";
    }

}
