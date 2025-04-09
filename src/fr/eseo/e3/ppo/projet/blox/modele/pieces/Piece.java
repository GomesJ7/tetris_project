package fr.eseo.e3.ppo.projet.blox.modele.pieces;

import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;

public abstract class Piece {

    // Chaque pièce connaît le puits dans lequel elle se trouve
    protected Puits puits;

    // Méthode abstraite qui retourne tous les éléments (blocs) de la pièce
    public abstract Element[] getElements();

    // Méthode abstraite pour changer la position de la pièce
    public abstract void setPosition(int abscisse, int ordonnee);

    // Associe cette pièce à un puits
    public void setPuits(Puits puits) {
        this.puits = puits;
    }

    // Permet d’accéder au puits dans lequel la pièce a été placée
    public Puits getPuits() {
        return this.puits;
    }

    // Méthode abstraite pour déplacer la pièce
    public abstract void deplacerDe(int deltaX, int deltaY) throws IllegalArgumentException;

    // Nouvelle méthode abstraite pour faire tourner la pièce
    public abstract void tourner(boolean sensHoraire);
}
