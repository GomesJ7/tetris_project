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

    // Vérifie si deux objets Coordonnees sont égaux
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false; // Si l'objet comparé est nul, ils ne sont pas égaux
        if (this == obj) return true;  // Si c'est le même objet, ils sont égaux
        Coordonnees other = (Coordonnees) obj; // Convertit l'objet en Coordonnees
        return this.x == other.x && this.y == other.y; // Compare les coordonnées
    }

    // Calcule un code de hachage simple basé sur x et y
    @Override
    public int hashCode() {
        return x + y; // Retourne la somme de x et y comme un simple code de hachage
    }
}