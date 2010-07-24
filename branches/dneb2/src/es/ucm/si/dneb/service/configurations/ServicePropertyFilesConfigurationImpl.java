package es.ucm.si.dneb.service.configurations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.springframework.stereotype.Service;

@Service("servicePropertyFilesConfiguration")
public class ServicePropertyFilesConfigurationImpl implements es.ucm.si.dneb.service.configurations.ServicePropertyFilesConfiguration {

	@Override
	public ResourceBundle loadDataBaseConfiguration() {
		ResourceBundle dBProp = ResourceBundle.getBundle("jdbc");
		
		return dBProp;
	}



	@Override
	public void createNewPropertyFile(Properties propertyFile, String path) {
		
		try {
			propertyFile.store(new FileOutputStream(new File(path)), "path");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}  
		
		
	}

	@Override
	public void saveDataBaseConfiguration(String connector, String url,
			String name, String password) {
		
		Properties dataBaseProperties= new Properties();
		
		dataBaseProperties.setProperty("jdbc.driverClassName", connector); 
		dataBaseProperties.setProperty("jdbc.url", url); 
		dataBaseProperties.setProperty("jdbc.username",name); 
		dataBaseProperties.setProperty("jdbc.password", password); 
		
		
		try {
			dataBaseProperties.store(new FileOutputStream(new File("jdbc.properties")), "DataBase Access Configuration File");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ServiceConfigurationException("Fichero no encontrado"+e.getMessage());
			
		} catch (IOException e) {
			
			e.printStackTrace();
			throw new ServiceConfigurationException(e.getMessage());
		}  
		
		
	}

}
