package application;

import net.wimpi.modbus.net.*;
import net.wimpi.modbus.procimg.*;
import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.ModbusCoupler;

/** 
 * 
 * @author falcon
 *
 *Cette classe s'occupe de recevoir des requête Modbus et de renvoyer la réponse associée 
 */

public class SlaveModbus {

	
	public  SlaveModbus(int port) {
		try {
			/* The important instances and variables */
			ModbusTCPListener listener = null;
			listener = new ModbusTCPListener(3);
			System.out.println("port d'ecoute : " + port);
			listener.setPort(port);
			listener.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}// class TCPSlaveTest