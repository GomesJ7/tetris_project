package fr.eseo.e3.ppo.projet.blox.vue;

import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class VuePiece extends JPanel {

    public static final int TAILLE_PAR_DEFAUT = 15;

    private Piece piece;
    private int taille;

    // Constructeur avec taille personnalisée
    public VuePiece(Piece piece, int taille) {
        this.piece = piece;
        this.taille = taille;
    }

    // Constructeur avec taille par défaut
    public VuePiece(Piece piece) {
        this(piece, TAILLE_PAR_DEFAUT);
    }

    // Getters / setters
    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        repaint();
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
        repaint();
    }

    // Paint : on délègue à afficherPiece()
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        afficherPiece(g);
    }

    // Nouvelle méthode demandée par le point 3.3.8
    public void afficherPiece(Graphics g) {
        if (piece != null) {
            for (Element e : piece.getElements()) {
                Coordonnees c = e.getCoordonnees();
                int x = c.getAbscisse();
                int y = c.getOrdonnee();

                // Dessin temporaire en gris (la couleur sera améliorée en 3.3.9)
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x * taille, y * taille, taille, taille);

                g.setColor(Color.BLACK);
                g.drawRect(x * taille, y * taille, taille, taille);
            }
        }
    }
}
