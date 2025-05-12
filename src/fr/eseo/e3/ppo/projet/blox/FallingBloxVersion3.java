package fr.eseo.e3.ppo.projet.blox;

import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;
import fr.eseo.e3.ppo.projet.blox.controleur.Gravite;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Classe de lancement de la version 3 du jeu FallingBlox.
 * Cette version introduit des extensions importantes :
 * - Affichage d’une file de pièces (multiPiece)
 * - Gestion de la défaite (empilement jusqu'en haut)
 * - Contrôles clavier (déplacement et rotation)
 *
 * L’architecture MVC est conservée, mais enrichie avec des comportements supplémentaires
 * activés via le constructeur du modèle (Puits).
 */
public class FallingBloxVersion3 {

    public static void main(String[] args) {
        // Utilisation du thread de l’EDT (bonne pratique Swing)
        SwingUtilities.invokeLater(() -> {
            // === Modèle ===
            // Création du puits avec des fonctionnalités activables :
            // multiPiece = true, defaite = true, clavier = true
            Puits puits = new Puits(10, 20, true, true, true);

            // Initialise la file de pièces (nouvelle fonctionnalité)
            puits.initialiserFilePieces();
            puits.avancerFilePieces(); // place la première pièce dans le jeu

            // === Vue ===
            VuePuits vuePuits = new VuePuits(puits, 25); // facteur de taille réduit ici

            // === Fenêtre principale ===
            JFrame fenetre = new JFrame("FallingBlox - Version 3");
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setContentPane(vuePuits);
            fenetre.pack();
            fenetre.setLocationRelativeTo(null); // centre la fenêtre
            fenetre.setVisible(true);

            // === Contrôleur ===
            // Démarrage de la gravité automatique (nouvelle instance de Gravite)
            Gravite gravite = new Gravite(vuePuits, puits, 900); // cadence plus lente
        });
    }
}
