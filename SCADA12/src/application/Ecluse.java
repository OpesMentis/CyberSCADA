package application;
import java.awt.Graphics2D;
import java.util.Vector;

public class Ecluse {
	Bateau bat;
	Vector<Barriere> barrieres;
	static int nombreBarriere;
	Automate automate;
	boolean sonTour;
	Ecluse(){
		nombreBarriere = 4;
		barrieres = new Vector<Barriere>();
					
		barrieres.add(new Barriere("verticale", 450, 150));
		barrieres.add(new Barriere("horizontale", 600, 400));
		barrieres.add(new Barriere("verticale", 450, 500));
		barrieres.add(new Barriere("horizontale", 200, 400));
		automate = new Automate(this);
		bat = new Bateau();
		sonTour =false;
		
	}
	
	public boolean estSonTour(){
		return sonTour;
	}
	
	public void setSontTour(boolean b){
		sonTour =b;
		automate.update("tour");
	}
	
	public void fermer(int numeroBarriere){
		if(numeroBarriere>=0 && numeroBarriere<nombreBarriere){
		barrieres.get(numeroBarriere).fermer();
		automate.update("barriere");
		}
	}
	public void ouvrir(int numeroBarriere){
		if(numeroBarriere>=0 && numeroBarriere<nombreBarriere){
		barrieres.get(numeroBarriere).ouvrir();
		automate.update("barriere");
		}
	}
	public void avancerBateau(){
		bat.avancer();
		automate.update("bateau");
	}
	public void avancerBateau(int pos){
		bat.avancer(pos);
		automate.update("bateau");
	}

	public void afficher(Graphics2D fenetre){
		for(Barriere elem: barrieres){
			elem.affiche(fenetre);
			}
		bat.affiche(fenetre);
	}
	public int getNombreBarriere(){
		return nombreBarriere;
	}
	public int getPosBateau(){
		return bat.getPos();
	}
	public boolean estFermer(int numBar){
		return barrieres.get(numBar).estFermer();
	}
}
