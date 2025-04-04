package fr.eseo.e3.ppo.projet.blox.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.OTetromino;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.ITetromino;

import static org.junit.jupiter.api.Assertions.*;

// Classe de test unitaire pour la classe Puits
class PuitsTest {

    private Puits puitsDefaut; // Puits créé avec les dimensions par défaut
    private Puits puitsPerso;  // Puits avec des dimensions personnalisées

    // Initialisation des objets avant chaque test
    @BeforeEach
    void setUp() {
        puitsDefaut = new Puits();               // Puits 10x15
        puitsPerso = new Puits(12, 20);          // Puits 12x20
    }

    // Teste les valeurs du constructeur par défaut
    @Test
    void testConstructeurParDefaut() {
        assertEquals(10, puitsDefaut.getLargeur());
        assertEquals(15, puitsDefaut.getProfondeur());
    }

    // Teste un constructeur avec des dimensions valides
    @Test
    void testConstructeurAvecParametresValidés() {
        assertEquals(12, puitsPerso.getLargeur());
        assertEquals(20, puitsPerso.getProfondeur());
    }

    // Teste les cas où le constructeur reçoit des dimensions invalides
    @Test
    void testConstructeurParametresInvalides() {
        assertThrows(IllegalArgumentException.class, () -> new Puits(4, 20));   // largeur trop petite
        assertThrows(IllegalArgumentException.class, () -> new Puits(16, 20));  // largeur trop grande
        assertThrows(IllegalArgumentException.class, () -> new Puits(10, 14));  // profondeur trop petite
        assertThrows(IllegalArgumentException.class, () -> new Puits(10, 26));  // profondeur trop grande
    }

    // Vérifie que le puits vide n’est pas considéré comme plein
    @Test
    void testEstPlein_false() {
        assertFalse(puitsDefaut.estPlein());
    }

    // Remplit la première ligne à la main, puis vérifie que le puits est détecté comme plein
    @Test
    void testEstPlein_true() {
        Element[][] grille = puitsDefaut.getGrille();
        for (int i = 0; i < puitsDefaut.getLargeur(); i++) {
            grille[0][i] = new Element(new Coordonnees(i, 0), Couleur.BLEU);
        }
        assertTrue(puitsDefaut.estPlein());
    }

    // Vérifie le contenu textuel retourné par toString()
    @Test
    void testToString_FormatTexte() {
        String str = puitsDefaut.toString();
        assertTrue(str.contains("Puits : Dimension 10 x 15"));
        assertTrue(str.contains("Piece Actuelle : <aucune>"));
        assertTrue(str.contains("Piece Suivante : <aucune>"));
    }

    // Vérifie le comportement de setPieceSuivante quand aucune pièce actuelle n'existe
    @Test
    void testSetPieceSuivante_PremierAppel() {
        Piece pieceO = new OTetromino(new Coordonnees(4, 5), Couleur.ROUGE);
        puitsDefaut.setPieceSuivante(pieceO);

        assertNull(puitsDefaut.getPieceActuelle());
        assertEquals(pieceO, puitsDefaut.getPieceSuivante());
    }

    // Vérifie que l’ancienne pièceSuivante devient pièceActuelle lors du 2e appel
    @Test
    void testSetPieceSuivante_DeplacementPieceActuelle() {
        Piece pieceO = new OTetromino(new Coordonnees(2, 2), Couleur.JAUNE);
        Piece pieceI = new ITetromino(new Coordonnees(3, 3), Couleur.CYAN);

        puitsDefaut.setPieceSuivante(pieceO);
        puitsDefaut.setPieceSuivante(pieceI);

        assertEquals(pieceO, puitsDefaut.getPieceActuelle());

        // La position doit avoir été recalculée à (largeur / 2, -4)
        assertEquals(5, pieceO.getElements()[0].getCoordonnees().getAbscisse());
        assertEquals(-4, pieceO.getElements()[0].getCoordonnees().getOrdonnee());

        assertEquals(pieceI, puitsDefaut.getPieceSuivante());
    }

    // Vérifie que clearLigne supprime bien une ligne remplie
    @Test
    void testClearLigne() {
        // Ajout d'une pièce O dans le puits
        Piece pieceO = new OTetromino(new Coordonnees(4, 5), Couleur.ROUGE);
        puitsDefaut.setPieceSuivante(pieceO);
        puitsDefaut.ajouterPiece(pieceO);

        // Remplissage manuel de la ligne 5
        for (int i = 0; i < puitsDefaut.getLargeur(); i++) {
            if (puitsDefaut.getGrille()[5][i] == null) {
                puitsDefaut.getGrille()[5][i] = new Element(new Coordonnees(i, 5), Couleur.BLEU);
            }
        }

        // Vérifie que la ligne 5 est bien remplie avant suppression
        for (int i = 0; i < puitsDefaut.getLargeur(); i++) {
            assertNotNull(puitsDefaut.getGrille()[5][i], "Ligne non remplie avant clear.");
        }

        // Appel à clearLigne
        puitsDefaut.clearLigne(5);

        // Vérifie que la ligne 5 est vide après suppression
        for (int i = 0; i < puitsDefaut.getLargeur(); i++) {
            assertNull(puitsDefaut.getGrille()[5][i], "Ligne non vidée après clear.");
        }
    }

    // Teste que les getters grille / largeur / profondeur sont cohérents
    @Test
    void testAccesseurs() {
        assertEquals(puitsDefaut.getGrille().length, puitsDefaut.getProfondeur());
        assertEquals(puitsDefaut.getGrille()[0].length, puitsDefaut.getLargeur());
    }
}
