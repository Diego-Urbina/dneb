package es.ucm.si.dneb.service.gestionHilos;

import java.util.HashMap;


import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;

@Service("gestorDescargas")
public class GestorDescargas{
	

	
	private static final Log LOG = LogFactory
	.getLog(GestorDescargas.class);

	private HashMap <Long,Hilo> hilos;
	//ExecutorService service = Executors.newFixedThreadPool(60);
	
	public GestorDescargas(){
		LOG.debug("GESTOR DESCARGAS CREADO CON CERO HILOS");
		hilos=new HashMap<Long,Hilo>();
	}
	
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

	
	public void eleminarHilo(Long id) {
		
		
		Hilo hilo=hilos.get(id);
		
		if(hilo.isInterrupted()==false){
			
			
			LOG.info(hilo.continuar());
			hilo.parar();
			LOG.info(hilo.continuar());
			hilo.interrupt();
		}
		
		
		hilos.remove(id);
		
		LOG.info("ELIMINADO EL HILO:" +id);
	}

	
	public HashMap<Long, Hilo> getHilos() {
		return hilos;
	}

	
	public void iniciarHilo(Long idHilo) {
		Hilo hilo=hilos.get(idHilo);
		
		//service.execute(hilo);
		hilo.start();
		
		LOG.info("INICIADO EL HILO:" +idHilo);
		
	}

	public void interrumpirHilo(Long idHilo) {
		Hilo hilo=hilos.get(idHilo);
		

			/**TODO**/
			LOG.info(hilo.continuar());
			hilo.parar();
			LOG.info("*************************"+hilo.continuar());
			//hilo.interrupt();

		LOG.info("INTERRUMPIDO EL HILO:" + idHilo);
	}


	public void setHilos(HashMap<Long, Hilo> hilos) {
		this.hilos = hilos;
		
	}
	


}
