package es.ucm.si.dneb;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface ImageDownloader {
	
	public void downloadImage(String survey,String ascensionRecta,String declinacion,String equinocio,String alto,String ancho,String formato, String compresion) throws ClientProtocolException, IOException;
		

}
