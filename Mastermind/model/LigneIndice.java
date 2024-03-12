package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class LigneIndice {

    private ArrayList<INDICE> ligne_indice = new ArrayList<INDICE>();
    private int nb_pion_combis;
    private int score_mal_place = 0;
    private int score_bien_place = 0;
    private int typeAffichage = 0;

    public int getTypeAffichage()
    {
        return this.typeAffichage;
    }
    public LigneIndice(LigneIndice ligneIndice)
    {

        for(INDICE ligne : ligneIndice.getLigneIndice()) {
            this.ligne_indice.add(ligne);
        }
    }
    public LigneIndice(int nb_pions_combis)
    {
        this.nb_pion_combis = nb_pions_combis;

        for(int i = 0; i < nb_pions_combis; i++) {
            this.ligne_indice.add(i, INDICE.VIDE);
        }
    }
    public void setLigneIndice(Combinaison combinaison_joueur, Combinaison combinaison_secret) {
        // Réinitialiser les scores
        this.score_bien_place = 0;
        this.score_mal_place = 0;

        // Réinitialiser les indices
        for (int i = 0; i < nb_pion_combis; i++) {
            this.ligne_indice.set(i, INDICE.VIDE);
        }

        System.out.print("combiSecret : ");
        combinaison_secret.affichageCombi();
        System.out.println();

        // Tableau qui dit si un pion a déjà été trouvé
        ArrayList<Boolean> pionsBienPlaceDejaTrouve = new ArrayList<>(nb_pion_combis);
        for (int i = 0; i < nb_pion_combis; i++) {
            pionsBienPlaceDejaTrouve.add(false);
        }

        ArrayList<Boolean> pionsMalPlaceDejaTrouve = new ArrayList<>(nb_pion_combis);
        for (int i = 0; i < nb_pion_combis; i++) {
            pionsMalPlaceDejaTrouve.add(false);
        }

        // Trouver les indices bien placés
        for (int i = 0; i < nb_pion_combis; i++) {
            if (combinaison_secret.getPion(i).equals(combinaison_joueur.getPion(i))) {
                this.ligne_indice.set(i, INDICE.BIEN_PLACE);
                pionsBienPlaceDejaTrouve.set(i,true);
                score_bien_place++;
            }
        }


        // Trouver les indices mal placés
        for (int i = 0; i < nb_pion_combis; i++) {
            for (int j = 0; j < nb_pion_combis; j++) {
                if (i != j && combinaison_secret.getPion(j).equals(combinaison_joueur.getPion(i)) && this.ligne_indice.get(i) != INDICE.BIEN_PLACE && !pionsBienPlaceDejaTrouve.get(j)) {
                    this.ligne_indice.set(i, INDICE.MAL_PLACE);
                    pionsBienPlaceDejaTrouve.set(j,true);
                    score_mal_place++;
                    break; // Sortir de la boucle intérieure dès qu'un indice mal placé est trouvé
                }
            }
        }
    }
    public int GetScoreIndice()
    {
        return score_mal_place + 3 * score_bien_place;
    }
    public ArrayList<INDICE> getLigneIndice()
    {
        return this.ligne_indice;
    }

    public int getNb_pion_combis()
    {
        return this.nb_pion_combis;
    }
    public void resetLigne()
    {
        this.ligne_indice = new ArrayList<INDICE>();
        for(int i = 0; i < this.nb_pion_combis; i++) {
            this.ligne_indice.add(i, INDICE.VIDE);
        }
    }
    public void afficheLigneIndice()
    {

        for(INDICE i : this.ligne_indice)
        {
            System.out.print(i + " ");

        }
        System.out.println();
    }
}
