package es.ucm.si.dneb.service.gestionTareas;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;

import es.ucm.si.dneb.domain.Tarea;

public interface ServicioGestionTareas {
	
	public void downloadImage(String survey,String ascensionRecta,String declinacion,String equinocio,String alto,String ancho,String formato, String compresion,String ruta);
	
	public void reanudarTarea(long tareaId);
	
	public void pararTarea(long tareaId);
	
	public double obtenerPorcentajeCompletado(long tareaId);
	
	public void procesoDescarga(Tarea tarea);
	
	
}
