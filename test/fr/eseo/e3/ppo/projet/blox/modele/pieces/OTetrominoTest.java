package fr.eseo.e3.ppo.projet.blox.modele.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.OTetromino;

public class OTetrominoTest {

    @Test
    public void testElements() {
        OTetromino o = new OTetromino(new Coordonnees(3, 4), Couleur.BLEU);
        Element[] elements = o.getElements();

        assertEquals(4, elements.length);
        assertEquals("(3, 4) - BLEU", elements[0].toString());
        assertEquals("(4, 4) - BLEU", elements[1].toString());
        assertEquals("(3, 3) - BLEU", elements[2].toString());
        assertEquals("(4, 3) - BLEU", elements[3].toString());
    }

    @Test
    public void testToString() {
        OTetromino o = new OTetromino(new Coordonnees(1, 1), Couleur.CYAN);
        String result = o.toString();
        assertTrue(result.contains("OTetromino"));
        assertTrue(result.contains("(1, 1) - CYAN"));
    }
}
