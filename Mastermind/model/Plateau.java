package model;

public class Plateau {
    private Partie partie;
    private Joueur joueur;
    public void creerPartie()
    {
        this.partie = new Partie();
        this.joueur.ajouterNbPartie();
    }
    public void setJoueur(Joueur joueur)
    {
        this.joueur = joueur;
    }
    public String getNomJoueur(){
        return this.joueur.getNom();
    }
    public void setNomJoueur(String nom)
    {
        this.joueur.setNom(nom);
    }
    public Partie getPartie()
    {
        return this.partie;
    }
}
