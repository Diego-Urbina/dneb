package es.ucm.si.dneb;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

@Service("ImageDownloader")
public class ImageDownloaderImpl implements ImageDownloader{
	
	private static final Log LOG= LogFactory.getLog(ImageDownloaderImpl.class);
	
	public void downloadImage(String survey,String ascensionRecta,String declinacion,String equinocio,String alto,String ancho,String formato, String compresion) throws ClientProtocolException, IOException{
		
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
		
		LOG.info("Parámetros configurados correctamente");
		
		final UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
				"UTF-8");
		final HttpPost httppost = new HttpPost(
				"http://archive.stsci.edu/cgi-bin/dss_search");
		httppost.setEntity(entity);

		final HttpResponse response = httpclient.execute(httppost);
		LOG.info("HTTPPOST EJECUTADO");
		final HttpEntity resEnt = response.getEntity();
		if (resEnt != null) {
			final BufferedInputStream bis = new BufferedInputStream(resEnt.getContent());

			// BufferedInputStream bis = new BufferedInputStream(response.get);
			final FileOutputStream fos = new FileOutputStream(new File("d:\\kk.fits"));
			LOG.info("Se procede a la lecuta de datos");
			final byte[] buffer = new byte[1024];
			int cont = bis.read(buffer);
			int total=cont;
			while (cont >= 0) {
				LOG.info("Leidos "+cont);
				fos.write(buffer, 0, cont);
				cont = bis.read(buffer);
				total+=cont;
			}
			LOG.info("Fin de la lectura de datos");
			LOG.info("Leidos: "+total+" bytes");
			bis.close();
			fos.close();
			LOG.info("Fichero cerrado");
		}

		
	}

}
