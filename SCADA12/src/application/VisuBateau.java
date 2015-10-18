package application;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class VisuBateau {
	BufferedImage img;
	public VisuBateau() {
		// TODO Auto-generated constructor stub
		
		try {
			img =  ImageIO.read(new File("bateau.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void affiche(Graphics2D g, int x, int y){
		g.drawImage(img, null, x, y);
		
	}
}
