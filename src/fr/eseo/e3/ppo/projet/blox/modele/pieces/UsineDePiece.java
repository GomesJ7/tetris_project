package fr.eseo.e3.ppo.projet.blox.modele.pieces;

import fr.eseo.e3.ppo.projet.blox.modele.Mode;
import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.*;

import java.util.Random;

/**
 * Classe représentant une usine de fabrication de pièces (pattern Factory).
 * Elle génère des objets `Piece` selon un mode défini :
 * - ALÉATOIRE : une des 7 pièces de Tetris, au hasard
 * - DÉTERMINISTE : alternance I / O (utile pour les tests)
 * - CYCLIC : enchaînement cyclique des 7 formes
 *
 * Cette classe centralise la logique de création,
 * ce qui respecte les principes de modularité et de séparation des responsabilités.
 */
public class UsineDePiece {

    private Mode mode = Mode.ALEATOIRE;       // Mode de génération courant
    private int compteur = 0;                 // Sert pour DETERMINISTE et CYCLIC
    private final Random random = new Random(); // Générateur de hasard

    // === Configuration ===

    /**
     * Définit le mode de génération.
     * @param mode l’un des modes de l’énumération Mode
     */
    public void setMode(Mode mode) {
        this.mode = mode;
    }

    /**
     * Récupère le mode actuel.
     */
    public Mode getMode() {
        return this.mode;
    }

    // === Génération de pièce ===

    /**
     * Fabrique une nouvelle pièce en fonction du mode défini.
     * Les pièces sont positionnées en (0, 0) initialement, avec une couleur aléatoire.
     */
    public Piece genererPiece() {
        Coordonnees coord = new Coordonnees(0, 0); // Position d’origine de la pièce
        Couleur[] couleurs = Couleur.values();     // Récupère toutes les couleurs possibles
        Couleur couleur = couleurs[random.nextInt(couleurs.length)]; // Tire une couleur au hasard

        // === Mode ALÉATOIRE ===
        if (mode == Mode.ALEATOIRE) {
            int type = random.nextInt(7); // Tire un entier entre 0 et 6
            return switch (type) {
                case 0 -> new ITetromino(coord, couleur);
                case 1 -> new OTetromino(coord, couleur);
                case 2 -> new TTetromino(coord, couleur);
                case 3 -> new LTetromino(coord, couleur);
                case 4 -> new JTetromino(coord, couleur);
                case 5 -> new ZTetromino(coord, couleur);
                case 6 -> new STetromino(coord, couleur);
                default -> new ITetromino(coord, couleur); // sécurité
            };
        }

        // === Mode DETERMINISTE ===
        else if (mode == Mode.DETERMINISTE) {
            // Alterne entre I et O
            Piece piece = (compteur % 2 == 0)
                    ? new ITetromino(coord, couleur)
                    : new OTetromino(coord, couleur);
            compteur++;
            return piece;
        }

        // === Mode CYCLIC ===
        else {
            Piece piece;
            switch (compteur % 7) {
                case 0 -> piece = new ITetromino(coord, couleur);
                case 1 -> piece = new OTetromino(coord, couleur);
                case 2 -> piece = new TTetromino(coord, couleur);
                case 3 -> piece = new LTetromino(coord, couleur);
                case 4 -> piece = new JTetromino(coord, couleur);
                case 5 -> piece = new ZTetromino(coord, couleur);
                case 6 -> piece = new STetromino(coord, couleur);
                default -> piece = new ITetromino(coord, couleur);
            }
            compteur++;
            return piece;
        }
    }
}
