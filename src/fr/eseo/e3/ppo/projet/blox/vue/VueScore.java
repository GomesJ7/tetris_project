package fr.eseo.e3.ppo.projet.blox.vue;

import javax.swing.*;
import java.awt.*;

/**
 * Panneau Swing dédié à l'affichage du score.
 * Peut être intégré n’importe où dans une interface graphique.
 */
public class VueScore extends JPanel {

    private int score = 0; // Score à afficher

    /**
     * Constructeur par défaut.
     * Définit les dimensions et rend le fond transparent.
     */
    public VueScore() {
        this.setOpaque(false); // Transparent pour laisser voir le fond
        this.setPreferredSize(new Dimension(150, 50)); // Taille suggérée
    }

    /**
     * Définit une nouvelle valeur de score et redessine le panneau.
     */
    public void setScore(int score) {
        this.score = score;
        repaint(); // Redessine le panneau avec la nouvelle valeur
    }

    /**
     * Retourne la valeur actuelle du score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Affiche le score à une position personnalisée dans le Graphics donné.
     * Utile pour une intégration manuelle.
     *
     * @param g Contexte graphique
     * @param x Position X
     * @param y Position Y
     */
    public void afficher(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));

        String scoreText = "Score : " + score;
        g2d.drawString(scoreText, x, y);
    }

    /**
     * Redéfinit le rendu automatique du composant.
     * Affiche le score dans le coin bas gauche du panneau.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        afficher(g, 10, getHeight() - 10);
    }
}
