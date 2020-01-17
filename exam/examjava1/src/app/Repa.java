package app;

import java.util.ArrayList;
import java.util.List;

public class Repa implements Calcul {

    private long id;
    private double total;

    private PlatPrincipal platPrincipal;
    private List<Supplement> supplements = new ArrayList<>();
    // private Set<Supplement> supplements = new ;

    public Repa(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotal() {
        CalculerTotal();
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public void CalculerTotal() {
        total = 0.0;
        for (Supplement supplement : supplements) {
            total += supplement.getPrix();
        }

        total += platPrincipal.getTotal();

    }

    public void addSupplement(Supplement supp) {
        supplements.add(supp);
    }

    public void setPlatPrincipal(PlatPrincipal platPrincipal) {
        this.platPrincipal = platPrincipal;
    }

    public void affichePlatPSupp() {
        System.out.println("le Plate Princepal : " + platPrincipal.getNom());
        platPrincipal.afficherIngs();
        System.out.println("Supplement:");
        for (Supplement supplement : supplements) {
            System.out.println(supplement.getNom() + " " + supplement.getPrix());
        }
    }

}
