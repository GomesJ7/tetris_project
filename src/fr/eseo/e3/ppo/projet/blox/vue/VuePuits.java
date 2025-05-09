package fr.eseo.e3.ppo.projet.blox.vue;

import fr.eseo.e3.ppo.projet.blox.controleur.ControleClavier;
import fr.eseo.e3.ppo.projet.blox.controleur.PieceDeplacement;
import fr.eseo.e3.ppo.projet.blox.controleur.PieceRotation;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VuePuits extends JPanel implements PropertyChangeListener {

    public static final int TAILLE_PAR_DEFAUT = 15;

    private Puits puits;
    private int taille;
    private VuePiece vuePiece;
    private VueTas vueTas;
    private PieceDeplacement pieceDeplacement;
    private PieceRotation pieceRotation;
    private ControleClavier controleClavier;

    public VuePuits(Puits puits) {
        this(puits, TAILLE_PAR_DEFAUT);
    }

    public VuePuits(Puits puits, int taille) {
        this.taille = taille;
        this.setPuits(puits);
    }

    public Puits getPuits() {
        return this.puits;
    }

    public VueTas getVueTas() {
        return this.vueTas;
    }

    public void setPuits(Puits nouveauPuits) {
        if (this.puits != null) {
            this.puits.removePropertyChangeListener(this);
        }

        this.puits = nouveauPuits;

        if (this.puits != null) {
            this.puits.addPropertyChangeListener(this);
            setVuePiece(this.puits.getPieceActuelle());
            this.vueTas = new VueTas(this);

            if (this.pieceDeplacement != null) {
                this.removeMouseMotionListener(this.pieceDeplacement);
                this.removeMouseListener(this.pieceDeplacement);
                this.removeMouseWheelListener(this.pieceDeplacement);
            }

            if (this.pieceRotation != null) {
                this.removeMouseListener(this.pieceRotation);
            }

            this.pieceDeplacement = new PieceDeplacement(this, this.puits);
            this.addMouseMotionListener(this.pieceDeplacement);
            this.addMouseListener(this.pieceDeplacement);
            this.addMouseWheelListener(this.pieceDeplacement);

            this.pieceRotation = new PieceRotation(this.puits);
            this.addMouseListener(this.pieceRotation);

            // Ajout du clavier uniquement pour la version 3
            if (puits.isControlesClavier()) {
                if (this.controleClavier != null) {
                    this.removeKeyListener(this.controleClavier);
                }
                this.controleClavier = new ControleClavier(this.puits, this);
                this.addKeyListener(this.controleClavier);
                this.setFocusable(true);
                this.requestFocusInWindow();
            }
        }

        repaint();
    }

    public int getTaille() {
        return this.taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
        if (this.vuePiece != null) {
            this.vuePiece.setTaille(taille);
        }
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
        return new Dimension(puits.getLargeur() * taille + 150, puits.getProfondeur() * taille);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fond général en gris foncé
        g.setColor(Color.DARK_GRAY);
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

        if (vueTas != null) {
            vueTas.afficher((Graphics2D) g);
        }

        if (vuePiece != null) {
            vuePiece.afficherPiece(g);
        }

        if (puits.isModeMultiPiece() && puits.getFilePiecesSuivantes() != null) {
            Graphics2D g2d = (Graphics2D) g;
            int caseTaille = taille / 2;
            int offsetX = puits.getLargeur() * taille + 20;
            int offsetY = 60;
            int spacing = 20;
            int fondLargeur = caseTaille * 6;
            int fondHauteurUnitaire = caseTaille * 5;

            String titre = "Pièces suivantes";
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            int titreWidth = g2d.getFontMetrics().stringWidth(titre);
            g2d.setColor(Color.WHITE);
            g2d.drawString(titre, offsetX + (fondLargeur - titreWidth) / 2 + 5, 30);

            int currentY = offsetY;
            for (Piece p : puits.getFilePiecesSuivantes()) {
                VuePiece vp = new VuePiece(p, caseTaille);
                int minX = p.getElements()[0].getCoordonnees().getAbscisse();
                int minY = p.getElements()[0].getCoordonnees().getOrdonnee();
                for (var e : p.getElements()) {
                    minX = Math.min(minX, e.getCoordonnees().getAbscisse());
                    minY = Math.min(minY, e.getCoordonnees().getOrdonnee());
                }
                int drawX = offsetX + (fondLargeur - caseTaille * 4) / 2 - minX * caseTaille;
                int drawY = currentY + (fondHauteurUnitaire - caseTaille * 4) / 2 - minY * caseTaille;
                vp.afficherPiece(g, drawX, drawY);
                currentY += fondHauteurUnitaire + spacing;
            }
        }

        if (puits.isJeuTermine()) {
            Graphics2D g2d = (Graphics2D) g;
            String msg = "PERDU";
            g2d.setFont(new Font("Arial", Font.BOLD, 48));
            int textWidth = g2d.getFontMetrics().stringWidth(msg);
            int textHeight = g2d.getFontMetrics().getHeight();
            int x = (getWidth() - textWidth) / 2;
            int y = getHeight() / 2;

            int padding = 20;
            g2d.setColor(new Color(100, 100, 100)); // gris clair pour contraste
            g2d.fillRect(x - padding, y - textHeight, textWidth + 2 * padding, textHeight + padding);
            g2d.setColor(Color.WHITE);
            g2d.drawString(msg, x, y);
        }
    }
}