package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;

/**
 * Représente la pièce en forme de 'Z' dans le Tetris.
 * Elle hérite de {@link Tetromino}, qui gère le déplacement, la rotation et les collisions.
 */
public class ZTetromino extends Tetromino {

    /**
     * Constructeur du ZTetromino.
     * @param coordonnees Coordonnée de base de la pièce
     * @param couleur Couleur unique des 4 blocs constituants
     */
    public ZTetromino(final Coordonnees coordonnees, final Couleur couleur) {
        super(coordonnees, couleur); // Appelle setElements()
    }

    /**
     * Initialise les 4 blocs de la pièce Z.
     * Organisation visuelle :
     *   [1][0]
     *      [2][3]
     *  - [0] est le pivot
     *  - [1] est à sa gauche
     *  - [2] est en dessous du pivot
     *  - [3] est à droite de [2], formant la diagonale inverse
     */
    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {
        // Bloc pivot (en haut à droite de la forme Z)
        elements[0] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()), couleur);
        // Bloc à gauche du pivot (haut gauche)
        elements[1] = new Element(coordonnees, couleur);
        // Bloc en bas du pivot (bas gauche)
        elements[2] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() + 1), couleur);
        // Bloc à droite du précédent (bas droite)
        elements[3] = new Element(new Coordonnees(coordonnees.getAbscisse() + 2, coordonnees.getOrdonnee() + 1), couleur);
    }

    /**
     * Affiche une représentation textuelle de la pièce Z.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ZTetromino :\n");
        for (Element e : getElements()) {
            sb.append("\t").append(e).append("\n");
        }
        return sb.toString();
    }
}
