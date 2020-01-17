package app;

public class App {
    public static void main(String[] args) throws Exception {

        IngredientDAO ingredDAO = new IngredientDAOIMPL();
        SupplementDAO supplDao = new SupplementDAOIMPL();
        PlatPrincipalDAO platDao = new PlatPrincipalDAOIMPL();
        Data data = Data.getInstance();
        Client client = data.getClient("baba", "baba");

        System.out.println("Bienvenue " + client.getNom());

        for (Ingredient ing : ingredDAO.getAll()) {
            System.out.println(ing);
        }

        for (Supplement supp : supplDao.getAll()) {
            System.out.println(supp);
        }

        for (PlatPrincipal plat : platDao.getAll()) {
            System.out.println(plat);
        }

        Commande c1 = new Commande(1);

        Repa r1 = new Repa(1);
        Repa r2 = new Repa(2);

        PlatPrincipal p1 = platDao.getOne("01");
        PlatPrincipal p2 = platDao.getOne("02");

        Supplement s1 = supplDao.getOne("01");
        Supplement s2 = supplDao.getOne("02");

        Supplement s3 = supplDao.getOne("03");
        Supplement s4 = supplDao.getOne("04");

        IngredientPlat ingp1 = new IngredientPlat(250, ingredDAO.getOne("01"));
        IngredientPlat ingp2 = new IngredientPlat(1, ingredDAO.getOne("04"));

        IngredientPlat ingp3 = new IngredientPlat(250, ingredDAO.getOne("03"));
        IngredientPlat ingp4 = new IngredientPlat(1, ingredDAO.getOne("05"));
        IngredientPlat ingp5 = new IngredientPlat(1, ingredDAO.getOne("06"));
        IngredientPlat ingp6 = new IngredientPlat(1, ingredDAO.getOne("07"));

        r1.addSupplement(s1);
        r1.addSupplement(s2);

        r2.addSupplement(s3);
        r2.addSupplement(s4);

        p1.addIngedient(ingp1);
        p1.addIngedient(ingp2);


        p2.addIngedient(ingp3);
        p2.addIngedient(ingp4);
        p2.addIngedient(ingp5);
        p2.addIngedient(ingp6);

        r1.setPlatPrincipal(p1);

        r2.setPlatPrincipal(p2);

        c1.addRepa(r1);
        c1.addRepa(r2);

        client.addCommande(c1);

        client.afficherCommandes();

    }
}
