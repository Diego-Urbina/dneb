package es.ucm.si.dneb.service.image.centroid;

import es.ucm.si.dneb.service.image.util.Point;
import es.ucm.si.dneb.service.image.util.Star;

public interface CalculateCentroid <P,I>{
	
	public  P giveMeTheCentroid(I points);

}
