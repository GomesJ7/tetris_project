package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;

/**
 * Représente la pièce en forme de 'J' du Tetris.
 * Cette classe hérite de {@link Tetromino} et définit son agencement initial.
 *
 * L'élément[0] est utilisé comme pivot de rotation.
 */
public class JTetromino extends Tetromino {

    /**
     * Constructeur du JTetromino.
     * @param coordonnees Coordonnée de base (souvent coin supérieur gauche de la pièce)
     * @param couleur Couleur appliquée à tous les blocs de la pièce
     */
    public JTetromino(final Coordonnees coordonnees, final Couleur couleur) {
        super(coordonnees, couleur); // Appelle le constructeur de Tetromino, qui appelle setElements()
    }

    /**
     * Initialise les 4 éléments (blocs) de la pièce en forme de 'J'.
     * Position relative :
     *   [1]   ← en haut     (coordonnée de base)
     *   [0]   ← pivot       (au centre vertical)
     *   [2]   ← en bas
     * [3]     ← bloc en bas à gauche (forme du J)
     */
    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {
        // Pivot au centre (élément[0])
        elements[0] = new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() + 1), couleur);     // pivot central
        elements[1] = new Element(coordonnees, couleur);                                                                  // en haut
        elements[2] = new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() + 2), couleur);     // en bas
        elements[3] = new Element(new Coordonnees(coordonnees.getAbscisse() - 1, coordonnees.getOrdonnee() + 2), couleur); // bas gauche
    }

    /**
     * Affiche un JTetromino sous forme textuelle (utile pour les tests ou le debug).
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("JTetromino :\n");
        for (Element e : getElements()) {
            sb.append("\t").append(e).append("\n");
        }
        return sb.toString();
    }
}
