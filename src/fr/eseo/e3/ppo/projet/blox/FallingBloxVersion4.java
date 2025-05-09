package fr.eseo.e3.ppo.projet.blox;

import fr.eseo.e3.ppo.projet.blox.controleur.Gravite;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuitsV4;

import javax.swing.*;

public class FallingBloxVersion4 {

    public static void main(String[] args) {
        // Détermination du délai de gravité selon la difficulté
        int delai = 1000; // Normal par défaut
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

        // Initialisation du puits avec les options de la V4
        Puits puits = new Puits(10, 20, true, true, true);
        puits.initialiserFilePieces();
        puits.avancerFilePieces();

        // Création de la vue
        VuePuitsV4 vuePuits = new VuePuitsV4(puits, 30); // Taille des cases ajustable

        // Ajout de la gravité
        new Gravite(vuePuits, puits, delai);

        // Création de la fenêtre
        JFrame fenetre = new JFrame("FallingBlox - Version 4");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setContentPane(vuePuits);
        fenetre.pack();
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
    }
}
