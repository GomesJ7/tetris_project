package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;

/**
 * Classe représentant la pièce en forme de 'I' (barre verticale/horizontale).
 * Elle hérite de Tetromino, une classe abstraite qui centralise les comportements communs.
 *
 * Le pivot de rotation est positionné au 2e bloc (élément[0]).
 * Cette classe définit donc l'arrangement initial de ses 4 blocs dans la grille.
 */
public class ITetromino extends Tetromino {

    /**
     * Constructeur du ITetromino.
     * Initialise les 4 blocs à partir d’une coordonnée de référence et d’une couleur.
     *
     * @param coordonnees coordonnée d’ancrage (ex. coin gauche)
     * @param couleur couleur de tous les blocs de cette pièce
     */
    public ITetromino(final Coordonnees coordonnees, final Couleur couleur) {
        super(coordonnees, couleur); // délégué à Tetromino, qui appelle setElements()
    }

    /**
     * Initialise les 4 éléments de la pièce en ligne horizontale.
     * La pièce est construite à partir de coordonnees :
     * - pivot : (x+1, y)
     * - gauche : (x, y)
     * - droite : (x+2, y), (x+3, y)
     */
    @Override
    protected void setElements(final Coordonnees coordonnees, final Couleur couleur) {
        elements[0] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()), couleur); // pivot
        elements[1] = new Element(coordonnees, couleur); // extrémité gauche
        elements[2] = new Element(new Coordonnees(coordonnees.getAbscisse() + 2, coordonnees.getOrdonnee()), couleur); // droite
        elements[3] = new Element(new Coordonnees(coordonnees.getAbscisse() + 3, coordonnees.getOrdonnee()), couleur); // extrême droite
    }

    /**
     * Affiche la pièce sous forme texte.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ITetromino :\n");
        for (Element e : getElements()) {
            sb.append("\t").append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}
