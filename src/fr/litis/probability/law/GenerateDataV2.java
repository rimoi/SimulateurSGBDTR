package fr.litis.probability.law;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GenerateDataV2 {
	private int numberOfLines;        // Nombre de lignes qui figureront dans le fichier de donn�es
	private ArrayList<Data> columns;  // Liste des colonnes (description)
	private double listofdata[][];
	public final int fiability=1000;

	public GenerateDataV2 (){
		numberOfLines = 10;           // Par d�faut, il y aura 10 lignes
		columns = new ArrayList<Data> ();
	}

	public int getNumberOfLines() {
		return numberOfLines;
	};

	public void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

	public void addDataColumn (double min, double max, boolean rand){
		columns.add(new Data(min,max,rand));
	}

	private void generateData (int k){
		Random random = new Random ();
		Data oneData;
		double aRandomData;
		for (int i=0; i<numberOfLines; i++){
			for (int j=0; j<columns.size(); j++){
				oneData = columns.get(j);
				aRandomData = oneData.getMin();
				if (oneData.isRand()){
					aRandomData += random.nextDouble()*(oneData.getMax()-oneData.getMin());							
				}
				else{
					aRandomData += i*(oneData.getMax()-oneData.getMin())/numberOfLines;
				}
				listofdata[i][j]=(listofdata[i][j]*(k-1)+aRandomData)/k;
			}
		}
	}

	private void generateData (){
		if (columns.size()>0){
			listofdata = new double[numberOfLines][columns.size()];
			for (int k=1; k<fiability; k++)
				generateData(k);
		}
	}

	public void writeDataFile (String filename){
		generateData();
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
		GenerateDataV2 gd = new GenerateDataV2();
		gd.addDataColumn(1, 11, false);
		gd.addDataColumn(20, 100, true);
		gd.writeDataFile("Courbe2.dat");

	}

}
