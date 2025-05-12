package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;

/**
 * Représente la pièce en forme de 'S' dans le Tetris.
 * Cette classe hérite de {@link Tetromino} et définit la disposition spécifique de ses blocs.
 */
public class STetromino extends Tetromino {

    /**
     * Constructeur de la pièce S.
     * @param coordonnees Coordonnée de départ servant de base pour placer la pièce
     * @param couleur Couleur de tous les éléments de la pièce
     */
    public STetromino(final Coordonnees coordonnees, final Couleur couleur) {
        super(coordonnees, couleur); // Appelle setElements() automatiquement
    }

    /**
     * Initialise les 4 blocs de la pièce S, agencés en diagonale inversée.
     * Organisation :
     *    [1] [0]
     * [2] [3]
     * - [0] est le pivot pour la rotation.
     */
    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {
        // Bloc central qui sert de pivot
        elements[0] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()), couleur); // pivot
        // Bloc à droite du pivot
        elements[1] = new Element(new Coordonnees(coordonnees.getAbscisse() + 2, coordonnees.getOrdonnee()), couleur); // haut droite
        // Bloc en bas à gauche
        elements[2] = new Element(coordonnees, couleur); // bas gauche
        // Bloc au centre bas
        elements[3] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() + 1), couleur); // bas centre
    }

    /**
     * Affiche la pièce S sous forme textuelle.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("STetromino :\n");
        for (Element e : getElements()) {
            sb.append("\t").append(e).append("\n");
        }
        return sb.toString();
    }
}
