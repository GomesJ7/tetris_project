package fr.eseo.e3.ppo.projet.blox.modele;

import java.awt.Color;

/**
 * Enumération représentant les couleurs possibles des éléments du jeu.
 * Chaque valeur de l'énumération est associée à une instance de {@link java.awt.Color}
 * pour l'affichage graphique.
 *
 * Cette classe montre l'utilisation avancée des énumérations en Java :
 * - chaque constante est un objet avec des données associées (couleur d'affichage)
 * - encapsulation de l'attribut
 * - méthode d’accès (getter)
 */
public enum Couleur {

    // === Valeurs de l'énumération ===
    ROUGE(Color.RED),
    ORANGE(Color.ORANGE),
    BLEU(Color.BLUE),
    VERT(Color.GREEN),
    JAUNE(Color.YELLOW),
    CYAN(Color.CYAN),
    VIOLET(Color.MAGENTA); // MAGENTA utilisé comme substitut du violet

    // === Attribut associé à chaque valeur de l'enum ===
    private final Color couleurPourAffichage;

    /**
     * Constructeur privé (implicite dans enum) associant une couleur AWT à chaque couleur de jeu.
     * @param couleurPourAffichage couleur à utiliser pour l’affichage graphique
     */
    private Couleur(Color couleurPourAffichage) {
        this.couleurPourAffichage = couleurPourAffichage;
    }

    /**
     * Accesseur pour obtenir la couleur associée à la constante.
     * Permet de séparer la logique métier (COULEUR) de la logique graphique (Color).
     * @return la couleur Java AWT pour affichage
     */
    public Color getCouleurPourAfficher() {
        return couleurPourAffichage;
    }
}
