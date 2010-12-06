package es.ucm.si.dneb.service.inicializador;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import es.ucm.si.dneb.service.downloadImage.ServiceDownloadImageException;


public interface ServicioInicializador {
	public void inicializar();
	
	public void eleminarTareasHistoricas(Date fecha);
	
	public void chequeoConsistencia();
	
	public void generarTareaSobreDatosManuales();
	
	public boolean testConnection();

}
