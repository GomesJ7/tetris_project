package fr.eseo.e3.ppo.projet.blox.modele;

public class Coordonnees {

    private int abscisse;
    private int ordonnee;

    // Constructeur pour initialiser les coordonnées
    public Coordonnees(int abscisse, int ordonnee) {
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }

    // Getters et setters pour abscisse et ordonnee
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

    // Retourne une chaîne sous la forme "(abscisse, ordonnee)"
    @Override
    public String toString() {
        return "(" + abscisse + ", " + ordonnee + ")"; // Espaces après la virgule
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
        return abscisse == other.abscisse && ordonnee == other.ordonnee;  // Les deux objets sont égaux si abscisse et ordonnee sont égaux
    }

    // Calcule un code de hachage simple basé sur abscisse et ordonnee
    @Override
    public int hashCode() {
        return 31 * abscisse + ordonnee; // Retourne la somme de abscisse et ordonnee comme un simple code de hachage
    }	//on utilise 31 comme facteur multiplicatif pour éviter que les code de hash soit les mêmes par exemple pour (1,4) et (4,1)
}
