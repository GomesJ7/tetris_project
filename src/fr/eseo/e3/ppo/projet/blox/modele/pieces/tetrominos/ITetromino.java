package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;

public class ITetromino extends Tetromino {

    // Constructeur
    public ITetromino(final Coordonnees coordonnees, final Couleur couleur) {
        super(coordonnees, couleur);
    }

    @Override
    protected void setElements(final Coordonnees coordonnees, final Couleur couleur) {
        // Pivot centré sur le 2e bloc (au lieu du premier)
        // Exemple : (x+1, y) est le pivot
        elements[0] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()), couleur); // pivot
        elements[1] = new Element(coordonnees, couleur); // gauche du pivot
        elements[2] = new Element(new Coordonnees(coordonnees.getAbscisse() + 2, coordonnees.getOrdonnee()), couleur); // droite
        elements[3] = new Element(new Coordonnees(coordonnees.getAbscisse() + 3, coordonnees.getOrdonnee()), couleur); // extrême droite
    }

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
