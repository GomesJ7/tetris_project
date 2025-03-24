package fr.eseo.e3.poo.projet.blox.modele.pieces;

import fr.eseo.e3.ppo.projet.blox.modele.Element;

public interface Piece {

    // Retourne le tableau d'éléments de la pièce
    Element[] getElements();  

    // Positionne la pièce à la position (abscisse, ordonnée) sur le plateau
    void setPosition(int abscisse, int ordonnee);  
}
