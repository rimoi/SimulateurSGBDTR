package ImplementationSimulateur;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import fr.litis.probability.law.Poisson;

public class GenerateTransactionUSER implements Runnable{
private int nTransaction;
private int durerLectureTR;
private int durerLectureCL;
private int durerEcritureCL;
private int durerSimulation;
private int echeance;
private double lambda;
private SGBDTR sgbdtr;
private SGBD sgbd;
private BlockingQueue<SGBD>  queue1 =  new ArrayBlockingQueue<SGBD>(20);
private BlockingQueue<GenerateTransactionUSER>  queue2 =  new ArrayBlockingQueue<GenerateTransactionUSER>(20);


public GenerateTransactionUSER(int borneInf,int borneSup,int durerLectureTR, int durerLectureCL, int durerEcritureCL, int durerSimulation,double lambda,SGBDTR sgbdtr,SGBD sgbd) {
	super();
	
	this.nTransaction = 	borneInf + (int) (Math.random() *( (borneSup - borneInf) + 1));
	this.durerLectureTR = durerLectureTR;
	this.durerLectureCL = durerLectureCL;
	this.durerEcritureCL = durerEcritureCL;
	this.durerSimulation = durerSimulation;
	this.lambda =lambda;
	this.sgbdtr = sgbdtr;
	this.sgbd = sgbd;
	
}



public GenerateTransactionUSER() {
	super();
}






public void run(){
	try{
		
			
			while(queue2.take().getSgbd().getValidite() < queue2.take().durerSimulation){
			//while(t<queue2.size()){
				 this.echeance = (int) ((queue2.take().getSgbd().getValidite()*(2.0/3.0) ) + queue2.take().getSgbd().getValidite());
				Thread.sleep(echeance*1000);
				System.out.println("[" + Thread.currentThread().getName() +  "]"+"la données " +queue2.take().getSgbd()+" ou"+queue2.take().getSgbdtr()+ " est générer"+" longeur de file d'attente"+ queue2.size());
				
			}
			
				
				
				
					
				
		
	} catch(InterruptedException err){
        System.out.println("[" + Thread.currentThread().getName() +  "] Simulation arrêter") ;
     }
}



public void setQueue2(BlockingQueue<GenerateTransactionUSER> queue4) {
	// TODO Auto-generated method stub
	this.queue2=queue4;
}
public SGBDTR getSgbdtr() {
	return sgbdtr;
}



public void setSgbdtr(SGBDTR sgbdtr) {
	this.sgbdtr = sgbdtr;
}



public SGBD getSgbd() {
	return sgbd;
}



public void setSgbd(SGBD sgbd) {
	this.sgbd = sgbd;
}


}
