class Client{
    private int id;
    private String nom;
    private String prenom;
    public static int nbr = 0;

    public Client(){nbr++;}
    public Client(int id, String nom, String prenom){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        nbr++;
        nbr = nbr+1;
    }

    static void inc(){
        nbr = nbr+1;
    }

    public void affiche(){
        System.out.print("id: "+id+" nom: "+nom+" prenom: "+prenom+" nbr: "+nbr);
    }

    public void modifer(String nom, String prenom){
        this.nom = nom;
        this.prenom = prenom;
    }

}