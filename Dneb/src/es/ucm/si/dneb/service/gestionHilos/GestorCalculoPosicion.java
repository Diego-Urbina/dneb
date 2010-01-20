package es.ucm.si.dneb.service.gestionHilos;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;

@Service("gestorCalculoPosicion")
public class GestorCalculoPosicion extends GestorHilos{
	
	
	HashMap <Long,Hilo> hilos;
	
	private static final Log LOG = LogFactory
	.getLog(GestorCalculoPosicion.class);
	
	
	
	public GestorCalculoPosicion() {
		super();
		super.setNombreGestor("GESTOR CALCULO POSICION");
	}

	@Override
	public void anadirHilo(Tarea tarea) {
		LOG.debug("A�ADIR HILO:"+tarea.getIdTarea());
		
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
		EjecutorTarea gestor=(EjecutorTarea)ctx.getBean("ejecutorCalculoPosicion");
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
			EjecutorTarea gestor=(EjecutorTarea)ctx.getBean("ejecutorCalculoPosicion");
			gestor.setTarea(tarea);
			gestor.setIdTarea(tarea.getIdTarea());
			
			Hilo hilo = new Hilo(gestor);
			hilos.put(tarea.getIdTarea(), hilo);
		}
		
		LOG.info("TAREAS CREADAS PARA TODAS LAS DESCARGAS");
		
	}


	

}
