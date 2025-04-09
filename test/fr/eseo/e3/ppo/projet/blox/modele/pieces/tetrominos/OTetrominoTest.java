package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OTetrominoTest {

    @Test
    void testToString() {
        OTetromino tetromino = new OTetromino(new Coordonnees(6, 5), Couleur.CYAN);
        String expected = "OTetromino :\n" +
                          "\t(6, 5) - CYAN\n" +
                          "\t(7, 5) - CYAN\n" +
                          "\t(6, 6) - CYAN\n" +
                          "\t(7, 6) - CYAN\n";
        assertEquals(expected, tetromino.toString());
    }

    @Test
    void testGetElements() {
        OTetromino tetromino = new OTetromino(new Coordonnees(6, 5), Couleur.CYAN);
        Element[] elements = tetromino.getElements();
        assertEquals(4, elements.length);
        assertEquals(new Coordonnees(6, 5), elements[0].getCoordonnees());
        assertEquals(new Coordonnees(7, 5), elements[1].getCoordonnees());
        assertEquals(new Coordonnees(6, 6), elements[2].getCoordonnees());
        assertEquals(new Coordonnees(7, 6), elements[3].getCoordonnees());
    }

    @Test
    void testOTetrominoConstructorDefaultColor() {
        OTetromino tetromino = new OTetromino(new Coordonnees(12, 7), Couleur.VIOLET);
        for (Element e : tetromino.getElements()) {
            assertEquals(Couleur.VIOLET, e.getCouleur());
        }
    }

    @Test
    void testDeplacerDeBas() {
        OTetromino tetromino = new OTetromino(new Coordonnees(4, 0), Couleur.BLEU);
        tetromino.deplacerDe(0, 1);
        for (Element e : tetromino.getElements()) {
            assertEquals(1, e.getCoordonnees().getOrdonnee());
        }
    }

    @Test
    void testRotationDoesNothing() {
        OTetromino tetromino = new OTetromino(new Coordonnees(2, 3), Couleur.JAUNE);
        // Sauvegarde de l'état initial
        Element[] before = tetromino.getElements();
        Coordonnees[] initialCoords = new Coordonnees[before.length];
        for (int i = 0; i < before.length; i++) {
            initialCoords[i] = before[i].getCoordonnees();
        }

        // Tentative de rotation
        tetromino.tourner(true);
        tetromino.tourner(false);

        // Vérifie qu'aucune coordonnée n'a changé
        Element[] after = tetromino.getElements();
        for (int i = 0; i < after.length; i++) {
            assertEquals(initialCoords[i], after[i].getCoordonnees(), "La rotation ne devrait pas affecter un OTetromino.");
        }
    }
}
