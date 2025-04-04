package fr.eseo.e3.ppo.projet.blox.modele.pieces;

import fr.eseo.e3.ppo.projet.blox.modele.Mode;
import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.*;

import java.util.Random;

/**
 * Classe représentant une usine de fabrication de pièces du jeu.
 * Elle peut fonctionner en mode aléatoire ou déterministe.
 */
public class UsineDePiece {

    // Le mode courant de l'usine (ALEATOIRE ou DETERMINISTE)
    private Mode mode = Mode.ALEATOIRE;

    // Compteur utilisé pour alterner les pièces en mode DETERMINISTE
    private int compteur = 0;

    // Générateur de nombres aléatoires (utilisé pour le type et la couleur)
    private final Random random = new Random();

    /**
     * Définit le mode de génération (ALEATOIRE ou DETERMINISTE).
     * @param mode Le mode choisi
     */
    public void setMode(Mode mode) {
        this.mode = mode;
    }

    /**
     * Retourne le mode actuel utilisé par l’usine.
     * @return Le mode de génération
     */
    public Mode getMode() {
        return this.mode;
    }

    /**
     * Génère une pièce selon le mode sélectionné.
     * - En mode ALEATOIRE : type et couleur choisis aléatoirement.
     * - En mode DETERMINISTE : alternance I → O → I → ...
     * @return La pièce nouvellement créée.
     */
    public Piece genererPiece() {
        // Toutes les pièces doivent être créées à la position (0, 0)
        Coordonnees coord = new Coordonnees(0, 0);

        // Sélection aléatoire d’une couleur parmi toutes celles disponibles
        Couleur[] couleurs = Couleur.values();
        Couleur couleur = couleurs[random.nextInt(couleurs.length)];

        // Génération selon le mode sélectionné
        if (mode == Mode.ALEATOIRE) {
            // Génère aléatoirement une pièce de type I ou O
            int type = random.nextInt(2); // 0 = I, 1 = O
            return (type == 0)
                ? new ITetromino(coord, couleur)
                : new OTetromino(coord, couleur);
        } else {
            // En mode déterministe, alterne I puis O, etc.
            Piece piece;
            if (compteur % 2 == 0) {
                piece = new ITetromino(coord, couleur);
            } else {
                piece = new OTetromino(coord, couleur);
            }
            compteur++; // Incrémenter pour alterner à l’appel suivant
            return piece;
        }
    }
}
