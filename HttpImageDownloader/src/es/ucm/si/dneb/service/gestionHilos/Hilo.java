package es.ucm.si.dneb.service.gestionHilos;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.Descarga;
import es.ucm.si.dneb.domain.Tarea;

public class Hilo extends Thread {
	
	private static final Log LOG = LogFactory
	.getLog(Hilo.class);
	
	private EjecutorTarea ejecutor;
	
	public Hilo(){
		
		ejecutor=null;
		
	}
	
	public Hilo(EjecutorTarea ejecutor){
		
		this.ejecutor=ejecutor;
	}


	
	public void run() {

		ejecutor.ejecutar();
		

	}


	public void setEjecutor(EjecutorTarea ejecutor) {
		this.ejecutor = ejecutor;
	}


	public EjecutorTarea getEjecutor() {
		return ejecutor;
	}

	
	
	

}
