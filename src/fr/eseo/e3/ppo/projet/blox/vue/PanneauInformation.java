package fr.eseo.e3.ppo.projet.blox.vue;

import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Représente un petit panneau Swing affichant la pièce suivante.
 * Ce composant graphique observe le puits pour être notifié des changements de pièce.
 * Il utilise un {@link VuePiece} pour dessiner la pièce suivante.
 */
public class PanneauInformation extends JPanel implements PropertyChangeListener {

    public static final int TAILLE_VUE_PIECE = 10; // Taille (en pixels) d’un carré de pièce

    private final Puits puits;              // Modèle observé
    private VuePiece vuePieceSuivante;      // Vue miniature de la pièce suivante

    /**
     * Constructeur : initialise le panneau et s’abonne aux notifications du puits.
     * @param puits le modèle à observer (notifie les changements de pièce suivante)
     */
    public PanneauInformation(Puits puits) {
        this.puits = puits;
        this.puits.addPropertyChangeListener(this); // Observation du modèle (MVC)
        setPreferredSize(new Dimension(70, 70)); // Taille fixe du panneau
    }

    /**
     * Méthode appelée automatiquement lorsqu’une propriété du puits change.
     * Ici, on écoute les modifications de la pièce suivante.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Puits.MODIFICATION_PIECE_SUIVANTE.equals(evt.getPropertyName())) {
            Piece nouvellePiece = (Piece) evt.getNewValue();
            if (nouvellePiece != null) {
                this.vuePieceSuivante = new VuePiece(nouvellePiece, TAILLE_VUE_PIECE);
            } else {
                this.vuePieceSuivante = null;
            }
            repaint(); // Redessine le panneau avec la nouvelle pièce
        }
    }

    /**
     * Méthode appelée par Swing lorsqu’il faut redessiner le composant.
     * Si une pièce suivante est présente, on la dessine.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (vuePieceSuivante != null) {
            vuePieceSuivante.afficherPiece(g);
        }
    }

    // === Accesseurs pour tests ou manipulation externe ===

    public VuePiece getVuePieceSuivante() {
        return this.vuePieceSuivante;
    }

    public void setVuePieceSuivante(VuePiece vuePieceSuivante) {
        this.vuePieceSuivante = vuePieceSuivante;
        repaint();
    }
}
