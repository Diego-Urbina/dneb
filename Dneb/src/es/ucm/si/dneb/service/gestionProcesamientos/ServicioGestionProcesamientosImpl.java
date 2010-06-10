package es.ucm.si.dneb.service.gestionProcesamientos;

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
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.TaskProsecParam;
import es.ucm.si.dneb.domain.ImageProsec;
import es.ucm.si.dneb.domain.TaskProsec;
import es.ucm.si.dneb.domain.ParamType;
import es.ucm.si.dneb.domain.ProsecType;
import es.ucm.si.dneb.service.calculoPosicion.ServiceCalculoPosicionException;
import es.ucm.si.dneb.service.gestionHilos.GestorProcesamientos;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareasException;

@Service("servicioGestionProcesamientos")
public class ServicioGestionProcesamientosImpl implements ServicioGestionProcesamientos{
	
	@PersistenceContext
	EntityManager manager;
	
	@Resource
	private GestorProcesamientos gestorProcesamientos;
	
	private static final Log LOG = LogFactory
	.getLog(ServicioGestionProcesamientosImpl.class);
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public Integer getPorcentajeCompletado(Long idProcesamiento) {
		
		TaskProsec procTarea = manager.find(TaskProsec.class,idProcesamiento);
		
		List <Long> terminados = manager.createNamedQuery("TaskProsec.getNumeroProcesamientoCompletados").setParameter(1, procTarea).getResultList();
		if(terminados.size()!=1){
			LOG.error("Poblema ejecuntado query:TaskProsec.getNumeroProcesamientoCompletados, se esperaba un único resultado y se obtuvieron:"+terminados.size());
			throw new ServiceCalculoPosicionException("Poblema ejecuntado query:TaskProsec.getNumeroProcesamientoCompletados, se esperaba un único resultado y se obtuvieron:"+terminados.size());
		}
		
		List <Long> total = manager.createNamedQuery("TaskProsec.getNumeroProcesamientos").setParameter(1, procTarea).getResultList();
		if(total.size()!=1){
			LOG.error("Poblema ejecuntado query:TaskProsec.getNumeroProcesamientoCompletados, se esperaba un único resultado y se obtuvieron:"+total.size());
			throw new ServiceCalculoPosicionException("Poblema ejecuntado query:TaskProsec.getNumeroProcesamientoCompletados, se esperaba un único resultado y se obtuvieron:"+terminados.size());
		}
		
		long completado= terminados.get(0)/total.get(0);
		
		LOG.debug("Procesamiento con id:"+idProcesamiento+" completado al:"+completado);
		
		return new Integer((int) completado*100);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TaskProsec> getProcesamientosDistancia() {
		
		ProsecType tipoProcesamiento = manager.find(ProsecType.class, 2L);
		
		return manager.createNamedQuery("TaskProsec.getProcesamientosByType").setParameter(1, tipoProcesamiento).getResultList();
		
		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TaskProsec> getProcesamientosDobles() {
		
		ProsecType tipoProcesamiento = manager.find(ProsecType.class, 1L);
		
		return manager.createNamedQuery("TaskProsec.getProcesamientosByType").setParameter(1, tipoProcesamiento).getResultList();
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void eliminarProcesamiento(Long idProcesamiento) {
		
		gestorProcesamientos.eliminarHilo(idProcesamiento);
		
		TaskProsec proc = manager.find(TaskProsec.class, idProcesamiento);

		if(proc==null){
			throw new ServicioGestionTareasException("La tarea no existe");
		}
		
		List<ImageProsec> procsImags=proc.getProcesamientoImagenes();
		
		for(ImageProsec imagen : procsImags){
			manager.remove(imagen);
		}
			
		manager.remove(proc);
	
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void pararProcesamiento(Long idProcesamiento) {
		
		TaskProsec tarea = manager.find(TaskProsec.class, idProcesamiento);

		if (!tarea.isActiva()) {
			throw new ServicioGestionTareasException(
					"PararTarea: La tareas ya está parada");
		}
		tarea.setActiva(false);
		
		manager.merge(tarea);

		gestorProcesamientos.interrumpirHilo(idProcesamiento);

		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void reanudarProcesamiento(Long idProcesamiento) {
		
		TaskProsec tareaProc= manager.find(TaskProsec.class, idProcesamiento);
		
		if(tareaProc.isActiva()){
			throw new ServicioGestionProcesamientosException(
			"ReanudarTarea: El procesamiento ya está activo");
		}
		/**Se marca el procesamiento como activo**/
		tareaProc.setActiva(true);

		GregorianCalendar calendar = new GregorianCalendar();
		Date fechaActual = calendar.getTime();
		tareaProc.setFechaUltimaAct(fechaActual);
		
		manager.merge(tareaProc);
		
		gestorProcesamientos.iniciarHilo(tareaProc.getIdProcesamiento());

		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void crearProcesamiento(TaskProsec procesamiento) {
		
		manager.persist(procesamiento);
		
		for(ImageProsec procImg : procesamiento.getProcesamientoImagenes()){
			manager.persist(procImg);
		}
		for(TaskProsecParam paramProcTarea : procesamiento.getParametros()){
			manager.persist(paramProcTarea);
		}
		
		
		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<String> getTiposProcesamiento() {
		return manager.createNamedQuery("ProsecType:dameTiposProcesamiento")
		.getResultList();
	}

	public void setGestorProcesamientos(GestorProcesamientos gestorProcesamientos) {
		this.gestorProcesamientos = gestorProcesamientos;
	}

	public GestorProcesamientos getGestorProcesamientos() {
		return gestorProcesamientos;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public ProsecType getTipoProcesamientoByAlias(String alias) {
		
		List<ProsecType> tipoProcesamientos = manager.createNamedQuery("ProsecType:dameTipoPorAlias").setParameter(1,alias).getResultList();
		
		return tipoProcesamientos.get(0);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public ParamType getTipoParametroById(Long idParametro) {
		return manager.find(ParamType.class, idParametro);
	}

	@Override
	public List<TaskProsec> getProcesamientos() {
		return manager.createNamedQuery("TaskProsec.getAllProcesamientos").getResultList();
	}
	
}
