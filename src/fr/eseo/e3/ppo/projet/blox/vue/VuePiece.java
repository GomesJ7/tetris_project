package fr.eseo.e3.ppo.projet.blox.vue;

import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Classe représentant l'affichage graphique d’une pièce Tetris.
 * Elle peut être utilisée aussi bien dans la vue du puits que dans un panneau
 * de prévisualisation (ex : pièce suivante).
 */
public class VuePiece extends JPanel {

    public static final int TAILLE_PAR_DEFAUT = 15; // taille d’un bloc (carré)

    private Piece piece; // La pièce à afficher
    private int taille;  // La taille de chaque carré (pour le zoom ou miniature)

    /**
     * Constructeur avec taille personnalisée.
     * @param piece La pièce à dessiner
     * @param taille La taille de chaque bloc de la pièce
     */
    public VuePiece(Piece piece, int taille) {
        this.piece = piece;
        this.taille = taille;
    }

    /**
     * Constructeur par défaut, utilise TAILLE_PAR_DEFAUT.
     */
    public VuePiece(Piece piece) {
        this(piece, TAILLE_PAR_DEFAUT);
    }

    // === Getters / Setters ===

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        repaint(); // Redessine la pièce si elle a changé
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
        repaint(); // Redessine avec une nouvelle échelle
    }

    /**
     * Méthode standard de Swing appelée automatiquement pour dessiner le composant.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fond blanc
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Dessin de la pièce à l'origine (0,0)
        afficherPiece(g);
    }

    /**
     * Affiche la pièce à l’origine (0, 0).
     * Peut être redirigée vers la version avec décalage.
     */
    public void afficherPiece(Graphics g) {
        afficherPiece(g, 0, 0);
    }

    /**
     * Affiche la pièce avec un décalage (utile pour l'affichage centré ou miniature).
     * @param g Contexte graphique
     * @param xOffset Décalage horizontal
     * @param yOffset Décalage vertical
     */
    public void afficherPiece(Graphics g, int xOffset, int yOffset) {
        if (piece != null) {
            for (Element e : piece.getElements()) {
                Coordonnees c = e.getCoordonnees();

                // Calcule la position graphique
                int x = xOffset + c.getAbscisse() * taille;
                int y = yOffset + c.getOrdonnee() * taille;

                // Remplit le carré avec la couleur de l'élément
                g.setColor(e.getCouleur().getCouleurPourAfficher());
                g.fill3DRect(x, y, taille, taille, true);

                // Contour noir
                g.setColor(Color.BLACK);
                g.drawRect(x, y, taille, taille);
            }
        }
    }
}
