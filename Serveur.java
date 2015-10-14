import java.util.Vector;

public class Serveur {
	private Bateau bat;
	private Vector<Barriere> bars;
	public Serveur(Bateau titanic, Barriere bar1, Barriere bar2, Barriere bar3, Barriere bar4) {
		bat = titanic;
		bars.add(bar1);
		bars.add(bar2);
		bars.add(bar3);
		bars.add(bar4);
	}
	
	public void start() {
		
	}
}
