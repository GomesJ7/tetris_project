package fr.eseo.e3.ppo.projet.blox.controleur;

import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PieceDeplacement implements MouseMotionListener {

    private final VuePuits vuePuits;
    private final Puits puits;
    private int ancienneColonne = -1;

    public PieceDeplacement(VuePuits vuePuits, Puits puits) {
        this.vuePuits = vuePuits;
        this.puits = puits;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Piece piece = puits.getPieceActuelle();

        if (piece != null) {
            int xPixel = e.getX();
            int colonne = xPixel / vuePuits.getTaille();

            if (ancienneColonne == -1) {
                ancienneColonne = colonne;
            } else if (colonne != ancienneColonne) {
                int deplacement = colonne - ancienneColonne;

                try {
                    if (deplacement > 0) {
                        piece.deplacerDe(1, 0); // droite
                    } else {
                        piece.deplacerDe(-1, 0); // gauche
                    }
                } catch (IllegalArgumentException ex) {
                    // Aucun déplacement si le mouvement n'est pas valide (bord du puits par exemple)
                }

                ancienneColonne = colonne;
                vuePuits.repaint();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Ignoré selon les consignes
    }
}
