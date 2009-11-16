package es.ucm.si.dneb.service.image.centroid;

import java.util.ArrayList;

import es.ucm.si.dneb.service.image.util.Point;

public class CalculateBookCentroid implements
CalculateCentroid<Point, int [][]>{

	@Override
	public Point giveMeTheCentroid(int[][] points) {
		
		Point centroid = new Point();
		double tempX = 0, tempY = 0;
		
		for(int i=0 ;i<=points.length;i++){
			for(int j=0 ;j<=points[0].length;j++){
				
			}
		}
		
		return centroid;
	}

	/*@Override
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
*/
}
