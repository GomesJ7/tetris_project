package fr.eseo.e3.ppo.projet.blox.controleur;

import fr.eseo.e3.ppo.projet.blox.controleur.PieceDeplacement;
import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.ITetromino;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;

import javax.swing.JFrame;

public class PieceDeplacementTest {

    public static void main(String[] args) {
        Puits puits = new Puits(10, 20); // largeur, hauteur
        ITetromino piece = new ITetromino(new Coordonnees(4, 0), Couleur.ROUGE);
        puits.setPieceActuelle(piece);

        VuePuits vuePuits = new VuePuits(puits);

        JFrame fenetre = new JFrame("Test Déplacement Souris");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.add(vuePuits);
        fenetre.pack();
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
    }
}
