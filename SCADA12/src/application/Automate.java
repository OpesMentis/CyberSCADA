package application;
import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.procimg.SimpleDigitalIn;
import net.wimpi.modbus.procimg.SimpleDigitalOut;
import net.wimpi.modbus.procimg.SimpleInputRegister;
import net.wimpi.modbus.procimg.SimpleProcessImage;
import net.wimpi.modbus.procimg.SimpleRegister;


/**
 * 
 * @author falcon
 *
 *Cette classe est la représentation du systéme écluse mais sous forme de registre comme dans un vrai automate.
 *
 */

public class Automate {
	SimpleProcessImage spi;
	Ecluse ecluse;
	
	public Automate(Ecluse ecluse){
	 this.ecluse = ecluse;	
	 spi = new SimpleProcessImage();
	  // 4 Coil pour 4 barriere
	  // false = elles sont fermées
	 for(int i =0; i<ecluse.getNombreBarriere(); i++){
	  spi.addDigitalOut(new SimpleDigitalOut(false));
	 }
	 // creation du registre "sonTour", pour savoir si c'est notre tour ou pas
	 spi.addDigitalOut(new SimpleDigitalOut(false));
	  // Position du bateau à 0 au début
	 SimpleRegister registreSimple = new SimpleRegister(0);
	
	  spi.addRegister(registreSimple);
	 
	  //3. Set the image on the coupler
	  // Attention ici ModbusCoupler crée une copie de spi !!!
	  // on utilisera donc par la suite ModbusCoupler.getReference().getProcessImage() plutôt que spi
	  ModbusCoupler.getReference().setProcessImage(spi);
	  ModbusCoupler.getReference().setMaster(false);
	  ModbusCoupler.getReference().setUnitID(15);
	  
	}
	
	public void update(String type){
		
		// Ces changements n'entrainent heureusement pas de miseAJourEcluse (risque de récurrence infinie)
		// chgmt ecluse -> chgt automate -> chgt ecluse -> etc ..
		
		//les barriere
		if(type.equals("barriere")){
		for(int i =0; i<ecluse.getNombreBarriere(); i++){
			if(ecluse.estFermer(i)) ModbusCoupler.getReference().getProcessImage().getDigitalOut(i).set(false);
			else ModbusCoupler.getReference().getProcessImage().getDigitalOut(i).set(true);
		}
		}
		if(type.equals("bateau")){
		//le bateau
		ModbusCoupler.getReference().getProcessImage().getRegister(0).setValue(ecluse.getPosBateau());
		}
		if(type.equals("tour")){
		//le sonTour
		if(ecluse.estSonTour()) ModbusCoupler.getReference().getProcessImage().getDigitalOut(4).set(true);
		else ModbusCoupler.getReference().getProcessImage().getDigitalOut(4).set(false);
		}
	}
	
	public void miseAJourEcluse(){
		
		if(ModbusCoupler.getReference().getProcessImage() != null){

		 for(int i =0; i<ecluse.getNombreBarriere(); i++){
			 
			 if(ModbusCoupler.getReference().getProcessImage().getDigitalOut(i).isSet()) ecluse.ouvrir(i);
			 else ecluse.fermer(i);
		 }
		    
		ecluse.avancerBateau(ModbusCoupler.getReference().getProcessImage().getRegister(0).getValue());
		if(ModbusCoupler.getReference().getProcessImage().getDigitalOut(4).isSet()) ecluse.setSontTour(true);
		else ecluse.setSontTour(false);
		}
	}
	
}
