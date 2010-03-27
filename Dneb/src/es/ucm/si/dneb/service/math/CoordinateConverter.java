package es.ucm.si.dneb.service.math;

public class CoordinateConverter {
	
	public static DecimalCoordinate sexagesimalToDecimalConverter(SexagesimalCoordinate sc){
		
		DecimalCoordinate dc= new DecimalCoordinate();
		/*DecDecimal = IIF(DecDegs<0,-1,+1)*(abs(DecDegs)+DecMins/60+DecSecs/3600)
		y en RA
		RA= ((RAHours*3600+RAMins*60+RASecs)/240)*/

		dc.setAr( (double)( (sc.getArh()*3600) + (sc.getArmin()*60) + (sc.getArsec() ) )/240);
		
		/*SI(D29>0;D29+E29/60+F29/3600; D29-E29/60-F29/3600)*/
		//dc.setDec(((double)(sc.getDech()<0 ? -1 : 1 ) * (double)Math.abs(sc.getDech()))+( (double)sc.getDecmin()/60)+((double)sc.getDecsec()/3600) );
		dc.setDec(sc.getDech()>0 ? sc.getDech()+((double)sc.getDecmin()/60)+ ((double)sc.getDecsec()/3600): sc.getDech()-((double)sc.getDecmin()/60)-((double)sc.getDecsec()/3600));
		
		return dc;
	}
	
	public static SexagesimalCoordinate decimalToSexagesimalConverter(DecimalCoordinate dc){
		
		SexagesimalCoordinate sc = new SexagesimalCoordinate();
		
		sc.setArh((int) ((dc.getAr()*24)/360));
		sc.setArmin((int)((dc.getAr()-(15*sc.getArh()))*4));
		sc.setArsec((dc.getAr() - ((double)((sc.getArh()*60) +(sc.getArmin()))/4))*240 );
		
		
		sc.setDech((dc.getDec()<0 ? (int) (dc.getDec()) : (int) (dc.getDec())));
		sc.setDecmin((int)(Math.abs((((double)dc.getDec()-(double)sc.getDech())))*60));
		sc.setDecsec( (dc.getDec()*3600)>((sc.getDech()*3600)-(sc.getDecmin()*60))? ((dc.getDec()*3600)-(sc.getDech()*3600)-(sc.getDecmin()*60)):( (-dc.getDec()*3600)+(sc.getDech()*3600)-(sc.getDecmin()*60) ));
		
		return sc;
	}

}
