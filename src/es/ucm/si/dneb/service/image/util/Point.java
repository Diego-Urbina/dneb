package es.ucm.si.dneb.service.image.util;

public class Point {
	
	private Double x;
	private Double y;
	
	public Point() {
		x = 0.;
		y = 0.;
	}
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
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
		return Math.sqrt(Math.pow(p.getX()-this.getX(), 2) +
				Math.pow(p.getY()-this.getY(), 2));
	}
	
	public Double getDireccion(Point p) {
		return Math.atan((p.getY()-this.getY()) / (p.getX()-this.getX()));
	}
		
	public Point clone() {
		return new Point(x.doubleValue(), y.doubleValue());
	}

}
