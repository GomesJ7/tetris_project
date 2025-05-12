package fr.eseo.e3.ppo.projet.blox.modele;

/**
 * Classe utilitaire représentant une paire de coordonnées (x, y) dans la grille du jeu.
 * Cette classe est un composant fondamental du modèle, utilisée pour positionner les éléments.
 *
 * Elle illustre l'encapsulation, la redéfinition de méthodes fondamentales (equals, hashCode, toString)
 * et des pratiques courantes dans la modélisation d’objets simples mais essentiels.
 */
public class Coordonnees {

    // === Attributs encapsulés ===

    private int abscisse;   // Coordonnée en X
    private int ordonnee;   // Coordonnée en Y

    // === Constructeur ===

    /**
     * Initialise les coordonnées avec les valeurs données.
     * @param abscisse coordonnée horizontale
     * @param ordonnee coordonnée verticale
     */
    public Coordonnees(int abscisse, int ordonnee) {
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }

    // === Accesseurs (getters) et mutateurs (setters) ===

    public int getAbscisse() {
        return abscisse;
    }

    public void setAbscisse(int abscisse) {
        this.abscisse = abscisse;
    }

    public int getOrdonnee() {
        return ordonnee;
    }

    public void setOrdonnee(int ordonnee) {
        this.ordonnee = ordonnee;
    }

    // === Méthodes redéfinies d'Object ===

    /**
     * Affiche les coordonnées sous la forme (x, y)
     */
    @Override
    public String toString() {
        return "(" + this.abscisse + ", " + this.ordonnee + ")";
    }

    /**
     * Vérifie l'égalité entre deux objets Coordonnees.
     * L'égalité repose sur l'égalité des deux champs abscisse et ordonnee.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;        // null n’est jamais égal
        if (obj == this) return true;         // même référence → égal
        if (!(obj instanceof Coordonnees))    // pas le bon type
            return false;
        Coordonnees other = (Coordonnees) obj;
        return abscisse == other.abscisse && ordonnee == other.ordonnee;
    }

    /**
     * Code de hachage cohérent avec equals() pour fonctionner dans les HashSet / HashMap.
     * Le 31 est un multiplicateur standard pour éviter les collisions (meilleure répartition).
     */
    @Override
    public int hashCode() {
        return 31 * abscisse + ordonnee;
    }
}
