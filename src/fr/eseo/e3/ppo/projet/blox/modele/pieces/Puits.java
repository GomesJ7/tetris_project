package fr.eseo.e3.ppo.projet.blox.modele.pieces;

import fr.eseo.e3.ppo.projet.blox.modele.Element;

public class Puits {
    private final int largeur; // Largeur du Puits, c'est-à-dire le nombre de colonnes dans le Puits
    private final int hauteur; // Hauteur du Puits, c'est-à-dire le nombre de lignes dans le Puits
    private final Element[][] grille; // La grille qui représente le Puits, avec des cases de type Element

    // Constructeur de la classe Puits
    public Puits(int largeur, int hauteur) {
        this.largeur = largeur; // On initialise la largeur du Puits
        this.hauteur = hauteur; // On initialise la hauteur du Puits
        this.grille = new Element[hauteur][largeur]; // On initialise la grille avec la taille spécifiée par largeur et hauteur
    }

    // Méthode pour ajouter une pièce dans le Puits
    public void ajouterPiece(Piece piece) {
        // Implémentation nécessaire pour ajouter la pièce à la grille
        // Cette méthode devra gérer la logique de placement des pièces, vérifier les collisions, etc.
    }

    // Méthode pour vérifier si le Puits est plein (si la première ligne est complètement remplie)
    public boolean estPlein() {
        for (int i = 0; i < largeur; i++) { // On parcourt toutes les colonnes de la première ligne
            if (grille[0][i] == null) { // Si une case de la première ligne est vide
                return false; // Le Puits n'est pas plein
            }
        }
        return true; // Si toutes les cases de la première ligne sont remplies, le Puits est plein
    }

    // Méthode pour supprimer une ligne du Puits si elle est complètement remplie
    public void clearLigne(int ligne) {
        for (int i = ligne; i > 0; i--) { // On commence par la ligne spécifiée et on déplace toutes les lignes supérieures d'une position vers le bas
            for (int j = 0; j < largeur; j++) { // On parcourt chaque colonne de la ligne
                grille[i][j] = grille[i - 1][j]; // On déplace les éléments de la ligne supérieure vers la ligne actuelle
            }
        }
        // Une fois les lignes déplacées, on réinitialise la première ligne
        for (int i = 0; i < largeur; i++) { // On parcourt toutes les colonnes de la première ligne
            grille[0][i] = null; // On vide la première ligne
        }
    }

    // Méthode pour afficher l'état actuel du Puits (utile pour débogage et affichage)
    public void afficher() {
        for (int i = 0; i < hauteur; i++) { // On parcourt chaque ligne du Puits
            for (int j = 0; j < largeur; j++) { // On parcourt chaque colonne de la ligne
                if (grille[i][j] != null) { // Si la case contient un élément (une pièce)
                    System.out.print(" X "); // On affiche un "X" pour représenter une pièce
                } else {
                    System.out.print(" . "); // Si la case est vide, on affiche un point
                }
            }
            System.out.println(); // On passe à la ligne suivante pour l'affichage
        }
    }
}
