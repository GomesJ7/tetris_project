package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;

public class ZTetromino extends Tetromino {

    public ZTetromino(final Coordonnees coordonnees, final Couleur couleur) {
        super(coordonnees, couleur);
    }

    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {
        // Forme en Z avec pivot en haut à droite
        elements[0] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()), couleur); // pivot
        elements[1] = new Element(coordonnees, couleur); // haut gauche
        elements[2] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() + 1), couleur); // bas droite
        elements[3] = new Element(new Coordonnees(coordonnees.getAbscisse() + 2, coordonnees.getOrdonnee() + 1), couleur); // extrême bas droite
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ZTetromino :\n");
        for (Element e : getElements()) {
            sb.append("\t").append(e).append("\n");
        }
        return sb.toString();
    }
}
