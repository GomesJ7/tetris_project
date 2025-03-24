package fr.eseo.e3.ppo.projet.blox.modele.pieces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;

class PuitsTest {

    private Puits puitsDefaut;  // Puits créé avec le constructeur par défaut
    private Puits puitsPerso;   // Puits créé avec des dimensions personnalisées

    @BeforeEach
    void setUp() {
        // Initialisation avant chaque test
        // Le constructeur par défaut doit utiliser LARGEUR_PAR_DEFAUT et PROFONDEUR_PAR_DEFAUT
        puitsDefaut = new Puits();

        // Constructeur avec des dimensions valides (ex: largeur=12, profondeur=20)
        // qui respectent les bornes [5..15] et [15..25]
        puitsPerso = new Puits(12, 20);
    }

    // Vérification du constructeur par défaut
    @Test
    void testConstructeurDefaut() {
        assertEquals(Puits.LARGEUR_PAR_DEFAUT, puitsDefaut.getLargeur(),
            "La largeur par défaut n'est pas correcte.");
        assertEquals(Puits.PROFONDEUR_PAR_DEFAUT, puitsDefaut.getHauteur(),
            "La profondeur par défaut n'est pas correcte.");
    }

    // Vérification du constructeur avec des dimensions valides
    @Test
    void testConstructeurPerso() {
        assertEquals(12, puitsPerso.getLargeur(),
            "La largeur du puits perso n'est pas celle attendue.");
        assertEquals(20, puitsPerso.getHauteur(),
            "La profondeur du puits perso n'est pas celle attendue.");
    }

    // Test des valeurs limites (5..15 pour la largeur, 15..25 pour la profondeur)
    @Test
    void testConstructeurLimites() {
        // Aux limites minimales
        Puits p1 = new Puits(5, 15);
        assertEquals(5, p1.getLargeur());
        assertEquals(15, p1.getHauteur());

        // Aux limites maximales
        Puits p2 = new Puits(15, 25);
        assertEquals(15, p2.getLargeur());
        assertEquals(25, p2.getHauteur());
    }

    // Vérification que des dimensions hors bornes provoquent une exception
    @Test
    void testConstructeurInvalide() {
        // Largeur < 5
        assertThrows(IllegalArgumentException.class, () -> {
            new Puits(4, 20);
        });
        // Largeur > 15
        assertThrows(IllegalArgumentException.class, () -> {
            new Puits(16, 20);
        });
        // Profondeur < 15
        assertThrows(IllegalArgumentException.class, () -> {
            new Puits(10, 14);
        });
        // Profondeur > 25
        assertThrows(IllegalArgumentException.class, () -> {
            new Puits(10, 26);
        });
    }

    // Test setPieceSuivante() quand aucune pieceSuivante n'existe encore
    @Test
    void testSetPieceSuivanteSansPrecedente() {
        // Au départ, pieceActuelle et pieceSuivante sont null
        assertNull(puitsDefaut.getPieceActuelle(),
            "La pieceActuelle devrait être null avant toute initialisation.");
        assertNull(puitsDefaut.getPieceSuivante(),
            "La pieceSuivante devrait être null avant toute initialisation.");

        // On définit une OTetromino en tant que nouvelle pièce suivante
        Piece pieceO = new OTetromino(); 
        puitsDefaut.setPieceSuivante(pieceO);

        // Vérification : pieceSuivante est maintenant pieceO
        assertEquals(pieceO, puitsDefaut.getPieceSuivante(),
            "La pièce suivante n'a pas été correctement définie.");
        // pieceActuelle doit toujours être null après le premier appel
        assertNull(puitsDefaut.getPieceActuelle(),
            "La pieceActuelle ne doit pas être modifiée lors du premier appel.");
    }

    // Test setPieceSuivante() quand une pieceSuivante existait déjà
    @Test
    void testSetPieceSuivanteAvecPrecedente() {
        // Première pièce suivante
        Piece pieceO = new OTetromino();
        puitsDefaut.setPieceSuivante(pieceO);

        // Deuxième pièce suivante
        Piece pieceI = new ITetromino();
        puitsDefaut.setPieceSuivante(pieceI);

        // Vérification : pieceActuelle doit être la précédente pieceSuivante (pieceO)
        assertEquals(pieceO, puitsDefaut.getPieceActuelle(),
            "La pieceActuelle devrait être l'ancienne pièceSuivante.");

        // Vérification : pieceSuivante doit être pieceI
        assertEquals(pieceI, puitsDefaut.getPieceSuivante(),
            "La nouvelle pièceSuivante devrait être pieceI.");

        // Vérification de la position de la pieceActuelle (coordonnées (largeur/2, -4))
        // On suppose que la classe Puits définit pieceActuelle.setPosition(largeur/2, -4)
        // On teste ici le premier élément de la pièce (si c'est un OTetromino, par exemple)
        assertEquals(puitsDefaut.getLargeur() / 2,
            pieceO.getElements()[0].getCoordonnees().getAbscisse(),
            "Coordonnée X de la pieceActuelle incorrecte.");
        assertEquals(-4,
            pieceO.getElements()[0].getCoordonnees().getOrdonnee(),
            "Coordonnée Y de la pieceActuelle incorrecte.");
    }

    // Test simple de la méthode estPlein() (facultatif selon l'énoncé)
    @Test
    void testEstPlein() {
        // Au départ, le puits est vide => pas plein
        assertFalse(puitsDefaut.estPlein(),
            "Un puits vide ne devrait pas être plein.");

        // Remplir artificiellement la première ligne (exemple minimal)
        // On ne montre pas tout le code ici, mais tu peux ajouter des Element
        // sur la ligne 0 pour simuler le fait que le puits est plein
        // ...
        // assertTrue(...) ensuite
    }
}
