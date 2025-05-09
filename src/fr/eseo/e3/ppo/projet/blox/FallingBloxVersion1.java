package fr.eseo.e3.ppo.projet.blox;

import fr.eseo.e3.ppo.projet.blox.controleur.Gravite;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.UsineDePiece;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.vue.PanneauInformation;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class FallingBloxVersion1 {

    public static void main(String[] args) {
        Puits puits;

        // Gestion des arguments
        if (args.length == 0) {
            puits = new Puits(); // Par défaut
        } else if (args.length == 1) {
            int nbElements = Integer.parseInt(args[0]);
            int nbLignes = nbElements / 10 + 1;
            puits = new Puits(10, 20, nbElements, nbLignes);
        } else {
            int nbElements = Integer.parseInt(args[0]);
            int nbLignes = Integer.parseInt(args[1]);
            puits = new Puits(10, 20, nbElements, nbLignes);
        }

        // Création des vues
        VuePuits vuePuits = new VuePuits(puits, 30); // taille de la grille
        PanneauInformation panneauInfo = new PanneauInformation(puits);

        // Fenêtre principale
        JFrame frame = new JFrame("Falling Blox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(vuePuits, BorderLayout.CENTER);
        frame.add(panneauInfo, BorderLayout.EAST);

        frame.pack(); // ajustement de la taille
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // centrer la fenêtre
        
        puits.setPieceSuivante(new UsineDePiece().genererPiece());
        puits.setPieceSuivante(new UsineDePiece().genererPiece());
        // Démarrage de la gravité automatique
        new Gravite(vuePuits, puits, 500);

        frame.setVisible(true);
    }
}
