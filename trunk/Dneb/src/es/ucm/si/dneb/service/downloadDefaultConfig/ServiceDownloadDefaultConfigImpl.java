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

import es.ucm.si.dneb.domain.DownloadConfig;
import es.ucm.si.dneb.domain.FileFormat;
import es.ucm.si.dneb.domain.Survey;


@Service("serviceDownloadDefaultConfig")
public class ServiceDownloadDefaultConfigImpl implements ServiceDownloadDefaultConfig {
	
	private static final Log LOG = LogFactory
	.getLog(ServiceDownloadDefaultConfigImpl.class);

	@PersistenceContext
	EntityManager manager;

	@Transactional(propagation= Propagation.REQUIRED)
	public void createNewDownloadDefaultConfig(DownloadConfig downloadConfig) {
		
		String alias = downloadConfig.getAlias();
		boolean error = true;
		
		try{			
			downloadConfig = (DownloadConfig) manager.createNamedQuery("DownloadConfig:dameConfiguracionPorAlias").setParameter(1, alias).getSingleResult();
		}catch(NoResultException e){			
			error=false;			
		}
		
		if(!error){
			manager.persist(downloadConfig);
		}else{			
			throw new ServiceDownloadDefaultConfigException("Ya existe una configuracion con ese alias");
		}
		
	}

	@Transactional(propagation= Propagation.SUPPORTS)
	public DownloadConfig loadDownloadDefaultConfiguration(String alias) {
		
		DownloadConfig downloadConfig;
		
		try{
			
			downloadConfig = (DownloadConfig) manager.createNamedQuery("DownloadConfig:dameConfiguracionPorAlias").setParameter(1, alias).getSingleResult();
		
		}catch(NoResultException e){
			
			throw new ServiceDownloadDefaultConfigException("No existe una configuración con el alias solicitado");
			
		}catch(NonUniqueResultException e){
			
			throw new ServiceDownloadDefaultConfigException("Inconsitencia en la base de datos, existe mas de una configuración con el alias dado");
			
		}
		
		return downloadConfig;
	}
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Survey> getAllSurveys() {
		List resultList = manager
				.createNamedQuery("Survey:dameTodosLosSurveys").getResultList();
		return resultList;
	}


	@Transactional(propagation = Propagation.SUPPORTS)
	public List<FileFormat> getFormatosFichero() {
		return manager.createNamedQuery("FileFormat:dameTodosFormatos")
				.getResultList();
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<DownloadConfig> getDownloadConfigs() {
		return manager.createNamedQuery("DownloadConfig:dameTodasConfiguraciones")
				.getResultList();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean existsConfig(String alias) {
		
		try{
			DownloadConfig downloadConfig = (DownloadConfig) manager.createNamedQuery("DownloadConfig:dameConfiguracionPorAlias").setParameter(1, alias).getSingleResult();
		}catch(NoResultException e){
			return false;
		}
		
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteConfig(Long id) {
		
		DownloadConfig downloadConfig =manager.find(DownloadConfig.class,id);
		manager.remove(downloadConfig);
	}

	public static Log getLog() {
		return LOG;
	}
	

}
