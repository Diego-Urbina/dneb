package es.ucm.si.dneb.service.gestionProcesamientos;

import java.util.List;

import es.ucm.si.dneb.domain.Parametro;
import es.ucm.si.dneb.domain.TareaProcesamiento;

public interface ServicioGestionProcesamientos {
	
	public List<TareaProcesamiento> getProcesamientosDobles();
	
	public List<TareaProcesamiento> getProcesamientosDistancia();
	
	public Long getPorcentajeCompletado(Long idProcesamiento);
	
	public void eliminarProcesamiento(Long idProcesamiento);
	
	public void pararProcesamiento(Long idProcesamiento);
	
	public void reanudarProcesamiento(Long idProcesamiento);
	
	public List<String> getTiposProcesamiento();
	
	public void crearProcesamiento(TareaProcesamiento procesamiento );
	
	

}
