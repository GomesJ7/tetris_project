package fr.eseo.e3.ppo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoordonneesTest {

    private Coordonnees coord1;
    private Coordonnees coord2;
    private Coordonnees coord3;

    // Méthode exécutée avant chaque test pour initialiser les coordonnées
    // Cette méthode est utilisée pour créer des objets avant chaque test
    @BeforeEach
    void setUp() {
        // Initialisation des coordonnées de test
        coord1 = new Coordonnees(5, 10);  // Coordonnée 1
        coord2 = new Coordonnees(5, 10);  // Coordonnée 2 (identique à coord1)
        coord3 = new Coordonnees(7, 8);   // Coordonnée 3 (différente de coord1)
    }

    // Test du constructeur de Coordonnees
    @Test
    void testConstructor() {
        // Vérification que l'abscisse de coord1 est bien 5
        assertEquals(5, coord1.getAbscisse(), "L'abscisse devrait être 5");
        // Vérification que l'ordonnée de coord1 est bien 10
        assertEquals(10, coord1.getOrdonnee(), "L'ordonnée devrait être 10");
    }

    // Test de la méthode toString() de Coordonnees
    @Test
    void testToString() {
        // Vérification que la méthode toString() renvoie bien la chaîne "(5, 10)"
        assertEquals("(5, 10)", coord1.toString(), "La méthode toString() doit retourner (5, 10)");
    }

    // Test de la méthode equals() pour comparer deux objets Coordonnees
    @Test
    void testEquals() {
        // Comparaison entre coord1 et coord2 (qui sont identiques)
        assertTrue(coord1.equals(coord2), "Les coordonnées (5, 10) doivent être égales");
        // Comparaison entre coord1 et coord3 (qui sont différentes)
        assertFalse(coord1.equals(coord3), "Les coordonnées (5, 10) et (7, 8) ne doivent pas être égales");
    }

    // Test de la méthode hashCode() pour vérifier les codes de hachage
    @Test
    void testHashCode() {
        // Vérification que les objets égaux (coord1 et coord2) ont le même code de hachage
        assertEquals(coord1.hashCode(), coord2.hashCode(), "Les objets égaux doivent avoir le même hashCode");
        // Vérification que les objets différents (coord1 et coord3) ont des codes de hachage différents
        assertNotEquals(coord1.hashCode(), coord3.hashCode(), "Les objets différents doivent avoir des hashCodes différents");
    }

    // Test des setters pour vérifier que les coordonnées peuvent être mises à jour
    @Test
    void testSetters() {
        // Changement de l'abscisse et de l'ordonnée de coord1
        coord1.setAbscisse(8);
        coord1.setOrdonnee(12);

        // Vérification que l'abscisse a été correctement mise à jour
        assertEquals(8, coord1.getAbscisse(), "L'abscisse doit être mise à jour à 8");
        // Vérification que l'ordonnée a été correctement mise à jour
        assertEquals(12, coord1.getOrdonnee(), "L'ordonnée doit être mise à jour à 12");
    }
}
