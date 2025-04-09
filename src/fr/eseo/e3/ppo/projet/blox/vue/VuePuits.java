package fr.eseo.e3.ppo.projet.blox.vue;

import fr.eseo.e3.ppo.projet.blox.controleur.PieceDeplacement;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VuePuits extends JPanel implements PropertyChangeListener {

    public static final int TAILLE_PAR_DEFAUT = 15;

    private Puits puits;
    private int taille;
    private VuePiece vuePiece;
    private PieceDeplacement pieceDeplacement;

    // Constructeur avec taille par défaut
    public VuePuits(Puits puits) {
        this(puits, TAILLE_PAR_DEFAUT);
    }

    // Constructeur avec taille personnalisée
    public VuePuits(Puits puits, int taille) {
        this.taille = taille;
        this.setPuits(puits);
    }

    public Puits getPuits() {
        return this.puits;
    }

    public void setPuits(Puits nouveauPuits) {
        if (this.puits != null) {
            this.puits.removePropertyChangeListener(this);
        }

        this.puits = nouveauPuits;

        if (this.puits != null) {
            this.puits.addPropertyChangeListener(this);
            setVuePiece(this.puits.getPieceActuelle());

            // (Re)crée un listener propre à l'instance actuelle de VuePuits et Puits
            if (this.pieceDeplacement != null) {
                this.removeMouseMotionListener(this.pieceDeplacement);
            }
            this.pieceDeplacement = new PieceDeplacement(this, this.puits);
            this.addMouseMotionListener(this.pieceDeplacement);
        }

        repaint();
    }

    public int getTaille() {
        return this.taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
        repaint();
    }

    private void setVuePiece(Piece piece) {
        this.vuePiece = (piece != null) ? new VuePiece(piece, taille) : null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Puits.MODIFICATION_PIECE_ACTUELLE.equals(evt.getPropertyName())) {
            setVuePiece((Piece) evt.getNewValue());
            repaint();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(puits.getLargeur() * taille, puits.getProfondeur() * taille);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (puits != null) {
            Element[][] grille = puits.getGrille();
            for (int y = 0; y < grille.length; y++) {
                for (int x = 0; x < grille[y].length; x++) {
                    int px = x * taille;
                    int py = y * taille;

                    if (grille[y][x] != null) {
                        g.setColor(grille[y][x].getCouleur().getCouleurPourAfficher());
                    } else {
                        g.setColor(Color.BLACK);
                    }

                    g.fillRect(px, py, taille, taille);
                    g.setColor(Color.GRAY);
                    g.drawRect(px, py, taille, taille);
                }
            }
        }

        if (vuePiece != null) {
            vuePiece.afficherPiece(g);
        }
    }
}
