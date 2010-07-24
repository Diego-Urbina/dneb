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
	        
	        + "ar = " + this.ar + TAB
	        + "dec = " + this.dec + " )";
	
	    return retValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(ar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(dec);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DecimalCoordinate other = (DecimalCoordinate) obj;
		if (Double.doubleToLongBits(ar) != Double.doubleToLongBits(other.ar))
			return false;
		if (Double.doubleToLongBits(dec) != Double.doubleToLongBits(other.dec))
			return false;
		return true;
	}
	
	
	
	
	
}
