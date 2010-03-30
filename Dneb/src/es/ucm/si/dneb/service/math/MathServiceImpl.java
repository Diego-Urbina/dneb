package es.ucm.si.dneb.service.math;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

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
		
		double distSec=ang*3600;
		
		Distance distance = new Distance(dist,ang);
		
		distance.setDistanceSeconds(distSec);
		
		return distance;
	}
	
	public Double rad(Double degree){
		return Math.toRadians(degree);
	}
	
	public Double grados(Double rad){
		
		return Math.toDegrees(rad);
	}

	@Override
	public double[][] transform(double[][] image, double scale,double rotation, double verticalTranslation,double horizontalTranslation) {
		
		
		
		
		
		return null;
	}
	
	public double[][] pixelTransform(double[][] image, double scale,double rotation, double verticalTranslation,double horizontalTranslation){
		return null;
	}

}
