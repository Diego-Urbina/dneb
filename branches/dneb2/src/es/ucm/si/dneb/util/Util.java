package es.ucm.si.dneb.util;

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Util {
	
	private static final  Log LOG = LogFactory.getLog(Util.class);
	
	
	public static String creaRuta(String rutaBase, String survey,
			String ascensionRecta, String declinacion, String formato) {

		String ruta = rutaBase;
		String nombreFichero = null;

		if (ruta != null) {
			if (rutaBase.charAt(rutaBase.length() - 1) != '/') {
				rutaBase = rutaBase + "/";
			}
			nombreFichero = "AR" + ascensionRecta + "DEC" + declinacion
					+ "SURV" + survey + "." + formato;
			ruta = rutaBase + nombreFichero;
			return ruta;

		} else {
			return null;
		}

	}
	
	public static Date dameFechaActual() {
		Date fechaActual;
		GregorianCalendar calendar = new GregorianCalendar();
		fechaActual= calendar.getTime();
		
		LOG.debug("LA FECHA ACTUAL GENERADA"+fechaActual.toString());
		
		return fechaActual;
	}

}
