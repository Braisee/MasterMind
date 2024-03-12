package view;

import controller.ControllerGlobal;
import model.Combinaison;
import model.ObserverManche;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Demarrage extends JFrame {
    private int indiceParametre = 1;
    private int valeurParametre = 10;

    public Demarrage(ControllerGlobal controleurDemarrage) {
        super("Mastermind"); // Titre "Mastermind"
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Utilisez BorderLayout pour le conteneur principal
        setLayout(new BorderLayout());

        // Ajoutez le titre en haut de la fenêtre
        JLabel titreLabel = new JLabel("Mastermind", SwingConstants.CENTER);
        titreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titreLabel, BorderLayout.NORTH);

        // Créez un panneau pour les boutons de mode
        JPanel boutonModePanel = new JPanel();
        boutonModePanel.setBackground(Color.GRAY);
        boutonModePanel.setLayout(new FlowLayout());

        // Créez des boutons radio pour les modes
        JRadioButton boutonClassique = new JRadioButton("Classique");
        JRadioButton boutonNumerique = new JRadioButton("Numérique");
        JRadioButton boutonFacile = new JRadioButton("Facile");

        ButtonGroup groupeBoutonsMode = new ButtonGroup();
        groupeBoutonsMode.add(boutonClassique);
        groupeBoutonsMode.add(boutonFacile);
        groupeBoutonsMode.add(boutonNumerique);

        // Ajoutez des écouteurs d'événements aux boutons radio
        boutonClassique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleurDemarrage.setClassique();
            }
        });

        boutonNumerique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleurDemarrage.setNumerique();
            }
        });

        boutonFacile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleurDemarrage.setFacile();
            }
        });

        // Ajoutez les boutons radio au panneau
        boutonModePanel.add(boutonClassique);
        boutonModePanel.add(boutonFacile);
        boutonModePanel.add(boutonNumerique);

        // Ajoutez le panneau des boutons de mode au centre du BorderLayout
        add(boutonModePanel, BorderLayout.CENTER);

        // Créez un panneau pour les paramètres
        JPanel parametresPanel = new JPanel();
        parametresPanel.setLayout(new GridLayout(4, 5, 10, 10));

        // Ajoutez les composants pour les paramètres
        parametresPanel.add(new JLabel("Nom du joueur:"));
        TextArea texteJoueur = new TextArea(2, 20);
        texteJoueur.setText("");
        parametresPanel.add(texteJoueur);

        JButton boutonValiderNom = new JButton("Valider Nom");
        boutonValiderNom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                texteJoueur.setEditable(false);
                boutonValiderNom.setEnabled(false);
            }
        });
        parametresPanel.add(boutonValiderNom);

        JButton commencerPartie = new JButton("Jouer");
        commencerPartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!texteJoueur.getText().equals("")) {
                    controleurDemarrage.GetPlateau().setNomJoueur(texteJoueur.getText());
                    controleurDemarrage.nouvelleManche();
                }
            }
        });
        parametresPanel.add(commencerPartie);

        JLabel labelNomParam = new JLabel("Nombre de tentatives");
        parametresPanel.add(labelNomParam);

        JLabel labelValParam = new JLabel(String.valueOf(valeurParametre));
        parametresPanel.add(labelValParam);

        JButton plus = new JButton("Plus");
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incrementerParametre(controleurDemarrage, labelNomParam, labelValParam);
            }
        });

        JButton moins = new JButton("Moins");
        moins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decrementerParametre(controleurDemarrage, labelNomParam, labelValParam);
            }
        });

        JButton changerTypeParametresGauche = new JButton("<");
        changerTypeParametresGauche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indiceParametre > 1) {
                    indiceParametre--;
                    majParamValeurs(controleurDemarrage, labelNomParam, labelValParam);
                }
            }
        });

        JButton changerTypeParametresDroite = new JButton(">");
        changerTypeParametresDroite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indiceParametre < 4) {
                    indiceParametre++;
                    majParamValeurs(controleurDemarrage, labelNomParam, labelValParam);
                }
            }
        });

        // Ajoutez les composants pour les paramètres au panneau
        parametresPanel.add(new JLabel("")); // Espace vide
        parametresPanel.add(new JLabel("")); // Espace vide
        parametresPanel.add(moins);
        parametresPanel.add(plus);
        parametresPanel.add(changerTypeParametresGauche);
        parametresPanel.add(changerTypeParametresDroite);

        // Ajoutez le panneau des paramètres au bas du BorderLayout
        add(parametresPanel, BorderLayout.SOUTH);

        // Rendre la fenêtre en plein écran
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Affichez la fenêtre
        setVisible(true);
    }

    private void majParamValeurs(ControllerGlobal controleurDemarrage, JLabel labelNomParam, JLabel labelValParam) {
        switch (indiceParametre) {
            case 1:
                valeurParametre = 10;
                controleurDemarrage.setNbTentatives(valeurParametre);
                labelNomParam.setText("Nombre de tentatives");
                break;
            case 2:
                valeurParametre = 3;
                controleurDemarrage.setNbManches(valeurParametre);
                labelNomParam.setText("Nombre de manches");
                break;
            case 3:
                valeurParametre = 8;
                controleurDemarrage.setPionsDispo(valeurParametre);
                labelNomParam.setText("Nombre de pions disponibles");
                break;
            case 4:
                valeurParametre = 4;
                controleurDemarrage.setPionsCombi(valeurParametre);
                labelNomParam.setText("Nombre de pions par combinaison");
                break;
        }
        labelValParam.setText(String.valueOf(valeurParametre));
    }

    private void incrementerParametre(ControllerGlobal controleurDemarrage, JLabel labelNomParam, JLabel labelValParam) {
        switch (indiceParametre) {
            case 1:
                if (valeurParametre < 12) {
                    valeurParametre++;
                    controleurDemarrage.setNbTentatives(valeurParametre);
                }
                break;
            case 2:
                if (valeurParametre < 5) {
                    valeurParametre++;
                    controleurDemarrage.setNbManches(valeurParametre);
                }
                break;
            case 3:
                if (valeurParametre < 8) {
                    valeurParametre++;
                    controleurDemarrage.setPionsDispo(valeurParametre);
                }
                break;
            case 4:
                if (valeurParametre < 6) {
                    valeurParametre++;
                    controleurDemarrage.setPionsCombi(valeurParametre);
                }
                break;
        }
        labelValParam.setText(String.valueOf(valeurParametre));
    }

    private void decrementerParametre(ControllerGlobal controleurDemarrage, JLabel labelNomParam, JLabel labelValParam) {
        switch (indiceParametre) {
            case 1:
                if (valeurParametre > 2) {
                    valeurParametre--;
                    controleurDemarrage.setNbTentatives(valeurParametre);
                }
                break;
            case 2:
                if (valeurParametre > 1) {
                    valeurParametre--;
                    controleurDemarrage.setNbManches(valeurParametre);
                }
                break;
            case 3:
                if (valeurParametre > 4) {
                    valeurParametre--;
                    controleurDemarrage.setPionsDispo(valeurParametre);
                }
                break;
            case 4:
                if (valeurParametre > 2) {
                    valeurParametre--;
                    controleurDemarrage.setPionsCombi(valeurParametre);
                }
                break;
        }
        labelValParam.setText(String.valueOf(valeurParametre));
    }
}