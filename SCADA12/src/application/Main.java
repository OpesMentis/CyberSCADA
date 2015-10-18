package application;
import java.awt.EventQueue;
import java.awt.Graphics2D;

import net.wimpi.modbus.ModbusCoupler;



public class Main {
	
	
	
	public static void main (String args[]) {
		long temp1 = System.currentTimeMillis();
		Ecluse ecluse = new Ecluse();
		Fenetre fenetre = new Fenetre(ecluse);
		//int numeroBar =  Integer.parseInt(args[0]);
		int numeroBar =  Integer.parseInt("0");
		Manager manager = new Manager(numeroBar, ecluse);

		//Demarage du serveur 
					SlaveModbus slave = new SlaveModbus(manager.getPort());
	
		//Boucle Principale
		while(!fenetre.isClosed()){
			
			manager.ouverture();
			
			
			if(System.currentTimeMillis() - temp1> 500){
				fenetre.repaint();
				temp1 = System.currentTimeMillis() ;
			}
			
		}
		
		
	}
}
