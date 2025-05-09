package fr.eseo.e3.ppo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.ppo.projet.blox.modele.BloxException; // Exception custom
import fr.eseo.e3.ppo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.ppo.projet.blox.modele.Couleur;
import fr.eseo.e3.ppo.projet.blox.modele.Element;

public class OTetromino extends Tetromino {

    public OTetromino(final Coordonnees coordonnees, final Couleur couleur) {  
        super(coordonnees, couleur);  
    }

    @Override
    protected void setElements(final Coordonnees coordonnees, final Couleur couleur) {
        elements[0] = new Element(coordonnees, couleur);  
        elements[1] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()), couleur);  
        elements[2] = new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() + 1), couleur);  
        elements[3] = new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() + 1), couleur);  
    }  

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OTetromino :\n");
        for (Element e : getElements()) {
            sb.append("\t").append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    // 🆕 Ajout de throws BloxException pour correspondre à la signature de la classe parente
    @Override
    public void tourner(boolean sensHoraire) throws BloxException {
        throw new UnsupportedOperationException("L'OTetromino ne peut pas être tourné.");
    }
}
