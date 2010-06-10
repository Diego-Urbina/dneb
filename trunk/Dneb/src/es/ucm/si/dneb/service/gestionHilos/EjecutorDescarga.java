package es.ucm.si.dneb.service.gestionHilos;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.Image;
import es.ucm.si.dneb.domain.Task;
import es.ucm.si.dneb.service.downloadImage.ServiceDownloadImage;

@Service("ejecutorDescarga")
@Scope("prototype")
@Transactional(propagation=Propagation.SUPPORTS)
public class EjecutorDescarga implements EjecutorTarea<Task>{

	 
    private static final Log LOG = LogFactory
    .getLog(EjecutorDescarga.class);
    private Long idTarea;
    private Task tarea;
    
    @PersistenceContext
    EntityManager manager;
    
    @Resource
    private ServiceDownloadImage serviceDownloadImage;
    
    volatile boolean stop=false;

    public void setId(Long id) {
            this.idTarea = id;
    }

    public Long getId() {
            return idTarea;
    }

    public void setCore(Task tarea) {
            this.tarea = tarea;
    }

    public Task getCore() {
            return tarea;
    }
    
    //@Transactional(propagation= Propagation.SUPPORTS)
    private List<Image> getDownloads() {
            List<Image> imagens = manager.createNamedQuery(
            "Task:DameDescargasPendientesDeEstaTarea").setParameter(1,
            tarea.getIdTarea()).getResultList();
            return imagens;
    }
    
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void ejecutar(Interrumpible inter){
            /**TODO**/
            List<Image> imagens = getDownloads();
            for (Image imagen : imagens) {
                    /*startDownload(descarga);*/
                    LOG.info("HILOS STOP =" + stop);
                    
                    if(inter.continuar()){
                            serviceDownloadImage.startDownload(imagen.getIdDescarga(), tarea.getAlto(), tarea.getFormatoFichero().getAlias());
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
