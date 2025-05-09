package fr.eseo.e3.ppo.projet.blox.controleur;

import fr.eseo.e3.ppo.projet.blox.modele.BloxException;
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
        if (puits.isJeuTermine()) return; // Blocage si perdu

        Piece piece = puits.getPieceActuelle();

        if (piece != null) {
            try {
                if (SwingUtilities.isRightMouseButton(e)) {
                    piece.tourner(true); // Sens horaire
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    piece.tourner(false); // Sens anti-horaire
                }
            } catch (BloxException ex) {
                System.err.println("Erreur rotation : " + ex.getMessage());
            }
        }
    }
}
