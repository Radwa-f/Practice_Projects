package ma.tp2_pizza_mobile.classes;

public class Produit {

    private int id;
    private String nom;
    private int nbrIngredients;
    private int photo;
    private int duree;
    private String detailsIngred;
    private String description;
    private String preparation;
    private static int compt;

    public Produit (String nom, int nbrIngredients, int photo, int duree, String detailsIngred, String description, String preparation){
        this.nom = nom;
        this.nbrIngredients = nbrIngredients;
        this.photo = photo;
        this.duree = duree;
        this.detailsIngred = detailsIngred;
        this.description = description;
        this.preparation = preparation;

        compt++;
        this.id=compt;

    }
    public Produit (){
        compt++;
        this.id=compt;

    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getNbrIngredients() {
        return nbrIngredients;
    }

    public int getPhoto() {
        return photo;
    }

    public float getDuree() {
        return duree;
    }

    public String getDetailsIngred() {
        return detailsIngred;
    }

    public String getDescription() {
        return description;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNbrIngredients(int nbrIngredients) {
        this.nbrIngredients = nbrIngredients;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setDetailsIngred(String detailsIngred) {
        this.detailsIngred = detailsIngred;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    @Override
    public String toString() {
        return "Produit : " +
                "Id=" + id +
                ", Nom='" + nom + '\'' +
                ", Nombre d'ingredients=" + nbrIngredients +
                ", Photo=" + photo +
                ", Duree=" + duree +
                ", Details des ingredients='" + detailsIngred + '\'' +
                ", Description='" + description + '\'' +
                ", Preparation='" + preparation + '\'' ;
    }
}
