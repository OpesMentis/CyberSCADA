package application;

import java.util.HashMap;
import java.util.Scanner;

//toolbox
//m.WriteMultipleRegister("192.168.0.25:5555", "0",i,"3");
//m.WriteMultipleCoil("192.168.0.20:5555", "0",b,"3");
//m.WriteCoil("192.168.0.20:5555", "1",false,"3");
//m.ReadCoil("192.168.0.20:5555", "0","2","3");
//m.ReadInputDiscrete("192.168.0.20:5555", "0","4","3");
//m.WriteRegister("192.168.0.20:5555", "1",22,"3");
//m.ReadInputRegister("192.168.0.20:5555", "0","3","3");

public class Manager {
	MasterModbus m;
	int etape;
	Scanner sc;
	int numeroBar;
	Ecluse ecluse;
	HashMap<String, String> annuaire;
	public Manager(int numeroBar, Ecluse ecluse) {
		this.ecluse = ecluse;
		m = new MasterModbus();
		etape = 0;
		sc = new Scanner(System.in);
		this.numeroBar = numeroBar;
		if(numeroBar ==0) ecluse.setSontTour(true);
		else ecluse.setSontTour(false);
		annuaire = new HashMap<String, String>();
		annuaire.put("ecluse0", "192.168.0.25:5555");
		annuaire.put("ecluse1", "192.168.0.25:5554");
		annuaire.put("ecluse2", "192.168.0.25:5553");
		annuaire.put("ecluse3", "192.168.0.25:5552");
		
		
	}
	public int getPort(){
		int port = 5555 - numeroBar;
		return port;
	}
	public void ouverture() {
		String numEcluse;
		// si c'est au tour de l'utilisateur
		if(ecluse.estSonTour()){
		//si l'utilisateur valide
		System.out.println("voulez-vous y aller ?");
		if(sc.nextBoolean()){
		// ouverture de la barriere
		try {
			switch (etape) {
			case 0:
				ecluse.ouvrir(numeroBar);
				for(int i= 0; i<4; i++){
					if(i!= numeroBar){
				numEcluse = "ecluse" + i;
				m.WriteCoil(annuaire.get(numEcluse), Integer.toString(numeroBar), true);
					}
				}
				Thread.sleep(2000);
				etape++;
				break;
			case 1:
				// avancer bateau
				
				ecluse.avancerBateau();
				for(int i= 0; i<4; i++){
				if(i!= numeroBar){
				numEcluse = "ecluse" + i;
				m.WriteRegister(annuaire.get(numEcluse), "0", ecluse.getPosBateau());
					}
				}
				Thread.sleep(2000);
				etape++;
				break;
			case 2:
				// fermer barriere
				ecluse.fermer(numeroBar);
				for(int i= 0; i<4; i++){
					if(i!= numeroBar){
				numEcluse = "ecluse" + i;
				m.WriteCoil(annuaire.get(numEcluse), Integer.toString(numeroBar), false);
					}
				}
				Thread.sleep(2000);
				etape++;
				break;
			case 3:
				ecluse.setSontTour(false);
				// on envoit au prochain qu'il peut y aller
				numeroBar = (numeroBar+1)%4;
				numEcluse = "ecluse" + numeroBar;
				m.WriteCoil(annuaire.get(numEcluse), "4", true);
				etape++;
				break;
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		}
	}
}
