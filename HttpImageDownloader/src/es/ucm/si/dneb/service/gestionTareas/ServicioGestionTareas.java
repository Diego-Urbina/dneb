package es.ucm.si.dneb.service.gestionTareas;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import es.ucm.si.dneb.domain.*;


public interface ServicioGestionTareas {
	
	public void downloadImage(String survey,String ascensionRecta,String declinacion,String equinocio,String alto,String ancho,String formato, String compresion,String ruta);
	
	public void reanudarTarea(long tareaId);
	
	public void pararTarea(long tareaId);
	
	public double obtenerPorcentajeCompletado(long tareaId);
	
	public void procesoDescarga(Tarea tarea);
	
	public List<Survey> getAllSurveys();
	
	public List<Tarea> getTareas();
}
