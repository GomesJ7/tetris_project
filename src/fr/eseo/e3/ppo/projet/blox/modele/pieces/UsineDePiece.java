package fr.eseo.e3.ppo.projet.blox.modele.pieces;

import fr.eseo.e3.ppo.projet.blox.modele.Mode;
import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos.*;

import java.util.Random;

public class UsineDePiece {

    private Mode mode = Mode.ALEATOIRE;
    private int compteur = 0;
    private final Random random = new Random();

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return this.mode;
    }

    public Piece genererPiece() {
        Coordonnees coord = new Coordonnees(0, 0);
        Couleur[] couleurs = Couleur.values();
        Couleur couleur = couleurs[random.nextInt(couleurs.length)];

        if (mode == Mode.ALEATOIRE) {
            // Étendu à 7 types possibles
            int type = random.nextInt(7);
            return switch (type) {
                case 0 -> new ITetromino(coord, couleur);
                case 1 -> new OTetromino(coord, couleur);
                case 2 -> new TTetromino(coord, couleur);
                case 3 -> new LTetromino(coord, couleur);
                case 4 -> new JTetromino(coord, couleur);
                case 5 -> new ZTetromino(coord, couleur);
                case 6 -> new STetromino(coord, couleur);
                default -> new ITetromino(coord, couleur); // fallback
            };
        } else if (mode == Mode.DETERMINISTE) {
            // Alterne uniquement I et O
            Piece piece = (compteur % 2 == 0)
                    ? new ITetromino(coord, couleur)
                    : new OTetromino(coord, couleur);
            compteur++;
            return piece;
        } else { // Mode CYCLIC
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
