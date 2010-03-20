package es.ucm.si.dneb.test;

import org.junit.Test;

import es.ucm.si.dneb.service.math.CoordinateConverter;
import es.ucm.si.dneb.service.math.DecimalCoordinate;
import es.ucm.si.dneb.service.math.SexagesimalCoordinate;

public class TestConversorUnidades {
	
	@Test
	public void testConvSexToDec(){
		SexagesimalCoordinate sc = new SexagesimalCoordinate(21, 28, 19.85, 36, 40, 6.7);
		System.out.println(sc.toString());
		DecimalCoordinate dc = CoordinateConverter.sexagesimalToDecimalConverter(sc);
		System.out.println(dc.toString());
		
		sc=CoordinateConverter.decimalToSexagesimalConverter(dc);
		
		System.out.println(sc.toString());
		
	}

}
