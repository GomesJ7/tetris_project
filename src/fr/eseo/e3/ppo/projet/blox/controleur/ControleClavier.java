package fr.eseo.e3.ppo.projet.blox.controleur;

import fr.eseo.e3.ppo.projet.blox.modele.BloxException;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Contrôleur gérant les actions clavier pour déplacer ou faire tourner la pièce.
 * S'intègre dans le pattern MVC pour contrôler le modèle (Puits) via les touches du clavier.
 */
public class ControleClavier extends KeyAdapter {

    private final Puits puits;         // Modèle du jeu
    private final VuePuits vuePuits;   // Vue associée à mettre à jour

    /**
     * Constructeur du contrôleur clavier.
     * @param puits Le puits de jeu à contrôler
     * @param vuePuits La vue du puits à rafraîchir après action
     */
    public ControleClavier(Puits puits, VuePuits vuePuits) {
        this.puits = puits;
        this.vuePuits = vuePuits;
    }

    /**
     * Méthode appelée à chaque touche pressée.
     * Gère les déplacements et rotations tant que la partie est active.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Si le jeu est terminé ou que les contrôles clavier sont désactivés, on ignore l'entrée
        if (puits.isJeuTermine() || !puits.isControlesClavier()) {
            return;
        }

        Piece piece = puits.getPieceActuelle();
        if (piece == null) {
            return;
        }

        boolean actionEffectuee = false;

        try {
            // Interprétation des touches clavier
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:   // Flèche gauche → déplacement à gauche
                    piece.deplacerDe(-1, 0);
                    actionEffectuee = true;
                    break;
                case KeyEvent.VK_RIGHT:  // Flèche droite → déplacement à droite
                    piece.deplacerDe(1, 0);
                    actionEffectuee = true;
                    break;
                case KeyEvent.VK_DOWN:   // Flèche bas → descente rapide
                    piece.deplacerDe(0, 1);
                    actionEffectuee = true;
                    break;
                case KeyEvent.VK_A:      // A / Q → rotation antihoraire (selon clavier AZERTY)
                case KeyEvent.VK_Q:
                    piece.tourner(false);
                    actionEffectuee = true;
                    break;
                case KeyEvent.VK_E:      // E → rotation horaire
                    piece.tourner(true);
                    actionEffectuee = true;
                    break;
            }
        } catch (BloxException ex) {
            // Affiche une erreur si un déplacement ou une rotation échoue
            System.err.println("Erreur de contrôle clavier : " + ex.getMessage());
        }

        // Si l’action a été réussie, on redessine la vue
        if (actionEffectuee) {
            vuePuits.repaint();
        }
    }
}
