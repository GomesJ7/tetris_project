package fr.eseo.e3.ppo.projet.blox.modele;

import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;

public class Puits {

    // Constantes pour définir les dimensions par défaut du puits
    public static final int LARGEUR_PAR_DEFAUT = 10;
    public static final int PROFONDEUR_PAR_DEFAUT = 15;

    // Attributs de l'instance : largeur et profondeur du puits
    private final int largeur;
    private final int profondeur;

    // Grille représentant l'état interne du puits (cases vides = null)
    private final Element[][] grille;

    // Pièces utilisées dans le jeu : actuelle (en jeu) et suivante
    private Piece pieceActuelle;
    private Piece pieceSuivante;

    // Constructeur par défaut, utilise les valeurs de largeur et profondeur par défaut
    public Puits() {
        this(LARGEUR_PAR_DEFAUT, PROFONDEUR_PAR_DEFAUT);
    }

    // Constructeur avec paramètres personnalisés
    public Puits(int largeur, int profondeur) {
        // Vérifie que la largeur est comprise dans les bornes autorisées
        if (largeur < 5 || largeur > 15)
            throw new IllegalArgumentException("La largeur doit être comprise entre 5 et 15.");

        // Vérifie que la profondeur est dans l’intervalle autorisé
        if (profondeur < 15 || profondeur > 25)
            throw new IllegalArgumentException("La profondeur doit être comprise entre 15 et 25.");

        // Initialise les attributs avec les valeurs passées en paramètre
        this.largeur = largeur;
        this.profondeur = profondeur;

        // Crée la grille vide avec les dimensions données
        this.grille = new Element[profondeur][largeur];
    }

    // Getter pour la largeur du puits
    public int getLargeur() {
        return this.largeur;
    }

    // Getter pour la profondeur (hauteur) du puits
    public int getProfondeur() {
        return this.profondeur;
    }

    // Retourne la grille entière (utile pour des tests ou affichage)
    public Element[][] getGrille() {
        return this.grille;
    }

    // Retourne la pièce actuellement en jeu
    public Piece getPieceActuelle() {
        return this.pieceActuelle;
    }

    // Retourne la pièce qui viendra après la pièce actuelle
    public Piece getPieceSuivante() {
        return this.pieceSuivante;
    }

    // Ajoute une pièce au puits : chaque élément de la pièce est copié dans la grille s’il est dans les bornes
    public void ajouterPiece(Piece piece) {
        for (Element e : piece.getElements()) {
            Coordonnees coord = e.getCoordonnees();

            // Vérifie que la coordonnée est bien dans les limites de la grille
            if (coord.getOrdonnee() >= 0 && coord.getOrdonnee() < profondeur &&
                coord.getAbscisse() >= 0 && coord.getAbscisse() < largeur) {

                // Place l'élément dans la case correspondante
                grille[coord.getOrdonnee()][coord.getAbscisse()] = e;
            }
        }
    }

    // Vérifie si la première ligne du puits est entièrement remplie (puits plein)
    public boolean estPlein() {
        for (int i = 0; i < largeur; i++) {
            if (grille[0][i] == null)  // Une case vide → pas plein
                return false;
        }
        return true;
    }

    // Supprime une ligne complète et décale les lignes au-dessus d'une case vers le bas
    public void clearLigne(int ligne) {
        for (int i = ligne; i > 0; i--) {
            for (int j = 0; j < largeur; j++) {
                grille[i][j] = grille[i - 1][j];
            }
        }
        // Réinitialise la première ligne (tout en haut)
        for (int j = 0; j < largeur; j++) {
            grille[0][j] = null;
        }
    }

    // Définit la prochaine pièce : si une pièce suivante existe déjà, elle devient la pièce actuelle
    public void setPieceSuivante(Piece piece) {
        if (this.pieceSuivante != null) {
            // La pièce suivante devient la pièce actuelle
            this.pieceActuelle = this.pieceSuivante;

            // On positionne cette pièce actuelle à (largeur / 2, -4) selon les consignes
            this.pieceActuelle.setPosition(this.largeur / 2, -4);
        }

        // La nouvelle pièce passée en paramètre devient la pièce suivante
        this.pieceSuivante = piece;

        // Lien entre la pièce et le puits (pour que la pièce sache dans quel puits elle est)
        piece.setPuits(this);
    }

    // Affiche un résumé textuel du puits, de ses dimensions et des pièces actuelles/suivantes
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Dimensions du puits
        sb.append("Puits : Dimension ").append(largeur).append(" x ").append(profondeur).append("\n");

        // Affichage de la pièce actuelle (ou <aucune>)
        sb.append("Piece Actuelle : ");
        if (pieceActuelle != null)
            sb.append(pieceActuelle.toString());
        else
            sb.append("<aucune>");
        sb.append("\n");

        // Affichage de la pièce suivante (ou <aucune>)
        sb.append("Piece Suivante : ");
        if (pieceSuivante != null)
            sb.append(pieceSuivante.toString());
        else
            sb.append("<aucune>");

        return sb.toString();
    }
}
