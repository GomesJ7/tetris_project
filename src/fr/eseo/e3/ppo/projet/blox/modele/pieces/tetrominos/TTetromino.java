package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;

/**
 * Représente la pièce en forme de 'T' dans le Tetris.
 * Elle hérite de {@link Tetromino} pour profiter des comportements communs (déplacement, rotation, etc.).
 */
public class TTetromino extends Tetromino {

    /**
     * Constructeur de la pièce T.
     *
     * @param coordonnees Coordonnée de base (en haut à gauche de la ligne horizontale)
     * @param couleur Couleur assignée à tous les éléments de la pièce
     */
    public TTetromino(final Coordonnees coordonnees, final Couleur couleur) {
        super(coordonnees, couleur); // Appelle setElements()
    }

    /**
     * Initialise les 4 blocs de la pièce T selon la forme :
     *    [1] [0] [2]
     *         [3]
     *  - [0] est le pivot central
     *  - [1] est à gauche
     *  - [2] est à droite
     *  - [3] est en dessous du pivot
     */
    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {
        // Pivot central (au milieu de la barre horizontale)
        elements[0] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()), couleur); // centre
        // Gauche
        elements[1] = new Element(coordonnees, couleur); // à gauche du pivot
        // Droite
        elements[2] = new Element(new Coordonnees(coordonnees.getAbscisse() + 2, coordonnees.getOrdonnee()), couleur); // à droite du pivot
        // Bas
        elements[3] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() + 1), couleur); // en dessous du pivot
    }

    /**
     * Affiche une représentation textuelle de la pièce T.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TTetromino :\n");
        for (Element e : getElements()) {
            sb.append("\t").append(e).append("\n");
        }
        return sb.toString();
    }
}
