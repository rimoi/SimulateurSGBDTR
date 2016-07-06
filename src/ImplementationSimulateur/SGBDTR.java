package ImplementationSimulateur;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//import java.util.*;


public class SGBDTR extends SGBD {
	
	private int  estampille = 0;

	
	private boolean verrou = false;

	//private static int tailleFileAttente; // taille de la fille d'attente
	//private static BlockingQueue<SGBDTR> queue =  new ArrayBlockingQueue<SGBDTR>(5);
	// les constructeurs
	
	public SGBDTR(int valeur, int login, int inf, int sup, int estampille, boolean verrou) {
		super(valeur, login, inf, sup);
		this.estampille = estampille;
		this.verrou = verrou;
		// this.queue =  new ArrayBlockingQueue<SGBDTR>(tailleFileAttente);
	}
	public SGBDTR(int login, int inf, int sup) {
		super(valeur, login, inf, sup);
		this.estampille = 0;
		this.verrou = false;
		 //this.queue =  new ArrayBlockingQueue<SGBDTR>(tailleFileAttente);
	}
	/*// pour insérer des données temps réel
	public static boolean donneesTR(SGBDTR s)  throws InterruptedException {
		return queue.offer(s);
	}
	// le nombre de données générer
	public static int donneesGenerer(){
		return queue.size();
	}*/
	// les getters et les setters

	public int getEstampille() {
		return estampille;
	}



	public void setEstampille(int estampille) {
		this.estampille = estampille;
	}


/*
	public static BlockingQueue<SGBDTR> getQueue() {
		return queue;
	}
	public static void setQueue(BlockingQueue<SGBDTR> queue) {
		SGBDTR.queue = queue;
	}*/
	public boolean isVerrou() {
		return verrou;
	}



	public void setVerrou(boolean verrou) {
		this.verrou = verrou;
	}
	
	

	

	
	
	
	
	
}
