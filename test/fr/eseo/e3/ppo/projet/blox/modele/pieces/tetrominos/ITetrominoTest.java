package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ITetrominoTest {

    @Test
    void testToString() {
        ITetromino tetromino = new ITetromino(new Coordonnees(3, 4), Couleur.ORANGE);
        String expected = "ITetromino :\n" +
                          "\t(3, 4) - ORANGE\n" +
                          "\t(4, 4) - ORANGE\n" +
                          "\t(5, 4) - ORANGE\n" +
                          "\t(6, 4) - ORANGE\n";
        assertEquals(expected, tetromino.toString());
    }

    @Test
    void testGetElements() {
        ITetromino tetromino = new ITetromino(new Coordonnees(3, 4), Couleur.ORANGE);
        Element[] elements = tetromino.getElements();
        assertEquals(4, elements.length);
        assertEquals(new Coordonnees(3, 4), elements[0].getCoordonnees());
        assertEquals(new Coordonnees(4, 4), elements[1].getCoordonnees());
        assertEquals(new Coordonnees(5, 4), elements[2].getCoordonnees());
        assertEquals(new Coordonnees(6, 4), elements[3].getCoordonnees());
    }

    @Test
    void testSetPosition() {
        ITetromino tetromino = new ITetromino(new Coordonnees(3, 4), Couleur.ORANGE);
        tetromino.setPosition(5, 6);
        Element[] elements = tetromino.getElements();
        assertEquals(new Coordonnees(5, 6), elements[0].getCoordonnees());
        assertEquals(new Coordonnees(6, 6), elements[1].getCoordonnees());
        assertEquals(new Coordonnees(7, 6), elements[2].getCoordonnees());
        assertEquals(new Coordonnees(8, 6), elements[3].getCoordonnees());
    }

    @Test
    void testDeplacerDeBas() {
        ITetromino tetromino = new ITetromino(new Coordonnees(4, 0), Couleur.BLEU);
        tetromino.deplacerDe(0, 1);
        for (Element e : tetromino.getElements()) {
            assertEquals(1, e.getCoordonnees().getOrdonnee());
        }
    }

    @Test
    void testRotationHoraire() {
        ITetromino tetromino = new ITetromino(new Coordonnees(4, 4), Couleur.JAUNE);
        tetromino.tourner(true); // rotation horaire
        Element[] elements = tetromino.getElements();

        assertEquals(new Coordonnees(4, 4), elements[0].getCoordonnees()); // centre
        assertEquals(new Coordonnees(4, 3), elements[1].getCoordonnees());
        assertEquals(new Coordonnees(4, 2), elements[2].getCoordonnees());
        assertEquals(new Coordonnees(4, 1), elements[3].getCoordonnees());
    }

    @Test
    void testRotationAntiHoraire() {
        ITetromino tetromino = new ITetromino(new Coordonnees(4, 4), Couleur.VERT);
        tetromino.tourner(false); // rotation anti-horaire
        Element[] elements = tetromino.getElements();

        assertEquals(new Coordonnees(4, 4), elements[0].getCoordonnees()); // centre
        assertEquals(new Coordonnees(4, 5), elements[1].getCoordonnees());
        assertEquals(new Coordonnees(4, 6), elements[2].getCoordonnees());
        assertEquals(new Coordonnees(4, 7), elements[3].getCoordonnees());
    }
}
