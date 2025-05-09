package fr.eseo.e3.ppo.projet.blox.controleur;

import fr.eseo.e3.ppo.projet.blox.modele.Puits;
import fr.eseo.e3.ppo.projet.blox.vue.VuePuits;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gravite implements ActionListener {

    public static final int DELAI_PAR_DEFAUT = 1000;

    private final VuePuits vuePuits;
    private final Puits puits;
    private final Timer timer;

    private boolean popupAffichee = false; // pour éviter d’afficher plusieurs fois

    public Gravite(VuePuits vuePuits, Puits puits, int delai) {
        this.vuePuits = vuePuits;
        this.puits = puits;
        this.timer = new Timer(delai, this);
        this.timer.start();
    }

    public Gravite(VuePuits vuePuits) {
        this(vuePuits, vuePuits.getPuits(), DELAI_PAR_DEFAUT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (puits.isJeuTermine()) {
            timer.stop();
            return;
        }

        puits.gravite();
        vuePuits.repaint();
    }


    public Timer getTimer() {
        return timer;
    }

    public VuePuits getVuePuits() {
        return vuePuits;
    }

    public Puits getPuits() {
        return puits;
    }
}
