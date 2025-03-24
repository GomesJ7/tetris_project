package fr.eseo.e3.ppo.projet.blox.modele.pieces;

import fr.eseo.e3.ppo.projet.blox.modele.Element;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;

public class Puits {

    // Constantes pour les dimensions par défaut du puits
    public static final int LARGEUR_PAR_DEFAUT = 10;    // Largeur par défaut, dans l'intervalle [5, 15]
    public static final int PROFONDEUR_PAR_DEFAUT = 15;  // Profondeur par défaut, dans l'intervalle [15, 25]

    // Largeur du puits (nombre de colonnes)
    private final int largeur;
    // Hauteur du puits (nombre de lignes)
    private final int hauteur;
    // Grille représentant le puits : chaque case peut contenir un Element ou être vide (null)
    private final Element[][] grille;

    // La pièce qui est actuellement en train de tomber (peut être null si aucune pièce n'est active)
    private Piece pieceActuelle;
    // La pièce suivante à tomber (peut être null si non définie)
    private Piece pieceSuivante;

    // Constructeur par défaut : utilise les dimensions par défaut
    public Puits() {
        this(LARGEUR_PAR_DEFAUT, PROFONDEUR_PAR_DEFAUT);
    }

    // Constructeur avec dimensions spécifiées
    public Puits(int largeur, int hauteur) {
        this.largeur = largeur;                          // Initialisation de la largeur
        this.hauteur = hauteur;                          // Initialisation de la hauteur
        this.grille = new Element[hauteur][largeur];     // Création de la grille avec le nombre de lignes et colonnes
    }

    // Accesseur pour la largeur
    public int getLargeur() {
        return this.largeur;
    }

    // Accesseur pour la hauteur (ou profondeur)
    public int getHauteur() {
        return this.hauteur;
    }

    // Accesseur pour la pièce actuelle
    public Piece getPieceActuelle() {
        return this.pieceActuelle;
    }

    // Accesseur pour la pièce suivante
    public Piece getPieceSuivante() {
        return this.pieceSuivante;
    }

    // Méthode pour ajouter une pièce dans le puits
    // Cette méthode devra gérer le placement de la pièce dans la grille,
    // vérifier les collisions, et mettre à jour l'état du puits.
    public void ajouterPiece(Piece piece) {
        // Exemple simplifié : on parcourt les éléments de la pièce et on les place dans la grille
        // Cette implémentation est à compléter selon la logique de placement et de collision du jeu.
        for (Element e : piece.getElements()) { // Pour chaque élément de la pièce
            Coordonnees coord = e.getCoordonnees(); // Récupère les coordonnées de l'élément
            // Vérifie que les coordonnées sont dans la grille
            if (coord.getOrdonnee() >= 0 && coord.getOrdonnee() < hauteur &&
                coord.getAbscisse() >= 0 && coord.getAbscisse() < largeur) {
                grille[coord.getOrdonnee()][coord.getAbscisse()] = e; // Place l'élément dans la grille
            }
        }
    }

    // Méthode pour vérifier si le puits est plein (si la première ligne est complètement remplie)
    public boolean estPlein() {
        for (int j = 0; j < largeur; j++) { // Parcours de chaque colonne de la première ligne
            if (grille[0][j] == null) {      // Si une case de la première ligne est vide
                return false;              // Le puits n'est pas plein
            }
        }
        return true; // Si toutes les cases de la première ligne sont occupées, le puits est plein
    }

    // Méthode pour supprimer une ligne du puits si elle est complètement remplie
    public void clearLigne(int ligne) {
        // Déplacer toutes les lignes situées au-dessus de la ligne spécifiée vers le bas
        for (int i = ligne; i > 0; i--) {       // Pour chaque ligne à partir de la ligne donnée jusqu'à la première
            for (int j = 0; j < largeur; j++) {   // Parcours de chaque colonne dans la ligne
                grille[i][j] = grille[i - 1][j];  // Déplacer l'élément de la ligne supérieure dans la case actuelle
            }
        }
        // Réinitialiser la première ligne en la vidant
        for (int j = 0; j < largeur; j++) {       // Parcours de chaque colonne de la première ligne
            grille[0][j] = null;                // Mettre la case à null (vide)
        }
    }

    // Méthode pour afficher l'état actuel du puits dans la console (pour débogage)
    public void afficher() {
        for (int i = 0; i < hauteur; i++) {       // Parcours de chaque ligne du puits
            for (int j = 0; j < largeur; j++) {     // Parcours de chaque colonne de la ligne
                if (grille[i][j] != null) {         // Si la case contient un élément
                    System.out.print(" X ");      // Afficher "X" pour représenter un élément présent
                } else {
                    System.out.print(" . ");      // Afficher "." pour une case vide
                }
            }
            System.out.println();                   // Passage à la ligne suivante
        }
    }

    // Méthode pour définir la pièce suivante dans le puits
    // Si une pièce suivante était déjà définie, cette méthode place cette pièce comme pièce actuelle
    // et ajuste sa position pour qu'elle commence à tomber.
    public void setPieceSuivante(Piece piece) {
        if (this.pieceSuivante != null) { // Si une pièce suivante existe déjà
            this.pieceActuelle = this.pieceSuivante; // La pièce actuelle devient l'ancienne pièce suivante
            // Modifier la position de la pièce actuelle pour qu'elle commence à tomber
            // On utilise ici la méthode setPosition() définie dans l'interface Piece (ou dans la classe Tetromino)
            // La position donnée est (largeur/2, -4) selon l'énoncé
            this.pieceActuelle.setPosition(largeur / 2, -4);
        }
        // Dans tous les cas, on remplace la pièce suivante par la nouvelle pièce
        this.pieceSuivante = piece;
    }
}
