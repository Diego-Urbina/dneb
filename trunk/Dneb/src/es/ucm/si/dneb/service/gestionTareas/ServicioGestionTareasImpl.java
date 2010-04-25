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
import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.FormatoFichero;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.domain.Tarea;
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
				"Imagen:dameNumeroDescargasDeUnaTarea")
				.setParameter(1, tarea).getSingleResult();
		
		Long pendientes = (Long) manager.createNamedQuery(
				"Imagen:dameNumeroDescargasPendientesDeUnaTarea")
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
		List<Survey> resultList = manager
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
		
		gestorDescargas.eleminarHilo(tareaId);
		
		Tarea tarea = manager.find(Tarea.class, tareaId);

		if(tarea==null){
			throw new ServicioGestionTareasException("La tarea no existe");
		}
		
		/*if (tarea.isActiva()) {
			gestorDescargas.interrumpirHilo(tarea.getIdTarea());
			gestorDescargas.eleminarHilo(tarea.getIdTarea());
		}*/
		
		List<Imagen> imagens = tarea.getDescargas();
		
		for(Imagen imagen : imagens){
			manager.remove(imagen);
		}
			
		manager.remove(tarea);

	}
	

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Imagen getImagenByPath(String path) {
		
		String pathm=path;
		
		
		pathm=pathm.replace("\\","%");
		pathm=pathm.replace("\\\\","%");
		pathm=pathm.replace("/","%");
		pathm=pathm.replace("//","%");
		
		
		List<Imagen> imagenes=manager.createQuery("select i from Imagen i where i.rutaFichero like ?").setParameter(1, pathm).getResultList();
		return imagenes.get(0);
		
	}
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Imagen> getDescargasTarea(Long tareaId) {
		Tarea tarea = manager.find(Tarea.class, tareaId);
			/*
		if(tarea==null){
			throw new ServicioGestionTareasException("La tarea no existe");
		}
		List<Imagen> imagens = tarea.getDescargas();
		
		return imagens;*/
		
		return manager.createNamedQuery("Imagen:dameImagenesDeUnaTarea").setParameter(1, tarea).getResultList();
		
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


	@Transactional(propagation = Propagation.REQUIRED)
	public void createSingleDownloadTask(String alias, String descripcion,
			Double alto, Double ancho, FormatoFichero formatoFichero,
			String ruta, List<Survey> surveys,Double ar, Double dec,boolean iniciarDescarga) {
		
		
		Tarea tarea = new Tarea();
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
		
		
		
		
		List<Imagen> imagens = new ArrayList<Imagen>();
		
		for(Survey survey : surveys){
			
			
			Imagen imagen = new Imagen();
			
			
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
	public List<Tarea> getTareasFinalizadas() {
		return (List<Tarea>) manager.createNamedQuery("Tarea:DameTareasFinalizadas")
		.getResultList();
	}


	@Transactional(propagation = Propagation.SUPPORTS)
	public Tarea getTareaById(long id) {
		
		return manager.find(Tarea.class,id);
	}


}
