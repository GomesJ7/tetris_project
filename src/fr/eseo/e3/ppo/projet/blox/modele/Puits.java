package fr.eseo.e3.ppo.projet.blox.modele;

import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;

public class Puits {

    // Dimensions par défaut (doivent respecter : largeur entre 5 et 15, profondeur entre 15 et 25)
    public static final int LARGEUR_PAR_DEFAUT = 10;
    public static final int PROFONDEUR_PAR_DEFAUT = 15;

    // Attributs de la classe : largeur (nombre de colonnes) et hauteur (nombre de lignes)
    private final int largeur;
    private final int hauteur;
    // La grille représente le puits sous forme de tableau 2D d'Element (les cases vides sont null)
    private final Element[][] grille;

    // La pièce actuellement en train de tomber (peut être null)
    private Piece pieceActuelle;
    // La pièce qui sera la prochaine à tomber (peut être null)
    private Piece pieceSuivante;

    // Constructeur par défaut qui utilise les dimensions par défaut
    public Puits() {
        this(LARGEUR_PAR_DEFAUT, PROFONDEUR_PAR_DEFAUT);
    }

    // Constructeur avec des dimensions spécifiées
    public Puits(int largeur, int hauteur) {
        // Vérifie que la largeur est dans l'intervalle [5, 15]
        if (largeur < 5 || largeur > 15) {
            throw new IllegalArgumentException("La largeur doit être comprise entre 5 et 15.");
        }
        // Vérifie que la hauteur (profondeur) est dans l'intervalle [15, 25]
        if (hauteur < 15 || hauteur > 25) {
            throw new IllegalArgumentException("La hauteur (profondeur) doit être comprise entre 15 et 25.");
        }
        this.largeur = largeur;                      // Initialise la largeur du puits
        this.hauteur = hauteur;                      // Initialise la hauteur (profondeur) du puits
        this.grille = new Element[hauteur][largeur]; // Crée la grille de taille hauteur x largeur
    }

    // Accesseur pour obtenir la largeur du puits
    public int getLargeur() {
        return this.largeur;
    }

    // Accesseur pour obtenir la hauteur (profondeur) du puits
    public int getHauteur() {
        return this.hauteur;
    }

    // Accesseur pour obtenir la pièce actuellement en jeu
    public Piece getPieceActuelle() {
        return this.pieceActuelle;
    }

    // Accesseur pour obtenir la pièce suivante
    public Piece getPieceSuivante() {
        return this.pieceSuivante;
    }

    // Méthode pour ajouter une pièce dans le puits
    // Elle parcourt les éléments de la pièce et les place dans la grille, si leurs coordonnées sont valides
    public void ajouterPiece(Piece piece) {
        for (Element e : piece.getElements()) {                     // Pour chaque élément de la pièce
            Coordonnees coord = e.getCoordonnees();                  // Récupère les coordonnées de l'élément
            // Vérifie que les coordonnées sont dans la plage de la grille
            if (coord.getOrdonnee() >= 0 && coord.getOrdonnee() < hauteur &&
                coord.getAbscisse() >= 0 && coord.getAbscisse() < largeur) {
                grille[coord.getOrdonnee()][coord.getAbscisse()] = e;  // Place l'élément dans la grille
            }
        }
    }

    // Méthode pour vérifier si le puits est plein
    // Le puits est considéré plein si la première ligne de la grille est entièrement occupée
    public boolean estPlein() {
        for (int j = 0; j < largeur; j++) {  // Parcourt chaque colonne de la première ligne (ligne 0)
            if (grille[0][j] == null) {        // Si une case est vide
                return false;                // Le puits n'est pas plein
            }
        }
        return true;  // Sinon, toutes les cases de la première ligne sont occupées
    }

    // Méthode pour supprimer une ligne (clearLigne) du puits lorsqu'elle est complètement remplie
    public void clearLigne(int ligne) {
        // Pour chaque ligne à partir de la ligne donnée jusqu'à la première ligne,
        // on déplace les lignes supérieures d'une position vers le bas
        for (int i = ligne; i > 0; i--) {       // Pour chaque ligne de 'ligne' à 1
            for (int j = 0; j < largeur; j++) {   // Parcours de chaque colonne de la ligne
                grille[i][j] = grille[i - 1][j];  // Déplace l'élément de la ligne supérieure dans la case actuelle
            }
        }
        // On vide la première ligne, qui vient d'être dupliquée
        for (int j = 0; j < largeur; j++) {       // Pour chaque colonne de la première ligne
            grille[0][j] = null;                // On réinitialise la case à null
        }
    }

    // Méthode pour afficher l'état actuel du puits dans la console (utile pour débogage)
    public void afficher() {
        for (int i = 0; i < hauteur; i++) {       // Parcours de chaque ligne de la grille
            for (int j = 0; j < largeur; j++) {     // Parcours de chaque colonne de la ligne
                // Affiche "X" si la case contient un élément, sinon affiche "."
                if (grille[i][j] != null) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();                   // Passage à la ligne suivante après chaque ligne de la grille
        }
    }

    // Méthode pour définir la pièce suivante dans le puits
    // Si une pièce suivante existait déjà, elle devient la pièce actuelle et sa position est ajustée
    public void setPieceSuivante(Piece piece) {
        if (this.pieceSuivante != null) {                   // Si une pièce suivante est déjà présente
            this.pieceActuelle = this.pieceSuivante;        // La pièce actuelle devient l'ancienne pièce suivante
            // Définir la position de la pièce actuelle pour qu'elle commence à tomber
            // Positionner la pièce à (largeur/2, -4) selon les consignes
            this.pieceActuelle.setPosition(largeur / 2, -4);
        }
        this.pieceSuivante = piece;                         // Définir la nouvelle pièce comme pièce suivante
    }

    // Redéfinition de la méthode toString() pour afficher l'état du puits
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Première ligne : afficher les dimensions du puits
        sb.append("Puits : Dimension ").append(largeur).append(" x ").append(hauteur).append("\n");
        // Deuxième ligne : afficher la pièce actuelle ou "<aucune>" si elle n'est pas définie
        sb.append("Piece Actuelle : ");
        if (pieceActuelle != null) {
            sb.append(pieceActuelle.toString());
        } else {
            sb.append("<aucune>");
        }
        sb.append("\n");
        // Troisième ligne : afficher la pièce suivante ou "<aucune>" si elle n'est pas définie
        sb.append("Piece Suivante : ");
        if (pieceSuivante != null) {
            sb.append(pieceSuivante.toString());
        } else {
            sb.append("<aucune>");
        }
        return sb.toString();  // Retourne la chaîne complète
    }
}
