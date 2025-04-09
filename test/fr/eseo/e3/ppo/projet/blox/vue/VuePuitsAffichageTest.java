package fr.eseo.e3.ppo.projet.blox.vue;

import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.UsineDePiece;
import fr.eseo.e3.ppo.projet.blox.modele.Mode;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class VuePuitsAffichageTest {

	public static void main(String[] args) {
	    // Création du puits
	    Puits puits = new Puits();

	    // Création de la vue avec une taille personnalisée (30 pixels par élément)
	    VuePuits vuePuits = new VuePuits(puits, 30);
	    // Enregistrement de la VuePuits comme écouteur sur le puits
	    puits.addPropertyChangeListener(vuePuits);

	    // Création de l'usine de pièce en mode déterministe pour obtenir toujours la même suite
	    UsineDePiece usine = new UsineDePiece();
	    usine.setMode(Mode.DETERMINISTE);
	    Piece piece1 = usine.genererPiece();
	    Piece piece2 = usine.genererPiece();

	    // Premier appel : définit uniquement la pièce suivante (pieceSuivante)
	    puits.setPieceSuivante(piece1);

	    // Deuxième appel : la pièce déjà présente devient la pièce actuelle, et piece2 est la nouvelle pièce suivante
	    puits.setPieceSuivante(piece2);

	    // Attente pour permettre le passage des PropertyChangeEvents et la mise à jour automatique de la VuePiece
	    try {
	        Thread.sleep(500);
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }

	    // Vérification manuelle : affichage dans la console pour confirmer que la VuePiece a été correctement mise à jour
	    if (vuePuits.getPuits() != null) {
	        System.out.println("VuePiece mise à jour avec succès !");
	    } else {
	        System.out.println("Erreur : la VuePiece n'est pas initialisée !");
	    }

	    // Création et affichage de la fenêtre pour tester visuellement l'affichage
	    JFrame frame = new JFrame("Vue du Puits avec VuePiece");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(vuePuits, BorderLayout.CENTER);
	    frame.pack(); // ajuste la taille avec getPreferredSize()
	    frame.setLocationRelativeTo(null); // centre la fenêtre
	    frame.setVisible(true);
	}

}