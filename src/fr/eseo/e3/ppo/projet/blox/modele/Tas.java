package fr.eseo.e3.ppo.projet.blox.modele;

import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe représentant le tas, c’est-à-dire l’ensemble des éléments déjà
 * tombés et immobilisés dans le puits.
 * Le tas est responsable :
 * - de contenir les éléments fixés
 * - de générer des éléments pour des tests (via constructeurs)
 * - de détecter/supprimer les lignes complètes
 * - de détecter les conditions de défaite (optionnelle)
 */
public class Tas {

    // Référence au puits associé
    private final Puits puits;

    // Liste des éléments déjà tombés dans le puits
    private final List<Element> elements;

    // === Constructeurs ===

    /**
     * Constructeur standard, crée un tas vide associé à un puits.
     */
    public Tas(Puits puits) {
        if (puits == null)
            throw new IllegalArgumentException("Le puits ne peut pas être null.");
        this.puits = puits;
        this.elements = new ArrayList<>();
    }

    /**
     * Génère un tas rempli aléatoirement d’éléments pour des tests ou démos.
     * Le nombre de lignes est estimé automatiquement.
     */
    public Tas(Puits puits, int nbElements) {
        this(puits, nbElements, nbElements / puits.getLargeur() + 1);
    }

    /**
     * Idem ci-dessus mais avec un nombre de lignes précisé.
     */
    public Tas(Puits puits, int nbElements, int nbLignes) {
        this(puits, nbElements, nbLignes, new Random());
    }

    /**
     * Génère un tas aléatoire avec coordonnées et couleurs variées.
     * Sert uniquement à pré-remplir un puits pour tester certaines conditions.
     */
    public Tas(Puits puits, int nbElements, int nbLignes, Random rand) {
        if (puits == null || rand == null)
            throw new IllegalArgumentException("Le puits et le générateur aléatoire ne peuvent pas être null.");

        int maxElements = nbLignes * puits.getLargeur();
        if (nbElements > maxElements || nbLignes > puits.getProfondeur())
            throw new IllegalArgumentException("Trop d'éléments ou trop de lignes pour le puits.");

        this.puits = puits;
        this.elements = new ArrayList<>();
        construireTas(nbElements, nbLignes, rand);
    }

    // === Accesseurs ===

    public Puits getPuits() {
        return this.puits;
    }

    public List<Element> getElements() {
        return this.elements;
    }

    /**
     * Vérifie si un élément existe à une coordonnée précise.
     */
    public boolean elementExiste(int x, int y) {
        for (Element e : elements) {
            if (e.getCoordonnees().getAbscisse() == x && e.getCoordonnees().getOrdonnee() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Génère aléatoirement un nombre d’éléments dans la zone inférieure du puits.
     */
    private void construireTas(int nbElements, int nbLignes, Random rand) {
        int largeur = puits.getLargeur();
        int profondeur = puits.getProfondeur();
        int ligneMin = profondeur - nbLignes; // Lignes inférieures seulement

        int tries = 0;
        while (elements.size() < nbElements && tries < nbElements * 10) {
            int x = rand.nextInt(largeur);
            int y = rand.nextInt(nbLignes) + ligneMin;

            if (!elementExiste(x, y)) {
                Coordonnees coord = new Coordonnees(x, y);
                Couleur couleur = Couleur.values()[rand.nextInt(Couleur.values().length)];
                elements.add(new Element(coord, couleur));
            }
            tries++;
        }

        if (elements.size() < nbElements)
            throw new IllegalArgumentException("Impossible de placer tous les éléments dans le tas.");
    }

    /**
     * Ajoute les éléments d'une pièce tombée dans le tas.
     * Gère également la condition de défaite (si activée).
     */
    public void ajouterElements(Piece piece) {
        if (piece != null) {
            for (Element e : piece.getElements()) {
                int y = e.getCoordonnees().getOrdonnee();

                // Si activée : condition de fin de jeu quand un élément dépasse le haut
                if (puits.isDetectionDefaite() && y < 0) {
                    throw new RuntimeException("Défaite : un élément dépasse le haut du puits !");
                }

                this.elements.add(e);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tas :\n");
        for (Element e : elements) {
            sb.append("  ").append(e).append("\n");
        }
        return sb.toString();
    }

    /**
     * Supprime toutes les lignes complètes du tas.
     * @return le nombre de lignes supprimées (utile pour le score)
     */
    public int supprimerLignesCompletes() {
        int largeur = puits.getLargeur();
        int profondeur = puits.getProfondeur();
        int lignesSupprimees = 0;

        // On part du bas du puits et on remonte
        for (int y = profondeur - 1; y >= 0; y--) {
            int compteur = 0;
            for (Element e : elements) {
                if (e.getCoordonnees().getOrdonnee() == y) {
                    compteur++;
                }
            }

            // Ligne complète détectée
            if (compteur == largeur) {
                supprimerLigne(y);
                lignesSupprimees++;
                y++; // Ligne descendue → on re-teste
            }
        }

        return lignesSupprimees;
    }

    /**
     * Supprime tous les éléments d’une ligne, puis décale ceux du dessus.
     */
    private void supprimerLigne(int y) {
        // Supprime tous les éléments situés sur la ligne y
        elements.removeIf(e -> e.getCoordonnees().getOrdonnee() == y);

        // Décale vers le bas les éléments situés au-dessus de y
        for (Element e : elements) {
            int yActuel = e.getCoordonnees().getOrdonnee();
            if (yActuel < y) {
                e.getCoordonnees().setOrdonnee(yActuel + 1);
            }
        }
    }
}
