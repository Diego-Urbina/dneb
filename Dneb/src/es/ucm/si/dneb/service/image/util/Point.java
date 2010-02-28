package es.ucm.si.dneb.service.image.util;

public class Point {
	
	private Double x;
	private Double y;
	
	
	public void setX(Double x) {
		this.x = x;
	}
	public Double getX() {
		return x;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public Double getY() {
		return y;
	}
	public Double getDistancia(Point p) {
		return Math.sqrt(Math.pow(this.getX() - p.getX(), 2) +
				Math.pow(this.getY() - p.getY(), 2));
	}
		
	

}
