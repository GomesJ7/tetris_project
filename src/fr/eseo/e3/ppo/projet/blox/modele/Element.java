package fr.eseo.e3.ppo.projet.blox.modele;

public class Element {
//Attribue pour faire le type avec les class
	private Coordonnees coordonnees;
	private Couleur couleur;
	
//Constructeurs pour Element
	public Element(Coordonnees coordonnees) {
		//this.coordonnees = new Coordonnees();
		//this.couleur = Couleur.values();
	}
	public Element(int x, int y) {
		this.coordonnees = new Coordonnees(x,y);
		
	}
	public Element(Coordonnees coordonnees, Couleur couleur) {
		this.coordonnees = coordonnees;
		this.couleur = couleur;
		
	}
	public Element(int x, int y, Couleur couleur) {
		this.coordonnees = new Coordonnees(x, y);
		this.couleur = couleur;
	}

//Getters et Setters	
	public Coordonnees getCoordonnees() {
		return this.coordonnees;
	}
	
	public void setCoordonnees(Coordonnees coordonnees) {
	    this.coordonnees = coordonnees;
	}

	public Couleur getCouleur() {
	    return this.couleur;
	}
	
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
	
	@Override
    public String toString() {
        return "( Les coordonnees de l'élément sont : " + coordonnees + ", de couleur " + couleur + " )";
    }
	
	@Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (coordonnees == null ? 0 : coordonnees.hashCode());
        result = 31 * result + (couleur == null ? 0 : couleur.hashCode());
        return result;
    }
}
