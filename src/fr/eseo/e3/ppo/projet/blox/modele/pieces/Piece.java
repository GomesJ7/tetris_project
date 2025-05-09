package fr.eseo.e3.ppo.projet.blox.modele.pieces;

import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.BloxException; // 🆕 Import de l'exception

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

    // 🆕 Ajout de throws BloxException
    public abstract void deplacerDe(int deltaX, int deltaY) throws BloxException;

    // 🆕 Ajout de throws BloxException
    public abstract void tourner(boolean sensHoraire) throws BloxException;

    public Element getElementReference() {
        return getElements()[0];
    }

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
