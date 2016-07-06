package ImplementationSimulateur;

public class SGBD {
	
	protected static int valeur=0;
	protected int login;
	/* durée de validité d'une données  va correspondre a  un nombre aléatoire compris entre la limite inférieure et la limite supérieure
	*	   et on rajoute 1 pour faire inclure la partie superieur dans l'intervalle et on enleve la partie décimal en convertissant le nombre en entier.
	*/
	protected double validite;
	
	
	
	public SGBD(int valeur, int login, int inf, int sup ) {
		/*
		 * *
		 *  Pour le constructeur il va prendre quatre valeur en paremetre qui sont une veleur, en identifiant(login) 
		 *  de la données ainsi que les bornes de l'intervalle(inf,sup) .
		 */
		this.valeur = valeur;
		this.login = login;
		
		this.validite = inf + (int) (Math.random() *( (sup - inf) + 1)); 
		
	}



	public int getValeur() {
		return valeur;
	}



	public void setValeur(int valeur) {
		this.valeur = valeur;
	}



	public int getLogin() {
		return login;
	}



	public void setLogin(int login) {
		this.login = login;
	}



	public double getValidite() {
		return validite;
	}



	public void setValidite(double validite) {
		this.validite = validite;
	}
	
}	