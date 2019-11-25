package app;

import java.util.ArrayList;

abstract class Salarie {
    protected String nom;
    protected String prenom;
    protected String matricule;
    protected int nbrEnfant;
    protected double salaireBase;
    protected double salaire;

    public Salarie(String nom, String prenom, String matricule, int nbrEnfant, double salaireBase) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.nbrEnfant = nbrEnfant;
        this.salaireBase = salaireBase;
    }

    public abstract void calculerSalaire();
}

class Projet{

    private String nom;
    private double montantTotal;
    private double ind;
    private Date date;

    public Projet(String nom, double x, double y) {
        this.nom = nom;
        this.x = x;
        this.y = y;
    }

    
}


class Ingenieur extends Salarie {
    private String email;
    private List<Projet> projets = new ArrayList();

    public Ingenieur(String nom, String prenom, String matricule, int nbrEnfant, double salaireBase, String email) {
        super(nom, prenom, matricule, nbrEnfant, salaireBase);
        this.email = email;
    }

    @Override
    public void calculerSalaire(){
        this.salaire = 10;
    }
}

class Technicien extends Salarie{
    private String numberPhone;
    private static double tx = 50.0;
    private List<Map<Date,Integer>> houreTravailler = new ArrayList();

    public Technicien(String nom, String prenom, String matricule, int nbrEnfant, double salaireBase, String numberPhone){
        super(nom, prenom, matricule, nbrEnfant, salaireBase);
        this.numberPhone = numberPhone;
    }

    @Override
    public void calculerSalaire(){
        this.salaire = 20;
    }

}

public class App {
    public static void main(String[] args) throws Exception {

    }
}