package es.ucm.si.dneb.service.gestionTareas;

import java.util.List;

import es.ucm.si.dneb.domain.*;
import es.ucm.si.dneb.service.gestionHilos.GestorDescargas;


public interface ServicioGestionTareas {
	
	public Task getTareaById(long id);
	
	public void iniciarTarea(long tareaId);
	
	public void anadirTareasAlGestor();
	
	public void eliminarTarea(long tareaId);
	
	public void reanudarTarea(long tareaId);
	
	public void pararTarea(long tareaId);
	
	public Integer obtenerPorcentajeCompletado(long tareaId);
		
	public List<Survey> getAllSurveys();
	
	public List<Task> getTareas();
	
	public List<Image> getDescargasTarea(Long tareaId);
	
	public List<Task> getTareasPendientes();
	
	public GestorDescargas getGestorDescargas();
	
	public void createSingleDownloadTask(String alias, String descripcion, Double alto, Double ancho, FileFormat formatoFichero, String ruta, List<Survey> surveys ,Double ar, Double dec,boolean iniciarDescarga);
	
	public List<Task> getTareasFinalizadas();
	
	public Image getImagenByPath(String path);
}

