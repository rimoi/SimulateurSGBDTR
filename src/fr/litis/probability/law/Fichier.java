package fr.litis.probability.law;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Fichier {

	private BufferedWriter tampon;
	
	public Fichier (String filename, boolean append){
		FileWriter fw;
		try {
			fw = new FileWriter (filename, append);
			tampon = new BufferedWriter (fw);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public Fichier (String filename){
		FileWriter fw;
		try {
			fw = new FileWriter (filename);
			tampon = new BufferedWriter (fw);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void ecrireLigne (String chaine){
		try {
			tampon.write(chaine+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void fermer(){
		try {
			tampon.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
		System.out.println("Cr�ation du fichier dans le r�pertoire : "+System.getProperty("user.dir"));
		double a = 1.0;
		double b = 2.0;
		
		Fichier f = new Fichier ("Donnees.dat");
		f.ecrireLigne(new Double (a).toString()+" "+new Double (b).toString());
		f.fermer();
	}

}
