package fr.eseo.e3.ppo.projet.blox.vue;

import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VuePuits extends JPanel implements PropertyChangeListener {

    public static final int TAILLE_PAR_DEFAUT = 15;

    private Puits puits;
    private int taille;
    private VuePiece vuePiece;

    // Constructeur avec taille par défaut
    public VuePuits(Puits puits) {
        this(puits, TAILLE_PAR_DEFAUT);
    }

    // Constructeur avec taille personnalisée
    public VuePuits(Puits puits, int taille) {
        this.taille = taille;
        setPuits(puits); // enregistre aussi comme listener
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

    // Méthode appelée automatiquement à chaque changement de propriété du puits
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Puits.MODIFICATION_PIECE_ACTUELLE.equals(evt.getPropertyName())) {
            Piece nouvelle = (Piece) evt.getNewValue();
            setVuePiece(nouvelle);
            repaint();
        }
    }

    // Méthode privée désormais : seule la classe elle-même peut changer la VuePiece
    private void setVuePiece(Piece piece) {
        this.vuePiece = (piece != null) ? new VuePiece(piece, taille) : null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fond blanc
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Dessin de la grille
        if (puits != null) {
            Element[][] grille = puits.getGrille();
            for (int y = 0; y < grille.length; y++) {
                for (int x = 0; x < grille[y].length; x++) {
                    if (grille[y][x] != null) {
                        g.setColor(Color.LIGHT_GRAY);
                        int px = x * taille;
                        int py = y * taille;
                        g.fillRect(px, py, taille, taille);
                        g.setColor(Color.BLACK);
                        g.drawRect(px, py, taille, taille);
                    }
                }
            }
        }

        // Affichage de la pièce actuelle via VuePiece
        if (vuePiece != null) {
            vuePiece.afficherPiece(g);
        }
    }
}
