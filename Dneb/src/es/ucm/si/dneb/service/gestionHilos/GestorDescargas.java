package es.ucm.si.dneb.service.gestionHilos;

import java.util.HashMap;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;

@Service("gestorDescargas")
public class GestorDescargas implements GestorHilos<Tarea> {

	private static final Log LOG = LogFactory.getLog(GestorDescargas.class);

	private HashMap<Long, Hilo> hilos;

	public GestorDescargas() {
		hilos = new HashMap<Long, Hilo>();
	}

	public void anadirHilo(Tarea tarea) {

		LOG.debug("AÑADIR HILO:" + tarea.getIdTarea());

		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
		EjecutorTarea gestor = (EjecutorTarea) ctx.getBean("ejecutorDescarga");
		gestor.setTarea(tarea);
		gestor.setIdTarea(tarea.getIdTarea());

		Hilo hilo = new Hilo(gestor);
		hilos.put(tarea.getIdTarea(), hilo);

		LOG.info("HILO CREADO:" + tarea.getIdTarea());

	}

	public void crearHilosParaTodasLasTareas(List<Tarea> tareas) {

		LOG.debug("LLAMADA A CREAR HILOS PARA TODAS LAS DESCARGAS");
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
		for (Tarea tarea : tareas) {
			EjecutorTarea gestor = (EjecutorTarea) ctx
					.getBean("ejecutorDescarga");
			gestor.setTarea(tarea);
			gestor.setIdTarea(tarea.getIdTarea());

			Hilo hilo = new Hilo(gestor);
			hilos.put(tarea.getIdTarea(), hilo);
		}

		LOG.info("TAREAS CREADAS PARA TODAS LAS DESCARGAS");

	}

	@Override
	public void eleminarHilo(Long id) {
		Hilo hilo = hilos.get(id);

		if (hilo.isInterrupted() == false) {

			LOG.info("GESTOR" + hilo.continuar());

			hilo.parar();

			LOG.info("GESTOR" + hilo.continuar());

			hilo.interrupt();
		}

		hilos.remove(id);

		LOG.info("GESTOR" + "ELIMINADO EL HILO:" + id);

	}

	@Override
	public void iniciarHilo(Long idHilo) {
		Hilo hilo = hilos.get(idHilo);

		hilo.start();

		LOG.info("GESTOR INICIADO EL HILO:" + idHilo);

	}

	@Override
	public void interrumpirHilo(Long idHilo) {
		Hilo hilo = hilos.get(idHilo);

		Tarea tarea = hilo.getEjecutor().getTarea();

		this.eleminarHilo(idHilo);

		this.anadirHilo(tarea);

		LOG.info("GESTOR   nombreGestor INTERRUMPIDO EL HILO:" + idHilo);

	}

	public HashMap<Long, Hilo> getHilos() {
		return hilos;
	}

	public void setHilos(HashMap<Long, Hilo> hilos) {
		this.hilos = hilos;
	}

	public static Log getLog() {
		return LOG;
	}
	
	

}
