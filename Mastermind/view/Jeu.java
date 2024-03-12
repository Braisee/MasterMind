package view;

import controller.ControllerJeu;
import model.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Jeu extends JFrame implements ObserverManche {

        JLabel[] combinaison;
        JPanel[] combinaisonJouee;

        JPanel[] ligneIndiceJouee;

        private int combinaisonJoueActu = 0;
        private int ligneIndiceJoueActu = 0;
        private ControllerJeu controllerJeu;

        public Jeu(ControllerJeu control_jeu) {
                // REGLAGE DE BASE

                super("Jeu"); // ou setTitle("My app")

                this.controllerJeu = control_jeu;
                setSize(1100, 750);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setVisible(true);

                setLayout(new BorderLayout());

                JPanel menuPanel = new JPanel();
                JPanel combiPanel = new JPanel();
                JPanel indicePanel = new JPanel();
                JPanel validePanel = new JPanel();


                int nb_pions = control_jeu.getNbPionCombinaison();
                int nb_chances = control_jeu.getNbChance();


                menuPanel.setBackground(Color.GRAY);
                combiPanel.setBackground(Color.RED);
                indicePanel.setBackground(Color.ORANGE);
                validePanel.setBackground(Color.BLUE);

                add(validePanel, BorderLayout.SOUTH);
                add(menuPanel, BorderLayout.WEST);
                add(combiPanel, BorderLayout.CENTER);
                add(indicePanel, BorderLayout.EAST);
                //////////////////////////////

                // PARTIE DU BAS (VALIDE)
                validePanel.setLayout(new GridLayout(0, 1));
                JPanel combiValidePanel = new JPanel();
                JPanel buttonValidePanel = new JPanel();


                this.combinaison = new JLabel[nb_pions];

                for (int i = 0; i < nb_pions; i++) {
                        final int indice = i;
                        JLabel pion = new JLabel();
                        pion.setPreferredSize(new Dimension(50, 50));
                        pion.setBackground(Color.GREEN);
                        pion.setOpaque(true);
                        pion.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                        control_jeu.modificationPion(indice);

                                }
                        });
                        this.combinaison[i] = pion;
                        combiValidePanel.add(pion);
                }
                combiValidePanel.setLayout(new FlowLayout());
                buttonValidePanel.setLayout(new FlowLayout());


                JButton zeroButton = new JButton("Remise à Zéro");
                JButton valideButton = new JButton("Valider");
                JButton nouvelleButton = new JButton("Nouvelle manche");

                JLabel score = new JLabel();
                score.setText("Score : 0");
                valideButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                control_jeu.validerTentative();
                                score.setText("Score : " + control_jeu.getScore());

                        }
                });

                nouvelleButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                control_jeu.nouvelleManche();
                                control_jeu.getManche().ajouterObserver(getThis());
                                nouvelleManche();

                        }
                });

                zeroButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                control_jeu.reinitialiserTentativeCourante();

                        }
                });

                buttonValidePanel.add(zeroButton);
                buttonValidePanel.add(valideButton);
                buttonValidePanel.add(nouvelleButton);

                validePanel.add(combiValidePanel);
                validePanel.add(buttonValidePanel);

                ////////////////////////////

                // PARTIE DE GAUCHE (MENU)
                menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

                JLabel joueurLabel = new JLabel(control_jeu.getNomJoueur());
                JButton quitterButton = new JButton("Quitter");

                quitterButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                System.exit(0);

                        }
                });

                menuPanel.add(joueurLabel);
                menuPanel.add(score);
                menuPanel.add(quitterButton);
                menuPanel.add(Box.createVerticalStrut(20));

                //////////////////

                // COMBINAISON

                this.combinaisonJouee = new JPanel[nb_chances];


                combiPanel.setLayout(new GridLayout(nb_chances, 0));

                for (int i = nb_chances - 1; i >= 0; i--) {
                        JPanel comb1 = new JPanel();
                        comb1.setOpaque(true);
                        comb1.setBackground(Color.BLACK);
                        comb1.setLayout(new FlowLayout());

                        Border border = new LineBorder(Color.WHITE, 2);
                        comb1.setBorder(border);


                        combiPanel.add(comb1);

                        this.combinaisonJouee[i] = comb1;
                }


                //////////////////

                // Ligne Indice

                this.ligneIndiceJouee = new JPanel[nb_chances];
                indicePanel.setLayout(new GridLayout(nb_chances, 0));


                for (int i = nb_chances - 1; i >= 0; i--) {
                        JPanel ind1 = new JPanel();
                        ind1.setOpaque(true);
                        ind1.setBackground(Color.GRAY);
                        ind1.setLayout(new FlowLayout());

                        Border border = new LineBorder(Color.WHITE, 2);
                        ind1.setBorder(border);


                        indicePanel.add(ind1);
                        indicePanel.revalidate();
                        indicePanel.repaint();

                        ligneIndiceJouee[i] = ind1;
                }
        }

        public Jeu getThis(){
                return this;
        }

        public void nouvelleManche()
        {
                for(int i = 0 ; i < this.combinaisonJouee.length ; i++)
                {
                        this.combinaisonJouee[i].removeAll();
                        this.ligneIndiceJouee[i].removeAll();

                        this.combinaisonJouee[i].revalidate();
                        this.combinaisonJouee[i].repaint();

                        this.ligneIndiceJouee[i].revalidate();
                        this.ligneIndiceJouee[i].repaint();
                }

                this.combinaisonJoueActu = 0;
                this.ligneIndiceJoueActu = 0;

                for(JLabel pion : this.combinaison)
                {
                        pion.setBackground(Color.GREEN);
                }

        }


        @Override
        public void majPlateauValidationTentative(ArrayList<Combinaison> tentatives, ArrayList<LigneIndice> indices_tentatives, StrategyMode mode) {

                System.out.println("Tentative : ");
                for (Combinaison combi : tentatives) {
                        combi.affichageCombi();
                }

                System.out.println("Ligne Indice : ");
                for (LigneIndice ligne : indices_tentatives) {
                        ligne.afficheLigneIndice();
                }


                // Partie combinaison
                Combinaison tentative = tentatives.get(this.combinaisonJoueActu);

                for (PION pion : tentative.getPions()) {
                        JLabel pionLabel = new JLabel();
                        pionLabel.setPreferredSize(new Dimension(20, 20));
                        pionLabel.setBackground(couleurDuPion(pion));
                        pionLabel.setOpaque(true);

                        this.combinaisonJouee[this.combinaisonJoueActu].add(pionLabel);
                        this.combinaisonJouee[this.combinaisonJoueActu].revalidate();
                        this.combinaisonJouee[this.combinaisonJoueActu].repaint();
                }
                this.combinaisonJoueActu++;

                // Partie ligne indice
                LigneIndice ligneIndice = indices_tentatives.get(this.ligneIndiceJoueActu);
                mode.AfficherIndice(ligneIndice, this.ligneIndiceJouee[this.ligneIndiceJoueActu]);
                int ligneTypeAffichage = ligneIndice.getTypeAffichage();

                this.ligneIndiceJoueActu++;


        }



        @Override
        public void majPlateauPion(Combinaison combinaison) {
                for(int i = 0 ; i < combinaison.getCount() ; i++)
                {
                        this.combinaison[i].setBackground(couleurDuPion(combinaison.getPion(i)));
                }
        }
        public Color couleurDuPion(PION pion) {
                switch (pion) {
                        case BLEU:
                                return Color.BLUE;
                        case ORANGE:
                                return Color.ORANGE;
                        case ROSE:
                                return Color.PINK;
                        case ROUGE:
                                return Color.RED;
                        case GRIS:
                                return Color.GRAY;
                        case CYAN:
                                return Color.CYAN;
                        case JAUNE:
                                return Color.YELLOW;
                        case VERT:
                                return Color.GREEN;

                }
                return Color.BLACK;
        }
}
