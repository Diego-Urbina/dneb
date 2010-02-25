package es.ucm.si.dneb.service.gestionProcesamientos;

import java.util.List;

import es.ucm.si.dneb.domain.ProcTarea;
import es.ucm.si.dneb.domain.TipoParametro;
import es.ucm.si.dneb.domain.TipoProcesamiento;

public interface ServicioGestionProcesamientos {
	
	public List<ProcTarea> getProcesamientosDobles();
	
	public List<ProcTarea> getProcesamientosDistancia();
	
	public List<ProcTarea> getProcesamientos();
	
	public Integer  getPorcentajeCompletado(Long idProcesamiento);
	
	public void eliminarProcesamiento(Long idProcesamiento);
	
	public void pararProcesamiento(Long idProcesamiento);
	
	public void reanudarProcesamiento(Long idProcesamiento);
	
	public List<String> getTiposProcesamiento();
	
	public void crearProcesamiento(ProcTarea procesamiento );
	
	public TipoProcesamiento getTipoProcesamientoByAlias(String alias);
	
	public TipoParametro getTipoParametroById(Long idParametro);

}
