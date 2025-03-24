package fr.eseo.e3.ppo.projet.blox.modele;

public class Element {
    // Attribut pour stocker les coordonnées de l'élément.
    private final Coordonnees coordonnees;  

    // Attribut pour stocker la couleur de l'élément.
    private final Couleur couleur;  

    // Constructeur pour initialiser un élément avec des coordonnées et une couleur spécifiques
    public Element(Coordonnees coordonnees, Couleur couleur) {
        // Vérification pour s'assurer que ni les coordonnées ni la couleur ne sont nulles.
        if (coordonnees == null || couleur == null) {
            // Si l'une des valeurs est nulle, une exception est lancée pour éviter des erreurs de logique.
            throw new IllegalArgumentException("Les coordonnées et la couleur ne peuvent pas être null");
        }
        // Initialisation des variables d'instance avec les valeurs passées en paramètres.
        this.coordonnees = coordonnees;
        this.couleur = couleur;
    }

    // Constructeur qui initialise l'élément avec des coordonnées (x, y) et une couleur donnée.
    public Element(int x, int y, Couleur couleur) {
        // Utilisation du constructeur de la classe Coordonnees pour créer l'objet coordonnees avec les valeurs x et y.
        this.coordonnees = new Coordonnees(x, y);
        // Initialisation de la couleur de l'élément.
        this.couleur = couleur;
    }

    // Constructeur qui initialise l'élément avec des coordonnées et attribue une couleur par défaut si aucune n'est donnée.
    public Element(Coordonnees coordonnees) {
        // Appel au constructeur précédent, mais avec la première couleur de l'énumération comme couleur par défaut.
        this(coordonnees, Couleur.values()[0]);  // Utilisation de la première couleur de l'énumération si aucune couleur n'est spécifiée.
    }

    // Getter pour obtenir les coordonnées de l'élément.
    public Coordonnees getCoordonnees() {
        return this.coordonnees;  // Retourne l'objet Coordonnees de l'élément.
    }

    // Getter pour obtenir la couleur de l'élément.
    public Couleur getCouleur() {
        return this.couleur;  // Retourne la couleur de l'élément.
    }

    // Méthode pour afficher l'élément sous forme de chaîne de caractères, incluant ses coordonnées et sa couleur.
    @Override
    public String toString() {
        // La chaîne retournée inclut les coordonnées de l'élément et son nom de couleur.
        return "(" + coordonnees.getAbscisse() + "," + coordonnees.getOrdonnee() + ") - " + couleur.name();
    }

    // Méthode pour vérifier si deux éléments sont égaux, en comparant leurs coordonnées et leurs couleurs.
    @Override
    public boolean equals(Object obj) {
        // Si l'objet comparé est le même, on retourne directement true (optimisation).
        if (this == obj) return true;

        // Si l'objet comparé est nul ou de type différent, on retourne false.
        if (obj == null || getClass() != obj.getClass()) return false;

        // Cast de l'objet comparé en un Element.
        Element element = (Element) obj;

        // On vérifie si les coordonnées et la couleur des deux éléments sont égales.
        return coordonnees.equals(element.coordonnees) && couleur == element.couleur;
    }

    // Méthode pour générer un code de hachage basé sur les coordonnées et la couleur de l'élément.
    @Override
    public int hashCode() {
        // Initialisation d'un code de hachage avec une valeur arbitraire (17 est souvent utilisée comme valeur de départ).
        int result = 17;

        // Calcul du code de hachage en multipliant le résultat actuel par 31 et en ajoutant le code de hachage de coordonnees.
        result = 31 * result + coordonnees.hashCode();

        // On répète le calcul pour la couleur.
        result = 31 * result + couleur.hashCode();

        // Retourne le code de hachage final.
        return result;
    }

	public void setCoordonnes(Coordonnees coordonnees2) {
		// TODO Auto-generated method stub
		
	}
}
