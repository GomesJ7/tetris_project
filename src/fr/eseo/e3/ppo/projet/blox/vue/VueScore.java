package fr.eseo.e3.ppo.projet.blox.vue;

import javax.swing.*;
import java.awt.*;

public class VueScore extends JPanel {

    private int score = 0;

    public VueScore() {
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(150, 50));
    }

    public void setScore(int score) {
        this.score = score;
        repaint();
    }

    public int getScore() {
        return this.score;
    }

    public void afficher(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));

        String scoreText = "Score : " + score;
        g2d.drawString(scoreText, x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        afficher(g, 10, getHeight() - 10);
    }
}
