package fr.eseo.e3.ppo.projet.blox.modele;

import fr.eseo.e3.ppo.projet.blox.modele.pieces.UsineDePiece;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour UsineDePiece.
 */
public class UsineDePieceTest {

    /**
     * Vérifie que le mode par défaut est bien ALEATOIRE.
     */
    @Test
    public void testModeParDefaut() {
        UsineDePiece usine = new UsineDePiece();
        assertEquals(Mode.ALEATOIRE, usine.getMode(), "Le mode par défaut devrait être ALEATOIRE.");
    }

    /**
     * Vérifie que le mode peut être modifié avec setMode().
     */
    @Test
    public void testSetGetMode() {
        UsineDePiece usine = new UsineDePiece();
        usine.setMode(Mode.DETERMINISTE);
        assertEquals(Mode.DETERMINISTE, usine.getMode(), "Le mode n’a pas été correctement modifié.");
    }

    /**
     * Teste la génération d’une pièce en mode ALEATOIRE.
     */
    @Test
    public void testGenererPieceAleatoire() {
        UsineDePiece usine = new UsineDePiece();
        usine.setMode(Mode.ALEATOIRE);

        Piece piece = usine.genererPiece();

        assertNotNull(piece, "La pièce générée ne doit pas être null.");
        assertEquals(0, piece.getElements()[0].getCoordonnees().getAbscisse(), "X doit être 0.");
        assertEquals(0, piece.getElements()[0].getCoordonnees().getOrdonnee(), "Y doit être 0.");
        assertNotNull(piece.getElements()[0].getCouleur(), "La couleur de la pièce ne doit pas être null.");
    }

    /**
     * Teste la génération déterministe : I, O, I, O...
     */
    @Test
    public void testGenererPieceDeterministe() {
        UsineDePiece usine = new UsineDePiece();
        usine.setMode(Mode.DETERMINISTE);

        Piece piece1 = usine.genererPiece(); // Doit être ITetromino
        Piece piece2 = usine.genererPiece(); // Doit être OTetromino
        Piece piece3 = usine.genererPiece(); // Doit être ITetromino

        assertEquals("ITetromino", piece1.getClass().getSimpleName());
        assertEquals("OTetromino", piece2.getClass().getSimpleName());
        assertEquals("ITetromino", piece3.getClass().getSimpleName());
    }

    /**
     * Vérifie que les pièces ont des couleurs valides.
     */
    @Test
    public void testCouleurPieceGenerée() {
        UsineDePiece usine = new UsineDePiece();
        Piece piece = usine.genererPiece();
        Couleur couleur = piece.getElements()[0].getCouleur();
        assertTrue(couleur instanceof Couleur, "La couleur doit être une instance de l’énumération Couleur.");
    }
}
