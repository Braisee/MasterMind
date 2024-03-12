package model;

import view.Classique;
import view.StrategyMode;

import java.util.ArrayList;
import java.util.Map;

public class Manche {
    private int score = 0;
    private int nb_pions_dispo;
    private ArrayList<Combinaison> tentatives = new ArrayList<Combinaison>();
    private ArrayList<LigneIndice> indices_tentatives = new ArrayList<LigneIndice>();
    private Combinaison combinaison_secrete;
    private Combinaison combinaison_courante;
    private LigneIndice ligne_indice;
    private StrategyMode mode = new Classique();
    private ArrayList<ObserverManche> observer_pions = new ArrayList<ObserverManche>();

    public Manche(int nb_pions_combis, int nb_pions_dispo)
    {
        this.ligne_indice = new LigneIndice(nb_pions_combis);
        this.combinaison_courante = new Combinaison(nb_pions_combis);
        this.combinaison_secrete = new Combinaison(nb_pions_combis);
        this.combinaison_secrete.setCombinaisonSecrete(nb_pions_combis);
        this.nb_pions_dispo = nb_pions_dispo;
        this.combinaison_courante.remplir(nb_pions_combis);

    }
    public void SetMode(StrategyMode mode)
    {
        this.mode = mode;
    }
    public void ajouterObserver(ObserverManche observer_manche)
    {
        this.observer_pions.add(observer_manche);
    }
    public void notifierChangementAffichageTentativeCourante()
    {
        for(ObserverManche observer : this.observer_pions)
        {
            observer.majPlateauPion(this.combinaison_courante);
        }

    }
    public void notifierChangementAffichageValidationTentative()
    {
        for(ObserverManche observer : this.observer_pions)
        {
            observer.majPlateauValidationTentative(this.tentatives, this.indices_tentatives, this.mode);
        }

    }
    public Boolean getVictoireDefaite()
    {
        for(int i  = 0; i < this.ligne_indice.getLigneIndice().size(); i++)
        {
            if(this.ligne_indice.getLigneIndice().get(i) != INDICE.BIEN_PLACE)
            {
                return false;
            }
        }
        return true;
    }
    public void ValiderCombinaison()
    {

        this.tentatives.add(new Combinaison(this.combinaison_courante));

        this.ligne_indice.afficheLigneIndice();

        this.ligne_indice.resetLigne();

        this.ligne_indice.afficheLigneIndice();

        this.ligne_indice.setLigneIndice(combinaison_courante,combinaison_secrete);

        this.indices_tentatives.add(new LigneIndice(this.ligne_indice));
        notifierChangementAffichageValidationTentative();
        if(getVictoireDefaite())
        {
            score = ligne_indice.GetScoreIndice();
            Classique classique = new Classique();
            if(this.mode == classique)
            {
                score = score+4;
            }
        }
    }
    public int GetScore()
    {
        return this.score;
    }
    public void reinitiliserCombinaisonCourante()
    {
        for(int i = 0; i< this.combinaison_courante.getCount() ; i++)
        {
            this.combinaison_courante.choixCouleur(PION.VERT,i);
        }
        notifierChangementAffichageTentativeCourante();
    }
    public void changementCouleurPion(int index){
        PION prochaineCouleur = prochaineCouleur(this.combinaison_courante.getPion(index));
        this.combinaison_courante.choixCouleur(prochaineCouleur,index);

        notifierChangementAffichageTentativeCourante();
    }

    public PION prochaineCouleur(PION pion)
    {
        PION[] valeurs = PION.values();

        int pionVal = pion.ordinal();
        if(pionVal == nb_pions_dispo-1)
        {
            pionVal = 0;
        }
        else
        {
            pionVal+=1;
        }
        return valeurs[pionVal];
    }
}
