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

import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.downloadImage.ServiceDownloadImage;

@Service("ejecutorDescarga")
@Scope("prototype")
@Transactional(propagation=Propagation.SUPPORTS)
public class EjecutorDescarga implements EjecutorTarea<Tarea>{

	 
    private static final Log LOG = LogFactory
    .getLog(EjecutorDescarga.class);
    private Long idTarea;
    private Tarea tarea;
    
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

    public void setCore(Tarea tarea) {
            this.tarea = tarea;
    }

    public Tarea getCore() {
            return tarea;
    }
    
    //@Transactional(propagation= Propagation.SUPPORTS)
    private List<Imagen> getDownloads() {
            List<Imagen> imagens = manager.createNamedQuery(
            "Tarea:DameDescargasPendientesDeEstaTarea").setParameter(1,
            tarea.getIdTarea()).getResultList();
            return imagens;
    }
    
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void ejecutar(Interrumpible inter){
            /**TODO**/
            List<Imagen> imagens = getDownloads();
            for (Imagen imagen : imagens) {
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
