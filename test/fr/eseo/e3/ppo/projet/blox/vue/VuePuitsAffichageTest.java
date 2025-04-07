package fr.eseo.e3.ppo.projet.blox.vue;

import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.UsineDePiece;
import fr.eseo.e3.ppo.projet.blox.modele.Mode;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class VuePuitsAffichageTest {

    private void testConstructeurPuits() {
        Puits puits = new Puits();

        UsineDePiece usine = new UsineDePiece();
        usine.setMode(Mode.DETERMINISTE);
        Piece piece = usine.genererPiece();
        puits.setPieceSuivante(piece);

        JFrame frame = new JFrame("Puits");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        VuePuits vuePuits = new VuePuits(puits);
        VuePiece vuePiece = new VuePiece(piece);  // <-- ajout explicite

        frame.add(vuePuits, BorderLayout.CENTER);
        frame.add(vuePiece, BorderLayout.SOUTH); // Ajout visible de VuePiece

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void testConstructeurPuitsTaille() {
        Puits puits = new Puits();

        UsineDePiece usine = new UsineDePiece();
        usine.setMode(Mode.DETERMINISTE);
        Piece piece = usine.genererPiece();
        puits.setPieceSuivante(piece);

        JFrame frame = new JFrame("Puits et taille");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        VuePuits vuePuits = new VuePuits(puits, 25);
        VuePiece vuePiece = new VuePiece(piece, 25);  // <-- ajout explicite

        frame.add(vuePuits, BorderLayout.CENTER);
        frame.add(vuePiece, BorderLayout.SOUTH); // Ajout visible de VuePiece

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        VuePuitsAffichageTest test = new VuePuitsAffichageTest();
        test.testConstructeurPuits();
        test.testConstructeurPuitsTaille();
    }
}
