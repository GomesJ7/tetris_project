package fr.eseo.e3.ppo.projet.blox.vue;

import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanneauInformation extends JPanel implements PropertyChangeListener {

    public static final int TAILLE_VUE_PIECE = 10; // taille de chaque carré
    private final Puits puits;
    private VuePiece vuePieceSuivante;

    public PanneauInformation(Puits puits) {
        this.puits = puits;
        this.puits.addPropertyChangeListener(this);
        setPreferredSize(new Dimension(70, 70)); // comme demandé
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Puits.MODIFICATION_PIECE_SUIVANTE.equals(evt.getPropertyName())) {
            Piece nouvellePiece = (Piece) evt.getNewValue();
            if (nouvellePiece != null) {
                this.vuePieceSuivante = new VuePiece(nouvellePiece, TAILLE_VUE_PIECE);
            } else {
                this.vuePieceSuivante = null;
            }
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (vuePieceSuivante != null) {
            vuePieceSuivante.afficherPiece(g);
        }
    }

    public VuePiece getVuePieceSuivante() {
        return this.vuePieceSuivante;
    }

    public void setVuePieceSuivante(VuePiece vuePieceSuivante) {
        this.vuePieceSuivante = vuePieceSuivante;
        repaint();
    }
}
