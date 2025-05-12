package fr.eseo.e3.ppo.projet.blox;

// Importation des classes nécessaires à la création d'une interface graphique avec Swing
import javax.swing.*;
import java.awt.*;

/**
 * Classe de lancement de l'application FallingBlox.
 * Cette classe sert de point d'entrée (main) et affiche un menu principal
 * puis un sous-menu pour choisir la difficulté.
 *
 * Ce code montre un usage simple de Swing pour construire une interface graphique.
 * Il ne fait pas appel à l’héritage, au polymorphisme ou à l’encapsulation de manière directe,
 * mais constitue une façade utilisateur initiale.
 */
public class FallingBloxLauncher {

    /**
     * Méthode principale du programme.
     * Utilise `SwingUtilities.invokeLater` pour garantir que les éléments graphiques
     * sont créés dans le thread de l’EDT (Event Dispatch Thread), ce qui est une bonne pratique Swing.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Création et configuration de la fenêtre principale (menu)
            JFrame fenetre = new JFrame("FallingBlox - Menu Principal");
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setSize(400, 300);
            fenetre.setLocationRelativeTo(null); // centre la fenêtre

            // Panel principal avec disposition verticale
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBackground(Color.DARK_GRAY);

            // Titre du jeu
            JLabel titre = new JLabel("FALLINGBLOX");
            titre.setFont(new Font("Arial", Font.BOLD, 24));
            titre.setForeground(Color.WHITE);
            titre.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Bouton "Jouer"
            JButton jouerBtn = new JButton("Jouer");
            jouerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Bouton "Quitter"
            JButton quitterBtn = new JButton("Quitter");
            quitterBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Action sur le bouton "Jouer"
            jouerBtn.addActionListener(e -> {
                fenetre.dispose(); // ferme le menu principal
                lancerMenuDifficulte(); // ouvre la sélection de difficulté
            });

            // Action sur le bouton "Quitter"
            quitterBtn.addActionListener(e -> System.exit(0)); // quitte l'application

            // Ajout des composants avec espacements
            panel.add(Box.createVerticalGlue()); // Espace flexible en haut
            panel.add(titre);
            panel.add(Box.createRigidArea(new Dimension(0, 30))); // espace fixe
            panel.add(jouerBtn);
            panel.add(Box.createRigidArea(new Dimension(0, 15)));
            panel.add(quitterBtn);
            panel.add(Box.createVerticalGlue()); // Espace flexible en bas

            // Affectation du panel à la fenêtre et affichage
            fenetre.setContentPane(panel);
            fenetre.setVisible(true);
        });
    }

    /**
     * Méthode privée appelée pour afficher une nouvelle fenêtre avec le choix de la difficulté.
     * Cette séparation montre une bonne pratique : découpler les responsabilités (principe SRP).
     */
    private static void lancerMenuDifficulte() {
        JFrame fenetre = new JFrame("Choix de la difficulté");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(400, 250);
        fenetre.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.GRAY);

        // Label de titre
        JLabel label = new JLabel("Sélectionnez une difficulté");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Menu déroulant des niveaux
        String[] niveaux = {"Facile", "Normal", "Difficile"};
        JComboBox<String> combo = new JComboBox<>(niveaux);
        combo.setMaximumSize(new Dimension(200, 25));
        combo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Bouton de lancement de partie
        JButton lancerBtn = new JButton("Lancer la partie");
        lancerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Action du bouton : récupère la difficulté sélectionnée
        lancerBtn.addActionListener(e -> {
            String niveau = (String) combo.getSelectedItem();
            fenetre.dispose(); // ferme la fenêtre actuelle

            // Lance la version 4 du jeu avec la difficulté en paramètre
            // Le choix de la difficulté peut être utilisé par la suite pour configurer la gravité, etc.
            FallingBloxVersion4.main(new String[]{niveau});
        });

        // Ajout des composants au panel
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
