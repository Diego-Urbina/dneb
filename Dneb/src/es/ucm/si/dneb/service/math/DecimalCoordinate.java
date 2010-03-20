package es.ucm.si.dneb.service.math;

public class DecimalCoordinate {
	
	private double ar;
	private double dec;
	
	public DecimalCoordinate(){
		
	}
	
	public DecimalCoordinate(double ar, double dec) {
		super();
		this.ar = ar;
		this.dec = dec;
	}
	
	public double getAr() {
		return ar;
	}
	public void setAr(double ar) {
		this.ar = ar;
	}
	public double getDec() {
		return dec;
	}
	public void setDec(double dec) {
		this.dec = dec;
	}
	
	
	
}
