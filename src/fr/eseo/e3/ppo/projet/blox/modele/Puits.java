package fr.eseo.e3.ppo.projet.blox.modele;

import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.UsineDePiece;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Classe représentant le puits de jeu dans FallingBlox.
 * Le puits contient une grille, une pièce actuelle, une pièce suivante (ou une file de pièces si multi-pièces),
 * ainsi qu’un tas représentant les pièces déjà tombées.
 * Il est le cœur de la logique du jeu : gravité, détection de collision, suppression de lignes, etc.
 */
public class Puits {

    // === Constantes utilisées pour la configuration et les notifications ===
    public static final int LARGEUR_PAR_DEFAUT = 10;
    public static final int PROFONDEUR_PAR_DEFAUT = 15;
    public static final String MODIFICATION_PIECE_ACTUELLE = "pieceActuelle";
    public static final String MODIFICATION_PIECE_SUIVANTE = "pieceSuivante";

    // === Données principales du puits ===
    private final int largeur; // Largeur en nombre de cases
    private final int profondeur; // Hauteur en nombre de cases
    private final Element[][] grille; // Grille représentant les éléments fixes (le tas)

    // === État courant du jeu ===
    private Piece pieceActuelle; // La pièce actuellement en chute
    private Piece pieceSuivante; // La prochaine pièce (mode simple)
    private final Queue<Piece> filePiecesSuivantes = new LinkedList<>(); // File de pièces (mode multi)

    // Gestion des événements pour notifier les vues de changements (MVC)
    private final PropertyChangeSupport pcs;

    // Tas d'éléments déjà tombés
    private Tas tas;

    // Options de jeu activables dynamiquement
    private boolean modeMultiPiece = false; // Mode avec plusieurs pièces en file
    private boolean detectionDefaite = false; // Active la détection de fin de partie
    private boolean controlesClavier = false; // Active les contrôles par le clavier

    // État de fin de jeu
    private boolean jeuTermine = false;

    // Callback pour transmettre les lignes supprimées (score)
    private Consumer<Integer> scoreConsumer;

    // === Constructeurs ===

    /** Constructeur avec dimensions par défaut */
    public Puits() {
        this(LARGEUR_PAR_DEFAUT, PROFONDEUR_PAR_DEFAUT);
    }

    /** Constructeur avec largeur et profondeur personnalisées */
    public Puits(int largeur, int profondeur) {
        if (largeur < 5 || largeur > 15)
            throw new IllegalArgumentException("La largeur doit être comprise entre 5 et 15.");
        if (profondeur < 15 || profondeur > 25)
            throw new IllegalArgumentException("La profondeur doit être comprise entre 15 et 25.");

        this.largeur = largeur;
        this.profondeur = profondeur;
        this.grille = new Element[profondeur][largeur];
        this.pcs = new PropertyChangeSupport(this);
        this.tas = new Tas(this);
    }

    /** Constructeur avec pré-remplissage du tas pour test ou configuration initiale */
    public Puits(int largeur, int profondeur, int nbElements, int nbLignes) {
        this(largeur, profondeur);
        this.tas = new Tas(this, nbElements, nbLignes);
    }

    /** Constructeur avec options de jeu activables */
    public Puits(int largeur, int profondeur, boolean modeMultiPiece, boolean detectionDefaite, boolean controlesClavier) {
        this(largeur, profondeur);
        this.modeMultiPiece = modeMultiPiece;
        this.detectionDefaite = detectionDefaite;
        this.controlesClavier = controlesClavier;
    }

    // === Accesseurs de base ===

    public int getLargeur() { return this.largeur; }
    public int getProfondeur() { return this.profondeur; }
    public Element[][] getGrille() { return this.grille; }
    public Piece getPieceActuelle() { return this.pieceActuelle; }
    public Piece getPieceSuivante() {
        return modeMultiPiece ? filePiecesSuivantes.peek() : pieceSuivante;
    }
    public Queue<Piece> getFilePiecesSuivantes() { return this.filePiecesSuivantes; }
    public Tas getTas() { return this.tas; }
    public void setTas(Tas tas) { this.tas = tas; }

    public boolean isModeMultiPiece() { return modeMultiPiece; }
    public boolean isDetectionDefaite() { return detectionDefaite; }
    public boolean isControlesClavier() { return controlesClavier; }
    public boolean isJeuTermine() { return jeuTermine; }

    // === Gestion des observateurs (vue MVC) ===

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void setScoreConsumer(Consumer<Integer> consumer) {
        this.scoreConsumer = consumer;
    }

    // === Gestion des pièces ===

