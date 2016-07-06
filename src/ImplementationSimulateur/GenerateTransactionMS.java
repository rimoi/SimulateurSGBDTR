package ImplementationSimulateur;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class GenerateTransactionMS implements Runnable {
	

	private int IDtransaction; 
	private int deadline;
	private double periode;
	private  SGBDTR sgbdtr;
	
	private BlockingQueue<SGBDTR>  queue =  new ArrayBlockingQueue<SGBDTR>(20);
	private BlockingQueue<GenerateTransactionMS >  queue2 =  new ArrayBlockingQueue<GenerateTransactionMS >(20);
	
	// constructeur
	
	
	public GenerateTransactionMS(int iDtransaction, SGBDTR sgbdtr,int tailleQueue) {
		super();
		this.IDtransaction = iDtransaction;
		this.sgbdtr = sgbdtr;
		// la période de la mise à jour
		this.periode = (2.0/3.0) * sgbdtr.getValidite();
		
		// écheance de la transaction
		this.deadline = (int)(periode + sgbdtr.getValidite());
		this.queue=   new ArrayBlockingQueue<SGBDTR>(tailleQueue);
	}
	
	// constructeur vide
	public GenerateTransactionMS(int tailleQueue) {
		super();
		this.queue=  new ArrayBlockingQueue<SGBDTR>(tailleQueue);
	}	
	
	
	public void run(){
		try{
			
						
						
			
			
						int t=0;
						
						while(t<queue2.size()){
							wait(deadline*1000);// écheance
							System.out.println("[" + Thread.currentThread().getName() +  "]"+"la données " +queue2.take()+ " est générer"+" longeur de file d'attente"+ queue2.size());
							t++;
						}
						 
						

				
			
			
			
			
			
		} catch(InterruptedException e){
	         System.out.println("[" + Thread.currentThread().getName() +  "] est arrêter") ;
	      }
	}

	public BlockingQueue<SGBDTR> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<SGBDTR> queu) {
		this.queue = queu;
	}

	public void setQueue2(BlockingQueue<GenerateTransactionMS > queu) {
		this.queue2 = queu;
	}

	public double getPeriode() {
		return periode;
	}

	public void setPeriode(double periode) {
		this.periode = periode;
	}

	
}
