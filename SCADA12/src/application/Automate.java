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
	int nombreBarriere;
	public Automate(int nombreBarriere){
		this.nombreBarriere = nombreBarriere;
	SimpleProcessImage spi = new SimpleProcessImage();
	  // 4 Coil pour 4 barriere
	  // false = elles sont fermées
	 for(int i =0; i<nombreBarriere; i++){
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
	void setSonTour(boolean sonTour){
		ModbusCoupler.getReference().getProcessImage().getDigitalOut(4).set(sonTour);
	}
	boolean estFermee(int numBarriere){
		if(numBarriere >=0 &&  numBarriere < nombreBarriere)
		return !ModbusCoupler.getReference().getProcessImage().getDigitalOut(numBarriere).isSet();
		else return true;
	}
	int getPosBat(){
		return ModbusCoupler.getReference().getProcessImage().getRegister(0).getValue();
	}
	boolean estSonTour(){
		return ModbusCoupler.getReference().getProcessImage().getDigitalOut(4).isSet();
	}
}
