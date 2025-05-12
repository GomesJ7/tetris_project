package fr.eseo.e3.ppo.projet.blox.controleur;

import fr.eseo.e3.ppo.projet.blox.modele.BloxException;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Contrôleur permettant la rotation de la pièce via des clics souris :
 * - clic gauche  → rotation antihoraire
 * - clic droit   → rotation horaire
 */
public class PieceRotation extends MouseAdapter {

    private final Puits puits; // Référence au modèle à manipuler

    /**
     * Constructeur de la classe, prenant en paramètre le modèle.
     * @param puits Le puits de jeu à contrôler
     */
    public PieceRotation(Puits puits) {
        this.puits = puits;
    }

    /**
     * Méthode déclenchée lorsqu’un clic de souris est détecté.
     * Effectue une rotation de la pièce active si le jeu est en cours.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (puits.isJeuTermine()) return; // Ne rien faire si la partie est terminée

        Piece piece = puits.getPieceActuelle();
        if (piece != null) {
            try {
                if (SwingUtilities.isRightMouseButton(e)) {
                    piece.tourner(true); // Rotation horaire
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    piece.tourner(false); // Rotation anti-horaire
                }
            } catch (BloxException ex) {
                System.err.println("Erreur rotation : " + ex.getMessage());
            }
        }
    }
}
