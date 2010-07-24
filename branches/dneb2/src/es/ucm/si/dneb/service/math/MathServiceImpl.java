package es.ucm.si.dneb.service.math;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import es.ucm.si.dneb.domain.Imagen;

@Service("mathService")
public class MathServiceImpl implements MathService{
	
	private static final Log LOG = LogFactory
	.getLog(MathServiceImpl.class);
	
	@Override
	public Distance calculateDecimalDistance(Double ar1, Double dec1,Double ar2,Double dec2) {
		
		double a =Math.pow(Math.sin(rad((dec2-dec1)/2)),2)+Math.cos(rad(dec1))*Math.cos(rad(dec2))*Math.pow(Math.sin(rad(((ar2-ar1)/2))),2);
		LOG.debug("a="+a);
		double c = 2*Math.atan2( Math.sqrt(a),Math.sqrt((1-a)));
		LOG.debug("c="+c);
		double ap1 = grados(Math.asin(Math.cos(rad(dec2))*Math.sin(rad((ar2-ar1)))/Math.sin(c)));
		LOG.debug("ap1="+ap1);
		
		double dist = grados(c)*3600;
		LOG.debug("dist="+dist);
		
		double ang;
		
		if(ar2>ar1){
			if(dec2<dec1){
				ang=180-ap1;
			}else{
				ang=ap1;
			}
		}else{
			if(dec2>=dec1){
				ang=360+ap1;
			}else{
				ang=180-ap1;
			}
		}
		LOG.debug("ang="+ang);
		
		double distSec=dist/3600;
		
		Distance distance = new Distance(distSec,ang);
		
		distance.setDistanceSeconds(dist);
		
		return distance;
	}
	
	public Double rad(Double degree){
		return Math.toRadians(degree);
	}
	
	public Double grados(Double rad){
		
		return Math.toDegrees(rad);
	}
	
	
	/**
	 * width y height son el ancho y el alto de la imagen en pixeles.
	 * x e y son las coordenadas que queremos transformar
	 */
	@Override
	public DecimalCoordinate pixelToCoordinatesConverter(Imagen imagen, int width, int height, double x, double y) {
		SexagesimalCoordinate sc;
		DecimalCoordinate dc;
		
		double ancho = imagen.getAncho();
		double alto = imagen.getTarea().getAlto();
		double ar = Double.parseDouble(imagen.getAscensionRecta());
		double dec = Double.parseDouble(imagen.getDeclinacion());
		
		sc = new SexagesimalCoordinate(0,0,0,0,alto,0);
		dc = CoordinateConverter.sexagesimalToDecimalConverter(sc);
		double incX = width/2.0 - x;
		double incY = y - height/2.0;
		
		double dec1 = dec + incY*(dc.getDec()/height);
		double anchoAux = ancho/(15.0*Math.cos(Math.toRadians(dec1)));
		sc = new SexagesimalCoordinate(0,anchoAux,0,0,0,0);
		dc = CoordinateConverter.sexagesimalToDecimalConverter(sc);
		double ar1 = ar + incX*(dc.getAr()/width);
		
		if (dec1 > 90) { // Cruzo el polo norte
			dec1 = 180 - dec1;
			ar1 = ar1 - 180;
		}
		if (dec1 < 0) { // Cruzo el polo sur
			dec1 = Math.abs(dec1);
			ar1 = ar1 - 180;
		}
		if (ar1 > 360) ar1 -= 360;
		if (ar1 < 0) ar1 += 360;
		
		return new DecimalCoordinate(ar1, dec1);
	} 


	@Override
	public double[][] transform(double[][] image, double scale,double rotation, double verticalTranslation,double horizontalTranslation) {
		
		
		
		
		
		return null;
	}
	
	public double[][] pixelTransform(double[][] image, double scale,double rotation, double verticalTranslation,double horizontalTranslation){
		return null;
	}

}
