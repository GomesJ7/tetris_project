package fr.eseo.e3.ppo.projet.blox;

import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;
import fr.eseo.e3.ppo.projet.blox.controleur.Gravite;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class FallingBloxVersion3 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Création du modèle avec extensions activées
            Puits puits = new Puits(10, 20, true, true, true); // multiPiece, defaite, clavier

            // Initialisation des pièces
            puits.initialiserFilePieces();
            puits.avancerFilePieces();

            // Création de la vue
            VuePuits vuePuits = new VuePuits(puits, 25);

            // Création de la fenêtre
            JFrame fenetre = new JFrame("FallingBlox - Version 3");
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setContentPane(vuePuits);
            fenetre.pack();
            fenetre.setLocationRelativeTo(null);
            fenetre.setVisible(true);

            // Démarrage de la gravité automatique (toutes les 500 ms)
            Gravite gravite = new Gravite(vuePuits, puits, 900);
        });
    }
}
