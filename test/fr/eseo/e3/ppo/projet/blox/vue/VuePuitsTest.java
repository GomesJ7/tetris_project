package fr.eseo.e3.ppo.projet.blox.vue;

import javax.swing.JFrame;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.OTetromino;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;

public class VuePuitsTest {
	private static Puits puits;
	private static VuePuits vue;
	private static OTetromino pieceOTetromino;
	
	@BeforeEach
	void beforeEach() {
		// Crée un puits
        puits = new Puits(10, 20); 

        // Ajoute une pièce dans le puits (ex : OTetromino rouge)
        pieceOTetromino = new OTetromino(new Coordonnees(4, 5), Couleur.ROUGE);
        puits.ajouterPiece(pieceOTetromino);

        // Crée la vue du puits
        VuePuits vue = new VuePuits(puits, 30); // Taille plus grande pour test visuel

        // Crée une fenêtre et affiche la vue
        JFrame fenetre = new JFrame("Test VuePuits");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setContentPane(vue);
        fenetre.pack();
        fenetre.setSize(400, 600);
        fenetre.setVisible(true); 
    }
    
	@Test
	void testGetTaille() {
	    // Vérifie que getTaille() renvoie la taille passée dans le constructeur.
	    assertEquals(30, vue.getTaille());
	}

	@Test
	void testGetPuits() {
	    // Vérifie que la vue retourne le même Puits qui lui a été associé.
	    assertSame(puits, vue.getPuits());
	}

	@Test
	void testSetTaille() {
	    // Modifier la taille et vérifier que getTaille() reflète le changement.
	    vue.setTaille(50);
	    assertEquals(50, vue.getTaille());
	}

	@Test
	void testSetPuits() {
	    // Créer un nouveau puits et s'assurer que le setter met à jour l'association dans la vue.
	    Puits puits816 = new Puits(8, 16);
	    vue.setPuits(puits816);
	    assertSame(puits816, vue.getPuits());
	}

	// Test manuel : créer une fenêtre et afficher la vue pour vérification visuelle.
	public static void main(String[] args) {
	    VuePuitsTest testInstance = new VuePuitsTest();
	    testInstance.beforeEach(); // initialisation manuelle
	    
	    JFrame fenetre = new JFrame("Test VuePuits");
	    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    fenetre.setContentPane(VuePuitsTest.vue);
	    fenetre.pack();
	    fenetre.setSize(new Dimension(400, 600));
	    fenetre.setVisible(true); 
	}
    
}

