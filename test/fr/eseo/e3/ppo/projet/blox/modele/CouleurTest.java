package fr.eseo.e3.ppo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eseo.e3.ppo.projet.blox.modele.Couleur;

class CouleurTest {

	@Test
	void testGetCouleurPourAffichage() {
		assertEquals(Color.RED, Couleur.ROUGE.getCouleurPourAfficher());
		assertEquals(Color.ORANGE, Couleur.ORANGE.getCouleurPourAfficher());
        assertEquals(Color.BLUE, Couleur.BLEU.getCouleurPourAfficher());
        assertEquals(Color.GREEN, Couleur.VERT.getCouleurPourAfficher());
        assertEquals(Color.YELLOW, Couleur.JAUNE.getCouleurPourAfficher());
        assertEquals(Color.CYAN, Couleur.CYAN.getCouleurPourAfficher());
        assertEquals(Color.MAGENTA, Couleur.VIOLET.getCouleurPourAfficher());
	}

}
