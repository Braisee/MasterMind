package model;

import java.util.ArrayList;

public class Combinaison {
    private int nb_pions_combis;
    private ArrayList<PION> pions = new ArrayList<PION>();

    public Combinaison(Combinaison combinaison)
    {
        this.nb_pions_combis = combinaison.getCount();
        for(PION pion : combinaison.getPions())
        {
            this.pions.add(pion);
        }
    }

    public Combinaison(int nb_pions_combis)
    {
        this.nb_pions_combis = nb_pions_combis;
    }
    public void choixCouleur(PION pion, int index)
    {
        this.pions.set(index, pion);
    }
    public void setCombinaisonSecrete(int nb_pions_dispos)
    {
        for(int i = 0; i < nb_pions_dispos; i++)
        {
            PION pion;
            int n = (int)(Math.random() * nb_pions_dispos);
            switch (n) {
                case 0 -> pion = PION.VERT;
                case 1 -> pion = PION.BLEU;
                case 2 -> pion = PION.ROSE;
                case 3 -> pion = PION.ROUGE;
                case 4 -> pion = PION.JAUNE;
                case 5 -> pion = PION.GRIS;
                case 6 -> pion = PION.CYAN;
                case 7 -> pion = PION.ORANGE;
                default -> pion = PION.BLEU;
            };
            this.pions.add(pion);
        }
    }
    public PION getPion(int index)
    {
        return this.pions.get(index);
    }
    public int getCount()
    {
        return this.nb_pions_combis;
    }
    public void remplir(int nb_pion_combinaison)
    {
        for(int i = 0 ; i < nb_pion_combinaison ; i++)
        {
            this.pions.add(PION.VERT);
        }
    }
    public ArrayList<PION> getPions()
    {
        return this.pions;
    }

    public void affichageCombi()
    {
        for(PION p : this.pions)
        {
            System.out.print(p + " ; ");
        }
        System.out.println();
    }
}
