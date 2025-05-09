package fr.eseo.e3.ppo.projet.blox.modele;

import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.ITetromino;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TasTest {

    @Test
    public void testConstructeurVide() {
        Puits puits = new Puits(10, 20);
        Tas tas = new Tas(puits);
        assertEquals(puits, tas.getPuits());
        assertTrue(tas.getElements().isEmpty());
    }

    @Test
    public void testConstructeurAvecNbElementsEtLignes() {
        Puits puits = new Puits(10, 20);
        Tas tas = new Tas(puits, 15, 3);
        assertEquals(15, tas.getElements().size());

        for (Element e : tas.getElements()) {
            int y = e.getCoordonnees().getOrdonnee();
            assertTrue(y >= 17 && y <= 19);
        }
    }

    @Test
    public void testElementExiste() {
        Puits puits = new Puits(10, 20);
        Tas tas = new Tas(puits, 1, 1);
        Element e = tas.getElements().get(0);
        assertTrue(tas.elementExiste(e.getCoordonnees().getAbscisse(), e.getCoordonnees().getOrdonnee()));
    }

    @Test
    public void testConstructeurAvecRandom() {
        Puits puits = new Puits(10, 20);
        Random rand = new Random(12345);
        Tas tas = new Tas(puits, 10, 2, rand);
        assertEquals(10, tas.getElements().size());
    }

    @Test
    public void testConstructeurAutoNbLignes() {
        Puits puits = new Puits(10, 20);
        Tas tas = new Tas(puits, 25);
        assertEquals(25, tas.getElements().size());
    }

    @Test
    public void testExceptionTropDElements() {
        Puits puits = new Puits(10, 20);
        assertThrows(IllegalArgumentException.class, () -> {
            new Tas(puits, 1000, 1);
        });
    }

    @Test
    public void testExceptionLignesTropHautes() {
        Puits puits = new Puits(10, 15);
        assertThrows(IllegalArgumentException.class, () -> {
            new Tas(puits, 10, 16);
        });
    }

    @Test
    public void testAjouterElementsDepuisPiece() {
        Puits puits = new Puits(10, 20);
        Tas tas = new Tas(puits);
        puits.setTas(tas);

        Piece piece = new ITetromino(new Coordonnees(4, 10), Couleur.ROUGE);
        tas.ajouterElements(piece);

        for (Element e : piece.getElements()) {
            assertTrue(tas.elementExiste(e.getCoordonnees().getAbscisse(), e.getCoordonnees().getOrdonnee()));
        }
    }

    @Test
    public void testSupprimerLigneComplete() {
        Puits puits = new Puits(10, 15);
        Tas tas = puits.getTas();

        // Remplit complètement la ligne 14
        for (int x = 0; x < puits.getLargeur(); x++) {
            tas.getElements().add(new Element(new Coordonnees(x, 14), Couleur.BLEU));
        }

        // Vérifie que la ligne 14 contient 10 éléments
        long countAvant = tas.getElements().stream()
                .filter(e -> e.getCoordonnees().getOrdonnee() == 14)
                .count();
        assertEquals(10, countAvant);

        // Supprime les lignes complètes
        tas.supprimerLignesCompletes();

        // Vérifie que la ligne 14 est maintenant vide
        long countApres = tas.getElements().stream()
                .filter(e -> e.getCoordonnees().getOrdonnee() == 14)
                .count();
        assertEquals(0, countApres);
    }
}
