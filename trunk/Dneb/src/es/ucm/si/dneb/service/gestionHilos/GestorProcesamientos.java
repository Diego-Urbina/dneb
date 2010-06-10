package es.ucm.si.dneb.service.gestionHilos;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import es.ucm.si.dneb.domain.TaskProsec;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;

@Service("gestorProcesamientos")
public class GestorProcesamientos implements GestorHilos<TaskProsec>{
	
	@PersistenceContext
	EntityManager manager;
	
	private Map<Long,Hilo> hilos;
	
	private static final Log LOG = LogFactory
	.getLog(GestorProcesamientos.class);
	
	public GestorProcesamientos(){
		
		hilos = new HashMap<Long, Hilo>();
		
	}

	@Override
	public void anadirHilo(TaskProsec tareaProc) {
		
		LOG.debug("AÑADIR HILO:" + tareaProc.getIdProcesamiento());

		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
		EjecutorTarea gestor = (EjecutorTarea) ctx.getBean("ejecutorProcesamiento");
		
		gestor.setCore(tareaProc);
		gestor.setId(tareaProc.getIdProcesamiento());

		Hilo hilo = new Hilo(gestor);
		hilos.put(tareaProc.getIdProcesamiento(), hilo);

		//LOG.info("HILO CREADO:" + tarea.getIdTarea());
		
	}

	@Override
	public void eliminarHilo(Long id) {
		
		Hilo hilo = hilos.get(id);
		
		if (hilo == null) return;

		if (hilo.isInterrupted() == false) {

			LOG.info("GESTOR CÁCULO POSICIÓN" + hilo.continuar());

			hilo.parar();

			LOG.info("GESTOR CÁCULO POSICIÓN" + hilo.continuar());

			hilo.interrupt();
		}

		hilos.remove(id);

		LOG.info("GESTOR CÁCULO POSICIÓN : ELIMINADO EL HILO:" + id);
		
	}

	@Override
	public void iniciarHilo(Long idHilo) {
		
		Hilo hilo = hilos.get(idHilo);
		
		if(hilo==null){
			
			TaskProsec tp=manager.find(TaskProsec.class, idHilo);
			this.anadirHilo(tp);
			hilo = hilos.get(idHilo);
			
		}

		hilo.start();

		LOG.info("GESTOR INICIADO EL HILO:" + idHilo);
		
	}

	@Override
	public void interrumpirHilo(Long idHilo) {
		
		Hilo hilo = hilos.get(idHilo);

		TaskProsec tarProc = (TaskProsec) hilo.getEjecutor().getCore();

		this.eliminarHilo(idHilo);

		this.anadirHilo(tarProc);

		LOG.info("GESTOR   nombreGestor INTERRUMPIDO EL HILO:" + idHilo);
		
	}

	public Map<Long, Hilo> getHilos() {
		return hilos;
	}

	public void setHilos(Map<Long, Hilo> hilos) {
		this.hilos = hilos;
	}

	public static Log getLog() {
		return LOG;
	}

	
	
	
	

	

}
