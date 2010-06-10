package es.ucm.si.dneb.service.gestionProcesamientos;

import java.util.List;

import es.ucm.si.dneb.domain.TaskProsec;
import es.ucm.si.dneb.domain.ParamType;
import es.ucm.si.dneb.domain.ProsecType;

public interface ServicioGestionProcesamientos {
	
	public List<TaskProsec> getProcesamientosDobles();
	
	public List<TaskProsec> getProcesamientosDistancia();
	
	public List<TaskProsec> getProcesamientos();
	
	public Integer  getPorcentajeCompletado(Long idProcesamiento);
	
	public void eliminarProcesamiento(Long idProcesamiento);
	
	public void pararProcesamiento(Long idProcesamiento);
	
	public void reanudarProcesamiento(Long idProcesamiento);
	
	public List<String> getTiposProcesamiento();
	
	public void crearProcesamiento(TaskProsec procesamiento );
	
	public ProsecType getTipoProcesamientoByAlias(String alias);
	
	public ParamType getTipoParametroById(Long idParametro);

}
