package fr.eseo.e3.ppo.projet.blox;

// Importations des classes nécessaires
import fr.eseo.e3.ppo.projet.blox.controleur.Gravite;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.UsineDePiece;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.vue.PanneauInformation;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;

import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * Classe représentant la première version fonctionnelle de FallingBlox.
 * Ce fichier constitue une démonstration basique du jeu avec :
 * - affichage graphique du puits
 * - panneau d'information
 * - génération d’une pièce
 * - lancement automatique de la gravité
 *
 * Cette version montre bien la séparation des responsabilités entre le modèle, la vue et le contrôleur.
 */
public class FallingBloxVersion1 {

    public static void main(String[] args) {
        Puits puits;

        // === Traitement des arguments d'entrée ===
        // Encapsulation de la logique d'initialisation selon le nombre d’éléments souhaités
        if (args.length == 0) {
            puits = new Puits(); // Appelle le constructeur par défaut
        } else if (args.length == 1) {
            int nbElements = Integer.parseInt(args[0]);
            int nbLignes = nbElements / 10 + 1; // estimation automatique
            puits = new Puits(10, 20, nbElements, nbLignes);
        } else {
            int nbElements = Integer.parseInt(args[0]);
            int nbLignes = Integer.parseInt(args[1]);
            puits = new Puits(10, 20, nbElements, nbLignes);
        }

        // === Création des vues (MVC) ===
        // Vue principale du puits avec un facteur d’échelle
        VuePuits vuePuits = new VuePuits(puits, 30);

        // Panneau latéral affichant des infos sur la partie
        PanneauInformation panneauInfo = new PanneauInformation(puits);

        // === Initialisation de la fenêtre graphique ===
        JFrame frame = new JFrame("Falling Blox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(vuePuits, BorderLayout.CENTER);      // Vue principale au centre
        frame.add(panneauInfo, BorderLayout.EAST);     // Infos à droite

        frame.pack();                  // Ajustement automatique de la taille
        frame.setResizable(false);     // Empêche le redimensionnement
        frame.setLocationRelativeTo(null); // Centre la fenêtre à l’écran

        // === Initialisation des pièces ===
        puits.setPieceSuivante(new UsineDePiece().genererPiece()); // première pièce ignorée
        puits.setPieceSuivante(new UsineDePiece().genererPiece()); // pièce active réellement jouée

        // === Démarrage de la gravité automatique ===
        // Ce composant suit le pattern "contrôleur" dans le modèle MVC
        new Gravite(vuePuits, puits, 500); // gravité toutes les 500ms

        frame.setVisible(true); // affichage final de la fenêtre
    }
}
