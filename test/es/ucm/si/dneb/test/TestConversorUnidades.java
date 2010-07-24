package es.ucm.si.dneb.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import es.ucm.si.dneb.service.math.CoordinateConverter;
import es.ucm.si.dneb.service.math.DecimalCoordinate;
import es.ucm.si.dneb.service.math.SexagesimalCoordinate;

public class TestConversorUnidades {
	
	private static final Log LOG = LogFactory
	.getLog(TestConversorUnidades.class);

	
	@Test
	public void testConvSexToDec(){
		/*0	33	23,05	-20	51	31,8*/
		SexagesimalCoordinate sc = new SexagesimalCoordinate(0, 33, 23.05, -20, 51, 31.8);
		LOG.info(sc.toString());
		DecimalCoordinate dc = CoordinateConverter.sexagesimalToDecimalConverter(sc);
		LOG.info(dc.toString());
		
		sc=CoordinateConverter.decimalToSexagesimalConverter(dc);
		
		LOG.info(sc.toString());
		
	}


	public static Log getLog() {
		return LOG;
	}

}
