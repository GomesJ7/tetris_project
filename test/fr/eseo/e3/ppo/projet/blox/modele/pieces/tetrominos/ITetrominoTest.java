package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ITetrominoTest {

    // Test de la méthode toString pour vérifier l'affichage de l'ITetromino
    @Test
    void testToString() {
        // Création d'un ITetromino à la position (3, 4) avec la couleur ORANGE
        ITetromino tetromino = new ITetromino(new Coordonnees(3, 4), Couleur.ORANGE);
        
        // Chaîne attendue (les coordonnées des éléments doivent être alignées horizontalement)
        String expected = "ITetromino :\n" +
                          "\t(3,4) - ORANGE\n" +
                          "\t(4,4) - ORANGE\n" +
                          "\t(5,4) - ORANGE\n" +
                          "\t(6,4) - ORANGE\n";
        
        // Vérification que toString retourne exactement la chaîne attendue
        assertEquals(expected, tetromino.toString(), "La méthode toString() ne retourne pas la chaîne attendue.");
    }
    
    // Test de la méthode getElements pour vérifier l'initialisation correcte des éléments
    @Test
    void testGetElements() {
        ITetromino tetromino = new ITetromino(new Coordonnees(3, 4), Couleur.ORANGE);
        Element[] elements = tetromino.getElements();
        
        // L'ITetromino doit contenir 4 éléments
        assertEquals(4, elements.length, "Un ITetromino doit comporter 4 éléments.");
        
        // Vérification des positions des éléments attendues en configuration horizontale
        assertEquals(new Coordonnees(3, 4), elements[0].getCoordonnees(), "L'élément 0 a des coordonnées incorrectes.");
        assertEquals(new Coordonnees(4, 4), elements[1].getCoordonnees(), "L'élément 1 a des coordonnées incorrectes.");
        assertEquals(new Coordonnees(5, 4), elements[2].getCoordonnees(), "L'élément 2 a des coordonnées incorrectes.");
        assertEquals(new Coordonnees(6, 4), elements[3].getCoordonnees(), "L'élément 3 a des coordonnées incorrectes.");
    }
    
    // Test de la méthode setPosition pour vérifier le déplacement de l'ITetromino
    @Test
    void testSetPosition() {
        ITetromino tetromino = new ITetromino(new Coordonnees(3, 4), Couleur.ORANGE);
        // Déplacer l'ITetromino vers (5,6)
        tetromino.setPosition(5, 6);
        Element[] elements = tetromino.getElements();
        
        // Le décalage est de +2 en X et +2 en Y sur tous les éléments
        // Les positions attendues deviennent : (5,6), (6,6), (7,6), (8,6)
        assertEquals(new Coordonnees(5, 6), elements[0].getCoordonnees(), "L'élément 0 n'a pas été déplacé correctement.");
        assertEquals(new Coordonnees(6, 6), elements[1].getCoordonnees(), "L'élément 1 n'a pas été déplacé correctement.");
        assertEquals(new Coordonnees(7, 6), elements[2].getCoordonnees(), "L'élément 2 n'a pas été déplacé correctement.");
        assertEquals(new Coordonnees(8, 6), elements[3].getCoordonnees(), "L'élément 3 n'a pas été déplacé correctement.");
    }
}
