package es.ucm.si.dneb.service.math;

import es.ucm.si.dneb.domain.Imagen;

public interface MathService{
	
	public Distance calculateDecimalDistance(Double ar1, Double dec1,Double ar2,Double dec2);
	
	public double[][] transform(double image[][], double scale,double rotation,double verticalTranslation,double horizontalTranslation);
	
	public DecimalCoordinate pixelToCoordinatesConverter(Imagen imagen, int width, int height, double x, double y);

}
