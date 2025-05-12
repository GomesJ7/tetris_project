package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.BloxException;
import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;

/**
 * Classe représentant la pièce 'O' (carré) du Tetris.
 * Cette pièce est un cas particulier car elle ne change pas d’apparence
 * lorsqu’elle tourne — sa rotation est inutile.
 */
public class OTetromino extends Tetromino {

    /**
     * Constructeur d’une pièce O (carré).
     * @param coordonnees Coordonnée du coin supérieur gauche du carré
     * @param couleur Couleur commune aux 4 blocs de la pièce
     */
    public OTetromino(final Coordonnees coordonnees, final Couleur couleur) {  
        super(coordonnees, couleur); // Appelle setElements()
    }

    /**
     * Définit les 4 blocs formant un carré 2x2 :
     *  [0] [1]
     *  [2] [3]
     * Le pivot (élément[0]) est le coin supérieur gauche.
     */
    @Override
    protected void setElements(final Coordonnees coordonnees, final Couleur couleur) {
        elements[0] = new Element(coordonnees, couleur); // coin haut gauche
        elements[1] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()), couleur); // haut droit
        elements[2] = new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() + 1), couleur); // bas gauche
        elements[3] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() + 1), couleur); // bas droit
    }  

    /**
     * Représentation textuelle de la pièce, utile pour le debug.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OTetromino :\n");
        for (Element e : getElements()) {
            sb.append("\t").append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * La pièce O étant symétrique, elle ne peut pas être tournée.
     * Cette méthode lève une exception volontairement pour l’indiquer.
     *
     * @throws UnsupportedOperationException toujours
     */
    @Override
    public void tourner(boolean sensHoraire) throws BloxException {
        throw new UnsupportedOperationException("L'OTetromino ne peut pas être tourné.");
    }
}
