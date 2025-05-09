package fr.eseo.e3.ppo.projet.blox.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.OTetromino;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.ITetromino;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PuitsTest {

    private Puits puitsDefaut;
    private Puits puitsPerso;

    @BeforeEach
    void setUp() {
        puitsDefaut = new Puits();
        puitsPerso = new Puits(12, 20);
    }

    @Test
    void testConstructeurParDefaut() {
        assertEquals(10, puitsDefaut.getLargeur());
        assertEquals(15, puitsDefaut.getProfondeur());
    }

    @Test
    void testConstructeurAvecParametresValidés() {
        assertEquals(12, puitsPerso.getLargeur());
        assertEquals(20, puitsPerso.getProfondeur());
    }

    @Test
    void testConstructeurParametresInvalides() {
        assertThrows(IllegalArgumentException.class, () -> new Puits(4, 20));
        assertThrows(IllegalArgumentException.class, () -> new Puits(16, 20));
        assertThrows(IllegalArgumentException.class, () -> new Puits(10, 14));
        assertThrows(IllegalArgumentException.class, () -> new Puits(10, 26));
    }

    @Test
    void testEstPlein_false() {
        assertFalse(puitsDefaut.estPlein());
    }

    @Test
    void testEstPlein_true() {
        Element[][] grille = puitsDefaut.getGrille();
        for (int i = 0; i < puitsDefaut.getLargeur(); i++) {
            grille[0][i] = new Element(new Coordonnees(i, 0), Couleur.BLEU);
        }
        assertTrue(puitsDefaut.estPlein());
    }

    @Test
    void testToString_FormatTexte() {
        String str = puitsDefaut.toString();
        assertTrue(str.contains("Puits : Dimension 10 x 15"));
        assertTrue(str.contains("Piece Actuelle : <aucune>"));
        assertTrue(str.contains("Piece Suivante : <aucune>"));
    }

    @Test
    void testSetPieceSuivante_PremierAppel() {
        Piece pieceO = new OTetromino(new Coordonnees(4, 5), Couleur.ROUGE);
        puitsDefaut.setPieceSuivante(pieceO);

        assertNull(puitsDefaut.getPieceActuelle());
        assertEquals(pieceO, puitsDefaut.getPieceSuivante());
    }

    @Test
    void testSetPieceSuivante_DeplacementPieceActuelle() {
        Piece pieceO = new OTetromino(new Coordonnees(2, 2), Couleur.JAUNE);
        Piece pieceI = new ITetromino(new Coordonnees(3, 3), Couleur.CYAN);

        puitsDefaut.setPieceSuivante(pieceO);
        puitsDefaut.setPieceSuivante(pieceI);

        assertEquals(pieceO, puitsDefaut.getPieceActuelle());

        assertEquals(5, pieceO.getElements()[0].getCoordonnees().getAbscisse());
        assertEquals(-4, pieceO.getElements()[0].getCoordonnees().getOrdonnee());

        assertEquals(pieceI, puitsDefaut.getPieceSuivante());
    }

    @Test
    void testAccesseurs() {
        assertEquals(puitsDefaut.getGrille().length, puitsDefaut.getProfondeur());
        assertEquals(puitsDefaut.getGrille()[0].length, puitsDefaut.getLargeur());
    }

    @Test
    void testGetTasVideParDefaut() {
        assertNotNull(puitsDefaut.getTas(), "Le tas ne doit pas être null");
        assertTrue(puitsDefaut.getTas().getElements().isEmpty(), "Le tas doit être vide");
    }

    @Test
    void testSetTas() {
        Tas nouveauTas = new Tas(puitsDefaut);
        puitsDefaut.setTas(nouveauTas);
        assertEquals(nouveauTas, puitsDefaut.getTas());
    }

    @Test
    void testConstructeurAvecTasRempli() {
        Puits puitsAvecTas = new Puits(10, 20, 15, 3);
        Tas tas = puitsAvecTas.getTas();

        assertNotNull(tas);
        assertEquals(15, tas.getElements().size());

        for (Element e : tas.getElements()) {
            int y = e.getCoordonnees().getOrdonnee();
            assertTrue(y >= 17 && y < 20, "Élément hors des lignes de base");
        }
    }

    @Test
    void testGraviteSansCollision() {
        Puits puits = new Puits(10, 20);
        Piece piece = new ITetromino(new Coordonnees(4, 0), Couleur.VERT);
        puits.setPieceActuelle(piece);

        puits.gravite();

        for (Element e : piece.getElements()) {
            assertEquals(1, e.getCoordonnees().getOrdonnee());
        }
    }

    @Test
    void testGraviteAvecCollisionBasPuits() {
        Puits puits = new Puits(10, 15);
        Piece piece = new ITetromino(new Coordonnees(4, 11), Couleur.ROUGE);
        puits.setPieceActuelle(piece);

        while (puits.getPieceActuelle() != null) {
            puits.gravite();
        }

        List<Element> tasElements = puits.getTas().getElements();
        assertFalse(tasElements.isEmpty(), "Le tas doit contenir des éléments après collision");
        assertNull(puits.getPieceActuelle(), "La pièce actuelle doit être nulle après collision");
    }
}
