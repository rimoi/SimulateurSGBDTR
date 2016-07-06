package fr.litis.probability.law;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Exponentielle {

	private double listofdata[][];
	private int fiabilite = 1;

	public static int factorielle(int n){
		if(n>1)
			return n*factorielle(n-1);
		else
			return 1;
	}

	public double getDensity(double lambda, double x){
		return 1 - Math.exp (-lambda*x);
	}

	public double getRepartition(double lambda, double x){
		return lambda * Math.exp (-lambda*x);
	}

	public double getExponentielle(double lambda, double y){
		if (y>=1) y=0.999;
		return -Math.log (1-y)/lambda*10;
	}

	public void generateDensity (double lambda){
		int nbelem = 250;
		listofdata = new double[nbelem][2];
		int nbexp=100000;
		for (int i=0; i<nbelem; i++){
			listofdata[i][0]=i;
			listofdata[i][1]=0;
		}

		for (int j=0; j<fiabilite; j++){
			Random random = new Random();
			for (int i=0; i<nbexp; i++){
				double alea=random.nextDouble();
				int expo = (int)(getExponentielle(lambda, alea));
				if (expo>100) System.out.println("("+alea+","+expo+")");
				listofdata[expo][1]++;
			}
		}

		for (int i=0; i<nbelem; i++){
			listofdata[i][1]=(int)(listofdata[i][1]/fiabilite);
		}
	}

	public void generateExponentielle (double lambda){
		int nbelem = 11;
		listofdata = new double[nbelem][2];
		for (int i=0; i<nbelem; i++){
			listofdata[i][0]=(double)i/10;
			listofdata[i][1]=0;
		}

		for (int j=0; j<nbelem; j++){
			listofdata[j][1]=getExponentielle(lambda, listofdata[j][0]);
		}
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
						line += new Double(listofdata[i][j]).toString()+" ";
					}
					bw.write(line+"\n");
				}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Exponentielle expo = new Exponentielle ();
		expo.generateExponentielle(0.5);
		expo.writeDataFile("CourbeExponentiel05.dat");
		expo.generateExponentielle(1.0);
		expo.writeDataFile("CourbeExponentiel10.dat");
		expo.generateExponentielle(1.5);
		expo.writeDataFile("CourbeExponentiel15.dat");
		expo.generateDensity(0.5);
		expo.writeDataFile("HistoExponentiel05.dat");
		expo.generateDensity(1.0);
		expo.writeDataFile("HistoExponentiel10.dat");
		expo.generateDensity(1.5);
		expo.writeDataFile("HistoExponentiel15.dat");
	}

}
