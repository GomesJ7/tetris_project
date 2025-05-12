package fr.eseo.e3.ppo.projet.blox;

// Importation du contrôleur, modèle et vue spécifique à la V4
import fr.eseo.e3.ppo.projet.blox.controleur.Gravite;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuitsV4;

import javax.swing.*;

/**
 * Classe de lancement de la version 4 de FallingBlox.
 * Cette version introduit la sélection de difficulté (ajustement du délai de gravité)
 * en plus des fonctionnalités de la version 3 :
 * - affichage multi-pièces
 * - détection de défaite
 * - contrôle clavier
 */
public class FallingBloxVersion4 {

    public static void main(String[] args) {
        // === Paramétrage dynamique de la difficulté ===
        int delai = 1000; // valeur par défaut (normal)

        // Lecture d’un argument éventuel (passé depuis le menu de démarrage)
        if (args.length > 0) {
            switch (args[0]) {
                case "Facile":
                    delai = 1200;
                    break;
                case "Difficile":
                    delai = 300;
                    break;
                case "Normal":
                default:
                    delai = 800;
                    break;
            }
        }

        // === Initialisation du modèle (Puits) ===
        // Les trois booléens activent :
        // - la file de pièces (multiPiece)
        // - la détection de défaite (defaite)
        // - les contrôles clavier (clavier)
        Puits puits = new Puits(10, 20, true, true, true);
        puits.initialiserFilePieces();
        puits.avancerFilePieces(); // met la première pièce en jeu

        // === Vue personnalisée pour la version 4 ===
        // Vue enrichie avec les ajouts visuels spécifiques (par ex. file de pièces visibles ?)
        VuePuitsV4 vuePuits = new VuePuitsV4(puits, 30); // taille de case ajustée

        // === Contrôleur ===
        // La gravité automatique utilise le délai adapté à la difficulté choisie
        new Gravite(vuePuits, puits, delai);

        // === Interface graphique Swing ===
        JFrame fenetre = new JFrame("FallingBlox - Version 4");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setContentPane(vuePuits);
        fenetre.pack(); // ajuste automatiquement la taille
        fenetre.setLocationRelativeTo(null); // centre la fenêtre
        fenetre.setVisible(true); // affiche la fenêtre
    }
}
