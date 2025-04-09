package fr.eseo.e3.ppo.projet.blox.controleur;

import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PieceRotation extends MouseAdapter {

    private final Puits puits;

    public PieceRotation(Puits puits) {
        this.puits = puits;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Piece piece = puits.getPieceActuelle();

        if (piece != null) {
            try {
                if (SwingUtilities.isRightMouseButton(e)) {
                    // Sens horaire
                    piece.tourner(true);
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    // Sens anti-horaire
                    piece.tourner(false);
                }
            } catch (IllegalArgumentException ex) {
                // Si la rotation n’est pas possible, on ignore
            }
        }
    }
}
