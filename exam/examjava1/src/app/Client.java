package app;

import java.util.ArrayList;
import java.util.List;

public class Client {

	private long id;
	private String nom;
	private String tel;
	private String login;
	private String pwd;

	private List<Commande> commandes = new ArrayList<>();

	public Client(long id, String nom, String tel, String login, String pwd) {
		this.id = id;
		this.nom = nom;
		this.tel = tel;
		this.login = login;
		this.pwd = pwd;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void addCommande(Commande c) {
		commandes.add(c);
	}

	public void afficherCommandes() {
		for (Commande commande : commandes) {
			System.out.println("Commande : " + commande.getId());
			commande.afficherRepa();
			System.out.println("-----------------------");
			System.out.println("total : " + commande.getTotal());
		}
	}
}
