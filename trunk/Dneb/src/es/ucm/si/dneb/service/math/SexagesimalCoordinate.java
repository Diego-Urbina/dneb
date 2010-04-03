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
	
	public String paint(){
		return ""+this.getArh()+this.getArmin()+this.getArsec()+" "+this.getDech()+this.getDecmin()+this.getDecsec();
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

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";
	    
	    String retValue = "";
	    
	    retValue = "SexagesimalCoordinate ( "
	        + super.toString() + TAB
	        + "arh = " + this.arh + TAB
	        + "armin = " + this.armin + TAB
	        + "arsec = " + this.arsec + TAB
	        + "dech = " + this.dech + TAB
	        + "decmin = " + this.decmin + TAB
	        + "decsec = " + this.decsec + TAB
	        + " )";
	
	    return retValue;
	}
	
	
	
}