package view;

import model.INDICE;
import model.LigneIndice;

import javax.swing.*;
import java.util.ArrayList;

public class Numerique implements StrategyMode {
    @Override
    public void AfficherIndice(LigneIndice ligneIndice, JPanel panel) {

        ArrayList<INDICE> indices_actu = ligneIndice.getLigneIndice();

        int nb_bien_places = 0;
        int nb_mal_places = 0;


        for(INDICE ind : indices_actu)
        {
            if(ind == INDICE.BIEN_PLACE)
            {
                nb_bien_places += 1;
            }
            else if(ind == INDICE.MAL_PLACE)
            {
                nb_mal_places += 1;
            }
        }
        JLabel bienPlace = new JLabel("" + nb_bien_places);

        JLabel malPlace = new JLabel("" + nb_mal_places);

        panel.add(bienPlace);

        panel.add(malPlace);

        System.out.println(nb_bien_places + " " + nb_mal_places);


    }
}
