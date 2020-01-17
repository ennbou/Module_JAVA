package app;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class IngredientPlat {

    private SimpleIntegerProperty quantite;
    private Ingredient ingredient;
    private SimpleDoubleProperty total;

    public IngredientPlat(int quantite, Ingredient ingredient) {
        this.quantite = new SimpleIntegerProperty(quantite);
        this.ingredient = ingredient;
        total = new SimpleDoubleProperty();
        total.bind(quantiteProperty().multiply(ingredient.getPrixUnitaire()));
    }

    public int getQuantite() {
        return quantite.get();
    }

    public void setQuantite(int quantite) {
        this.quantite.set(quantite);
    }

    public IntegerProperty quantiteProperty() {
        return quantite;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    public double getTotal() {
        return total.get();
    }

}