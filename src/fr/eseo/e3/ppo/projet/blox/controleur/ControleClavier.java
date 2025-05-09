package fr.eseo.e3.ppo.projet.blox.controleur;

import fr.eseo.e3.ppo.projet.blox.modele.BloxException;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControleClavier extends KeyAdapter {

    private final Puits puits;
    private final VuePuits vuePuits;

    public ControleClavier(Puits puits, VuePuits vuePuits) {
        this.puits = puits;
        this.vuePuits = vuePuits;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (puits.isJeuTermine() || !puits.isControlesClavier()) {
            return;
        }

        Piece piece = puits.getPieceActuelle();
        if (piece == null) {
            return;
        }

        boolean actionEffectuee = false;

        try {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    piece.deplacerDe(-1, 0);
                    actionEffectuee = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    piece.deplacerDe(1, 0);
                    actionEffectuee = true;
                    break;
                case KeyEvent.VK_DOWN:
                    piece.deplacerDe(0, 1);
                    actionEffectuee = true;
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_Q:
                    piece.tourner(false);
                    actionEffectuee = true;
                    break;
                case KeyEvent.VK_E:
                    piece.tourner(true);
                    actionEffectuee = true;
                    break;
            }
        } catch (BloxException ex) {
            System.err.println("Erreur de contrôle clavier : " + ex.getMessage());
        }

        if (actionEffectuee) {
            vuePuits.repaint();
        }
    }
}
