package application;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * 
 * @author falcon
 *
 *Cette classe gére l'affichage et la fenêtre
 */

class Fenetre extends JFrame {
	
	// à mettre dans une future classe flotte ?
	BufferedImage eau;
	int posXEau = 100;
	int posYEau = 100;
	
	Ecluse ecluse;
	boolean closed;
		Fenetre(Ecluse ecluse){
			try {
				eau = ImageIO.read(new File("eau.png"));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.ecluse = ecluse;
			closed = false;
			setBounds(0, 0, 1000, 800);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			this.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent evt) {
					closed = true;
				}
			});
		}
		public boolean isClosed(){
			
			return closed;
		}
		public void paint(Graphics arg0) {
			super.paint(arg0);
			((Graphics2D) arg0).drawImage(eau, null, posXEau, posYEau);
			ecluse.afficher((Graphics2D) getGraphics());
		}
		
	}
	