package application;

import java.awt.Graphics2D;
import java.util.Vector;

/**
 * 
 * @author falcon
 *
 *         Cette classe est la représentation du systéme écluse en entier:
 *         barierre et bateau Elle met à jour l'automate
 */

public class Ecluse {
	Bateau bat;
	Vector<Barriere> barrieres;
	static int nombreBarriere;
	Automate automate;
	boolean sonTour;

	Ecluse() {
		nombreBarriere = 4;
		barrieres = new Vector<Barriere>();

		barrieres.add(new Barriere("verticale", 450, 150));
		barrieres.add(new Barriere("horizontale", 600, 400));
		barrieres.add(new Barriere("verticale", 450, 500));
		barrieres.add(new Barriere("horizontale", 200, 400));
		automate = new Automate(this.getNombreBarriere());
		bat = new Bateau();
		sonTour = false;

	}

	public boolean estSonTour() {
		return sonTour;
	}

	 void setSontTour(boolean b) {
		sonTour = b;
		automate.setSonTour(b);
	}

	void fermer(int numeroBarriere) {
		if (numeroBarriere >= 0 && numeroBarriere < nombreBarriere) {
			barrieres.get(numeroBarriere).fermer();

		}
	}

	void ouvrir(int numeroBarriere) {
		if (numeroBarriere >= 0 && numeroBarriere < nombreBarriere) {
			barrieres.get(numeroBarriere).ouvrir();

		}
	}
	void actionner(int numeroBarriere, boolean fermer){
		if(fermer) fermer(numeroBarriere);
		else ouvrir(numeroBarriere);
	}

	void avancerBateau(int pos) {
		bat.avancer(pos);

	}

	public void afficher(Graphics2D fenetre) {
		for (Barriere elem : barrieres) {
			elem.affiche(fenetre);
		}
		bat.affiche(fenetre);
	}

	public int getNombreBarriere() {
		return nombreBarriere;
	}

	public int getPosBateau() {
		return bat.getPos();
	}

	boolean estFermee(int numeroBarriere){
		if (numeroBarriere >= 0 && numeroBarriere < nombreBarriere) {
			return barrieres.get(numeroBarriere).estFermer();

		}
		else return true;
	}
	public void update() {
		// on remarque si il y a un décalage entre l'automate et l'écluse
		// barriere
		boolean miseAJour = false;
		
		for (int i = 0; i < nombreBarriere; i++) {
			if (automate.estFermee(i) != estFermee(i)) {
				actionner(i, automate.estFermee(i));
				miseAJour = true;
			}
		}
		// bateau
		if(automate.getPosBat() != getPosBateau()){
			avancerBateau(automate.getPosBat());
			miseAJour = true;
		}
		//tour
		if(automate.estSonTour() != estSonTour()){
			setSontTour(automate.estSonTour());
			miseAJour = true;
		}
		if(miseAJour)System.out.println("Ecluse update: L'écluse a été mise à jour");
	}
}
