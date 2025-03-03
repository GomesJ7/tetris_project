package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;

public class OTetromino {

    private final Element[] elements;  // Utilisation de final pour garantir que le tableau ne peut pas être réassigné

    // Constructeur
    public OTetromino(final Coordonnees coordonnees, final Couleur couleur) {
        setElements(coordonnees, couleur);  // Remplir le tableau d'éléments
    }

    // Remplir le tableau d'éléments
    protected void setElements(final Coordonnees coordonnees, final Couleur couleur) {
        elements = new Element[4];

        // Premier élément (élément de référence)
        elements[0] = new Element(coordonnees, couleur);

        // Deuxième élément : à droite
        elements[1] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()), couleur);

        // Troisième élément : en dessous
        elements[2] = new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() + 1), couleur);

        // Quatrième élément : en bas à droite
        elements[3] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() + 1), couleur);
    }

    // Retourner le tableau des éléments
    public Element[] getElements() {
        return elements;
    }

    // Afficher l'OTetromino sous forme de texte
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OTetromino :\n");

        // Pour chaque élément, afficher sa position et couleur
        for (Element e : elements) {
            sb.append("\t").append(e.toString()).append("\n");
        }

        return sb.toString();  // Retourne la chaîne
    }
}
