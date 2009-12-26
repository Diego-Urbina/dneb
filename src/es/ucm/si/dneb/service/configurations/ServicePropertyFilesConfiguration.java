package es.ucm.si.dneb.service.configurations;

import java.util.Properties;
import java.util.ResourceBundle;

public interface ServicePropertyFilesConfiguration {
	
	public ResourceBundle loadDataBaseConfiguration() ;
	
	public void saveDataBaseConfiguration(String connector, String url, String name, String password);
	
	public void createNewPropertyFile(Properties propertyFile, String path);
}
