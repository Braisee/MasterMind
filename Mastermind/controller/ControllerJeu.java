package controller;
import model.Manche;
import model.Plateau;
import view.FinPartie;
import view.StrategyMode;

public class ControllerJeu {
    private Manche manche;
    private Plateau plateau;
    private int nb_chances;
    private int nb_pion_combinaison;
    private  int nb_pions_dispos;
    private StrategyMode mode;
    public ControllerJeu(Manche manche, int nb_pion_combinaison, int nb_chances, int nb_pions_dispos, StrategyMode mode, Plateau plateau)
    {
        this.manche = manche;
        this.mode = mode;
        this.plateau = plateau;
        manche.SetMode(this.mode);
        this.nb_pion_combinaison = nb_pion_combinaison;
        this.nb_chances = nb_chances;
        this.nb_pions_dispos = nb_pions_dispos;
    }

    public Manche getManche(){
        return this.manche;
    }
    public String getNomJoueur()
    {
        return plateau.getNomJoueur();
    }
    public void modificationPion(int index){
        manche.changementCouleurPion(index);
    }
    public void validerTentative() {
        this.manche.ValiderCombinaison();
        if(manche.getVictoireDefaite())
        {
            FinPartie finPartie = new FinPartie(getScore(), true);
        }

    }
    public int getScore()
    {
        plateau.getPartie().SetScore(manche.GetScore());
        return plateau.getPartie().GetScore();
    }
    public void reinitialiserTentativeCourante()
    {
        this.manche.reinitiliserCombinaisonCourante();
    }
    public void nouvelleManche()
    {
        this.manche = new Manche(this.nb_pion_combinaison, this.nb_pions_dispos);

    }
    public int getNbPionCombinaison()
    {
        return this.nb_pion_combinaison;
    }

    public int getNbChance()
    {
        return this.nb_chances;
    }
}
