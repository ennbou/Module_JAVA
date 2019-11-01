public class Program{
    public static void main(String args[]){
        
        Client c1 = new Client(1, "nom1", "prenom1");
        c1.affiche();
        System.out.println("");
        Client c2 = new Client(2, "nom2", "prenom2");
        c2.affiche();
        System.out.println("");
        System.out.println("nombre des clients: "+Client.nbr);
        Client.inc();
    }
}