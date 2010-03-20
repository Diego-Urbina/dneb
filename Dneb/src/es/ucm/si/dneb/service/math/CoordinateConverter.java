package es.ucm.si.dneb.service.math;

public class CoordinateConverter {
	
	public static DecimalCoordinate sexagesimalToDecimalConverter(SexagesimalCoordinate sc){
		
		DecimalCoordinate dc= new DecimalCoordinate();
		/*DecDecimal = IIF(DecDegs<0,-1,+1)*(abs(DecDegs)+DecMins/60+DecSecs/3600)
		y en RA
		RA= ((RAHours*3600+RAMins*60+RASecs)/240)*/

		dc.setAr( (double)( (sc.getArh()*3600) + (sc.getArmin()*60) + (sc.getArsec() ) )/240);
		dc.setDec((sc.getDech()<0 ? -1 : 1 ) * Math.abs(sc.getDech())+( (double)sc.getDecmin()/60)+((double)sc.getDecsec()/3600) );
		
		return dc;
	}
	
	public static SexagesimalCoordinate decimalToSexagesimalConverter(DecimalCoordinate dc){
		
		return null;
	}

}
