package fr.eseo.e3.ppo.projet.blox.modele.pieces;

import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;

public abstract class Piece {

    protected Puits puits;

    public abstract Element[] getElements();

    public abstract void setPosition(int abscisse, int ordonnee);

    public void setPuits(Puits puits) {
        this.puits = puits;
    }

    public Puits getPuits() {
        return this.puits;
    }

    public abstract void deplacerDe(int deltaX, int deltaY) throws IllegalArgumentException;

    public abstract void tourner(boolean sensHoraire);

    /**
     * Renvoie l'élément de référence (par convention le premier).
     */
    public Element getElementReference() {
        return getElements()[0];
    }

    /**
     * Vérifie si un déplacement est possible dans le puits (pas de sortie ou de collision).
     */
    public boolean deplacementPossible(int dx, int dy) {
        for (Element e : getElements()) {
            int newX = e.getCoordonnees().getAbscisse() + dx;
            int newY = e.getCoordonnees().getOrdonnee() + dy;

            if (newX < 0 || newX >= puits.getLargeur() || newY >= puits.getProfondeur()) {
                return false;
            }

            if (newY >= 0 && puits.getGrille()[newY][newX] != null) {
                return false;
            }
        }
        return true;
    }
}
