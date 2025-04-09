package fr.eseo.e3.ppo.projet.blox.modele;

// Déclaration de la classe Element
public class Element {

    // Attribut privé pour stocker les coordonnées de l'élément
    private Coordonnees coordonnees;
    // Attribut privé pour stocker la couleur de l'élément
    private Couleur couleur;

    // Constructeur qui initialise un Element avec des coordonnées et une couleur spécifiques
    public Element(Coordonnees coordonnees, Couleur couleur) {
        // Vérifie que les coordonnées et la couleur ne sont pas nulles
        if (coordonnees == null || couleur == null) {
            throw new IllegalArgumentException("Les coordonnées et la couleur ne peuvent pas être nulles.");
        }
        // Affecte les coordonnées fournies à l'attribut coordonnees
        this.coordonnees = coordonnees;
        // Affecte la couleur fournie à l'attribut couleur
        this.couleur = couleur;
    }

    // Constructeur qui initialise un Element en fournissant x, y et une couleur
    public Element(int x, int y, Couleur couleur) {
        // Crée un objet Coordonnees avec x et y et appelle le constructeur principal
        this(new Coordonnees(x, y), couleur);
    }

    // Constructeur qui initialise un Element avec des coordonnées et utilise la première couleur de l'énumération par défaut
    public Element(Coordonnees coordonnees) {
        // Utilise la première valeur de l'énumération Couleur comme couleur par défaut
        this(coordonnees, Couleur.values()[0]);
    }

    // Constructeur qui initialise un Element avec x et y et utilise la première couleur par défaut
    public Element(int x, int y) {
        // Crée un objet Coordonnees avec x et y et utilise la première couleur par défaut
        this(new Coordonnees(x, y), Couleur.values()[0]);
    }

    // Accesseur pour obtenir les coordonnées de l'élément
    public Coordonnees getCoordonnees() {
        return this.coordonnees;
    }

    // Mutateur pour modifier les coordonnées de l'élément
    public void setCoordonnees(Coordonnees coordonnees) {
        // Vérifie que les coordonnées ne sont pas nulles
        if (coordonnees == null) {
            throw new IllegalArgumentException("Les coordonnées ne peuvent pas être nulles.");
        }
        this.coordonnees = coordonnees;
    }

    // Accesseur pour obtenir la couleur de l'élément
    public Couleur getCouleur() {
        return this.couleur;
    }

    // Mutateur pour modifier la couleur de l'élément
    public void setCouleur(Couleur couleur) {
        // Vérifie que la couleur ne soit pas nulle
        if (couleur == null) {
            throw new IllegalArgumentException("La couleur ne peut pas être nulle.");
        }
        this.couleur = couleur;
    }

    // Redéfinition de la méthode toString() pour retourner une chaîne formatée
    @Override
    public String toString() {
        // Construit et retourne une chaîne sous la forme "(abscisse, ordonnee) - COULEUR"
        return "(" + this.coordonnees.getAbscisse() + ", " + this.coordonnees.getOrdonnee() + ") - " + this.couleur.name();
    }

    // Redéfinition de la méthode equals() pour comparer deux Element sur la base de leurs coordonnées et couleur
    @Override
    public boolean equals(Object obj) {
        // Vérifie si l'objet comparé est le même (même référence)
        if (this == obj) return true;
        // Vérifie si l'objet est null ou de classe différente
        if (obj == null || getClass() != obj.getClass()) return false;
        // Convertit l'objet en Element
        Element other = (Element) obj;
        // Compare les coordonnées et la couleur pour déterminer l'égalité
        return this.coordonnees.equals(other.coordonnees) && this.couleur == other.couleur;
    }

    // Redéfinition de la méthode hashCode() pour calculer un code de hachage cohérent avec equals()
    @Override
    public int hashCode() {
        int result = 17; // Valeur de départ arbitraire
        // Intègre le hashCode des coordonnées en utilisant 31 comme multiplicateur
        result = 31 * result + this.coordonnees.hashCode();
        // Intègre le hashCode de la couleur en utilisant 31 comme multiplicateur
        result = 31 * result + this.couleur.hashCode();
        return result; // Retourne le code de hachage final
    }
    
    public void deplacerDe(int deltaX, int deltaY) {
        int nouvelleAbscisse = this.coordonnees.getAbscisse() + deltaX;
        int nouvelleOrdonnee = this.coordonnees.getOrdonnee() + deltaY;
        this.coordonnees = new Coordonnees(nouvelleAbscisse, nouvelleOrdonnee);
    }

}
