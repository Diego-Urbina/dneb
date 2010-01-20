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
public class GestorDescargas extends GestorHilos{
	
	private static final Log LOG = LogFactory
	.getLog(GestorDescargas.class);
	
	public GestorDescargas() {
		super();
		super.setNombreGestor("GESTOR DESCARGAS");
	}


	public void anadirHilo(Tarea tarea) {
		
		LOG.debug("AÑADIR HILO:"+tarea.getIdTarea());
		
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
		EjecutorTarea gestor=(EjecutorTarea)ctx.getBean("ejecutorDescarga");
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
			EjecutorTarea gestor=(EjecutorTarea)ctx.getBean("ejecutorDescarga");
			gestor.setTarea(tarea);
			gestor.setIdTarea(tarea.getIdTarea());
			
			Hilo hilo = new Hilo(gestor);
			hilos.put(tarea.getIdTarea(), hilo);
		}
		
		LOG.info("TAREAS CREADAS PARA TODAS LAS DESCARGAS");
		
	}

	
}
