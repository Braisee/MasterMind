package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinPartie extends JFrame {

    public FinPartie(int score, boolean aGagne) {
        super("Fin de la partie");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel finPanel = new JPanel();
        finPanel.setLayout(new BorderLayout());
        finPanel.setBackground(Color.LIGHT_GRAY);

        JLabel labelMessage = new JLabel(aGagne ? "Félicitations, vous avez gagné !" : "Dommage, vous avez perdu.");
        labelMessage.setFont(new Font("Arial", Font.BOLD, 16));
        labelMessage.setHorizontalAlignment(JLabel.CENTER);

        JLabel labelScore = new JLabel("Votre score : " + score);
        labelScore.setFont(new Font("Arial", Font.PLAIN, 14));
        labelScore.setHorizontalAlignment(JLabel.CENTER);

        JButton boutonQuitter = new JButton("Quitter");
        boutonQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel boutonsPanel = new JPanel();
        boutonsPanel.setLayout(new FlowLayout());
        boutonsPanel.add(boutonQuitter);

        finPanel.add(labelMessage, BorderLayout.NORTH);
        finPanel.add(labelScore, BorderLayout.CENTER);
        finPanel.add(boutonsPanel, BorderLayout.SOUTH);

        add(finPanel);
        setVisible(true);
    }
}

