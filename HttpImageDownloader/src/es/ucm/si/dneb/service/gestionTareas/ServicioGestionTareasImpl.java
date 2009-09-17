package es.ucm.si.dneb.service.gestionTareas;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;


@Service("servicioGestionTareas")
public class ServicioGestionTareasImpl implements ServicioGestionTareas{

private static final Log LOG= LogFactory.getLog(ServicioGestionTareasImpl.class);
	
	public void downloadImage(String survey,String ascensionRecta,String declinacion,String equinocio,String alto,String ancho,String formato, String compresion,String ruta){
		
		LOG.info("Entrada en el método downloadImage");
		final HttpClient httpclient = new DefaultHttpClient();
		
		final List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>(10);
		formparams.add(new BasicNameValuePair("v", survey));
		formparams.add(new BasicNameValuePair("r", ascensionRecta));
		formparams.add(new BasicNameValuePair("d", declinacion));
		formparams.add(new BasicNameValuePair("e", equinocio));
		formparams.add(new BasicNameValuePair("h", alto));
		formparams.add(new BasicNameValuePair("w", ancho));
		formparams.add(new BasicNameValuePair("f", formato));
		formparams.add(new BasicNameValuePair("c", compresion));
		formparams.add(new BasicNameValuePair("fov", "none"));
		formparams.add(new BasicNameValuePair("v3", ""));
		
		
		
		UrlEncodedFormEntity entity=null;
		try {
			entity = new UrlEncodedFormEntity(formparams,
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.debug("Problema con la codificación de los parámetros");
			e.printStackTrace();
		}
		
		LOG.info("Parámetros configurados correctamente");
		final HttpPost httppost = new HttpPost(
				"http://archive.stsci.edu/cgi-bin/dss_search");
		
		httppost.setEntity(entity);

		HttpResponse response=null;
		try {
			response = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			LOG.debug("ERROR EJECUTANDO HTTPPOST");
			e.printStackTrace();
		} catch (IOException e) {
			LOG.debug("ERROR EJECUTANDO HTTPPOST");
			e.printStackTrace();
		}
		
		LOG.info("HTTPPOST EJECUTADO SATISFACTORIAMENTE");
		
		final HttpEntity resEnt = response.getEntity();
		if (resEnt != null) {
			BufferedInputStream bis = null;
			try {
				bis = new BufferedInputStream(resEnt.getContent());
			} catch (IllegalStateException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			FileOutputStream fos = null;
			try {
				/**TODO PONER LA RUTA DE MANERA CORRECTA**/
				fos = new FileOutputStream(new File(creaRuta(ruta,survey,ascensionRecta,declinacion,formato)));
			} catch (FileNotFoundException e) {
				LOG.debug("ERROR AL CREAR EL FICHERO");
				e.printStackTrace();
			}
			LOG.info("Se procede a la lecuta de datos");
			final byte[] buffer = new byte[1024];
			int cont = 0;
			try {
				cont = bis.read(buffer);
			} catch (IOException e) {
				LOG.debug("ERROR AL LEER DATOS DE LA URL");
				e.printStackTrace();
			}
			int total=cont;
			while (cont >= 0) {
				LOG.info("Leidos "+cont);
				try {
					fos.write(buffer, 0, cont);
				} catch (IOException e) {
					LOG.debug("ERROR AL ESCRIBIR DATOS EN EL FICHERO");
					e.printStackTrace();
				}
				try {
					cont = bis.read(buffer);
				} catch (IOException e) {
					LOG.debug("ERROR AL LEER DATOS DE LA URL");
					e.printStackTrace();
				}
				total+=cont;
			}
			LOG.info("Fin de la lectura de datos");
			LOG.info("Leidos: "+total+" bytes");
			try {
				bis.close();
			} catch (IOException e) {
				LOG.debug("ERROR AL CERRAR EL FICHERO");
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				LOG.debug("ERROR AL CERRAR EL FICHERO");
				e.printStackTrace();
			}
			LOG.info("Fichero cerrado");
		}else{
			LOG.debug("PROBLEMA OBTENIENDO RESPONSE ENTITY");
			
		}

		
	}
	
	public String creaRuta( String rutaBase, String survey, String ascensionRecta, String declinacion,String formato){
		
		String ruta=rutaBase;
		String nombreFichero=null;
		
		if (ruta!=null){
			if(rutaBase.charAt(rutaBase.length()-1)!='/'){
				rutaBase=rutaBase+"/";
			}
			nombreFichero="AR"+ascensionRecta+"DEC"+declinacion+"SURV"+survey+"."+formato;
			ruta=rutaBase+nombreFichero;
			return ruta;
			
		}else{
			return null;
		}
		
	}

}
