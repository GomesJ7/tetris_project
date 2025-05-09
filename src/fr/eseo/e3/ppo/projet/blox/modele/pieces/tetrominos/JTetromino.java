package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;

public class JTetromino extends Tetromino {

    public JTetromino(final Coordonnees coordonnees, final Couleur couleur) {
        super(coordonnees, couleur);
    }

    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {
        // Pivot au deuxième bloc (milieu vertical)
        elements[0] = new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() + 1), couleur); // centre
        elements[1] = new Element(coordonnees, couleur); // haut
        elements[2] = new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() + 2), couleur); // bas
        elements[3] = new Element(new Coordonnees(coordonnees.getAbscisse() - 1, coordonnees.getOrdonnee() + 2), couleur); // bas gauche
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("JTetromino :\n");
        for (Element e : getElements()) {
            sb.append("\t").append(e).append("\n");
        }
        return sb.toString();
    }
}
