package es.ucm.si.dneb;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
	
	public void downloadImage(String survey,String ascensionRecta,String declinacion,String equinocio,String alto,String ancho,String formato, String compresion) throws ClientProtocolException, IOException{
		
		HttpClient httpclient = new DefaultHttpClient();
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
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

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
				"UTF-8");
		HttpPost httppost = new HttpPost(
				"http://archive.stsci.edu/cgi-bin/dss_search");
		httppost.setEntity(entity);

		HttpResponse response = httpclient.execute(httppost);
		HttpEntity resEnt = response.getEntity();
		if (resEnt != null) {
			BufferedInputStream bis = new BufferedInputStream(resEnt.getContent());

			// BufferedInputStream bis = new BufferedInputStream(response.get);
			FileOutputStream fos = new FileOutputStream(new File("d:\\kk.fits"));

			byte[] x = new byte[1000];
			int cont = bis.read(x);
			while (cont >= 0) {
				System.out.print(".");
				fos.write(x, 0, cont);
				cont = bis.read(x);
			}

			bis.close();
			fos.close();
		}

		
	}

}
