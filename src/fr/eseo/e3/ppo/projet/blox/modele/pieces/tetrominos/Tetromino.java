package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import java.util.Arrays;
import java.util.stream.Collectors;

import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;

public abstract class Tetromino implements Piece {
	protected Element[] elements = new Element[4];
	
    // Méthode à redéfinir pour positionner les éléments selon la forme
    protected abstract void setElements(Coordonnees coordonnees, Couleur couleur);
    
	public Tetromino(Coordonnees coordonnees, Couleur couleur) {
		// Initialise les éléments de la pièce
        setElements(coordonnees, couleur);
	}
	
	public Element[] getElements() {
		return this.elements;
	}
	
	public void setPosition(int abscisse, int ordonnee) {
		// Calcule le décalage à appliquer à tous les éléments
        Coordonnees ref = elements[0].getCoordonnees();
        int deltaX = abscisse - ref.getAbscisse();
        int deltaY = ordonnee - ref.getOrdonnee();
        
        for (Element e : elements) {
        	e.setCoordonnes(new Coordonnees(
	        	e.getCoordonnees().getAbscisse() + deltaX,
	        	e.getCoordonnees().getOrdonnee() + deltaY
        	));
        }
	}
	
	public String toString() {
	    return getClass().getSimpleName() + " :\n" +
	        Arrays.stream(elements)
	              .map(e -> "\t" + e.toString())
	              .collect(Collectors.joining("\n")) + "\n";
	}


}
