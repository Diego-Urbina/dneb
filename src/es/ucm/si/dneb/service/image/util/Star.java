package es.ucm.si.dneb.service.image.util;

import java.util.ArrayList;

public class Star {
	
	private Integer height;
	private Integer weight;
	
	private ArrayList<Point> points;

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getHeight() {
		return height;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getWeight() {
		return weight;
	}
	

}
