package fr.eseo.e3.ppo.projet.blox.modele;

public class Coordonnees {

    private int x;
    private int y;

    // Constructeur pour initialiser les coordonnées
    public Coordonnees(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Accesseur pour obtenir la valeur de x (abscisse)
    public int getX() {
        return this.x;
    }

    // Modificateur pour définir la valeur de x
    public void setX(int x) {
        this.x = x;
    }

    // Accesseur pour obtenir la valeur de y (ordonnée)
    public int getY() {
        return this.y;
    }

    // Modificateur pour définir la valeur de y
    public void setY(int y) {
        this.y = y;
    }

    // Redéfinition de toString pour une représentation sous forme de chaîne
    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {  // Si l'objet à comparer est nul, ce n'est pas le même
            return false;
        }
        if (obj == this) {  // Si l'objet est exactement le même (même référence en mémoire)
            return true;
        }
        // On vérifie que l'objet est bien une instance de Coordonnees
        if (!(obj instanceof Coordonnees)) {
            return false;  // Si l'objet n'est pas de type Coordonnees, ce n'est pas égal
        }
        // Convertit l'objet en Coordonnees et compare les valeurs
        Coordonnees other = (Coordonnees) obj; 
        return x == other.x && y == other.y;  // Les deux objets sont égaux si x et y sont égaux
    }


    // Calcule un code de hachage simple basé sur x et y
    @Override
    public int hashCode() {
        return x + y; // Retourne la somme de x et y comme un simple code de hachage
    }
}