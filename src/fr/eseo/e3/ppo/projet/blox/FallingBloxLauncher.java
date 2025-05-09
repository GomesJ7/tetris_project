package fr.eseo.e3.ppo.projet.blox;

import javax.swing.*;
import java.awt.*;

public class FallingBloxLauncher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame fenetre = new JFrame("FallingBlox - Menu Principal");
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setSize(400, 300);
            fenetre.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBackground(Color.DARK_GRAY);

            JLabel titre = new JLabel("FALLINGBLOX");
            titre.setFont(new Font("Arial", Font.BOLD, 24));
            titre.setForeground(Color.WHITE);
            titre.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton jouerBtn = new JButton("Jouer");
            jouerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton quitterBtn = new JButton("Quitter");
            quitterBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

            jouerBtn.addActionListener(e -> {
                fenetre.dispose(); // ferme la fenêtre actuelle
                lancerMenuDifficulte();
            });

            quitterBtn.addActionListener(e -> System.exit(0));

            panel.add(Box.createVerticalGlue());
            panel.add(titre);
            panel.add(Box.createRigidArea(new Dimension(0, 30)));
            panel.add(jouerBtn);
            panel.add(Box.createRigidArea(new Dimension(0, 15)));
            panel.add(quitterBtn);
            panel.add(Box.createVerticalGlue());

            fenetre.setContentPane(panel);
            fenetre.setVisible(true);
        });
    }

    private static void lancerMenuDifficulte() {
        JFrame fenetre = new JFrame("Choix de la difficulté");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(400, 250);
        fenetre.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.GRAY);

        JLabel label = new JLabel("Sélectionnez une difficulté");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] niveaux = {"Facile", "Normal", "Difficile"};
        JComboBox<String> combo = new JComboBox<>(niveaux);
        combo.setMaximumSize(new Dimension(200, 25));
        combo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton lancerBtn = new JButton("Lancer la partie");
        lancerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        lancerBtn.addActionListener(e -> {
            String niveau = (String) combo.getSelectedItem();
            fenetre.dispose();

            // TODO : définir le délai en fonction du niveau (plus tard)
            // Pour l'instant, on lance la V4 sans prise en compte réelle
            FallingBloxVersion4.main(new String[]{niveau});
        });

        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(combo);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(lancerBtn);
        panel.add(Box.createVerticalGlue());

        fenetre.setContentPane(panel);
        fenetre.setVisible(true);
    }
}
