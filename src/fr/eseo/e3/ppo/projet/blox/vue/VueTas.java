package fr.eseo.e3.ppo.projet.blox.vue;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.Tas;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Vue dédiée à l'affichage du tas (éléments fixés dans le puits).
 * Elle est utilisée par {@link VuePuits} pour dessiner les blocs déjà tombés.
 */
public class VueTas {

    // Facteur d’assombrissement appliqué aux couleurs du tas
    public static final double MULTIPLIER_NUANCE = 0.25;

    private final VuePuits vuePuits; // Référence à la vue principale du puits

    /**
     * Constructeur : attache cette vue à une instance de VuePuits.
     */
    public VueTas(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
    }

    /**
     * Méthode statique utilitaire pour créer une version assombrie d'une couleur.
     * @param c La couleur d’origine
     * @return La couleur atténuée (plus sombre)
     */
    public static Color nuance(Color c) {
        int r = (int)(c.getRed() * (1 - MULTIPLIER_NUANCE));
        int g = (int)(c.getGreen() * (1 - MULTIPLIER_NUANCE));
        int b = (int)(c.getBlue() * (1 - MULTIPLIER_NUANCE));
        return new Color(r, g, b); // Nouvelle couleur plus sombre
    }

    /**
     * Dessine tous les éléments du tas en appliquant la nuance sombre.
     * @param g2d Contexte graphique
     */
    public void afficher(Graphics2D g2d) {
        Tas tas = vuePuits.getPuits().getTas(); // Récupère les éléments du tas
        int taille = vuePuits.getTaille();      // Taille des cases

        for (Element e : tas.getElements()) {
            Coordonnees coord = e.getCoordonnees();
            int x = coord.getAbscisse() * taille;
            int y = coord.getOrdonnee() * taille;

            g2d.setColor(nuance(e.getCouleur().getCouleurPourAfficher())); // couleur atténuée
            g2d.fill3DRect(x, y, taille, taille, true); // effet 3D
        }
    }

    /**
     * Accesseur vers la vue associée.
     */
    public VuePuits getVuePuits() {
        return vuePuits;
    }
}
