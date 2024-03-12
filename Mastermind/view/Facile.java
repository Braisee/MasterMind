package view;

import model.INDICE;
import model.LigneIndice;
import model.PION;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Facile implements StrategyMode {
    @Override
    public void AfficherIndice(LigneIndice ligneIndice, JPanel panel) {

        ArrayList<INDICE> indices = ligneIndice.getLigneIndice();
        for(INDICE indice : indices)
        {
            System.out.print(indice + " | ");

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
