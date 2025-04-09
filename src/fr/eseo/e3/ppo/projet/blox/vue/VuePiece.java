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

    public VuePiece(Piece piece, int taille) {
        this.piece = piece;
        this.taille = taille;
    }

    public VuePiece(Piece piece) {
        this(piece, TAILLE_PAR_DEFAUT);
    }

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        afficherPiece(g);
    }

    public void afficherPiece(Graphics g) {
        if (piece != null) {
            System.out.println("Affichage de la pièce : " + piece); 
            for (Element e : piece.getElements()) {
                Coordonnees c = e.getCoordonnees();
                int x = c.getAbscisse();
                int y = c.getOrdonnee();

                g.setColor(Color.RED);
                g.fillRect(x * taille, y * taille, taille, taille);
                g.setColor(Color.BLACK);
                g.drawRect(x * taille, y * taille, taille, taille);
            }
        } else {
            System.out.println("Aucune pièce à afficher.");
        }
    }


}
