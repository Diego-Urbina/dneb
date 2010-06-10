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

import es.ucm.si.dneb.domain.ParamProcTarea;
import es.ucm.si.dneb.domain.ProcImagen;
import es.ucm.si.dneb.domain.ProcTarea;
import es.ucm.si.dneb.domain.TipoParametro;
import es.ucm.si.dneb.domain.TipoProcesamiento;
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
		
		ProcTarea procTarea = manager.find(ProcTarea.class,idProcesamiento);
		
		List <Long> terminados = manager.createNamedQuery("ProcTarea.getNumeroProcesamientoCompletados").setParameter(1, procTarea).getResultList();
		if(terminados.size()!=1){
			LOG.error("Poblema ejecuntado query:ProcTarea.getNumeroProcesamientoCompletados, se esperaba un único resultado y se obtuvieron:"+terminados.size());
			throw new ServiceCalculoPosicionException("Poblema ejecuntado query:ProcTarea.getNumeroProcesamientoCompletados, se esperaba un único resultado y se obtuvieron:"+terminados.size());
		}
		
		List <Long> total = manager.createNamedQuery("ProcTarea.getNumeroProcesamientos").setParameter(1, procTarea).getResultList();
		if(total.size()!=1){
			LOG.error("Poblema ejecuntado query:ProcTarea.getNumeroProcesamientoCompletados, se esperaba un único resultado y se obtuvieron:"+total.size());
			throw new ServiceCalculoPosicionException("Poblema ejecuntado query:ProcTarea.getNumeroProcesamientoCompletados, se esperaba un único resultado y se obtuvieron:"+terminados.size());
		}
		
		long completado= terminados.get(0)/total.get(0);
		
		LOG.debug("Procesamiento con id:"+idProcesamiento+" completado al:"+completado);
		
		return new Integer((int) completado*100);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ProcTarea> getProcesamientosDistancia() {
		
		TipoProcesamiento tipoProcesamiento = manager.find(TipoProcesamiento.class, 2L);
		
		return manager.createNamedQuery("ProcTarea.getProcesamientosByType").setParameter(1, tipoProcesamiento).getResultList();
		
		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ProcTarea> getProcesamientosDobles() {
		
		TipoProcesamiento tipoProcesamiento = manager.find(TipoProcesamiento.class, 1L);
		
		return manager.createNamedQuery("ProcTarea.getProcesamientosByType").setParameter(1, tipoProcesamiento).getResultList();
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void eliminarProcesamiento(Long idProcesamiento) {
		
		gestorProcesamientos.eliminarHilo(idProcesamiento);
		
		ProcTarea proc = manager.find(ProcTarea.class, idProcesamiento);

		if(proc==null){
			throw new ServicioGestionTareasException("La tarea no existe");
		}
		
		List<ProcImagen> procsImags=proc.getProcesamientoImagenes();
		
		for(ProcImagen imagen : procsImags){
			manager.remove(imagen);
		}
			
		manager.remove(proc);
	
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void pararProcesamiento(Long idProcesamiento) {
		
		ProcTarea tarea = manager.find(ProcTarea.class, idProcesamiento);

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
		
		ProcTarea tareaProc= manager.find(ProcTarea.class, idProcesamiento);
		
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
	public void crearProcesamiento(ProcTarea procesamiento) {
		
		manager.persist(procesamiento);
		
		for(ProcImagen procImg : procesamiento.getProcesamientoImagenes()){
			manager.persist(procImg);
		}
		for(ParamProcTarea paramProcTarea : procesamiento.getParametros()){
			manager.persist(paramProcTarea);
		}
		
		
		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<String> getTiposProcesamiento() {
		return manager.createNamedQuery("TipoProcesamiento:dameTiposProcesamiento")
		.getResultList();
	}

	public void setGestorProcesamientos(GestorProcesamientos gestorProcesamientos) {
		this.gestorProcesamientos = gestorProcesamientos;
	}

	public GestorProcesamientos getGestorProcesamientos() {
		return gestorProcesamientos;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public TipoProcesamiento getTipoProcesamientoByAlias(String alias) {
		
		List<TipoProcesamiento> tipoProcesamientos = manager.createNamedQuery("TipoProcesamiento:dameTipoPorAlias").setParameter(1,alias).getResultList();
		
		return tipoProcesamientos.get(0);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public TipoParametro getTipoParametroById(Long idParametro) {
		return manager.find(TipoParametro.class, idParametro);
	}

	@Override
	public List<ProcTarea> getProcesamientos() {
		return manager.createNamedQuery("ProcTarea.getAllProcesamientos").getResultList();
	}
	
}
