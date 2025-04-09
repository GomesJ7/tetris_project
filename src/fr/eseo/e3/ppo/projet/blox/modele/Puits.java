package fr.eseo.e3.ppo.projet.blox.modele;

import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Puits {

    public static final int LARGEUR_PAR_DEFAUT = 10;
    public static final int PROFONDEUR_PAR_DEFAUT = 15;

    public static final String MODIFICATION_PIECE_ACTUELLE = "pieceActuelle";
    public static final String MODIFICATION_PIECE_SUIVANTE = "pieceSuivante";

    private final int largeur;
    private final int profondeur;
    private final Element[][] grille;

    private Piece pieceActuelle;
    private Piece pieceSuivante;

    private final PropertyChangeSupport pcs;

    public Puits() {
        this(LARGEUR_PAR_DEFAUT, PROFONDEUR_PAR_DEFAUT);
    }

    public Puits(int largeur, int profondeur) {
        if (largeur < 5 || largeur > 15)
            throw new IllegalArgumentException("La largeur doit être comprise entre 5 et 15.");
        if (profondeur < 15 || profondeur > 25)
            throw new IllegalArgumentException("La profondeur doit être comprise entre 15 et 25.");

        this.largeur = largeur;
        this.profondeur = profondeur;
        this.grille = new Element[profondeur][largeur];
        this.pcs = new PropertyChangeSupport(this);
    }

    public int getLargeur() {
        return this.largeur;
    }

    public int getProfondeur() {
        return this.profondeur;
    }

    public Element[][] getGrille() {
        return this.grille;
    }

    public Piece getPieceActuelle() {
        return this.pieceActuelle;
    }

    public Piece getPieceSuivante() {
        return this.pieceSuivante;
    }

    
    public void setPieceActuelle(Piece piece) {
        Piece anciennePiece = this.pieceActuelle;
        this.pieceActuelle = piece;
        if (piece != null) {
            piece.setPuits(this);
        }
        pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE, anciennePiece, piece);
    }

    
    public void ajouterPiece(Piece piece) {
        for (Element e : piece.getElements()) {
            Coordonnees coord = e.getCoordonnees();
            if (coord.getOrdonnee() >= 0 && coord.getOrdonnee() < profondeur &&
                coord.getAbscisse() >= 0 && coord.getAbscisse() < largeur) {
                grille[coord.getOrdonnee()][coord.getAbscisse()] = e;
            }
        }
    }

    public boolean estPlein() {
        for (int i = 0; i < largeur; i++) {
            if (grille[0][i] == null)
                return false;
        }
        return true;
    }

    public void clearLigne(int ligne) {
        for (int i = ligne; i > 0; i--) {
            for (int j = 0; j < largeur; j++) {
                grille[i][j] = grille[i - 1][j];
            }
        }
        for (int j = 0; j < largeur; j++) {
            grille[0][j] = null;
        }
    }

    public void setPieceSuivante(Piece piece) {
        Piece ancienneActuelle = this.pieceActuelle;
        Piece ancienneSuivante = this.pieceSuivante;

        if (this.pieceSuivante != null) {
            this.pieceActuelle = this.pieceSuivante;
            this.pieceActuelle.setPuits(this);
            this.pieceActuelle.setPosition(this.largeur / 2, -4);
            pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE, ancienneActuelle, this.pieceActuelle);
        }

        this.pieceSuivante = piece;
        piece.setPuits(this);
        pcs.firePropertyChange(MODIFICATION_PIECE_SUIVANTE, ancienneSuivante, this.pieceSuivante);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Puits : Dimension ").append(largeur).append(" x ").append(profondeur).append("\n");

        sb.append("Piece Actuelle : ");
        if (pieceActuelle != null)
            sb.append(pieceActuelle.toString());
        else
            sb.append("<aucune>");
        sb.append("\n");

        sb.append("Piece Suivante : ");
        if (pieceSuivante != null)
            sb.append(pieceSuivante.toString());
        else
            sb.append("<aucune>");

        return sb.toString();
    }
}