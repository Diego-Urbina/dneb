package es.ucm.si.dneb.service.gestionHilos;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
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
import es.ucm.si.dneb.service.downloadImage.ServiceDownloadImage;

@Service("ejecutorTarea")
@Scope("prototype")
@Transactional(propagation=Propagation.SUPPORTS)
public class EjecutorTarea {
	
	
	
	
	private static final Log LOG = LogFactory
	.getLog(EjecutorTarea.class);
	private Long idTarea;
	private Tarea tarea;
	
	@PersistenceContext
	EntityManager manager;
	
	@Resource
	private ServiceDownloadImage serviceDownloadImage;
	
	volatile boolean stop=false;

	public void setIdTarea(Long idTarea) {
		this.idTarea = idTarea;
	}

	public Long getIdTarea() {
		return idTarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	public Tarea getTarea() {
		return tarea;
	}
	
	//@Transactional(propagation= Propagation.SUPPORTS)
	private List<Descarga> getDownloads() {
		List<Descarga> descargas = manager.createNamedQuery(
		"Tarea:DameDescargasPendientesDeEstaTarea").setParameter(1,
		tarea.getIdTarea()).getResultList();
		return descargas;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void ejecutar(Interrumpible inter){
		/**TODO**/
		List<Descarga> descargas = getDownloads();
		for (Descarga descarga : descargas) {
			/*startDownload(descarga);*/
			LOG.info("HILOS STOP =" + stop);
			
			if(inter.continuar()){
				serviceDownloadImage.startDownload(descarga.getIdDescarga(), tarea.getAlto(), tarea.getFormatoFichero().getDescipcion());
			}else{
				break;
			}
			
		}
		if(inter.continuar()){
			tarea.setFinalizada(true);
			manager.merge(tarea);
		}
	}



	

	public void setServiceDownloadImage(ServiceDownloadImage serviceDownloadImage) {
		this.serviceDownloadImage = serviceDownloadImage;
	}

	public ServiceDownloadImage getServiceDownloadImage() {
		return serviceDownloadImage;
	}

	
}
