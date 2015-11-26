package application;
 
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Scanner;

import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.ModbusCoupler;

//toolbox
//m.WriteMultipleRegister("192.168.0.25:5555", "0",i,"3");
//m.WriteMultipleCoil("192.168.0.20:5555", "0",b,"3");
//m.WriteCoil("192.168.0.20:5555", "1",false,"3");
//m.ReadCoil("192.168.0.20:5555", "0","2","3");
//m.ReadInputDiscrete("192.168.0.20:5555", "0","4","3");
//m.WriteRegister("192.168.0.20:5555", "1",22,"3");
//m.ReadInputRegister("192.168.0.20:5555", "0","3","3");

/** 
 * 
 * @author falcon
 * 
 * Cette classe met à jour l'écluse et envoie les modifications aux autres apllications
 *
 */

public class Manager {
	MasterModbus m;
	int etape;
	Scanner sc;
	HashMap<String, String> annuaire;
	int numeroBar;
	boolean local;
	
	public Manager(int numeroBar, boolean local) {
	
		m = new MasterModbus();
		
		etape = 0;
		sc = new Scanner(System.in);
		this.numeroBar = numeroBar;
		annuaire = new HashMap<String, String>();
		this.local = local;
		
		// si on travail en local, on doit écouter sur des ports différents, un pour chaque appli
		if(local){
			String adresseIp = "127.0.1.1";
			annuaire.put("ecluse0", adresseIp + ":"+ 5555);
			annuaire.put("ecluse1", adresseIp + ":"+ 5554);
			annuaire.put("ecluse2", adresseIp + ":"+ 5553);
			annuaire.put("ecluse3", adresseIp + ":"+ 5552);
			
		}
		
	}
	public void ajouterAdresse(String adresseIP, int numBar){
		
		String nom = "ecluse" + numBar;	
		int port = 5555 - numBar;
		annuaire.put(nom, adresseIP + ":"+ port);
		
	}
	public int getPort(){
		int port = 5555 -numeroBar;
		return port;
	}
	
	public void action(boolean estSonTour, int posBateau) {
		String nomEcluse;
		// si c'est au tour de l'utilisateur
		if(estSonTour){
		//si l'utilisateur decide de commencer
		System.out.println("voulez-vous y aller ? true or false");
		if(sc.nextBoolean()){
		// ouverture de la barriere
			switch (etape) {
			case 0:
	 
				for(int i= 0; i<4; i++){
				
				nomEcluse = "ecluse" + i;
				m.WriteCoil(annuaire.get(nomEcluse), Integer.toString(numeroBar), true);
				}
				etape++;
				break;
			case 1:
				// avancer bateau
				for(int i= 0; i<4; i++){
				nomEcluse = "ecluse" + i;
				m.WriteRegister(annuaire.get(nomEcluse), "0", posBateau+1);
				}
				etape++;
				break;
			case 2:
				// fermer barriere
				for(int i= 0; i<4; i++){
				nomEcluse = "ecluse" + i;
				m.WriteCoil(annuaire.get(nomEcluse), Integer.toString(numeroBar), false);
				}
				etape++;
				break;
			case 3:
				// on envoit au prochain qu'il peut y aller
				int numeroBar2 = (numeroBar+1)%4;
				nomEcluse = "ecluse" + numeroBar;
				String nomEcluse2 = "ecluse" + numeroBar2;
				m.WriteCoil(annuaire.get(nomEcluse2), "4", true);
				m.WriteCoil(annuaire.get(nomEcluse), "4", false);
				etape = 0;
				break;
			}
		
		}
		}
	}
}
