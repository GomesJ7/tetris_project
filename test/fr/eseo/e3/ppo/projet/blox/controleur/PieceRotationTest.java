package fr.eseo.e3.ppo.projet.blox.controleur;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.ITetromino;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;

import javax.swing.JFrame;

public class PieceRotationTest {

    public static void main(String[] args) {
        // Crée un puits 10x20
        Puits puits = new Puits(10, 20);

        // Crée une pièce ITetromino au centre
        ITetromino piece = new ITetromino(new Coordonnees(4, 1), Couleur.ROUGE);
        puits.setPieceActuelle(piece);

        // Crée la vue
        VuePuits vuePuits = new VuePuits(puits);
        vuePuits.setTaille(30); // taille des blocs en pixels

        // Crée la fenêtre
        JFrame fenetre = new JFrame("Test Rotation Souris");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.add(vuePuits);

        // Calcule la taille de la fenêtre
        int largeurFenetre = puits.getLargeur() * vuePuits.getTaille();
        int hauteurFenetre = puits.getProfondeur() * vuePuits.getTaille();
        fenetre.setSize(largeurFenetre + 16, hauteurFenetre + 39); // marges approx.

        fenetre.setLocationRelativeTo(null); // centre l’écran
        fenetre.setVisible(true);
    }
}
