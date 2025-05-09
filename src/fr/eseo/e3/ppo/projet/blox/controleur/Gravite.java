package fr.eseo.e3.ppo.projet.blox.controleur;

import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuitsV4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gravite implements ActionListener {

    public static final int DELAI_PAR_DEFAUT = 1000;

    private final Puits puits;
    private final Timer timer;
    private final JComponent composantVue;

    // Compatible VuePuits (V1 à V3)
    public Gravite(VuePuits vuePuits, Puits puits, int delai) {
        this.composantVue = vuePuits;
        this.puits = puits;
        this.timer = new Timer(delai, this);
        this.timer.start();
    }

    // Compatible VuePuitsV4
    public Gravite(VuePuitsV4 vuePuitsV4, Puits puits, int delai) {
        this.composantVue = vuePuitsV4;
        this.puits = puits;
        this.timer = new Timer(delai, this);
        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!puits.isJeuTermine()) {
            puits.gravite();
            composantVue.repaint();
        }
    }

    public Timer getTimer() {
        return timer;
    }

    public Puits getPuits() {
        return puits;
    }
}
