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
        Puits puits = new Puits(10, 20);
        ITetromino piece = new ITetromino(new Coordonnees(4, 0), Couleur.ROUGE);
        puits.setPieceActuelle(piece);

        VuePuits vuePuits = new VuePuits(puits);
        vuePuits.setTaille(30); // agrandit les cases
        vuePuits.revalidate();     // recalcul du layout
        vuePuits.repaint();        // redraw après changement


        int largeurFenetre = puits.getLargeur() * vuePuits.getTaille();
        int hauteurFenetre = puits.getProfondeur() * vuePuits.getTaille();

        JFrame fenetre = new JFrame("Test Déplacement Souris");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.add(vuePuits);
        fenetre.setSize(largeurFenetre + 16, hauteurFenetre + 39); // marge pour bords/fenêtre
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
    }
}

