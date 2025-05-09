package fr.eseo.e3.ppo.projet.blox.modele;

import fr.eseo.e3.ppo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.ppo.projet.blox.modele.pieces.UsineDePiece;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Puits {

    public static final int LARGEUR_PAR_DEFAUT = 10;
    public static final int PROFONDEUR_PAR_DEFAUT = 15;

    public static final String MODIFICATION_PIECE_ACTUELLE = "pieceActuelle";
    public static final String MODIFICATION_PIECE_SUIVANTE = "pieceSuivante";

    private final int largeur;
    private final int profondeur;
    private final Element[][] grille;

    private Piece pieceActuelle;
    private Piece pieceSuivante;
    private final Queue<Piece> filePiecesSuivantes = new LinkedList<>();

    private final PropertyChangeSupport pcs;
    private Tas tas;

    private boolean modeMultiPiece = false;
    private boolean detectionDefaite = false;
    private boolean controlesClavier = false;

    private boolean jeuTermine = false; // <- ajouté pour gérer la défaite
    
    private Consumer<Integer> scoreConsumer;

    public Puits() {
        this(LARGEUR_PAR_DEFAUT, PROFONDEUR_PAR_DEFAUT);
    }

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

    public Puits(int largeur, int profondeur, int nbElements, int nbLignes) {
        this(largeur, profondeur);
        this.tas = new Tas(this, nbElements, nbLignes);
    }

    public Puits(int largeur, int profondeur, boolean modeMultiPiece, boolean detectionDefaite, boolean controlesClavier) {
        this(largeur, profondeur);
        this.modeMultiPiece = modeMultiPiece;
        this.detectionDefaite = detectionDefaite;
        this.controlesClavier = controlesClavier;
    }

    public int getLargeur() {
        return this.largeur;
    }

    public int getProfondeur() {
        return this.profondeur;
    }

    public Element[][] getGrille() {
        return this.grille;
    }

    public Piece getPieceActuelle() {
        return this.pieceActuelle;
    }

    public Piece getPieceSuivante() {
        return modeMultiPiece ? filePiecesSuivantes.peek() : pieceSuivante;
    }

    public Queue<Piece> getFilePiecesSuivantes() {
        return this.filePiecesSuivantes;
    }

    public Tas getTas() {
        return this.tas;
    }

    public void setTas(Tas tas) {
        this.tas = tas;
    }

    public void setPieceActuelle(Piece piece) {
        Piece anciennePiece = this.pieceActuelle;
        this.pieceActuelle = piece;
        if (piece != null) {
            piece.setPuits(this);
        }
        pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE, anciennePiece, piece);
    }

    public void ajouterPiece(Piece piece) {
        for (Element e : piece.getElements()) {
            Coordonnees coord = e.getCoordonnees();
            if (coord.getOrdonnee() >= 0 && coord.getOrdonnee() < profondeur &&
                coord.getAbscisse() >= 0 && coord.getAbscisse() < largeur) {
                grille[coord.getOrdonnee()][coord.getAbscisse()] = e;
            }
        }
    }

    public boolean estPlein() {
        for (int i = 0; i < largeur; i++) {
            if (grille[0][i] == null)
                return false;
        }
        return true;
    }

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

    public void initialiserFilePieces() {
        UsineDePiece usine = new UsineDePiece();
        while (filePiecesSuivantes.size() < 3) {
            Piece p = usine.genererPiece();
            p.setPuits(this);
            filePiecesSuivantes.add(p);
        }
    }

    public void avancerFilePieces() {
        Piece ancienneActuelle = this.pieceActuelle;
        Piece ancienneSuivante = filePiecesSuivantes.peek();

        this.pieceActuelle = filePiecesSuivantes.poll();
        if (pieceActuelle != null) {
            pieceActuelle.setPuits(this);
            pieceActuelle.setPosition(this.largeur / 2, -4);
        }

        UsineDePiece usine = new UsineDePiece();
        Piece nouvelle = usine.genererPiece();
        nouvelle.setPuits(this);
        filePiecesSuivantes.add(nouvelle);

        pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE, ancienneActuelle, pieceActuelle);
        pcs.firePropertyChange(MODIFICATION_PIECE_SUIVANTE, ancienneSuivante, nouvelle);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
    
    public void setScoreConsumer(Consumer<Integer> consumer) {
        this.scoreConsumer = consumer;
    }

    private void gererCollision() {
        if (pieceActuelle != null) {
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

            int lignesSupprimees = tas.supprimerLignesCompletes(); // 🆕
            if (scoreConsumer != null && lignesSupprimees > 0) {
                scoreConsumer.accept(lignesSupprimees); // 🆕
            }

            if (!estPlein()) {
                if (modeMultiPiece) {
                    avancerFilePieces();
                } else {
                    UsineDePiece usine = new UsineDePiece();
                    Piece nouvellePiece = usine.genererPiece();
                    setPieceSuivante(nouvellePiece);
                }
            } else {
                setPieceSuivante(null);
            }
        }
    }


    public void gravite() {
        if (jeuTermine || pieceActuelle == null) return;
        try {
            pieceActuelle.deplacerDe(0, 1);
        } catch (BloxException e) {
            if (e.getType() == BloxException.BLOX_COLLISION || e.getType() == BloxException.BLOX_SORTIE_PUITS) {
                gererCollision();
                tas.supprimerLignesCompletes();
            }
        }
    }

    public boolean isModeMultiPiece() {
        return modeMultiPiece;
    }

    public boolean isDetectionDefaite() {
        return detectionDefaite;
    }

    public boolean isControlesClavier() {
        return controlesClavier;
    }

    public boolean isJeuTermine() {
        return jeuTermine;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Puits : Dimension ").append(largeur).append(" x ").append(profondeur).append("\n");

        sb.append("Piece Actuelle : ");
        if (pieceActuelle != null)
            sb.append(pieceActuelle.toString());
        else
            sb.append("<aucune>");
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
