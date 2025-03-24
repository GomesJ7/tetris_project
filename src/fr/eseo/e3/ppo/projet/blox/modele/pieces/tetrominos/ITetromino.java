package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;

public class ITetromino extends Tetromino{
	public ITetromino(Coordonnees coordonnees, Couleur couleur) {
        super(coordonnees, couleur);
    }

	@Override
	protected void setElements(Coordonnees coordonnees, Couleur couleur) {
		// TODO Auto-generated method stub
		        int x = coordonnees.getAbscisse();
        int y = coordonnees.getOrdonnee();

        elements[0] = new Element(x, y, couleur);       // Élément de référence
        elements[1] = new Element(x, y - 1, couleur);   // Au-dessus
        elements[2] = new Element(x, y + 1, couleur);   // En dessous
        elements[3] = new Element(x, y + 2, couleur);   // Encore en dessous
	}
}
