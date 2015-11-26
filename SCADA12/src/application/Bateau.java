package application;
import java.awt.Graphics2D;
import java.util.Vector;

/**
 * 
 * @author falcon
 *
 *Représentation du Bateau
 */
public class Bateau {
	private int pos;
	private VisuBateau visu;
	public Bateau() {
		pos = 0; // pos de 0 � 3
		visu = new VisuBateau();
	}
	
	public int getPos() {
		return this.pos;
	}
	
	public void avancer() {
		pos = (pos + 1) % 4;
	}
	public void avancer(int posArg) {
		pos = posArg;
		pos = pos % 4;

	}
	
	public void affiche(Graphics2D g){
		Vector<Integer> posReel = posToPixel(pos);
		visu.affiche(g, posReel.get(0), posReel.get(1));	
	}
	private Vector<Integer> posToPixel(int pos){
		Vector<Integer> posReel = new Vector<Integer>();
		switch (pos){
		case 0:
			posReel.add(200);
			posReel.add(200);
			break;
		case 1:
			posReel.add(600);
			posReel.add(200);
			break;
		case 2:
			posReel.add(450);
			posReel.add(430);
			break;
		case 3:
			posReel.add(200);
			posReel.add(430);
			break;
		}
		return posReel;
	}
}
