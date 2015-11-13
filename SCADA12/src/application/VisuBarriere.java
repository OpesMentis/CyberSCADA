package application;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * 
 * @author falcon
 *S'occupe d'afficher la barierre
 *
 */

public class VisuBarriere {

	BufferedImage img;
	public VisuBarriere(boolean type) {
		// TODO Auto-generated constructor stub
		String nomImg = "barriere";
		if(type) nomImg += "Ver.png";
		else nomImg += "Hor.png";
		
		try {
			img =  ImageIO.read(new File(nomImg));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void affiche(Graphics2D g, int x, int y,boolean etat){
		if(!etat) g.drawImage(img, null, x, y);
	}
}
