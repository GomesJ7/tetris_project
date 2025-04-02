package fr.eseo.e3.ppo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.ITetromino;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.OTetromino;

class PuitsTest {

    private Puits puitsDefaut; // Puits créé avec le constructeur par défaut
    private Puits puitsPerso;  // Puits créé avec des dimensions personnalisées

    @BeforeEach
    void setUp() {
        // Le constructeur par défaut doit utiliser LARGEUR_PAR_DEFAUT et PROFONDEUR_PAR_DEFAUT
        puitsDefaut = new Puits();

        // Constructeur avec des dimensions valides (ex: largeur=12, profondeur=20)
        // qui respectent [5..15] et [15..25]
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
        }, "Une largeur < 5 doit lever IllegalArgumentException.");

        // Largeur > 15
        assertThrows(IllegalArgumentException.class, () -> {
            new Puits(16, 20);
        }, "Une largeur > 15 doit lever IllegalArgumentException.");

        // Profondeur < 15
        assertThrows(IllegalArgumentException.class, () -> {
            new Puits(10, 14);
        }, "Une profondeur < 15 doit lever IllegalArgumentException.");

        // Profondeur > 25
        assertThrows(IllegalArgumentException.class, () -> {
            new Puits(10, 26);
        }, "Une profondeur > 25 doit lever IllegalArgumentException.");
    }

    // Test setPieceSuivante() quand aucune pieceSuivante n'existe encore
    @Test
    void testSetPieceSuivanteSansPrecedente() {
        // Au départ, pieceActuelle et pieceSuivante sont null
        assertNull(puitsDefaut.getPieceActuelle(),
            "La piece Actuelle devrait être null avant toute initialisation.");
        assertNull(puitsDefaut.getPieceSuivante(),
            "La piece Suivante devrait être null avant toute initialisation.");

        // On définit un OTetromino en tant que nouvelle pièce suivante
        // (Adapter si votre constructeur exige Couleur et Coordonnees)
        Piece pieceO = new OTetromino(new Coordonnees(0, 0), Couleur.ROUGE); 
        puitsDefaut.setPieceSuivante(pieceO);

        // Vérification : pieceSuivante est maintenant pieceO
        assertEquals(pieceO, puitsDefaut.getPieceSuivante(),
            "La pièce suivante n'a pas été correctement définie.");
        // pieceActuelle doit toujours être null après le premier appel
        assertNull(puitsDefaut.getPieceActuelle(),
            "La piece Actuelle ne doit pas être modifiée lors du premier appel.");
    }

    // Test setPieceSuivante() quand une pieceSuivante existait déjà
    @Test
    void testSetPieceSuivanteAvecPrecedente() {
        // Première pièce suivante
        Piece pieceO = new OTetromino(new Coordonnees(0, 0), Couleur.ROUGE);
        puitsDefaut.setPieceSuivante(pieceO);

        // Deuxième pièce suivante
        Piece pieceI = new ITetromino(new Coordonnees(5, 5), Couleur.ORANGE);
        puitsDefaut.setPieceSuivante(pieceI);

        // Vérification : pieceActuelle doit être la précédente pieceSuivante (pieceO)
        assertEquals(pieceO, puitsDefaut.getPieceActuelle(),
            "La piece Actuelle devrait être l'ancienne pièce Suivante.");

        // Vérification : pieceSuivante doit être pieceI
        assertEquals(pieceI, puitsDefaut.getPieceSuivante(),
            "La nouvelle pièce Suivante devrait être pieceI.");

        // Vérification de la position de la pieceActuelle (coordonnées (largeur/2, -4))
        // On teste le premier élément de la pièce
        int expectedX = puitsDefaut.getLargeur() / 2;
        int expectedY = -4;

        assertEquals(expectedX,
            pieceO.getElements()[0].getCoordonnees().getAbscisse(),
            "Coordonnée X de la piece Actuelle incorrecte.");
        assertEquals(expectedY,
            pieceO.getElements()[0].getCoordonnees().getOrdonnee(),
            "Coordonnée Y de la piece Actuelle incorrecte.");
    }

    // Test simple de la méthode estPlein() (facultatif selon l'énoncé)
    @Test
    void testEstPlein() {
        // Au départ, le puits est vide => pas plein
        assertFalse(puitsDefaut.estPlein(),
            "Un puits vide ne devrait pas être plein.");

        // Ex. minimal : remplir artificiellement la ligne 0 du puits (si vous souhaitez tester)
        // ...
        // for (int col = 0; col < puitsDefaut.getLargeur(); col++) {
        //     // Simuler un Element dans la première ligne
        //     // (vous pouvez créer un Element avec Coordonnees(col,0) + Couleur.ROUGE par ex.)
        // }

        // assertTrue(puitsDefaut.estPlein(), "Après remplissage, le puits devrait être plein.");
    }

    // Test de toString() pour vérifier le format
    @Test
    void testToString() {
        String str = puitsDefaut.toString();

        // On attend au minimum :
        // "Puits : Dimension 10 x 15"
        // "Piece Actuelle : <aucune>"
        // "Piece Suivante : <aucune>"
        // On peut vérifier avec contains() ou un equals() sur plusieurs lignes

        assertTrue(str.contains("Puits : Dimension 10 x 15"),
            "La première ligne doit indiquer la dimension du puits.");
        assertTrue(str.contains("Piece Actuelle : <aucune>"),
            "La pièce actuelle devrait être <aucune> avant initialisation.");
        assertTrue(str.contains("Piece Suivante : <aucune>"),
            "La pièce suivante devrait être <aucune> avant initialisation.");
    }
}
