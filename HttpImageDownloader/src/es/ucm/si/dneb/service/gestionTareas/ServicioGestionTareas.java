package es.ucm.si.dneb.service.gestionTareas;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;

public interface ServicioGestionTareas {
	
	public void downloadImage(String survey,String ascensionRecta,String declinacion,String equinocio,String alto,String ancho,String formato, String compresion,String ruta);
	
	public void reanudarTarea(long tareaId);
	
	public void PararTarea(long tareaId);
	
	
}
