package fr.eseo.e3.ppo.projet.blox.modele.pieces;

import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;

// Classe abstraite représentant une pièce du jeu (par exemple un Tetromino)
public abstract class Piece {

    // Chaque pièce connaît le puits dans lequel elle se trouve
    protected Puits puits;

    // Méthode abstraite qui retourne tous les éléments (blocs) de la pièce
    // Les classes filles doivent l'implémenter pour définir leur forme
    public abstract Element[] getElements();

    // Méthode abstraite pour changer la position de la pièce
    // L’abscisse et l’ordonnée définissent le point de référence (souvent le 1er bloc)
    public abstract void setPosition(int abscisse, int ordonnee);

    // Associe cette pièce à un puits (appelé lors de setPieceSuivante dans Puits)
    public void setPuits(Puits puits) {
        this.puits = puits;
    }

    // Permet d’accéder au puits dans lequel la pièce a été placée
    public Puits getPuits() {
        return this.puits;
    }
}
