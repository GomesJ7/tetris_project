package fr.eseo.e3.ppo.projet.blox.vue;

import fr.eseo.e3.ppo.projet.blox.controleur.ControleClavier;
import fr.eseo.e3.ppo.projet.blox.controleur.PieceDeplacement;
import fr.eseo.e3.ppo.projet.blox.controleur.PieceRotation;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

public class VuePuitsV4 extends VuePuits {

    private JButton rejouerBtn;
    private JButton menuBtn;
    private JLabel scoreLabel;
    private JLabel scorePermanentLabel;
    private boolean composantsAjoutes = false;
    private int score = 0;

    public VuePuitsV4(Puits puits) {
        this(puits, TAILLE_PAR_DEFAUT);
    }

    public VuePuitsV4(Puits puits, int taille) {
        super(puits, taille);
        setLayout(null);

        scorePermanentLabel = new JLabel("Score : 0", SwingConstants.LEFT);
        scorePermanentLabel.setForeground(Color.WHITE);
        scorePermanentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        scorePermanentLabel.setBounds(puits.getLargeur() * taille + 25, getHeight() - 40, 150, 25);
        this.add(scorePermanentLabel);

        // Lien entre VuePuitsV4 et le Puits pour permettre mise à jour du score
        puits.setScoreConsumer(this::ajouterScore);
    }

    public void ajouterScore(int lignesSupprimees) {
        int points = switch (lignesSupprimees) {
            case 1 -> 100;
            case 2 -> 300;
            case 3 -> 500;
            case 4 -> 800;
            default -> 0;
        };
        score += points;
        if (scorePermanentLabel != null) {
            scorePermanentLabel.setText("Score : " + score);
        }
        if (scoreLabel != null) {
            scoreLabel.setText("Score : " + score);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (scorePermanentLabel != null) {
            scorePermanentLabel.setBounds(getPuits().getLargeur() * getTaille() + 25, getHeight() - 40, 150, 25);
        }

        if (getPuits().isDetectionDefaite() && getPuits().isJeuTermine()) {
            Graphics2D g2d = (Graphics2D) g;
            String msg = "PERDU";
            g2d.setFont(new Font("Arial", Font.BOLD, 48));
            int textWidth = g2d.getFontMetrics().stringWidth(msg);
            int textHeight = g2d.getFontMetrics().getHeight();

            int bandeauY = getHeight() / 2 - textHeight;
            int bandeauHeight = textHeight + 120;
            g2d.setColor(new Color(100, 100, 100));
            g2d.fillRect(0, bandeauY, getWidth(), bandeauHeight);

            int textX = (getWidth() - textWidth) / 2;
            int textY = getHeight() / 2;
            g2d.setColor(Color.WHITE);
            g2d.drawString(msg, textX, textY);

            if (!composantsAjoutes) {
                ajouterComposantsFinDeJeu();
                composantsAjoutes = true;
            }
        }
    }

    private void ajouterComposantsFinDeJeu() {
        rejouerBtn = new JButton("Rejouer");
        menuBtn = new JButton("Menu Principal");
        scoreLabel = new JLabel("Score : " + score, SwingConstants.CENTER);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));

        int centerX = getWidth() / 2;
        int boutonY = getHeight() / 2 + 40;
        int boutonLargeur = 120;
        int boutonHauteur = 30;

        scoreLabel.setBounds(centerX - 60, boutonY - 40, 120, 30);
        rejouerBtn.setBounds(centerX - 130, boutonY, boutonLargeur, boutonHauteur);
        menuBtn.setBounds(centerX + 10, boutonY, boutonLargeur, boutonHauteur);

        this.add(scoreLabel);
        this.add(rejouerBtn);
        this.add(menuBtn);

        rejouerBtn.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(VuePuitsV4.this).dispose();
            fr.eseo.e3.ppo.projet.blox.FallingBloxVersion4.main(new String[]{});
        });

        menuBtn.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(VuePuitsV4.this).dispose();
            fr.eseo.e3.ppo.projet.blox.FallingBloxLauncher.main(new String[]{});
        });

        this.revalidate();
        this.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }
} 
