package es.ucm.si.dneb.service.gestionTareas;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import es.ucm.si.dneb.domain.*;


public interface ServicioGestionTareas {
	
	
	public void iniciarTarea(long tareaId);
	
	public void anadirTareasAlGestor();
	
	public void eliminarTarea(long tareaId);
	
	public void reanudarTarea(long tareaId);
	
	public void pararTarea(long tareaId);
	
	public Integer obtenerPorcentajeCompletado(long tareaId);
		
	public List<Survey> getAllSurveys();
	
	public List<Tarea> getTareas();
	
	public List<Descarga> getDescargasTarea(Long tareaId);
	
	public List<Tarea> getTareasPendientes();
}
