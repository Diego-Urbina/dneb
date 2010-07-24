package es.ucm.si.dneb.service.gestionHilos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Hilo extends Interrumpible {
	
	private static final Log LOG = LogFactory
	.getLog(Hilo.class);
	
	
	
	private EjecutorTarea ejecutor;
	
	public Hilo(){
		
		
		
		ejecutor=null;
		
	}
	
	public Hilo(EjecutorTarea ejecutor){
		
	
		
		this.ejecutor=ejecutor;
	}


	
	public void run() {

		ejecutor.ejecutar(this);
		

	}


	public void setEjecutor(EjecutorTarea ejecutor) {
		this.ejecutor = ejecutor;
	}


	public EjecutorTarea getEjecutor() {
		return ejecutor;
	}
	
	


}
