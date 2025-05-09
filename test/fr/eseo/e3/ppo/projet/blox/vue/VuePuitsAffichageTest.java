package fr.eseo.e3.ppo.projet.blox.vue;

import fr.eseo.e3.ppo.projet.blox.modele.Puits;

import javax.swing.JFrame;

public class VuePuitsAffichageTest {

    public static void main(String[] args) {
        // Création d'un puits avec un tas généré automatiquement
        int largeur = 10;
        int profondeur = 20;
        int nbElements = 50;
        int nbLignes = 5;

        Puits puits = new Puits(largeur, profondeur, nbElements, nbLignes);
        VuePuits vuePuits = new VuePuits(puits, 30); // taille personnalisée
        puits.addPropertyChangeListener(vuePuits);

        // Création de la fenêtre
        JFrame frame = new JFrame("Vue du Puits avec Tas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(vuePuits);
        frame.pack(); // ajuste automatiquement avec getPreferredSize()
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
