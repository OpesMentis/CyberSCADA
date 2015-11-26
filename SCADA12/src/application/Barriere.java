package application;
import java.awt.Graphics2D;

/**
 * 
 * @author falcon
 *
 *Représentation de la Barriere
 */

public class Barriere {
	private boolean ouverte; // true = ouverte, false = fermee
	private boolean type; // true = vertical; false = horyzontal
	private VisuBarriere visu;
	int posX;
	int posY;
	public Barriere(String type, int posX, int posY) {
		ouverte = false; 
		if(type.equals("verticale")) this.type =true;
		else this.type = false;
		visu = new VisuBarriere(this.type);
		this.posX =posX;
		this.posY = posY;
	}
	
	public void fermer() {
		ouverte = false;
	}
	
	public void ouvrir() {
		ouverte = true;
	}
	
	public void affiche(Graphics2D g){
		visu.affiche(g, posX, posY, ouverte);
		
	}
	public boolean estFermer(){
		
		return !ouverte;
	}
}
