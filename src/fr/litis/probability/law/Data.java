package fr.litis.probability.law;

public class Data {
	private double min;
	private double max;
	private boolean rand;
	
	public Data(double min, double max, boolean rand) {
		super();
		this.min = min;
		this.max = max;
		this.rand = rand;
	}
	
	public double getMin() {
		return min;
	}
	
	public void setMin(double min) {
		this.min = min;
	}
	
	public double getMax() {
		return max;
	}
	
	public void setMax(double max) {
		this.max = max;
	}
	
	public boolean isRand() {
		return rand;
	}
	
	public void setRand(boolean rand) {
		this.rand = rand;
	}
}
