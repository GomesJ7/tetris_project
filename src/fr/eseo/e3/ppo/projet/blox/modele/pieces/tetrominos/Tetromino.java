package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.BloxException;
import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;

/**
 * Classe abstraite représentant un tétrimino générique (4 blocs).
 * Elle fournit une implémentation commune à toutes les formes de pièces de Tetris
 * (I, O, T, L, J, S, Z) : déplacement, rotation, positionnement.
 *
 * Chaque sous-classe doit définir sa forme en implémentant `setElements(...)`.
 */
public abstract class Tetromino extends Piece {

    // Les 4 blocs de la pièce
    protected Element[] elements = new Element[4];

    /**
     * Constructeur d’un Tetromino.
     * @param coordonnees coordonnée de base pour positionner la pièce
     * @param couleur couleur commune aux 4 éléments
     */
    public Tetromino(final Coordonnees coordonnees, final Couleur couleur) {
        this.elements = new Element[4];
        setElements(coordonnees, couleur); // Appelle la méthode spécialisée de la sous-classe
    }

    /**
     * Méthode abstraite à implémenter dans chaque sous-classe pour définir
     * l’agencement des 4 blocs selon la forme (ex : ligne, carré, etc.).
     */
    protected abstract void setElements(final Coordonnees coordonnees, final Couleur couleur);

    @Override
    public Element[] getElements() {
        return elements;
    }

    /**
     * Repositionne toute la pièce à une nouvelle coordonnée en conservant sa forme.
     * Le déplacement est relatif à l’élément pivot (élément[0]).
     */
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

    /**
     * Déplace la pièce dans une direction autorisée : gauche, droite, ou bas.
     * Lève une exception en cas de sortie du puits ou collision avec le tas.
     */
    @Override
    public void deplacerDe(int deltaX, int deltaY) throws BloxException {
        boolean deplacementValide =
            (deltaX == -1 && deltaY == 0) ||  // gauche
            (deltaX == 1 && deltaY == 0)  ||  // droite
            (deltaX == 0 && deltaY == 1);     // bas

        if (!deplacementValide) {
            throw new IllegalArgumentException("Déplacement invalide : seuls les déplacements gauche, droite ou bas sont autorisés.");
        }

        // Vérifie la validité du déplacement pour chaque bloc
        for (Element e : elements) {
            int newX = e.getCoordonnees().getAbscisse() + deltaX;
            int newY = e.getCoordonnees().getOrdonnee() + deltaY;

            if (newX < 0 || newX >= puits.getLargeur()) {
                throw new BloxException("Sortie du puits (gauche/droite)", BloxException.BLOX_SORTIE_PUITS);
            }
            if (newY >= puits.getProfondeur()) {
                throw new BloxException("Collision avec le fond du puits", BloxException.BLOX_COLLISION);
            }
            if (newY >= 0 && puits.getTas().elementExiste(newX, newY)) {
                throw new BloxException("Collision avec le tas", BloxException.BLOX_COLLISION);
            }
        }

        // Si tout est valide, applique le déplacement
        for (Element e : elements) {
            e.deplacerDe(deltaX, deltaY);
        }
    }

    /**
     * Effectue une rotation autour du pivot (élément[0]).
     * Applique une transformation géométrique selon le sens.
     *
     * Lève une BloxException si la rotation sort du puits ou entre en collision.
     */
    @Override
    public void tourner(boolean sensHoraire) throws BloxException {
        Coordonnees ref = elements[0].getCoordonnees();
        int xRef = ref.getAbscisse();
        int yRef = ref.getOrdonnee();

        // Calcule les nouvelles coordonnées en rotation
        Coordonnees[] nouvellesCoordonnees = new Coordonnees[elements.length];
        nouvellesCoordonnees[0] = ref; // le pivot reste inchangé

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

        // Vérifie que toutes les nouvelles positions sont valides
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

        // Applique la rotation si toutes les vérifications sont passées
        for (int i = 1; i < elements.length; i++) {
            elements[i].setCoordonnees(nouvellesCoordonnees[i]);
        }
    }
}
