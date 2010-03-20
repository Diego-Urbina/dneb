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
	    
	    retValue = "DecimalCoordinate ( "
	        + super.toString() + TAB
	        + "ar = " + this.ar + TAB
	        + "dec = " + this.dec + TAB
	        + " )";
	
	    return retValue;
	}
	
	
	
}
