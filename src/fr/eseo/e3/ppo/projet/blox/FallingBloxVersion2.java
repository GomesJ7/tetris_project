package fr.eseo.e3.ppo.projet.blox;

import fr.eseo.e3.ppo.projet.blox.controleur.Gravite;
import fr.eseo.e3.ppo.projet.blox.modele.Mode;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.UsineDePiece;
import fr.eseo.e3.ppo.projet.blox.vue.PanneauInformation;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class FallingBloxVersion2 {

    public static void main(String[] args) {
        // Création d’un puits vide
        Puits puits = new Puits();

        // Création de l’usine et passage en mode CYCLIC
        UsineDePiece usine = new UsineDePiece();
        usine.setMode(Mode.CYCLIC);

        // Génère 2 pièces pour démarrer le cycle
        puits.setPieceSuivante(usine.genererPiece());
        puits.setPieceSuivante(usine.genererPiece());

        // Création des composants graphiques
        VuePuits vuePuits = new VuePuits(puits, 30);
        PanneauInformation panneauInfo = new PanneauInformation(puits);

        // Fenêtre principale
        JFrame frame = new JFrame("Falling Blox - Version 2 (CYCLIC)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(vuePuits, BorderLayout.CENTER);
        frame.add(panneauInfo, BorderLayout.EAST);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Gravité démarrée avec intervalle court pour tester
        new Gravite(vuePuits, puits, 800);

        frame.setVisible(true);
    }
}
