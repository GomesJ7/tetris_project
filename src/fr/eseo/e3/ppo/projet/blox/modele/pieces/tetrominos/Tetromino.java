package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;

public abstract class Tetromino implements Piece {  
    // Tableau des éléments constituant le Tetromino  
    protected Element[] elements;  

    /**
     * Constructeur générique pour tous les Tetrominos.
     * @param coordonnees les coordonnées de l'élément de référence
     * @param couleur la couleur de la pièce
     */
    public Tetromino(final Coordonnees coordonnees, final Couleur couleur) {  
        // On suppose ici que tous les tetrominos sont composés de 4 éléments  
        this.elements = new Element[4];  
        // Remplit le tableau d'éléments selon la configuration spécifique  
        setElements(coordonnees, couleur);  
    }

    /**
     * Méthode abstraite devant être implémentée par chaque type de tetromino pour initialiser 
     * correctement les positions relatives de ses éléments.
     * @param coordonnees les coordonnées de l'élément de référence
     * @param couleur la couleur de la pièce
     */
    protected abstract void setElements(final Coordonnees coordonnees, final Couleur couleur);  

    /**
     * Retourne le tableau des éléments qui composent le tetromino.
     * @return le tableau des éléments
     */
    @Override
    public Element[] getElements() {  
        return elements;  
    }

    /**
     * Positionne la pièce à une nouvelle position en décalant tous ses éléments.
     * Le déplacement est calculé par rapport à l'élément de référence (premier élément du tableau).
     * @param abscisse la nouvelle abscisse de l'élément de référence
     * @param ordonnee la nouvelle ordonnée de l'élément de référence
     */
    @Override
    public void setPosition(int abscisse, int ordonnee) {  
        // Récupère les coordonnées actuelles de l'élément de référence (élément 0)  
        Coordonnees ref = elements[0].getCoordonnees();  
        // Calcule le décalage en x  
        int deltaX = abscisse - ref.getAbscisse();  
        // Calcule le décalage en y  
        int deltaY = ordonnee - ref.getOrdonnee();  
        // Applique le décalage à chacun des éléments du tetromino  
        for (Element e : elements) {  
            Coordonnees current = e.getCoordonnees();  
            int newX = current.getAbscisse() + deltaX;  
            int newY = current.getOrdonnee() + deltaY;  
            // Mise à jour des coordonnées de l'élément (en créant de nouvelles coordonnées)  
            e.setCoordonnees(new Coordonnees(newX, newY));  
        }  
    }

    /**
     * Redéfinit la méthode toString() pour afficher le nom du tetromino et la représentation de chaque élément.
     * @return la chaîne représentant le tetromino
     */
    @Override
    public String toString() {  
        StringBuilder sb = new StringBuilder();  
        // Affiche le nom de la classe (sans le package) suivi d'un deux-points  
        sb.append(this.getClass().getSimpleName()).append(" :\n");  
        // Pour chaque élément, on affiche sa représentation avec une tabulation  
        for (Element e : elements) {  
            sb.append("\t").append(e.toString()).append("\n");  
        }  
        return sb.toString();  
    }  
}
