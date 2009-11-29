package es.ucm.si.dneb.service.downloadDefaultConfig;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.DownloadDefaultConfiguration;
import es.ucm.si.dneb.domain.FormatoFichero;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.domain.Tarea;


@Service("serviceDownloadDefaultConfig")
public class ServiceDownloadDefaultConfigImpl implements es.ucm.si.dneb.service.downloadDefaultConfig.ServiceDownloadDefaultConfig {
	
	private static final Log LOG = LogFactory
	.getLog(ServiceDownloadDefaultConfigImpl.class);

	@PersistenceContext
	EntityManager manager;

	@Transactional(propagation= Propagation.REQUIRED)
	public void createNewDownloadDefaultConfig(
			DownloadDefaultConfiguration downloadDefaultConfiguration) {
		
		String alias = downloadDefaultConfiguration.getAlias();
		boolean error = true;
		
		try{
			
			downloadDefaultConfiguration = (DownloadDefaultConfiguration) manager.createNamedQuery("DownloadDefaultConfiguration:dameConfiguracionPorAlias").setParameter(1, alias).getSingleResult();
		
		}catch(NoResultException e){
			
			error=false;
			
		}
		
		if(!error){
			manager.persist(downloadDefaultConfiguration);
		}else{
			
			throw new ServiceDownloadDefaultConfigException("Ya existe una configuracion con ese alias");
		}
		
	}

	@Transactional(propagation= Propagation.SUPPORTS)
	public DownloadDefaultConfiguration loadDownloadDefaultConfiguration(String alias) {
		
		DownloadDefaultConfiguration downloadDefaultConfiguration;
		
		try{
			
			downloadDefaultConfiguration = (DownloadDefaultConfiguration) manager.createNamedQuery("DownloadDefaultConfiguration:dameConfiguracionPorAlias").setParameter(1, alias).getSingleResult();
		
		}catch(NoResultException e){
			
			throw new ServiceDownloadDefaultConfigException("No existe una configuración con el alias solicitado");
			
		}catch(NonUniqueResultException e){
			
			throw new ServiceDownloadDefaultConfigException("Inconsitencia en la base de datos, existe mas de una configuración con el alias dado");
			
		}
		
		return downloadDefaultConfiguration;
	}
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Survey> getAllSurveys() {
		List resultList = manager
				.createNamedQuery("Survey:dameTodosLosSurveys").getResultList();
		return resultList;
	}


	@Transactional(propagation = Propagation.SUPPORTS)
	public List<FormatoFichero> getFormatosFichero() {
		return manager.createNamedQuery("FormatoFichero:dameTodosFormatos")
				.getResultList();
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<DownloadDefaultConfiguration> getDownloadConfigs() {
		return manager.createNamedQuery("DownloadDefaultConfiguration:dameTodasConfiguraciones")
				.getResultList();
	}
	

}
