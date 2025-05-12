package fr.eseo.e3.ppo.projet.blox;

// Importations nécessaires aux différents composants (MVC, gravité, logique de jeu)
import fr.eseo.e3.ppo.projet.blox.controleur.Gravite;
import fr.eseo.e3.ppo.projet.blox.modele.Mode;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.UsineDePiece;
import fr.eseo.e3.ppo.projet.blox.vue.PanneauInformation;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;

import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * Cette classe représente la deuxième version du jeu FallingBlox.
 * Elle introduit le mode CYCLIC dans la génération des pièces via l'usine.
 * Le reste du fonctionnement (MVC, gravité, interface graphique) reste similaire à la V1.
 */
public class FallingBloxVersion2 {

    public static void main(String[] args) {
        // === Création du modèle ===
        // Puits vide : on utilise le constructeur par défaut
        Puits puits = new Puits();

        // Création de l’usine à pièces (Factory Pattern)
        UsineDePiece usine = new UsineDePiece();

        // Passage en mode CYCLIC (vs RANDOM par défaut)
        // Principe de l'énumération (enum) pour représenter un ensemble de comportements prédéfinis
        usine.setMode(Mode.CYCLIC); // CYCLIC garantit une rotation ordonnée des pièces

        // On initialise le puits avec deux pièces successives pour simuler un enchaînement fluide
        puits.setPieceSuivante(usine.genererPiece());
        puits.setPieceSuivante(usine.genererPiece());

        // === Création de la vue ===
        // Vue principale du jeu : grille de jeu
        VuePuits vuePuits = new VuePuits(puits, 30);

        // Panneau latéral contenant les informations (pièce suivante, score éventuel, etc.)
        PanneauInformation panneauInfo = new PanneauInformation(puits);

        // === Création de la fenêtre Swing ===
        JFrame frame = new JFrame("Falling Blox - Version 2 (CYCLIC)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Placement des composants selon BorderLayout
        frame.add(vuePuits, BorderLayout.CENTER);
        frame.add(panneauInfo, BorderLayout.EAST);

        frame.pack(); // ajuste la taille automatiquement
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // centre la fenêtre

        // === Contrôleur : gravité automatique ===
        // Contrôleur autonome déclenchant la méthode `gravite()` du modèle à intervalles réguliers
        new Gravite(vuePuits, puits, 800); // 800ms = plus lent que V1

        // Affichage de la fenêtre
        frame.setVisible(true);
    }
}
