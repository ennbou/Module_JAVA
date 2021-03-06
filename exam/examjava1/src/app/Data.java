package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface DataInterface {
	public Map<String, Ingredient> getDataIngedients();

	public Map<String, Supplement> getDataSupplements();

	public Client getClient(String login, String pwd);

	public Map<String, PlatPrincipal> getDataPlats();
}

public class Data implements DataInterface {
	private static Data data = null;
	List<Client> clientsData = new ArrayList<Client>();

	List<Ingredient> ingredientsData;
	List<Supplement> supplementsData;
	List<PlatPrincipal> platsData;

	private Data() {
		ingredientsData = new ArrayList<>();
		supplementsData = new ArrayList<>();
		platsData = new ArrayList<>();

		ingredientsData.add(new Ingredient("01", "Viande", 0.14, "gramme"));
		ingredientsData.add(new Ingredient("02", "Poulet", 0.1, "gramme"));
		ingredientsData.add(new Ingredient("03", "Poisson", 0.16, "gramme"));
		ingredientsData.add(new Ingredient("04", "Pruneaux", 0.09, "gramme"));
		ingredientsData.add(new Ingredient("05", "Carrote", 0.05, "gramme"));
		ingredientsData.add(new Ingredient("06", "Pomme de terre", 0.03, "gramme"));
		ingredientsData.add(new Ingredient("07", "Olive", 0.07, "gramme"));

		supplementsData.add(new Supplement("01", "Frites", 11));
		supplementsData.add(new Supplement("02", "Boisson", 12));
		supplementsData.add(new Supplement("03", "Jus d'orange", 13));
		supplementsData.add(new Supplement("04", "Salade marocaine", 14));

		clientsData.add(new Client(1, "Ali baba", "0623232323", "baba", "baba"));
		clientsData.add(new Client(2, "Baba babi", "0623434343", "baba", "babi"));
		clientsData.add(new Client(3, "Mama Mami", "063535353", "mama", "mama"));

		platsData.add(new PlatPrincipal("01", "Tajine de viande & Pruneaux"));
		platsData.add(new PlatPrincipal("02", "Tajine de poulet & légumes"));
		platsData.add(new PlatPrincipal("03", "Tajine de poisson & légumes"));

	}

	public static Data getInstance() {
		if (data == null)
			data = new Data();
		return data;
	}

	@Override
	public Map<String, Ingredient> getDataIngedients() {
		Map<String, Ingredient> map = new HashMap<>();
		for (Ingredient ing : ingredientsData) {
			map.put(ing.getCode(), ing);
		}
		return map;
	}

	@Override
	public Map<String, Supplement> getDataSupplements() {
		Map<String, Supplement> map = new HashMap<>();
		for (Supplement supp : supplementsData) {
			map.put(supp.getCode(), supp);
		}
		return map;
	}

	@Override
	public Client getClient(String login, String pwd) {
		for (Client c : clientsData) {
			if (c.getLogin().equals(login) && c.getPwd().equals(pwd))
				return c;
		}
		return null;
	}

	@Override
	public Map<String, PlatPrincipal> getDataPlats() {
		Map<String, PlatPrincipal> map = new HashMap<>();
		for (PlatPrincipal plat : platsData) {
			map.put(plat.getCode(), plat);
		}
		return map;
	}

}
