package fr.eseo.e3.ppo.projet.blox.modele;

/**
 * Classe représentant une exception spécifique au projet FallingBlox.
 * Elle hérite de la classe standard {@code Exception}, ce qui permet de
 * signaler des erreurs propres à la logique métier du jeu :
 * - collision entre pièces
 * - sortie des limites du puits
 *
 * Utiliser des exceptions personnalisées est une bonne pratique pour
 * clarifier les erreurs attendues dans un contexte précis.
 */
public class BloxException extends Exception {

    // === Constantes de types d’erreur ===

    /** Collision entre éléments lors d’un déplacement ou d’une chute */
    public static final int BLOX_COLLISION = 1;

    /** Sortie de la pièce hors des limites du puits */
    public static final int BLOX_SORTIE_PUITS = 2;

    // === Attribut encapsulé ===

    /** Code de type d’erreur */
    private final int type;

    /**
     * Constructeur de l’exception.
     * @param message Message d’erreur détaillé (hérité de Exception)
     * @param type Type d’erreur (utilise les constantes définies ci-dessus)
     */
    public BloxException(String message, int type) {
        super(message);     // appel au constructeur de la classe mère Exception
        this.type = type;   // affectation du type d’erreur
    }

    /**
     * Accesseur au type d’erreur.
     * @return code entier correspondant au type d’erreur
     */
    public int getType() {
        return this.type;
    }
}
