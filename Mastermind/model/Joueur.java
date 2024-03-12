package model;

public class Joueur {
    private String nom;
    private Integer nbParties = 0;
    public void ajouterNbPartie()
    {
        this.nbParties++;
    }
    public String getNom()
    {
        return this.nom;
    }
    public void setNom(String nom)
    {
        this.nom = nom;
    }
}
