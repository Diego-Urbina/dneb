package es.ucm.si.dneb.service.gestionProcesamientos;

import java.util.List;

import es.ucm.si.dneb.domain.TareaProcesamiento;
import es.ucm.si.dneb.domain.TipoParametro;
import es.ucm.si.dneb.domain.TipoProcesamiento;

public interface ServicioGestionProcesamientos {
	
	public List<TareaProcesamiento> getProcesamientosDobles();
	
	public List<TareaProcesamiento> getProcesamientosDistancia();
	
	public List<TareaProcesamiento> getProcesamientos();
	
	public Integer  getPorcentajeCompletado(Long idProcesamiento);
	
	public void eliminarProcesamiento(Long idProcesamiento);
	
	public void pararProcesamiento(Long idProcesamiento);
	
	public void reanudarProcesamiento(Long idProcesamiento);
	
	public List<String> getTiposProcesamiento();
	
	public void crearProcesamiento(TareaProcesamiento procesamiento );
	
	public TipoProcesamiento getTipoProcesamientoByAlias(String alias);
	
	public TipoParametro getTipoParametroById(Long idParametro);

}
