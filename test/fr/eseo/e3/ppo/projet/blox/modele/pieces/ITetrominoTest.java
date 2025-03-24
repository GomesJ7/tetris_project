package fr.eseo.e3.ppo.projet.blox.modele.pieces;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.ITetromino;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ITetrominoTest {

	@Test
    public void testElements() {
        ITetromino i = new ITetromino(new Coordonnees(5, 5), Couleur.ROUGE);
        Element[] elements = i.getElements();

        assertEquals(4, elements.length);
        assertEquals("(5, 5) - ROUGE", elements[0].toString());
        assertEquals("(5, 4) - ROUGE", elements[1].toString());
        assertEquals("(5, 6) - ROUGE", elements[2].toString());
        assertEquals("(5, 7) - ROUGE", elements[3].toString());
    }

	@Test
	public void testToString() {
	    ITetromino i = new ITetromino(new Coordonnees(2, 2), Couleur.ORANGE);
	    String result = i.toString();

	    //  Affichage pour debug
	    System.out.println("Résultat de toString() :\n" + result);

	    assertTrue(result.startsWith("ITetromino"));

	}


}
