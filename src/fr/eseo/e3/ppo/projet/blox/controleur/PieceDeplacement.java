package fr.eseo.e3.ppo.projet.blox.controleur;

import fr.eseo.e3.ppo.projet.blox.modele.BloxException;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class PieceDeplacement extends MouseAdapter {

    private final VuePuits vuePuits;
    private final Puits puits;
    private int ancienneColonne = -1;

    public PieceDeplacement(VuePuits vuePuits, Puits puits) {
        this.vuePuits = vuePuits;
        this.puits = puits;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (puits.isJeuTermine()) return; // ⛔ Blocage si perdu

        Piece piece = puits.getPieceActuelle();

        if (piece != null) {
            int xPixel = e.getX();
            int colonne = xPixel / vuePuits.getTaille();

            int refX = piece.getElementReference().getCoordonnees().getAbscisse();
            int dx = colonne - refX;

            if (dx != 0 && piece.deplacementPossible(dx, 0)) {
                try {
                    piece.deplacerDe(dx, 0);
                    vuePuits.repaint();
                } catch (BloxException ex) {
                    System.err.println("Erreur déplacement horizontal : " + ex.getMessage());
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ancienneColonne = -1;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (puits.isJeuTermine()) return; // ⛔ Blocage si perdu

        Piece piece = puits.getPieceActuelle();

        if (piece != null && e.getWheelRotation() > 0) {
            if (piece.deplacementPossible(0, 1)) {
                try {
                    piece.deplacerDe(0, 1);
                    vuePuits.repaint();
                } catch (BloxException ex) {
                    System.err.println("Erreur descente : " + ex.getMessage());
                }
            }
        }
    }
}
