package es.ucm.si.dneb.service.gestionProcesamientos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.TareaProcesamiento;
import es.ucm.si.dneb.domain.TipoProcesamiento;
import es.ucm.si.dneb.service.calculoPosicion.ServiceCalculoPosicionException;
import es.ucm.si.dneb.service.creacionTareas.ServicioCreacionTareas;

@Service("servicioGestionProcesamientos")
public class ServicioGestionProcesamientosImpl implements ServicioGestionProcesamientos{
	
	@PersistenceContext
	EntityManager manager;
	
	private static final Log LOG = LogFactory
	.getLog(ServicioGestionProcesamientosImpl.class);
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public Long getPorcentajeCompletado(Long idProcesamiento) {
		
		TareaProcesamiento tareaProcesamiento = manager.find(TareaProcesamiento.class,idProcesamiento);
		
		List <Long> terminados = manager.createNamedQuery("TareaProcesamiento.getNumeroProcesamientoCompletados").setParameter(1, tareaProcesamiento).getResultList();
		if(terminados.size()!=1){
			LOG.error("Poblema ejecuntado query:TareaProcesamiento.getNumeroProcesamientoCompletados, se esperaba un �nico resultado y se obtuvieron:"+terminados.size());
			throw new ServiceCalculoPosicionException("Poblema ejecuntado query:TareaProcesamiento.getNumeroProcesamientoCompletados, se esperaba un �nico resultado y se obtuvieron:"+terminados.size());
		}
		
		List <Long> total = manager.createNamedQuery("TareaProcesamiento.getNumeroProcesamientos").setParameter(1, tareaProcesamiento).getResultList();
		if(total.size()!=1){
			LOG.error("Poblema ejecuntado query:TareaProcesamiento.getNumeroProcesamientoCompletados, se esperaba un �nico resultado y se obtuvieron:"+total.size());
			throw new ServiceCalculoPosicionException("Poblema ejecuntado query:TareaProcesamiento.getNumeroProcesamientoCompletados, se esperaba un �nico resultado y se obtuvieron:"+terminados.size());
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

	@Override
	public void eliminarProcesamiento(Long idProcesamiento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pararProcesamiento(Long idProcesamiento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reanudarProcesamiento(Long idProcesamiento) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}