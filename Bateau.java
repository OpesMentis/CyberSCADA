public class Bateau {
	private int pos;
	public Bateau() {
		pos = 0; // pos de 0 à 3
	}
	
	public int getPos() {
		return this.pos;
	}
	
	public void avancer() {
		pos = (pos + 1) % 4;
	}
}
