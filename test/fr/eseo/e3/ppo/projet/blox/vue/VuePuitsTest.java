package fr.eseo.e3.ppo.projet.blox.vue;

import javax.swing.JFrame;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.OTetromino;

public class VuePuitsTest {

    public static void main(String[] args) {
        // Crée un puits
        Puits puits = new Puits(10, 20);

        // Ajoute une pièce dans le puits (ex : OTetromino rouge)
        OTetromino piece = new OTetromino(new Coordonnees(4, 5), Couleur.ROUGE);
        puits.ajouterPiece(piece);

        // Crée la vue du puits
        VuePuits vue = new VuePuits(puits, 30); // Taille plus grande pour test visuel

        // Crée une fenêtre et affiche la vue
        JFrame fenetre = new JFrame("Test VuePuits");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setContentPane(vue);
        fenetre.pack();
        fenetre.setSize(400, 600);
        fenetre.setVisible(true);
    }
}
