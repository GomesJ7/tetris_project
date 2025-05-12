package fr.eseo.e3.ppo.projet.blox.controleur;

import fr.eseo.e3.ppo.projet.blox.modele.BloxException;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Contrôleur gérant les déplacements horizontaux de la pièce
 * avec le curseur de la souris, ainsi que la descente avec la molette.
 */
public class PieceDeplacement extends MouseAdapter {

    private final VuePuits vuePuits; // Vue utilisée pour connaître la taille des cases et redessiner
    private final Puits puits;       // Modèle manipulé
    private int ancienneColonne = -1; // Pour éviter les déplacements répétés sur la même colonne

    /**
     * Constructeur du contrôleur.
     * @param vuePuits La vue graphique du puits
     * @param puits Le modèle de jeu associé
     */
    public PieceDeplacement(VuePuits vuePuits, Puits puits) {
        this.vuePuits = vuePuits;
        this.puits = puits;
    }

    /**
     * Événement déclenché lors d’un mouvement de la souris.
     * Si la souris change de colonne, on déplace la pièce horizontalement.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (puits.isJeuTermine()) return; // Ne rien faire si la partie est terminée

        Piece piece = puits.getPieceActuelle();

        if (piece != null) {
            int xPixel = e.getX(); // Position de la souris
            int colonne = xPixel / vuePuits.getTaille(); // Conversion pixel → colonne

            int refX = piece.getElementReference().getCoordonnees().getAbscisse(); // Position actuelle
            int dx = colonne - refX;

            // Si la souris a bougé de colonne, et le déplacement est possible
            if (dx != 0 && piece.deplacementPossible(dx, 0)) {
                try {
                    piece.deplacerDe(dx, 0); // On déplace horizontalement
                    vuePuits.repaint();      // On redessine
                } catch (BloxException ex) {
                    System.err.println("Erreur déplacement horizontal : " + ex.getMessage());
                }
            }
        }
    }

    /**
     * Réinitialise la colonne lorsque la souris entre à nouveau dans la zone du puits.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        ancienneColonne = -1; // Permet un recalcul au prochain mouvement
    }

    /**
     * Événement déclenché lors d’un mouvement de la molette de la souris.
     * Si la molette tourne vers le bas, on tente une descente d’un cran.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (puits.isJeuTermine()) return;

        Piece piece = puits.getPieceActuelle();

        // Rotation négative = molette tournée vers soi → descente
        if (piece != null && e.getWheelRotation() > 0) {
            if (piece.deplacementPossible(0, 1)) {
                try {
                    piece.deplacerDe(0, 1); // Descente d’une ligne
                    vuePuits.repaint();
                } catch (BloxException ex) {
                    System.err.println("Erreur descente : " + ex.getMessage());
                }
            }
        }
    }
}
