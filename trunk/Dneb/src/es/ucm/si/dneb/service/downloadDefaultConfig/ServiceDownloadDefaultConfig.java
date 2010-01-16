package es.ucm.si.dneb.service.downloadDefaultConfig;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.DownloadConfig;
import es.ucm.si.dneb.domain.FormatoFichero;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.domain.Tarea;

public interface ServiceDownloadDefaultConfig {
	
	
	public void createNewDownloadDefaultConfig(DownloadConfig downloadConfig);
	
	public DownloadConfig loadDownloadDefaultConfiguration(String alias);
		
	public List<Survey> getAllSurveys();

	public List<FormatoFichero> getFormatosFichero();
	
	public List<DownloadConfig> getDownloadConfigs();
	
	public boolean existsConfig(String alias);
	
	public void deleteConfig(Long id);

}
