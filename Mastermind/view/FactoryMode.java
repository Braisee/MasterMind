package view;

import view.Classique;

public class FactoryMode {

    public StrategyMode classique()
    {
        return new Classique();
    }

    public StrategyMode facile()
    {
        return new Facile();
    }

    public StrategyMode numerique()
    {
        return new Numerique();
    }
}
