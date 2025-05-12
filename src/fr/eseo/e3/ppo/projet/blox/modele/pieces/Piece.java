package fr.eseo.e3.ppo.projet.blox.modele.pieces;

import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.BloxException;

/**
 * Classe abstraite représentant une pièce de jeu (ex : O, I, T, etc.).
 * Une pièce est composée de plusieurs éléments (blocs unitaires) disposés dans le puits.
 *
 * Cette classe définit l'interface commune à toutes les formes de pièces,
 * selon le principe d’abstraction et de spécialisation (héritage).
 */
public abstract class Piece {

    // Référence au puits dans lequel la pièce est insérée
    protected Puits puits;

    // === Méthodes abstraites (à implémenter dans les sous-classes) ===

    /**
     * Retourne les éléments constituant la pièce.
     */
    public abstract Element[] getElements();

    /**
     * Définit la position de référence de la pièce dans le puits.
     * Souvent, l’élément d’index 0 est utilisé comme point d’ancrage.
     */
    public abstract void setPosition(int abscisse, int ordonnee);

    /**
     * Fait descendre ou déplacer latéralement la pièce, si possible.
     * Peut lever une BloxException en cas de collision ou de sortie du puits.
     */
    public abstract void deplacerDe(int deltaX, int deltaY) throws BloxException;

    /**
     * Effectue une rotation de la pièce autour de son élément de référence.
     * @param sensHoraire vrai si la rotation est dans le sens des aiguilles d’une montre
     * @throws BloxException si la rotation est impossible (collision ou bord)
     */
    public abstract void tourner(boolean sensHoraire) throws BloxException;

    // === Méthodes concrètes partagées ===

    /**
     * Définit le puits dans lequel se trouve cette pièce.
     * Nécessaire pour valider les mouvements et accéder à la grille.
     */
    public void setPuits(Puits puits) {
        this.puits = puits;
    }

    /**
     * Retourne le puits associé à cette pièce.
     */
    public Puits getPuits() {
        return this.puits;
    }

    /**
     * Retourne l’élément de référence de la pièce.
     * Par convention, il s’agit du premier élément (index 0).
     */
    public Element getElementReference() {
        return getElements()[0];
    }

    /**
     * Vérifie si un déplacement est possible (sans collision ni sortie de la grille).
     * @param dx déplacement horizontal
     * @param dy déplacement vertical
     * @return vrai si tous les éléments peuvent se déplacer
     */
    public boolean deplacementPossible(int dx, int dy) {
        for (Element e : getElements()) {
            int newX = e.getCoordonnees().getAbscisse() + dx;
            int newY = e.getCoordonnees().getOrdonnee() + dy;

            // Test de sortie des limites du puits
            if (newX < 0 || newX >= puits.getLargeur() || newY >= puits.getProfondeur()) {
                return false;
            }

            // Test de collision avec un élément du tas
            if (newY >= 0 && puits.getGrille()[newY][newX] != null) {
                return false;
            }
        }
        return true;
    }
}
