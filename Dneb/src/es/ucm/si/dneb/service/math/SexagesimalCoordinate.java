package es.ucm.si.dneb.service.math;

public class SexagesimalCoordinate {
	
	private int arh;
	private int armin;
	private double arsec;
	
	private int dech;
	private int decmin;
	private double decsec;
	
	public SexagesimalCoordinate(){
		
	}
	
	public SexagesimalCoordinate(int arh, int armin, double arsec, int dech,
			int decmin, double decsec) {
		super();
		this.arh = arh;
		this.armin = armin;
		this.arsec = arsec;
		this.dech = dech;
		this.decmin = decmin;
		this.decsec = decsec;
	}
	
	public int getArh() {
		return arh;
	}
	public void setArh(int arh) {
		this.arh = arh;
	}
	public int getArmin() {
		return armin;
	}
	public void setArmin(int armin) {
		this.armin = armin;
	}
	public double getArsec() {
		return arsec;
	}
	public void setArsec(double arsec) {
		this.arsec = arsec;
	}
	public int getDech() {
		return dech;
	}
	public void setDech(int dech) {
		this.dech = dech;
	}
	public int getDecmin() {
		return decmin;
	}
	public void setDecmin(int decmin) {
		this.decmin = decmin;
	}
	public double getDecsec() {
		return decsec;
	}
	public void setDecsec(double decsec) {
		this.decsec = decsec;
	}
	
	
	
}
