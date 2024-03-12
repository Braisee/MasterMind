package controller;

import model.LigneIndice;
import model.Manche;
import model.Plateau;
import view.*;
import model.Partie;

public class ControllerGlobal {

    private Plateau plateau;
    private FactoryMode factoryMode;
    private StrategyMode mode;
    public ControllerGlobal(Plateau plateau)
    {
        this.plateau = plateau;
        this.factoryMode = new FactoryMode();
    }
    public void setFacile()
    {
        this.mode = factoryMode.facile();
    }
    public Plateau GetPlateau(){
        return this.plateau;
    }
    public void setClassique()
    {
        this.mode =  factoryMode.classique();
    }
    public void setNumerique()
    {
        this.mode = factoryMode.numerique();
    }
    public void nouvelleManche()
    {
        this.plateau.getPartie().creerPartie(this.mode, this.plateau);
    }
    public void setNbTentatives(int nb)
    {
        this.plateau.getPartie().setNbTentatives(nb);
    }

    public void setNbManches(int nb)
    {
        this.plateau.getPartie().setNbManches(nb);
    }
    public void setPionsDispo(int nb)
    {
        this.plateau.getPartie().setPionsDispo(nb);
    }
    public void setPionsCombi(int nb)
    {
        this.plateau.getPartie().setPionsCombi(nb);
    }
}
