package es.ucm.si.dneb.service.gestionHilos;

import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.creacionTareas.ServicioCreacionTareas;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;

@Service("gestorHilos")
public class GestorDescargas{
	
	private static final Log LOG = LogFactory
	.getLog(GestorDescargas.class);

	private HashMap <Long,Hilo> hilos;
	ExecutorService service = Executors.newFixedThreadPool(6);
	
	public GestorDescargas(){
		LOG.debug("GESTOR DESCARGAS CREADO CON CERO HILOS");
		hilos=new HashMap<Long,Hilo>();
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void anadirHilo(Tarea tarea) {
		
		LOG.debug("AÑADIR HILO:"+tarea.getIdTarea());
		
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
		EjecutorTarea gestor=(EjecutorTarea)ctx.getBean("ejecutorTarea");
		gestor.setTarea(tarea);
		gestor.setIdTarea(tarea.getIdTarea());
		
		Hilo hilo = new Hilo(gestor);
		hilos.put(tarea.getIdTarea(), hilo);
		
		LOG.info("HILO CREADO:"+ tarea.getIdTarea());
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void crearHilosParaTodasLasTareas(List<Tarea> tareas) {
		
		LOG.debug("LLAMADA A CREAR HILOS PARA TODAS LAS DESCARGAS");
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
		for(Tarea tarea : tareas){
			EjecutorTarea gestor=(EjecutorTarea)ctx.getBean("ejecutorTarea");
			gestor.setTarea(tarea);
			gestor.setIdTarea(tarea.getIdTarea());
			
			Hilo hilo = new Hilo(gestor);
			hilos.put(tarea.getIdTarea(), hilo);
		}
		
		LOG.info("TAREAS CREADAS PARA TODAS LAS DESCARGAS");
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void eleminarHilo(Long id) {
		
		
		Hilo hilo=hilos.get(id);
		
		if(hilo.isInterrupted()==false){
			hilo.interrupt();
		}
		hilos.remove(id);
		
		LOG.info("ELIMINADO EL HILO:" +id);
	}

	
	public HashMap<Long, Hilo> getHilos() {
		return hilos;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void iniciarHilo(Long idHilo) {
		Hilo hilo=hilos.get(idHilo);
		service.execute(hilo);
		
		LOG.info("INICIADO EL HILO:" +idHilo);
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void interrumpirHilo(Long idHilo) {
		Hilo hilo=hilos.get(idHilo);
		
		if(hilo.isInterrupted()==false){
			hilo.interrupt();
		}
		LOG.info("INTERRUMPIDO EL HILO:" + idHilo);
	}


	public void setHilos(HashMap<Long, Hilo> hilos) {
		this.hilos = hilos;
		
	}
	


}
