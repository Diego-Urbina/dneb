package es.ucm.si.dneb.service.gestionHilos;

import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;

@Service("gestorHilos")
public class GestorHilos{
	
	private HashMap <Long,Hilo> hilos;
	ExecutorService service = Executors.newFixedThreadPool(3);
	
	public GestorHilos(){
		hilos=new HashMap<Long,Hilo>();
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void anadirHilo(Tarea tarea) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
		"applicationContext.xml");
		Hilo hilo = (Hilo) ctx.getBean("hilo");
		hilo.setTarea(tarea);
		hilo.setIdTarea(tarea.getIdTarea());
		hilos.put(tarea.getIdTarea(), hilo);
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void crearHilosParaTodasLasTareas(List<Tarea> tareas) {
		/**TODO OJO QUE SE ESTÁ HACIENDO INYECCION**/
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
		"applicationContext.xml");
		for(Tarea tarea : tareas){
			Hilo hilo = (Hilo) ctx.getBean("hilo");
			hilo.setTarea(tarea);
			hilo.setIdTarea(tarea.getIdTarea());
			hilos.put(tarea.getIdTarea(),hilo);
		}
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void eleminarHilo(Long id) {
		Hilo hilo=hilos.get(id);
		/**TODO**/
		hilo.interrupt();
		hilos.remove(id);
		
	}

	
	public HashMap<Long, Hilo> getHilos() {
		return hilos;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void iniciarHilo(Long idHilo) {
		Hilo hilo=hilos.get(idHilo);
		service.execute(hilo);
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void interrumpirHilo(Long idHilo) {
		Hilo hilo=hilos.get(idHilo);
		hilo.interrupt();
		hilos.remove(idHilo);
		
	}


	public void setHilos(HashMap<Long, Hilo> hilos) {
		this.hilos = hilos;
		
	}
	


}
