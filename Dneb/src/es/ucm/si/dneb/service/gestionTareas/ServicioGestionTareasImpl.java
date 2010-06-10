package es.ucm.si.dneb.service.gestionTareas;


import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.*;
import es.ucm.si.dneb.domain.Image;
import es.ucm.si.dneb.domain.FileFormat;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.domain.Task;
import es.ucm.si.dneb.service.gestionHilos.GestorDescargas;
import es.ucm.si.dneb.util.Util;

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

		Task tarea = manager.find(Task.class, tareaId);

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
		
		if (!gestorDescargas.existeHilo(tarea.getIdTarea()))
			gestorDescargas.anadirHilo(tarea);
			
		gestorDescargas.iniciarHilo(tarea.getIdTarea());

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public Integer obtenerPorcentajeCompletado(long tareaId) {

		Task tarea = manager.find(Task.class, tareaId);
		Long total =(Long) manager.createNamedQuery(
				"Image:dameNumeroDescargasDeUnaTarea")
				.setParameter(1, tarea).getSingleResult();
		
		Long pendientes = (Long) manager.createNamedQuery(
				"Image:dameNumeroDescargasPendientesDeUnaTarea")
				.setParameter(1, tarea).getSingleResult();
		if (total==0){
			
			return 0;
		}
		
		double calParcial = (100 - ((pendientes*100) / (total)));
		
		//LOG.info("EL PORCENTAJE de: " + tareaId +" "+calParcial  );
		
		return new Integer((int) calParcial);
	}
	/**Detiene una tarea activa**/
	@Transactional(propagation = Propagation.REQUIRED)
	public void pararTarea(long tareaId) {

		Task tarea = manager.find(Task.class, tareaId);

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
		List<Survey> resultList = manager
				.createNamedQuery("Survey:dameTodosLosSurveys").getResultList();
		return resultList;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Task> getTareas() {
		return (List<Task>) manager.createNamedQuery("Task:DameTodasTareas")
				.getResultList();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<FileFormat> getFormatosFichero() {
		return manager.createNamedQuery("FileFormat:dameTodosFormatos")
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
		
		gestorDescargas.eliminarHilo(tareaId);
		
		Task tarea = manager.find(Task.class, tareaId);

		if(tarea==null){
			throw new ServicioGestionTareasException("La tarea no existe");
		}
		
		/*if (tarea.isActiva()) {
			gestorDescargas.interrumpirHilo(tarea.getIdTarea());
			gestorDescargas.eleminarHilo(tarea.getIdTarea());
		}*/
		
		List<Image> imagens = tarea.getDescargas();
		
		for(Image imagen : imagens){
			manager.remove(imagen);
		}
			
		manager.remove(tarea);

	}
	

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Image getImagenByPath(String path) {
		
		String pathm=path;
		
		
		pathm=pathm.replace("\\","%");
		pathm=pathm.replace("\\\\","%");
		pathm=pathm.replace("/","%");
		pathm=pathm.replace("//","%");
		
		
		List<Image> imagenes=manager.createQuery("select i from Image i where i.rutaFichero like ?").setParameter(1, pathm).getResultList();
		return imagenes.get(0);
		
	}
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Image> getDescargasTarea(Long tareaId) {
		Task tarea = manager.find(Task.class, tareaId);
			/*
		if(tarea==null){
			throw new ServicioGestionTareasException("La tarea no existe");
		}
		List<Image> imagens = tarea.getDescargas();
		
		return imagens;*/
		
		return manager.createNamedQuery("Image:dameImagenesDeUnaTarea").setParameter(1, tarea).getResultList();
		
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Task> getTareasPendientes() {
		return (List<Task>) manager.createNamedQuery("Task:DameTodasTareasPendientes")
		.getResultList();
	}


	public GestorDescargas getGestorDescargas() {
		return gestorDescargas;
	}


	public void setGestorDescargas(GestorDescargas gestorDescargas) {
		this.gestorDescargas = gestorDescargas;
	}


	@Transactional(propagation = Propagation.REQUIRED)
	public void createSingleDownloadTask(String alias, String descripcion,
			Double alto, Double ancho, FileFormat formatoFichero,
			String ruta, List<Survey> surveys,Double ar, Double dec,boolean iniciarDescarga) {
		
		
		Task tarea = new Task();
		tarea.setActiva(false);

		tarea.setAlias(alias);
		tarea.setDescripcion(descripcion);
		
		tarea.setAlto(alto);
		tarea.setAncho(ancho);
		
		tarea.setArFinal("0");
		tarea.setArInicial("0");
		tarea.setDecFinal("0");
		tarea.setDecInicial("0");
		
		tarea.setFechaCreacion(Util.dameFechaActual());
		tarea.setFechaUltimaActualizacion(Util.dameFechaActual());
		
		tarea.setFinalizada(false);
		
		tarea.setRuta(ruta);
		tarea.setSolpamiento(0);
		tarea.setSurveys(surveys);
		tarea.setFormatoFichero(formatoFichero);
		
		manager.persist(tarea);
		
		
		
		
		List<Image> imagens = new ArrayList<Image>();
		
		for(Survey survey : surveys){
			
			
			Image imagen = new Image();
			
			
			/**TODO OJO DEBERÍA DE SACAR UN ANCHO REAL??*/
			imagen.setAncho(ancho);
			
			imagen.setAscensionRecta(ar.toString());
			imagen.setDeclinacion(dec.toString());
			
			imagen.setDescargada(false);
			
			imagen.setTarea(tarea);
			
			imagen.setSurvey(survey);
			
			imagen.setRutaFichero(Util.creaRuta(ruta, survey.getDescripcion(), ar.toString(), dec.toString(), formatoFichero.getAlias()));
			
			imagens.add(imagen);
			
			manager.persist(imagen);
		
		}
		
		tarea.setDescargas(imagens);
		manager.merge(tarea);
		
		
		gestorDescargas.anadirHilo(tarea);
		
		
		if(iniciarDescarga==true){
			
			tarea.setActiva(true);
			
			manager.merge(tarea);
			
			gestorDescargas.iniciarHilo(tarea.getIdTarea());
			
		}
		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Task> getTareasFinalizadas() {
		return (List<Task>) manager.createNamedQuery("Task:DameTareasFinalizadas")
		.getResultList();
	}


	@Transactional(propagation = Propagation.SUPPORTS)
	public Task getTareaById(long id) {
		
		return manager.find(Task.class,id);
	}


	public static Log getLog() {
		return LOG;
	}


}
