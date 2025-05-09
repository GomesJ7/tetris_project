package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.BloxException;
import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
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
    void testDeplacerDeBas() throws BloxException {
        OTetromino tetromino = new OTetromino(new Coordonnees(4, 0), Couleur.BLEU);
        tetromino.setPuits(new Puits(10, 20));

        Coordonnees[] avant = new Coordonnees[4];
        for (int i = 0; i < 4; i++) {
            avant[i] = tetromino.getElements()[i].getCoordonnees();
        }

        tetromino.deplacerDe(0, 1);

        for (int i = 0; i < 4; i++) {
            Coordonnees apres = tetromino.getElements()[i].getCoordonnees();
            assertEquals(avant[i].getAbscisse(), apres.getAbscisse(), "X ne doit pas changer");
            assertEquals(avant[i].getOrdonnee() + 1, apres.getOrdonnee(), "Y doit augmenter de 1");
        }
    }

    @Test
    void testRotationThrowsException() {
        OTetromino tetromino = new OTetromino(new Coordonnees(4, 0), Couleur.BLEU);
        
        Exception exception1 = assertThrows(UnsupportedOperationException.class, () -> {
            tetromino.tourner(true);
        });
        assertEquals("L'OTetromino ne peut pas être tourné.", exception1.getMessage());

        Exception exception2 = assertThrows(UnsupportedOperationException.class, () -> {
            tetromino.tourner(false);
        });
        assertEquals("L'OTetromino ne peut pas être tourné.", exception2.getMessage());
    }

}
