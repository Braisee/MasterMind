package model;

import controller.ControllerJeu;
import view.Jeu;
import view.StrategyMode;

import java.util.ArrayList;

public class Partie {
    private int nb_manches = 3;
    private int score_partie = 0;
    private int nb_pions_dispos = 8;
    private int nb_tentatives = 10;
    private int nb_pions_combis = 4;
    public void SetScore(int score_manche){
        this.score_partie += score_manche;
    }

    public int GetScore()
    {
        return score_partie;
    }
    public void creerPartie(StrategyMode mode, Plateau plateau)
    {
        Manche manche = new Manche(nb_pions_combis, nb_pions_dispos);
        ControllerJeu control_jeu = new ControllerJeu(manche,nb_pions_combis, nb_tentatives, nb_pions_dispos, mode, plateau);
        Jeu jeu = new Jeu(control_jeu);
        manche.ajouterObserver(jeu);
    }
    public void setPionsDispo(Integer nb)
    {
        this.nb_pions_dispos = nb;
    }
    public void setNbTentatives(Integer nb)
    {
        this.nb_tentatives = nb;
    }
    public void setNbManches(int nb)
    {
        this.nb_manches = nb;
    }
    public void setPionsCombi(int nb)
    {
        this.nb_pions_combis = nb;
    }
}
