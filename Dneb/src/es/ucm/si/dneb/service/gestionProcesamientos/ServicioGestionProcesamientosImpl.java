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

import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.ProcesamientoImagen;
import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.domain.TareaProcesamiento;
import es.ucm.si.dneb.domain.TipoProcesamiento;
import es.ucm.si.dneb.service.calculoPosicion.ServiceCalculoPosicionException;
import es.ucm.si.dneb.service.creacionTareas.ServicioCreacionTareas;
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
	public Long getPorcentajeCompletado(Long idProcesamiento) {
		
		TareaProcesamiento tareaProcesamiento = manager.find(TareaProcesamiento.class,idProcesamiento);
		
		List <Long> terminados = manager.createNamedQuery("TareaProcesamiento.getNumeroProcesamientoCompletados").setParameter(1, tareaProcesamiento).getResultList();
		if(terminados.size()!=1){
			LOG.error("Poblema ejecuntado query:TareaProcesamiento.getNumeroProcesamientoCompletados, se esperaba un único resultado y se obtuvieron:"+terminados.size());
			throw new ServiceCalculoPosicionException("Poblema ejecuntado query:TareaProcesamiento.getNumeroProcesamientoCompletados, se esperaba un único resultado y se obtuvieron:"+terminados.size());
		}
		
		List <Long> total = manager.createNamedQuery("TareaProcesamiento.getNumeroProcesamientos").setParameter(1, tareaProcesamiento).getResultList();
		if(total.size()!=1){
			LOG.error("Poblema ejecuntado query:TareaProcesamiento.getNumeroProcesamientoCompletados, se esperaba un único resultado y se obtuvieron:"+total.size());
			throw new ServiceCalculoPosicionException("Poblema ejecuntado query:TareaProcesamiento.getNumeroProcesamientoCompletados, se esperaba un único resultado y se obtuvieron:"+terminados.size());
		}
		
		long completado= terminados.get(0)/total.get(0);
		
		LOG.debug("Procesamiento con id:"+idProcesamiento+" completado al:"+completado);
		
		return completado*100;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TareaProcesamiento> getProcesamientosDistancia() {
		
		TipoProcesamiento tipoProcesamiento = manager.find(TipoProcesamiento.class, 2L);
		
		return manager.createNamedQuery("TareaProcesamiento.getProcesamientosByType").setParameter(1, tipoProcesamiento).getResultList();
		
		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TareaProcesamiento> getProcesamientosDobles() {
		
		TipoProcesamiento tipoProcesamiento = manager.find(TipoProcesamiento.class, 1L);
		
		return manager.createNamedQuery("TareaProcesamiento.getProcesamientosByType").setParameter(1, tipoProcesamiento).getResultList();
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void eliminarProcesamiento(Long idProcesamiento) {
		
		gestorProcesamientos.eleminarHilo(idProcesamiento);
		
		TareaProcesamiento proc = manager.find(TareaProcesamiento.class, idProcesamiento);

		if(proc==null){
			throw new ServicioGestionTareasException("La tarea no existe");
		}
		
		List<ProcesamientoImagen> procsImags=proc.getProcesamientoImagenes();
		
		for(ProcesamientoImagen imagen : procsImags){
			manager.remove(imagen);
		}
			
		manager.remove(proc);
	
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void pararProcesamiento(Long idProcesamiento) {
		
		TareaProcesamiento tarea = manager.find(TareaProcesamiento.class, idProcesamiento);

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
		
		TareaProcesamiento tareaProc= manager.find(TareaProcesamiento.class, idProcesamiento);
		
		if(tareaProc.isActiva()){
			throw new ServicioGestionProcesamientosException(
			"ReanudarTarea: El procesamiento ya está activa");
		}
		/**Se marca el procesamiento como activo**/
		tareaProc.setActiva(true);

		GregorianCalendar calendar = new GregorianCalendar();
		Date fechaActual = calendar.getTime();
		tareaProc.setFechaUltimaAct(fechaActual);
		
		manager.merge(tareaProc);
		
		gestorProcesamientos.iniciarHilo(tareaProc.getIdTarea());

		
	}

	public void setGestorProcesamientos(GestorProcesamientos gestorProcesamientos) {
		this.gestorProcesamientos = gestorProcesamientos;
	}

	public GestorProcesamientos getGestorProcesamientos() {
		return gestorProcesamientos;
	}
	
	
	
	

}
