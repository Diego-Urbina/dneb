package es.ucm.si.dneb.service.gestionHilos;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;

public abstract class GestorHilos {

	HashMap <Long,Hilo> hilos;
	
	private String nombreGestor;
	
	private static final Log LOG = LogFactory
	.getLog(GestorHilos.class);

	
	public GestorHilos(){
		LOG.debug("GESTOR" + nombreGestor +"CREADO CON 0 HILOS");
		hilos=new HashMap<Long,Hilo>();
	}
	
	public abstract void anadirHilo(Tarea tarea);

	
	public abstract void crearHilosParaTodasLasTareas(List<Tarea> tareas);

	
	public void eleminarHilo(Long id) {
		
		
		Hilo hilo=hilos.get(id);
		
		if(hilo.isInterrupted()==false){
			
			
			LOG.info("GESTOR" + nombreGestor +hilo.continuar());
			
			hilo.parar();
			
			LOG.info("GESTOR" + nombreGestor +hilo.continuar());
			
			hilo.interrupt();
		}
		
		hilos.remove(id);
		
		LOG.info("GESTOR" + nombreGestor +"ELIMINADO EL HILO:" +id);
	}

	
	public HashMap<Long, Hilo> getHilos() {
		return hilos;
	}

	
	public void iniciarHilo(Long idHilo) {
		Hilo hilo=hilos.get(idHilo);
		
		hilo.start();
		
		LOG.info("GESTOR" + nombreGestor +"INICIADO EL HILO:" +idHilo);
		
	}

	public void interrumpirHilo(Long idHilo) {
		
		Hilo hilo=hilos.get(idHilo);
		
		Tarea tarea= hilo.getEjecutor().getTarea();
		
		this.eleminarHilo(idHilo);
		
		this.anadirHilo(tarea);
		
		LOG.info("GESTOR" + nombreGestor +"INTERRUMPIDO EL HILO:" + idHilo);
	}


	public void setHilos(HashMap<Long, Hilo> hilos) {
		this.hilos = hilos;
		
	}

	public String getNombreGestor() {
		return nombreGestor;
	}

	public void setNombreGestor(String nombreGestor) {
		this.nombreGestor = nombreGestor;
	}
	
	
	
}
