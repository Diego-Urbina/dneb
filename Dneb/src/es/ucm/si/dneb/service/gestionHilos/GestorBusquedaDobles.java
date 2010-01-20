package es.ucm.si.dneb.service.gestionHilos;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;

@Service("gestorBusquedaDobles")
public class GestorBusquedaDobles extends GestorHilos{
	
	private static final Log LOG = LogFactory
	.getLog(GestorBusquedaDobles.class);
	
	

	public GestorBusquedaDobles() {
		super();
		super.setNombreGestor("GESTOR BÚSQUEDA ESTRELLAS DOBLES");
	}

	@Override
	public void anadirHilo(Tarea tarea) {
		LOG.debug("AÑADIR HILO:"+tarea.getIdTarea());
		
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
		EjecutorTarea gestor=(EjecutorTarea)ctx.getBean("ejecutorBusquedaDobles");
		gestor.setTarea(tarea);
		gestor.setIdTarea(tarea.getIdTarea());
		
		Hilo hilo = new Hilo(gestor);
		hilos.put(tarea.getIdTarea(), hilo);
		
		LOG.info("HILO CREADO:"+ tarea.getIdTarea());
	}

	@Override
	public void crearHilosParaTodasLasTareas(List<Tarea> tareas) {
		
		LOG.debug("LLAMADA A CREAR HILOS PARA TODAS LAS DESCARGAS");
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
		for(Tarea tarea : tareas){
			EjecutorTarea gestor=(EjecutorTarea)ctx.getBean("ejecutorBusquedaDobles");
			gestor.setTarea(tarea);
			gestor.setIdTarea(tarea.getIdTarea());
			
			Hilo hilo = new Hilo(gestor);
			hilos.put(tarea.getIdTarea(), hilo);
		}
		
		LOG.info("TAREAS CREADAS PARA TODAS LAS DESCARGAS");
		
	}
	
}
