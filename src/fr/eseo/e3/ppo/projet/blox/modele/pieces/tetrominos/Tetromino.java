package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;

public abstract class Tetromino extends Piece {
    protected Element[] elements = new Element[4];

    public Tetromino(final Coordonnees coordonnees, final Couleur couleur) {
        this.elements = new Element[4];
        setElements(coordonnees, couleur);
    }

    protected abstract void setElements(final Coordonnees coordonnees, final Couleur couleur);

    @Override
    public Element[] getElements() {
        return elements;
    }

    @Override
    public void setPosition(int abscisse, int ordonnee) {
        Coordonnees ref = elements[0].getCoordonnees();
        int deltaX = abscisse - ref.getAbscisse();
        int deltaY = ordonnee - ref.getOrdonnee();
        for (Element e : elements) {
            Coordonnees current = e.getCoordonnees();
            int newX = current.getAbscisse() + deltaX;
            int newY = current.getOrdonnee() + deltaY;
            e.setCoordonnees(new Coordonnees(newX, newY));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(" :\n");
        for (Element e : elements) {
            sb.append("\t").append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void deplacerDe(int deltaX, int deltaY) throws IllegalArgumentException {
        boolean deplacementValide =
            (deltaX == -1 && deltaY == 0) ||  // gauche
            (deltaX == 1 && deltaY == 0)  ||  // droite
            (deltaX == 0 && deltaY == 1);     // bas

        if (!deplacementValide) {
            throw new IllegalArgumentException("Déplacement invalide : seuls les déplacements gauche, droite ou bas sont autorisés.");
        }

        for (Element e : elements) {
            e.deplacerDe(deltaX, deltaY);
        }
    }

    @Override
    public void tourner(boolean sensHoraire) {
        Coordonnees ref = elements[0].getCoordonnees();
        int xRef = ref.getAbscisse();
        int yRef = ref.getOrdonnee();

        Coordonnees[] nouvellesCoordonnees = new Coordonnees[elements.length];
        nouvellesCoordonnees[0] = ref; // l'élément de référence reste à sa place

        for (int i = 1; i < elements.length; i++) {
            Coordonnees c = elements[i].getCoordonnees();
            int dx = c.getAbscisse() - xRef;
            int dy = c.getOrdonnee() - yRef;

            int newX, newY;
            if (sensHoraire) {
                newX = xRef - dy;
                newY = yRef + dx;
            } else {
                newX = xRef + dy;
                newY = yRef - dx;
            }

            nouvellesCoordonnees[i] = new Coordonnees(newX, newY);
        }

        // Vérifie que la rotation est possible dans les limites du puits
        boolean rotationPossible = true;
        for (Coordonnees c : nouvellesCoordonnees) {
            int x = c.getAbscisse();
            int y = c.getOrdonnee();
            if (puits != null && (x < 0 || x >= puits.getLargeur() || y >= puits.getProfondeur())) {
                rotationPossible = false;
                break;
            }
        }

        if (rotationPossible) {
            for (int i = 1; i < elements.length; i++) {
                elements[i].setCoordonnees(nouvellesCoordonnees[i]);
            }
        }
        // Sinon, on ignore la rotation
    }
}
