package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OTetrominoTest {

    // Test de la méthode toString pour vérifier l'affichage de l'OTetromino
    @Test
    void testToString() {
        // Création d'un OTetromino à la position (6, 5) avec la couleur CYAN
        OTetromino tetromino = new OTetromino(new Coordonnees(6, 5), Couleur.CYAN);

        // La chaîne attendue qui devrait être retournée par toString()
        String expected = "OTetromino :\n" +
                "\t(6,5) - CYAN\n" +
                "\t(7,5) - CYAN\n" +
                "\t(6,6) - CYAN\n" +
                "\t(7,6) - CYAN\n";

        // Vérification que la méthode toString() retourne la chaîne correcte
        assertEquals(expected, tetromino.toString(), "La méthode toString() ne génère pas la chaîne attendue.");
    }

    // Test de la méthode getElements pour vérifier que tous les éléments sont correctement initialisés
    @Test
    void testGetElements() {
        OTetromino tetromino = new OTetromino(new Coordonnees(6, 5), Couleur.CYAN);

        // Vérifier que les éléments de l'OTetromino sont bien initialisés
        Element[] elements = tetromino.getElements();
        assertEquals(4, elements.length, "L'OTetromino doit avoir 4 éléments.");

        // Vérification des positions des éléments
        assertEquals(new Coordonnees(6, 5), elements[0].getCoordonnees(), "L'élément 0 n'a pas les bonnes coordonnées.");
        assertEquals(new Coordonnees(7, 5), elements[1].getCoordonnees(), "L'élément 1 n'a pas les bonnes coordonnées.");
        assertEquals(new Coordonnees(6, 6), elements[2].getCoordonnees(), "L'élément 2 n'a pas les bonnes coordonnées.");
        assertEquals(new Coordonnees(7, 6), elements[3].getCoordonnees(), "L'élément 3 n'a pas les bonnes coordonnées.");
    }

    // Test du constructeur avec des coordonnées par défaut
    @Test
    void testOTetrominoConstructorDefaultColor() {
        // Création d'un OTetromino avec des coordonnées (12, 7) et couleur VIOLET
        OTetromino tetromino = new OTetromino(new Coordonnees(12, 7), Couleur.VIOLET);

        // Vérification que la couleur VIOLET est bien utilisée pour tous les éléments
        for (Element e : tetromino.getElements()) {
            assertEquals(Couleur.VIOLET, e.getCouleur(), "La couleur de l'OTetromino n'est pas correcte.");
        }
    }
}
