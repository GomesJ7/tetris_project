package fr.eseo.e3.ppo.projet.blox.modele;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElementTest {

    // Test pour vérifier la méthode toString
    @Test
    void testToString() {
        // Création d'un élément avec des coordonnées (12, 7) et une couleur spécifique
        Element element = new Element(12, 7, Couleur.VIOLET);
        
        // Vérification que la méthode toString retourne la chaîne attendue
        String expected = "(12, 7) - VIOLET";
        assertEquals(expected, element.toString(), "La méthode toString() ne retourne pas la chaîne attendue.");
    }

    // Test pour vérifier la méthode equals
    @Test
    void testEquals() {
        // Création de deux éléments identiques
        Element element1 = new Element(12, 7, Couleur.VIOLET);
        Element element2 = new Element(12, 7, Couleur.VIOLET);
        
        // Les éléments devraient être égaux car ils ont les mêmes coordonnées et la même couleur
        assertTrue(element1.equals(element2), "Les éléments devraient être égaux.");

        // Création d'un élément différent avec des coordonnées différentes
        Element element3 = new Element(15, 7, Couleur.VIOLET);
        
        // Vérification que les éléments ne sont pas égaux
        assertFalse(element1.equals(element3), "Les éléments ne devraient pas être égaux.");
    }

    // Test pour vérifier la méthode hashCode
    @Test
    void testHashCode() {
        // Création de deux éléments identiques
        Element element1 = new Element(12, 7, Couleur.VIOLET);
        Element element2 = new Element(12, 7, Couleur.VIOLET);
        
        // Les deux éléments doivent avoir le même code de hachage
        assertEquals(element1.hashCode(), element2.hashCode(), "Les hashcodes des éléments identiques doivent être égaux.");
    }

    // Test pour vérifier la création de l'élément sans couleur (avec la couleur par défaut)
    @Test
    void testElementWithoutColor() {
        // Création d'un élément avec seulement des coordonnées
        Element element = new Element(new Coordonnees(12, 7));
        
        // Vérification que la couleur par défaut est utilisée (si Couleur.DEFAULT est défini)
        assertEquals(Couleur.values()[0], element.getCouleur(), "La couleur par défaut devrait être utilisée.");
    }

    // Test pour vérifier que la méthode equals() retourne false pour un objet null
    @Test
    void testEqualsNull() {
        Element element = new Element(12, 7, Couleur.VIOLET);
        
        // Un élément ne peut pas être égal à null
        assertFalse(element.equals(null), "L'élément ne doit pas être égal à null.");
    }
    
    @Test
    void testDeplacerDeValide() {
        Element element = new Element(5, 10, Couleur.ROUGE);
        element.deplacerDe(1, 1); // déplacement vers le bas-droite
        assertEquals(6, element.getCoordonnees().getAbscisse());
        assertEquals(11, element.getCoordonnees().getOrdonnee());
    }

}
