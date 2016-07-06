package fr.litis.probability.law;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GenerateData {
	private int numberOfLines;        // Nombre de lignes qui figureront dans le fichier de donn�es
	private ArrayList<Data> columns;  // Liste des colonnes (description)
	
	public GenerateData (){
		numberOfLines = 10;           // Par d�faut, il y aura 10 lignes
		columns = new ArrayList<Data> ();
	}
	
	public int getNumberOfLines() {
		return numberOfLines;
	}

	public void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

	public void addDataColumn (double min, double max, boolean rand){
		columns.add(new Data(min,max,rand));
	}
	
	public void writeDataFile (String filename){
		Random random = new Random ();
		try {
			String filepath = System.getProperty("user.dir") + File.separator + filename;
			System.out.println("Ecriture du fichier : "+filepath);
			FileWriter fw = new FileWriter (filepath);
			BufferedWriter bw = new BufferedWriter (fw);
			Data oneData;
			double aRandomData;
					
			if (columns.size()>0)
				for (int i=0; i<numberOfLines; i++){
					String line="";
					for (int j=0; j<columns.size(); j++){
						oneData = columns.get(j);
						aRandomData = oneData.getMin();
						if (oneData.isRand()){
							aRandomData += random.nextDouble()*(oneData.getMax()-oneData.getMin());							
						}
						else{
							aRandomData += i*(oneData.getMax()-oneData.getMin())/numberOfLines;
						}
						line += new Double(aRandomData).toString()+" ";
					}
					bw.write(line+"\n");
				}
				
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		GenerateData gd = new GenerateData();
		gd.addDataColumn(0, 2, false);
		gd.addDataColumn(20, 100, true);
		gd.writeDataFile("Courbe1.dat");

	}

}
