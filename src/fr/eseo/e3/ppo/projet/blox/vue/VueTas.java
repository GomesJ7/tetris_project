package fr.eseo.e3.ppo.projet.blox.vue;

import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Element;
import fr.eseo.e3.ppo.projet.blox.modele.Tas;

import java.awt.Color;
import java.awt.Graphics2D;

public class VueTas {

    public static final double MULTIPLIER_NUANCE = 0.25; // 💡 Libre mais < 1.0
    private final VuePuits vuePuits;

    public VueTas(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
    }

    /**
     * Méthode de classe pour assombrir une couleur avec le facteur MULTIPLIER_NUANCE
     */
    public static Color nuance(Color c) {
        int r = (int)(c.getRed() * (1 - MULTIPLIER_NUANCE));
        int g = (int)(c.getGreen() * (1 - MULTIPLIER_NUANCE));
        int b = (int)(c.getBlue() * (1 - MULTIPLIER_NUANCE));
        return new Color(r, g, b);
    }

    /**
     * Affiche le tas en utilisant une couleur adoucie et un effet 3D.
     */
    public void afficher(Graphics2D g2d) {
        Tas tas = vuePuits.getPuits().getTas();
        int taille = vuePuits.getTaille();

        for (Element e : tas.getElements()) {
            Coordonnees coord = e.getCoordonnees();
            int x = coord.getAbscisse() * taille;
            int y = coord.getOrdonnee() * taille;

            g2d.setColor(nuance(e.getCouleur().getCouleurPourAfficher()));
            g2d.fill3DRect(x, y, taille, taille, true);
        }
    }

    public VuePuits getVuePuits() {
        return vuePuits;
    }
}
