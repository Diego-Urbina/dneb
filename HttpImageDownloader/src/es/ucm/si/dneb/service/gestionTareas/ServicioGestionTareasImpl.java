package es.ucm.si.dneb.service.gestionTareas;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.*;
import es.ucm.si.dneb.domain.Descarga;
import es.ucm.si.dneb.domain.FormatoFichero;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.gestionHilos.GestorDescargas;

@Service("servicioGestionTareas")
public class ServicioGestionTareasImpl implements ServicioGestionTareas {

	private static final Log LOG = LogFactory
			.getLog(ServicioGestionTareasImpl.class);

	@PersistenceContext
	EntityManager manager;
	
	@Resource
	private GestorDescargas gestorDescargas;


	/**Anade las tareas que estén en case de datos pendientes de ser terminadas al gestor de descargas
	 * para que puedan ser gestionadas
	 * **/
	@Transactional(propagation = Propagation.REQUIRED)
	public void anadirTareasAlGestor(){
		/**Llama al gestor de descargas para que genere un hilo por cada una de las tareas 
		 * y que así puedan ser ejecutadas a peticiçon del usuario en cualquier momento**/
		gestorDescargas.crearHilosParaTodasLasTareas(getTareas());
	}
	
	
	/**Metodo encargado de reanudar una tarea existente. Se localiza ésta tarea mediante su id 
	 * único.
	 * 
	 * No se puede reanudar una tarea que ya está activa.
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void reanudarTarea(long tareaId) {

		Tarea tarea = manager.find(Tarea.class, tareaId);

		if (tarea.isActiva()) {
			throw new ServicioGestionTareasException(
					"ReanudarTarea: La tareas ya está activa");
		}
		/**Se marca la tarea como activa**/
		tarea.setActiva(true);

		GregorianCalendar calendar = new GregorianCalendar();
		Date fechaActual = calendar.getTime();
		tarea.setFechaUltimaActualizacion(fechaActual);
		
		manager.merge(tarea);
		
		gestorDescargas.iniciarHilo(tarea.getIdTarea());

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public Integer obtenerPorcentajeCompletado(long tareaId) {

		Tarea tarea = manager.find(Tarea.class, tareaId);
		Long total =(Long) manager.createNamedQuery(
				"Descarga:dameNumeroDescargasDeUnaTarea")
				.setParameter(1, tarea).getSingleResult();
		
		Long pendientes = (Long) manager.createNamedQuery(
				"Descarga:dameNumeroDescargasPendientesDeUnaTarea")
				.setParameter(1, tarea).getSingleResult();
		
		double calParcial = (100 - ((pendientes*100) / (total)));
		
		LOG.info("EL PORCENTAJE de: " + tareaId +" "+calParcial  );
		
		return new Integer((int) calParcial);
	}
	/**Detiene una tarea activa**/
	@Transactional(propagation = Propagation.REQUIRED)
	public void pararTarea(long tareaId) {

		Tarea tarea = manager.find(Tarea.class, tareaId);

		if (!tarea.isActiva()) {
			throw new ServicioGestionTareasException(
					"PararTarea: La tareas ya está parada");
		}
		tarea.setActiva(false);
		
		manager.merge(tarea);

		gestorDescargas.interrumpirHilo(tareaId);

	}



	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Survey> getAllSurveys() {
		List resultList = manager
				.createNamedQuery("Survey:dameTodosLosSurveys").getResultList();
		return resultList;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Tarea> getTareas() {
		return (List<Tarea>) manager.createNamedQuery("Tarea:DameTodasTareas")
				.getResultList();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<FormatoFichero> getFormatosFichero() {
		return manager.createNamedQuery("FormatoFichero:dameTodosFormatos")
				.getResultList();
	}
	
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void iniciarTarea(long tareaId) {
		
		reanudarTarea(tareaId);
		
	}
	
	public GestorDescargas getGestorHilos() {
		return gestorDescargas;
	}
	
	public void setGestorHilos(GestorDescargas gestorDescargas) {
		this.gestorDescargas = gestorDescargas;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void eliminarTarea(long tareaId) {
		
		Tarea tarea = manager.find(Tarea.class, tareaId);

		if(tarea==null){
			throw new ServicioGestionTareasException("La tarea no existe");
		}
		
		if (tarea.isActiva()) {
			gestorDescargas.interrumpirHilo(tarea.getIdTarea());
			gestorDescargas.eleminarHilo(tarea.getIdTarea());
		}
		
		List<Descarga> descargas = tarea.getDescargas();
		
		for(Descarga descarga : descargas){
			manager.remove(descarga);
		}
			
		manager.remove(tarea);

	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Descarga> getDescargasTarea(Long tareaId) {
		Tarea tarea = manager.find(Tarea.class, tareaId);

		if(tarea==null){
			throw new ServicioGestionTareasException("La tarea no existe");
		}
		
		
		List<Descarga> descargas = tarea.getDescargas();
		return descargas;
		
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Tarea> getTareasPendientes() {
		return (List<Tarea>) manager.createNamedQuery("Tarea:DameTodasTareasPendientes")
		.getResultList();
	}


	public GestorDescargas getGestorDescargas() {
		return gestorDescargas;
	}


	public void setGestorDescargas(GestorDescargas gestorDescargas) {
		this.gestorDescargas = gestorDescargas;
	}
	
	
	
	
}
