package fr.eseo.e3.ppo.projet.blox.modele;

/**
 * Enumération représentant les différents modes de génération des pièces dans le jeu.
 *
 * Utiliser une énumération permet de :
 * - limiter les valeurs possibles à un ensemble **prédéterminé** et **sécurisé**
 * - éviter les erreurs de saisie (par exemple, pas de "Random" mal orthographié)
 * - rendre le code plus lisible et **auto-documenté**
 */
public enum Mode {
    ALEATOIRE,      // Génération aléatoire complète
    DETERMINISTE,   // Génération prédictible (utile pour les tests)
    CYCLIC          // Génération répétée dans l’ordre fixe (cycle)
}