    /** Définit la pièce active actuelle et notifie la vue */
    public void setPieceActuelle(Piece piece) {
        Piece anciennePiece = this.pieceActuelle;
        this.pieceActuelle = piece;
        if (piece != null) {
            piece.setPuits(this);
        }
        pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE, anciennePiece, piece);
    }

    /** Définit la pièce suivante (mode classique) et fait avancer la pièce actuelle */
    public void setPieceSuivante(Piece piece) {
        if (!modeMultiPiece) {
            Piece ancienneActuelle = this.pieceActuelle;
            Piece ancienneSuivante = this.pieceSuivante;

            if (this.pieceSuivante != null) {
                this.pieceActuelle = this.pieceSuivante;
                this.pieceActuelle.setPuits(this);
                this.pieceActuelle.setPosition(this.largeur / 2, -4);
                pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE, ancienneActuelle, this.pieceActuelle);
            }

            this.pieceSuivante = piece;
            piece.setPuits(this);
            pcs.firePropertyChange(MODIFICATION_PIECE_SUIVANTE, ancienneSuivante, this.pieceSuivante);
        }
    }

    /** Initialise une file de 3 pièces à l'avance (mode multi-pièce) */
    public void initialiserFilePieces() {
        UsineDePiece usine = new UsineDePiece();
        while (filePiecesSuivantes.size() < 3) {
            Piece p = usine.genererPiece();
            p.setPuits(this);
            filePiecesSuivantes.add(p);
        }
    }

    /** Avance la file de pièces : actualise la pièce en jeu et en génère une nouvelle */
    public void avancerFilePieces() {
        Piece ancienneActuelle = this.pieceActuelle;
        Piece ancienneSuivante = filePiecesSuivantes.peek();

        this.pieceActuelle = filePiecesSuivantes.poll();
        if (pieceActuelle != null) {
            pieceActuelle.setPuits(this);
            pieceActuelle.setPosition(this.largeur / 2, -4);
        }

        Piece nouvelle = new UsineDePiece().genererPiece();
        nouvelle.setPuits(this);
        filePiecesSuivantes.add(nouvelle);

        pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE, ancienneActuelle, pieceActuelle);
        pcs.firePropertyChange(MODIFICATION_PIECE_SUIVANTE, ancienneSuivante, nouvelle);
    }

    // === Logique métier principale ===

    /** Ajoute les éléments d'une pièce dans la grille (après chute) */
    public void ajouterPiece(Piece piece) {
        for (Element e : piece.getElements()) {
            Coordonnees coord = e.getCoordonnees();
            if (coord.getOrdonnee() >= 0 && coord.getOrdonnee() < profondeur &&
                coord.getAbscisse() >= 0 && coord.getAbscisse() < largeur) {
                grille[coord.getOrdonnee()][coord.getAbscisse()] = e;
            }
        }
    }

    /** Vérifie si la ligne supérieure est occupée → partie perdue */
    public boolean estPlein() {
        for (int i = 0; i < largeur; i++) {
            if (grille[0][i] == null)
                return false;
        }
        return true;
    }

    /** Applique la gravité sur la pièce actuelle (descend d’un cran ou provoque collision) */
    public void gravite() {
        if (jeuTermine || pieceActuelle == null) return;
        try {
            pieceActuelle.deplacerDe(0, 1);
        } catch (BloxException e) {
            if (e.getType() == BloxException.BLOX_COLLISION || e.getType() == BloxException.BLOX_SORTIE_PUITS) {
                gererCollision();
            }
        }
    }

    /** Gère la fin de chute d'une pièce : ajout au tas, suppression de lignes, etc. */
    private void gererCollision() {
        try {
            tas.ajouterElements(pieceActuelle);
        } catch (RuntimeException e) {
            if (detectionDefaite) {
                jeuTermine = true;
                System.err.println("Défaite détectée : " + e.getMessage());
                return;
            } else {
                throw e;
            }
        }

        pieceActuelle = null;

        int lignesSupprimees = tas.supprimerLignesCompletes();
        if (scoreConsumer != null && lignesSupprimees > 0) {
            scoreConsumer.accept(lignesSupprimees);
        }

        if (!estPlein()) {
            if (modeMultiPiece) {
                avancerFilePieces();
            } else {
                setPieceSuivante(new UsineDePiece().genererPiece());
            }
        } else {
            setPieceSuivante(null);
        }
    }

    // === Affichage du puits (console/debug) ===

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Puits : Dimension ").append(largeur).append(" x ").append(profondeur).append("\n");

        sb.append("Piece Actuelle : ");
        sb.append(pieceActuelle != null ? pieceActuelle.toString() : "<aucune>");
        sb.append("\n");

        sb.append("Piece Suivante(s) : ");
        if (modeMultiPiece) {
            if (!filePiecesSuivantes.isEmpty()) {
                for (Piece p : filePiecesSuivantes) {
                    sb.append(p.toString()).append(" ");
                }
            } else {
                sb.append("<aucune>");
            }
        } else {
            sb.append(pieceSuivante != null ? pieceSuivante.toString() : "<aucune>");
        }

        return sb.toString();
    }
}
