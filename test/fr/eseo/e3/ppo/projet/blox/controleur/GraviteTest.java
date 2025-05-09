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
        Puits puits = new Puits(10, 20);
        VuePuits vuePuits = new VuePuits(puits, 30);

        Piece piece = new ITetromino(new Coordonnees(4, 0), Couleur.ROUGE);
        puits.setPieceActuelle(piece);

        JFrame frame = new JFrame("Gravité automatique");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(vuePuits, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Gravite(vuePuits); // Démarrage de la gravité automatique
    }
}
