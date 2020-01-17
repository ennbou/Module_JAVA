package app;

import java.util.HashMap;
import java.util.Map;

public class PlatPrincipal implements Calcul {

	private String code;
	private String nom;
	private double total;

	Map<Ingredient, IngredientPlat> ingPlats = new HashMap<>();

	public PlatPrincipal(String code, String nom) {
		this.code = code;
		this.nom = nom;
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
		IngredientPlat ip;
		for (Map.Entry<Ingredient, IngredientPlat> ingPlat : ingPlats.entrySet()) {
			ip = ingPlat.getValue();
			total += ip.getQuantite() * ip.getIngredient().getPrixUnitaire();
		}
	}

	@Override
	public String toString() {
		return "PlatPrincipal [code=" + code + ", ingPlats=" + ingPlats + ", nom=" + nom + ", total=" + total + "]";
	}

	public void addIngedient(IngredientPlat ing) {
		ingPlats.put(ing.getIngredient(), ing);
	}

	public void afficherIngs() {
		Ingredient ig;
		System.out.println("ingredient :");
		for (Map.Entry<Ingredient, IngredientPlat> ing : ingPlats.entrySet()) {
			ig = ing.getKey();
			System.out.println(ig.getNom() + ", Qte " + ing.getValue().getQuantite() + " " + ig.getUnite());
		}
	
	}

}