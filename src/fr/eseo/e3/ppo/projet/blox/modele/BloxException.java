package fr.eseo.e3.ppo.projet.blox.modele;

public class BloxException extends Exception {

    // Constantes de type d’erreur
    public static final int BLOX_COLLISION = 1;
    public static final int BLOX_SORTIE_PUITS = 2;

    // Code d’erreur
    private final int type;

    // Constructeur
    public BloxException(String message, int type) {
        super(message);
        this.type = type;
    }

    // Accesseur
    public int getType() {
        return this.type;
    }
}
