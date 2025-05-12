package fr.eseo.e3.ppo.projet.blox.modele;

/**
 * Représente un bloc élémentaire d’une pièce dans le jeu.
 * Chaque élément a une position (coordonnées) et une couleur.
 * C’est une classe clé du modèle, qui illustre :
 * - la composition (Coordonnees comme champ)
 * - l’encapsulation
 * - la surcharge de constructeurs
 * - les redéfinitions de méthodes fondamentales
 */
public class Element {

    // === Attributs encapsulés ===
    private Coordonnees coordonnees;
    private Couleur couleur;

    // === Constructeurs ===

    /**
     * Constructeur principal avec validation des arguments.
     */
    public Element(Coordonnees coordonnees, Couleur couleur) {
        if (coordonnees == null || couleur == null) {
            throw new IllegalArgumentException("Les coordonnées et la couleur ne peuvent pas être nulles.");
        }
        this.coordonnees = coordonnees;
        this.couleur = couleur;
    }

    /**
     * Constructeur secondaire à partir de x, y et couleur.
     * Illustre la **surcharge de constructeurs** pour améliorer l’ergonomie.
     */
    public Element(int x, int y, Couleur couleur) {
        this(new Coordonnees(x, y), couleur);
    }

    /**
     * Constructeur avec couleur par défaut.
     */
    public Element(Coordonnees coordonnees) {
        this(coordonnees, Couleur.values()[0]); // première couleur par défaut
    }

    /**
     * Constructeur avec x, y et couleur par défaut.
     */
    public Element(int x, int y) {
        this(new Coordonnees(x, y), Couleur.values()[0]);
    }

    // === Accesseurs / Mutateurs ===

    public Coordonnees getCoordonnees() {
        return this.coordonnees;
    }

    public void setCoordonnees(Coordonnees coordonnees) {
        if (coordonnees == null) {
            throw new IllegalArgumentException("Les coordonnées ne peuvent pas être nulles.");
        }
        this.coordonnees = coordonnees;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    public void setCouleur(Couleur couleur) {
        if (couleur == null) {
            throw new IllegalArgumentException("La couleur ne peut pas être nulle.");
        }
        this.couleur = couleur;
    }

    // === Redéfinitions Object ===

    /**
     * Affiche l'élément sous forme texte : "(x, y) - COULEUR"
     */
    @Override
    public String toString() {
        return "(" + this.coordonnees.getAbscisse() + ", " + this.coordonnees.getOrdonnee() + ") - " + this.couleur.name();
    }

    /**
     * Compare deux éléments sur leurs coordonnées et couleur.
     * Utilise la méthode equals de Coordonnees + égalité directe des enums (référence).
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Element other = (Element) obj;
        return this.coordonnees.equals(other.coordonnees) && this.couleur == other.couleur;
    }

    /**
     * Code de hachage basé sur coordonnees et couleur.
     * Important pour les structures de type HashSet, HashMap.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.coordonnees.hashCode();
        result = 31 * result + this.couleur.hashCode();
        return result;
    }

    // === Comportement spécifique ===

    /**
     * Méthode utilitaire pour déplacer l’élément dans la grille.
     * Crée une nouvelle Coordonnees à chaque appel (immutabilité de Coordonnees respectée).
     */
    public void deplacerDe(int deltaX, int deltaY) {
        int nouvelleAbscisse = this.coordonnees.getAbscisse() + deltaX;
        int nouvelleOrdonnee = this.coordonnees.getOrdonnee() + deltaY;
        this.coordonnees = new Coordonnees(nouvelleAbscisse, nouvelleOrdonnee);
    }
}
