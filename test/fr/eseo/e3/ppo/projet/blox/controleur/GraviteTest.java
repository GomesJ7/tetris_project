package fr.eseo.e3.ppo.projet.blox.controleur;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.ITetromino;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class GraviteTest {

    public static void main(String[] args) {
        // Création du puits pour la V3 : gravité, défaite et contrôles clavier activés
        Puits puits = new Puits(10, 20, true, true, true);
        VuePuits vuePuits = new VuePuits(puits, 30);

        // Génération d'une pièce manuelle
        Piece piece = new ITetromino(new Coordonnees(4, 0), Couleur.ROUGE);
        puits.setPieceActuelle(piece);

        // Création de la fenêtre
        JFrame frame = new JFrame("Gravité automatique - V3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(vuePuits, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Lancement de la gravité automatique (500ms par défaut)
        new Gravite(vuePuits, puits, 500);
    }
}
