package es.ucm.si.dneb.service.image.centroid;



import es.ucm.si.dneb.service.image.util.Point;

public class CalculateBookCentroid implements
CalculateCentroid<Point, int [][]>{

	@Override
	public Point giveMeTheCentroid(int[][] points) {
		
		Point centroid = new Point();
		double centroidX = 0, centroidY = 0;
		double tempX =0, tempY =0;
		double total =0;
		
		for(int i=points.length-1 ;i>=0;i--){
			
			tempY=0;
			
			for(int j=0 ;j<=points[0].length-1;j++){
				tempY+=points[i][j];
				total+=points[i][j];
			}
			
			centroidY+=(points.length-i)*(tempY); 
		}
		
		for(int j=0 ;j<=points.length-1;j++){
			tempX=0;
			for(int i=points[0].length-1 ;i>=0;i--){
				tempX+=points[i][j];
			}
			centroidX+=(j+1)*(tempX);
		}
		
		
		centroid.setX((centroidX/total));
		centroid.setY((centroidY/total));
		
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
