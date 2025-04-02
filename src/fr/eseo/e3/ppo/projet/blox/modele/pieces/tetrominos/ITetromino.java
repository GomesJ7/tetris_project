package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;

public class ITetromino extends Tetromino {

    // Constructeur qui appelle le constructeur de la superclasse
    public ITetromino(final Coordonnees coordonnees, final Couleur couleur) {
        super(coordonnees, couleur);
    }
    
    // Implémentation de la méthode abstraite setElements pour ITetromino
    @Override
    protected void setElements(final Coordonnees coordonnees, final Couleur couleur) {
        // Pour ITetromino, on dispose les 4 éléments en ligne horizontale.
        // Le premier élément est l'élément de référence.
        elements[0] = new Element(coordonnees, couleur); // Élément de référence
        elements[1] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()), couleur);
        elements[2] = new Element(new Coordonnees(coordonnees.getAbscisse() + 2, coordonnees.getOrdonnee()), couleur);
        elements[3] = new Element(new Coordonnees(coordonnees.getAbscisse() + 3, coordonnees.getOrdonnee()), couleur);
    }
    
    /**
     * Redéfinit la méthode toString pour afficher la pièce de type OTetromino.
     * Format attendu :
     * OTetromino :
     *     (x1,y1) - COULEUR
     *     (x2,y2) - COULEUR
     *     ...
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
