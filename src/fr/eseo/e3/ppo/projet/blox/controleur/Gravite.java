package fr.eseo.e3.ppo.projet.blox.controleur;

import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuitsV4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur de gravité automatique.
 * Utilise un Timer Swing pour faire descendre la pièce à intervalle régulier.
 */
public class Gravite implements ActionListener {

    public static final int DELAI_PAR_DEFAUT = 1000; // Délai par défaut (1 seconde)

    private final Puits puits;            // Le modèle à manipuler
    private final Timer timer;            // Timer Swing pour appels réguliers
    private final JComponent composantVue; // Vue à redessiner (VuePuits ou VuePuitsV4)

    /**
     * Constructeur compatible avec les versions 1 à 3 de VuePuits.
     * @param vuePuits La vue à redessiner
     * @param puits Le modèle (Puits) sur lequel appliquer la gravité
     * @param delai Intervalle de temps entre chaque "tick" de gravité
     */
    public Gravite(VuePuits vuePuits, Puits puits, int delai) {
        this.composantVue = vuePuits;
        this.puits = puits;
        this.timer = new Timer(delai, this); // Lien avec actionPerformed
        this.timer.start(); // Démarre la gravité automatique
    }

    /**
     * Constructeur alternatif pour VuePuitsV4.
     * (nécessaire car Swing ne reconnaît pas l’héritage implicite de JComponent sans cast explicite)
     */
    public Gravite(VuePuitsV4 vuePuitsV4, Puits puits, int delai) {
        this.composantVue = vuePuitsV4;
        this.puits = puits;
        this.timer = new Timer(delai, this);
        this.timer.start();
    }

    /**
     * Appelé automatiquement à chaque tick du timer.
     * Fait descendre la pièce d’une ligne et redessine la vue si le jeu est en cours.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!puits.isJeuTermine()) {
            puits.gravite(); // Appelle la méthode de gravité (déplace ou bloque la pièce)
            composantVue.repaint(); // Redessine la grille mise à jour
        }
    }

    // === Getters utiles ===

    public Timer getTimer() {
        return timer;
    }

    public Puits getPuits() {
        return puits;
    }
}
