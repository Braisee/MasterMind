import controller.ControllerGlobal;
import controller.ControllerJeu;
import model.*;
import view.*;

import java.util.ArrayList;

public class MainMastermind {
    public static void main(String[] args) {
        FactoryMode factory = new FactoryMode();
        StrategyMode mode = factory.facile();
        Plateau plateau = new Plateau();
        ControllerGlobal controller_global = new ControllerGlobal(plateau);
        Joueur joueur = new Joueur();
        plateau.setJoueur(joueur);
        plateau.creerPartie();
        Demarrage demarrage = new Demarrage(controller_global);

    }
}
