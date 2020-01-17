package app;

import java.util.ArrayList;
import java.util.List;

public class Commande implements Calcul {

    private long id;
    private double total;

    private List<Repa> repas = new ArrayList<>();

    public Commande(long id) {
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

        for (Repa repa : repas) {
            total += repa.getTotal();
        }

    }

    public void addRepa(Repa repa) {
        repas.add(repa);
    }

    public void afficherRepa() {
        System.out.println("Repas: ");
        for (Repa repa : repas) {
            System.out.println("id : " + repa.getId());
            repa.affichePlatPSupp();
        }
    }

}
