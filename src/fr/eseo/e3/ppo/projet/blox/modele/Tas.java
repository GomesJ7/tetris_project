package fr.eseo.e3.ppo.projet.blox.modele;

import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tas {

    private final Puits puits;
    private final List<Element> elements;

    public Tas(Puits puits) {
        if (puits == null) {
            throw new IllegalArgumentException("Le puits ne peut pas être null.");
        }
        this.puits = puits;
        this.elements = new ArrayList<>();
    }

    public Tas(Puits puits, int nbElements) {
        this(puits, nbElements, nbElements / puits.getLargeur() + 1);
    }

    public Tas(Puits puits, int nbElements, int nbLignes) {
        this(puits, nbElements, nbLignes, new Random());
    }

    public Tas(Puits puits, int nbElements, int nbLignes, Random rand) {
        if (puits == null || rand == null) {
            throw new IllegalArgumentException("Le puits et le générateur aléatoire ne peuvent pas être null.");
        }

        int maxElements = nbLignes * puits.getLargeur();
        if (nbElements > maxElements || nbLignes > puits.getProfondeur()) {
            throw new IllegalArgumentException("Trop d'éléments ou trop de lignes pour le puits.");
        }

        this.puits = puits;
        this.elements = new ArrayList<>();
        construireTas(nbElements, nbLignes, rand);
    }

    public Puits getPuits() {
        return this.puits;
    }

    public List<Element> getElements() {
        return this.elements;
    }

    public boolean elementExiste(int x, int y) {
        for (Element e : elements) {
            if (e.getCoordonnees().getAbscisse() == x && e.getCoordonnees().getOrdonnee() == y) {
                return true;
            }
        }
        return false;
    }

    private void construireTas(int nbElements, int nbLignes, Random rand) {
        int largeur = puits.getLargeur();
        int profondeur = puits.getProfondeur();
        int ligneMin = profondeur - nbLignes;

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

        if (elements.size() < nbElements) {
            throw new IllegalArgumentException("Impossible de placer tous les éléments dans le tas.");
        }
    }

    public void ajouterElements(Piece piece) {
        if (piece != null) {
            for (Element e : piece.getElements()) {
                int y = e.getCoordonnees().getOrdonnee();

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

    // Version modifiée : retourne le nombre de lignes supprimées
    public int supprimerLignesCompletes() {
        int largeur = puits.getLargeur();
        int profondeur = puits.getProfondeur();
        int lignesSupprimees = 0;

        for (int y = profondeur - 1; y >= 0; y--) {
            int compteur = 0;
            for (Element e : elements) {
                if (e.getCoordonnees().getOrdonnee() == y) {
                    compteur++;
                }
            }

            if (compteur == largeur) {
                supprimerLigne(y);
                lignesSupprimees++;
                y++; // Re-tester cette ligne après décalage
            }
        }

        return lignesSupprimees;
    }

    private void supprimerLigne(int y) {
        elements.removeIf(e -> e.getCoordonnees().getOrdonnee() == y);

        for (Element e : elements) {
            int yActuel = e.getCoordonnees().getOrdonnee();
            if (yActuel < y) {
                e.getCoordonnees().setOrdonnee(yActuel + 1);
            }
        }
    }
}
