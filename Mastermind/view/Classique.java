package view;

import model.INDICE;
import model.LigneIndice;
import model.PION;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Classique implements StrategyMode {
    @Override
    public void AfficherIndice(LigneIndice ligneIndice, JPanel panel) {

        ArrayList<INDICE> indices_actu = ligneIndice.getLigneIndice();
        ArrayList<INDICE> indices_nouv = new ArrayList<INDICE>();
        LigneIndice ligne = new LigneIndice(ligneIndice.getNb_pion_combis());
        int nb_bien_places = 0;
        int nb_mal_places = 0;
        int taille = indices_actu.size();

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

        for(int i = 0; i < nb_bien_places ; i++)
        {
            indices_nouv.add(INDICE.BIEN_PLACE);
        }
        for(int i = 0; i < nb_mal_places ; i++)
        {
            indices_nouv.add(INDICE.MAL_PLACE);
        }
        for(int i = 0 ; i < taille - (nb_mal_places + nb_bien_places) ; i++)
        {
            indices_nouv.add(INDICE.VIDE);
        }

        for(INDICE indice : indices_nouv)
        {
            System.out.print(indice + " | ");
            JLabel indiceLabel = new JLabel();
            indiceLabel.setPreferredSize(new Dimension(20, 20));
            indiceLabel.setBackground(couleurDeIndice(indice));
            indiceLabel.setOpaque(true);
            panel.add(indiceLabel);
        }

        panel.revalidate();
        panel.repaint();

    }

    public Color couleurDeIndice(INDICE indice)
    {
        switch (indice){
            case BIEN_PLACE:
                return Color.BLACK;

            case MAL_PLACE:
                return Color.WHITE;
        }
        return Color.GRAY;
    }

}
