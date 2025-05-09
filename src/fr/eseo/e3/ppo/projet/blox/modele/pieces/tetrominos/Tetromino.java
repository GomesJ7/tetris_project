package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.BloxException; // 🆕 Exception custom
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

    // 🆕 Maintenant lève une BloxException
    @Override
    public void deplacerDe(int deltaX, int deltaY) throws BloxException {
        boolean deplacementValide =
            (deltaX == -1 && deltaY == 0) ||  // gauche
            (deltaX == 1 && deltaY == 0)  ||  // droite
            (deltaX == 0 && deltaY == 1);     // bas

        if (!deplacementValide) {
            throw new IllegalArgumentException("Déplacement invalide : seuls les déplacements gauche, droite ou bas sont autorisés.");
        }

        for (Element e : elements) {
            int newX = e.getCoordonnees().getAbscisse() + deltaX;
            int newY = e.getCoordonnees().getOrdonnee() + deltaY;

            // 🆕 Sortie du puits
            if (newX < 0 || newX >= puits.getLargeur()) {
                throw new BloxException("Sortie du puits (gauche/droite)", BloxException.BLOX_SORTIE_PUITS);
            }

            // 🆕 Collision avec le fond
            if (newY >= puits.getProfondeur()) {
                throw new BloxException("Collision avec le fond du puits", BloxException.BLOX_COLLISION);
            }

            // 🆕 Collision avec le tas
            if (newY >= 0 && puits.getTas().elementExiste(newX, newY)) {
                throw new BloxException("Collision avec le tas", BloxException.BLOX_COLLISION);
            }
        }

        // ✅ Si tout est OK, déplacement normal
        for (Element e : elements) {
            e.deplacerDe(deltaX, deltaY);
        }
    }

    // 🆕 Maintenant lève une BloxException
    @Override
    public void tourner(boolean sensHoraire) throws BloxException {
        Coordonnees ref = elements[0].getCoordonnees();
        int xRef = ref.getAbscisse();
        int yRef = ref.getOrdonnee();

        Coordonnees[] nouvellesCoordonnees = new Coordonnees[elements.length];
        nouvellesCoordonnees[0] = ref;

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

        // 🆕 Vérification des collisions ou sorties
        for (Coordonnees c : nouvellesCoordonnees) {
            int x = c.getAbscisse();
            int y = c.getOrdonnee();

            if (x < 0 || x >= puits.getLargeur()) {
                throw new BloxException("Sortie du puits en rotation", BloxException.BLOX_SORTIE_PUITS);
            }
            if (y >= puits.getProfondeur()) {
                throw new BloxException("Collision avec le fond en rotation", BloxException.BLOX_COLLISION);
            }
            if (y >= 0 && puits.getTas().elementExiste(x, y)) {
                throw new BloxException("Collision avec le tas en rotation", BloxException.BLOX_COLLISION);
            }
        }

        // ✅ Rotation autorisée
        for (int i = 1; i < elements.length; i++) {
            elements[i].setCoordonnees(nouvellesCoordonnees[i]);
        }
    }
}
