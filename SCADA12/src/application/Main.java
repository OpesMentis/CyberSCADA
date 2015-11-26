package application;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.util.Scanner;

import net.wimpi.modbus.ModbusCoupler;


/**
 * 
 * @author falcon
 *
 *Cette classe contient le main, lance le programme avec la bonne configuration et déclare les objets principaux
 */
public class Main {
	
	
	public void askIp(Manager manager, Scanner sc, int numBar){
		System.out.println("quelle est l'adresse IP de l'ordinateur gérant la barierre "+ numBar+"?");
		System.out.println("exemple: 198.168.0.25");
		String adresseIp = sc.next();
		manager.ajouterAdresse(adresseIp, numBar);
	}
	
	// Pour lancer le programme 
	// il suffit de le lancer sans paramètre et de suivre les instructions en console
	public static void main (String args[]) {
		
		
		long temp1 = System.currentTimeMillis();
		Ecluse ecluse = new Ecluse();
		Fenetre fenetre = new Fenetre(ecluse);
		Scanner sc = new Scanner(System.in);
		Main main = new Main();
		int numeroBar;
		boolean local;
		
		//Configuration du Manager
		System.out.println("quel numero de barierre vous controlez ? entre 0 et 3.");
		numeroBar = sc.nextInt(); 
		if(numeroBar ==0) ecluse.setSontTour(true);
		else ecluse.setSontTour(false);
		System.out.println("Voulez-vous tournez le programme en local ? (voulez-vous jouer sur un ordi?) true or false");
		local = sc.nextBoolean();
		
		Manager manager = new Manager(numeroBar,local);
		
		if(!local) {
			System.out.println("Nous allons maintenant remplir le tableau des adresses IP");
		for(int i =0; i<ecluse.getNombreBarriere(); i++ )main.askIp(manager, sc, i);
		}
		
		//Demarage du serveur 
				SlaveModbus slave = new SlaveModbus(manager.getPort());
		
		//Boucle Principale
		while(!fenetre.isClosed()){
			
			// on affectue une action sur l'écluse
			//si ce n'est pas au tour de l'appli, on ne fait rien 
			// sinon on demande à l'utilisateur si on peut passer à la prochaine étape
			manager.action(ecluse.estSonTour(), ecluse.getPosBateau());
		
			
			if(System.currentTimeMillis() - temp1> 500){
				ecluse.update();
				fenetre.repaint();
				temp1 = System.currentTimeMillis() ;
			}
			
		}
		
	sc.close();	
	}
}
