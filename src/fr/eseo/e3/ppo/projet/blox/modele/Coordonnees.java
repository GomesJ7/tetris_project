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

    // Vérification de l'égalité entre deux objets Coordonnees
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Cas d'égalité immédiate (même référence)
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordonnees other = (Coordonnees) obj;
        return this.x == other.x && this.y == other.y;
    }

    // Calcul du hash code en fonction de x et y
    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}