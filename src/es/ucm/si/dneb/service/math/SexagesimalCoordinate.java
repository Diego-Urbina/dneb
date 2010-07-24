package es.ucm.si.dneb.service.math;

public class SexagesimalCoordinate {
	
	private double arh;
	private double armin;
	private double arsec;
	
	private double dech;
	private double decmin;
	private double decsec;
	
	public SexagesimalCoordinate(){
		
	}
	
	public SexagesimalCoordinate(double arh, double armin, double arsec, double dech,
			double decmin, double decsec) {
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
	
	public double getArh() {
		return arh;
	}
	public void setArh(double arh) {
		this.arh = arh;
	}
	public double getArmin() {
		return armin;
	}
	public void setArmin(double armin) {
		this.armin = armin;
	}
	public double getArsec() {
		return arsec;
	}
	public void setArsec(double arsec) {
		this.arsec = arsec;
	}
	public double getDech() {
		return dech;
	}
	public void setDech(double dech) {
		this.dech = dech;
	}
	public double getDecmin() {
		return decmin;
	}
	public void setDecmin(double decmin) {
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
	        + "arh = " + this.arh + TAB
	        + "armin = " + this.armin + TAB
	        + "arsec = " + this.arsec + TAB
	        + "dech = " + this.dech + TAB
	        + "decmin = " + this.decmin + TAB
	        + "decsec = " + this.decsec + " )";
	
	    return retValue;
	}
	
	
	
}
