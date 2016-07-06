package fr.litis.probability.law;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

public class Poisson {

	private int listofdata[][];
	private int listofdatamoyenne[][];
	private int fiabilite = 1;
	private Random random;
	private int duree;
	
	public Poisson(int duree) {
		this.duree = duree;
		random = new Random();
		listofdatamoyenne = new int[duree][2];
		for (int j=0;j<duree;j++){
			listofdatamoyenne[j][0]=j;
			listofdatamoyenne[j][1]=0;
		}
	}

	public void setFiabilite(int fiabilite){
		this.fiabilite = fiabilite;
	}
	
	public static int factorielle(int n){
		if(n>1)
			return n*factorielle(n-1);
		else
			return 1;
	}

    public double getExponentielle(double lambda, double y){
		return -Math.log (1-y)/lambda;
	}
	
	public double getPoisson (double lambda, int k){
		return Math.exp(0-lambda)*Math.pow(lambda, k)/factorielle (k);	
	}

	public double nextPoisson(double lambda){
		return -Math.log(random.nextDouble())/lambda;
	}
	
    public void generatePoissonProcess (double lambda){
    	Random random = new Random();
    	double listofdatas[][];
		listofdatas = new double[duree*3][2];
		double timeG=0; // Le temps genere
		int i = 0;
		do {
			timeG += getExponentielle(lambda, random.nextDouble());
			listofdatas[i][0]=i;
			listofdatas[i][1]=timeG;
			i++;
			//System.out.println ("Generated value: "+timeG);
		} while(timeG < (double)duree);
		i--;
		listofdata = new int[duree][2];
		for (int j=0;j<duree;j++){
			listofdata[j][0]=j;
			listofdata[j][1]=0;
		}

		for (int j=0;j<i;j++){
			listofdata[(int)listofdatas[j][1]][1]++;
		}

		for (int j=1;j<duree;j++){
			listofdata[j][1]+=listofdata[j-1][1];
		}
    }

    public void generateAveragePoissonProcess (double lambda){
    	for (int f=0; f<fiabilite; f++){
    		generatePoissonProcess(lambda);
    		for (int j=1;j<duree;j++){
    			listofdatamoyenne[j][1]=(listofdatamoyenne[j][1]*f+listofdata[j][1])/(f+1);
    		}
    	}
    	listofdata = listofdatamoyenne;
    }

	public void writeDataFile (String filename){
		try {
			String filepath = System.getProperty("user.dir") + File.separator + filename;
			System.out.println("Ecriture du fichier : "+filepath);
			FileWriter fw = new FileWriter (filepath);
			BufferedWriter bw = new BufferedWriter (fw);

			if (listofdata.length>0)
				for (int i=0; i<listofdata.length; i++){
					String line="";
					for (int j=0; j<listofdata[i].length; j++){
						line += new Integer(listofdata[i][j]).toString()+" ";
					}
					bw.write(line+"\n");
				}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.print(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":");
		System.out.print(Calendar.getInstance().get(Calendar.MINUTE)+":");
		System.out.print(Calendar.getInstance().get(Calendar.SECOND)+":");
		System.out.println(Calendar.getInstance().get(Calendar.MILLISECOND));
		
		Poisson poisson = new Poisson (1000);
		poisson.generateAveragePoissonProcess(0.1);
		poisson.writeDataFile("CourbePoisson01-1-1.dat");
		poisson.generateAveragePoissonProcess(0.1);
		poisson.writeDataFile("CourbePoisson01-1-2.dat");
		poisson.generateAveragePoissonProcess(0.5);
		poisson.writeDataFile("CourbePoisson05-1.dat");
		poisson.generateAveragePoissonProcess(1.0);
		poisson.writeDataFile("CourbePoisson10-1.dat");
		poisson.generateAveragePoissonProcess(1.5);
		poisson.writeDataFile("CourbePoisson15-1.dat");

		System.out.print(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":");
		System.out.print(Calendar.getInstance().get(Calendar.MINUTE)+":");
		System.out.print(Calendar.getInstance().get(Calendar.SECOND)+":");
		System.out.println(Calendar.getInstance().get(Calendar.MILLISECOND));		
		
		poisson.setFiabilite(100000);
		poisson.generateAveragePoissonProcess(0.1);
		poisson.writeDataFile("CourbePoisson01-100000-1.dat");
		poisson.generateAveragePoissonProcess(0.1);
		poisson.writeDataFile("CourbePoisson01-100000-2.dat");
		poisson.generateAveragePoissonProcess(0.5);
		poisson.writeDataFile("CourbePoisson05-100000.dat");
		poisson.generateAveragePoissonProcess(1.0);
		poisson.writeDataFile("CourbePoisson10-100000.dat");
		poisson.generateAveragePoissonProcess(1.5);
		poisson.writeDataFile("CourbePoisson15-100000.dat");
		
		System.out.print(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":");
		System.out.print(Calendar.getInstance().get(Calendar.MINUTE)+":");
		System.out.print(Calendar.getInstance().get(Calendar.SECOND)+":");
		System.out.println(Calendar.getInstance().get(Calendar.MILLISECOND));
    
	}

}
