package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;

public class OTetromino extends Tetromino {

    /**
     * Constructeur de OTetromino.
     * Appelle le constructeur de la superclasse Tetromino qui se charge d'initialiser et de configurer les éléments.
     * @param coordonnees les coordonnées de l'élément de référence
     * @param couleur la couleur de la pièce
     */
    public OTetromino(final Coordonnees coordonnees, final Couleur couleur) {  
        super(coordonnees, couleur);  // Appel du constructeur de la superclasse  
    }

    /**
     * Implémentation spécifique de la méthode setElements() pour configurer un OTetromino.
     * Les éléments sont placés de façon à former un carré 2x2.
     * @param coordonnees les coordonnées de l'élément de référence
     * @param couleur la couleur de la pièce
     */
    @Override
    protected void setElements(final Coordonnees coordonnees, final Couleur couleur) {  
        // Premier élément : élément de référence aux coordonnées données  
        elements[0] = new Element(coordonnees, couleur);  
        // Deuxième élément : placé à droite de l'élément de référence  
        elements[1] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()), couleur);  
        // Troisième élément : placé en dessous de l'élément de référence  
        elements[2] = new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() + 1), couleur);  
        // Quatrième élément : placé en diagonale (bas à droite de l'élément de référence)  
        elements[3] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() + 1), couleur);  
    }  
}
