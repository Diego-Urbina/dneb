package es.ucm.si.dneb.service.image.centroid;

import java.util.ArrayList;

import es.ucm.si.dneb.service.image.util.Point;

public class CalculateBasicCentroid implements
		CalculateCentroid<Point, ArrayList<Point>> {

	@Override
	public Point giveMeTheCentroid(ArrayList<Point> points) {
		
		Point centroid = new Point();
		
		int numberOfPoints=points.size();
		double tempX = 0, tempY = 0;
		
		for (Point point : points){
			
			tempX += point.getX();
			// total for x
			tempY += point.getY();
			// total for y
		}
		centroid.setX( tempX / numberOfPoints);
		centroid.setY( tempY / numberOfPoints);

		
		return centroid;

	}

}
