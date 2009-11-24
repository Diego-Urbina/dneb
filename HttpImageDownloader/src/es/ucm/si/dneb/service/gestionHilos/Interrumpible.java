package es.ucm.si.dneb.service.gestionHilos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Interrumpible extends Thread {

	

	private static final Log LOG = LogFactory
	.getLog(Interrumpible.class);

	public boolean continuar() {
		boolean parado = this.isInterrupted();
		LOG.info("************************* Parado? : "+parado+" "+this.toString());
		return !parado;
	}
	
	public void parar(){
		LOG.info("************************* Parando: "+this.toString());
		this.interrupt();
	}
}
