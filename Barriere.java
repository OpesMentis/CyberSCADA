public class Barriere {
	private boolean etat; // true = ouverte, false = fermee
	private int ref;
	
	public Barriere(int num) {
		ref = num;
		etat = false; 
	}
	
	public void fermer() {
		etat = false;
	}
	
	public void ouvrir() {
		etat = true;
	}
}
