package fr.eseo.e3.ppo.projet.blox.vue;

import fr.eseo.e3.ppo.projet.blox.modele.Puits;

import javax.swing.JPanel;

/**
 * Classe VuePuits pour représenter graphiquement le puits dans l’interface utilisateur.
 */
public class VuePuits extends JPanel {

    // Taille par défaut des éléments en pixels
    public static final int TAILLE_PAR_DEFAUT = 15;

    // Le modèle de données du puits
    private Puits puits;

    // Taille en pixels d’un élément (largeur/hauteur d’un carré)
    private int taille;

    /**
     * Constructeur avec taille par défaut.
     * @param puits le modèle du puits à afficher
     */
    public VuePuits(Puits puits) {
        this(puits, TAILLE_PAR_DEFAUT);
    }

    /**
     * Constructeur avec taille personnalisée.
     * @param puits le modèle du puits
     * @param taille la taille en pixels des éléments affichés
     */
    public VuePuits(Puits puits, int taille) {
        this.puits = puits;
        this.taille = taille;
    }

    /**
     * Accesseur pour le puits.
     * @return le puits associé
     */
    public Puits getPuits() {
        return this.puits;
    }

    /**
     * Modificateur du puits.
     * @param puits le nouveau puits à associer
     */
    public void setPuits(Puits puits) {
        this.puits = puits;
        repaint(); // Redessiner le panneau quand on change de puits
    }

    /**
     * Accesseur pour la taille des éléments.
     * @return la taille en pixels
     */
    public int getTaille() {
        return this.taille;
    }

    /**
     * Modificateur de la taille des éléments.
     * @param taille la nouvelle taille en pixels
     */
    public void setTaille(int taille) {
        this.taille = taille;
        repaint(); // Redessiner le panneau avec la nouvelle taille
    }
}
