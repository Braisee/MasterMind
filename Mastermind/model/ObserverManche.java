package model;

import view.StrategyMode;

import java.util.ArrayList;

public interface ObserverManche {
    public void majPlateauValidationTentative(ArrayList<Combinaison> tentatives, ArrayList<LigneIndice> indices_tentatives, StrategyMode mode);
    public void majPlateauPion(Combinaison combinaison);
}

