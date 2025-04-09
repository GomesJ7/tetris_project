package fr.eseo.e3.ppo.projet.blox.controleur;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.ITetromino;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;

import javax.swing.JFrame;

public class PieceRotationTest {

    public static void main(String[] args) {
        // Création du puits
        Puits puits = new Puits(10, 20);

        // Création d'une pièce I au centre
        ITetromino piece = new ITetromino(new Coordonnees(4, 1), Couleur.ROUGE);
        puits.setPieceActuelle(piece);

        // Création de la vue
        VuePuits vuePuits = new VuePuits(puits);
        vuePuits.setTaille(30);  // Agrandir la taille pour plus de lisibilité

        // Création de la fenêtre
        JFrame fenetre = new JFrame("Test Rotation Souris");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.add(vuePuits);

        // Calcul des dimensions de la fenêtre avec marges
        int largeurFenetre = puits.getLargeur() * vuePuits.getTaille();
        int hauteurFenetre = puits.getProfondeur() * vuePuits.getTaille();
        fenetre.setSize(largeurFenetre + 16, hauteurFenetre + 39);

        fenetre.setLocationRelativeTo(null);  // Centrer la fenêtre
        fenetre.setVisible(true);
    }
}
