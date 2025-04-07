package fr.eseo.e3.ppo.projet.blox.vue;

import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.UsineDePiece;
import fr.eseo.e3.ppo.projet.blox.modele.Mode;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class VuePuitsAffichageTest {

    public static void main(String[] args) {
        // Création du puits
        Puits puits = new Puits();

        // Création de la vue
        VuePuits vuePuits = new VuePuits(puits, 30); // taille personnalisée
        puits.addPropertyChangeListener(vuePuits);  // 🔁 Écouteur enregistré

        // Génération d’une pièce
        UsineDePiece usine = new UsineDePiece();
        usine.setMode(Mode.DETERMINISTE);
        Piece piece1 = usine.genererPiece();
        Piece piece2 = usine.genererPiece();

        // Premier appel : définit pieceSuivante uniquement
        puits.setPieceSuivante(piece1);

        // Deuxième appel : piece1 devient pieceActuelle, piece2 devient la nouvelle pieceSuivante
        puits.setPieceSuivante(piece2);

        // Création de la fenêtre
        JFrame frame = new JFrame("Vue du Puits avec pièce");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(vuePuits, BorderLayout.CENTER);
        frame.pack(); // ajuste la taille automatiquement avec getPreferredSize()
        frame.setLocationRelativeTo(null); // centre la fenêtre
        frame.setVisible(true);
    }
}
