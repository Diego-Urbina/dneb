package es.ucm.si.dneb.service.gestionTareas;

import java.util.List;

import es.ucm.si.dneb.domain.*;
import es.ucm.si.dneb.service.gestionHilos.GestorDescargas;


public interface ServicioGestionTareas {
	
	
	public void iniciarTarea(long tareaId);
	
	public void anadirTareasAlGestor();
	
	public void eliminarTarea(long tareaId);
	
	public void reanudarTarea(long tareaId);
	
	public void pararTarea(long tareaId);
	
	public Integer obtenerPorcentajeCompletado(long tareaId);
		
	public List<Survey> getAllSurveys();
	
	public List<Tarea> getTareas();
	
	public List<Imagen> getDescargasTarea(Long tareaId);
	
	public List<Tarea> getTareasPendientes();
	
	public GestorDescargas getGestorDescargas();
	
	public void createSingleDownloadTask(String alias, String descripcion, Double alto, Double ancho, FormatoFichero formatoFichero, String ruta, List<Survey> surveys ,Double ar, Double dec,boolean iniciarDescarga);
}
