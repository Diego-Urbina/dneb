package es.ucm.si.dneb.service.math;

public class Distance {
	
	
	private double distance;
	private double angle;
	
	private double distanceSeconds;
	
	private DecimalCoordinate point1;
	private DecimalCoordinate point2;
	
	public Distance(double distance, double angle) {
		super();
		this.distance = distance;
		this.angle = angle;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public DecimalCoordinate getPoint1() {
		return point1;
	}
	public void setPoint1(DecimalCoordinate point1) {
		this.point1 = point1;
	}
	public DecimalCoordinate getPoint2() {
		return point2;
	}
	public void setPoint2(DecimalCoordinate point2) {
		this.point2 = point2;
	}
	public void setDistanceSeconds(double distanceSeconds) {
		this.distanceSeconds = distanceSeconds;
	}
	public double getDistanceSeconds() {
		return distanceSeconds;
	}
	
	
	
	
	

}
