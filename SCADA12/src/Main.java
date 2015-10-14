public class Main {
	public static void main (String args[]) {
		Bateau bat = new Bateau();
		Barriere bar1 = new Barriere(1);
		Barriere bar2 = new Barriere(2);
		Barriere bar3 = new Barriere(3);
		Barriere bar4 = new Barriere(4);
		
		Serveur serv = new Serveur(bat, bar1, bar2, bar3, bar4);
		
		
	}
}
