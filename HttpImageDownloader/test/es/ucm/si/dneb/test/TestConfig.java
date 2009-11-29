package es.ucm.si.dneb.test;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.ucm.si.dneb.service.configurations.ServicePropertyFilesConfiguration;


public class TestConfig {
	
	static ServicePropertyFilesConfiguration servicePropertyFilesConfiguration;
	private static final Log LOG = LogFactory.getLog(TestConfig.class);
	@BeforeClass
	public static void setUpClass() throws Exception {
		/**CREACIÓN DE LOS BEANS DE SPRING**/
		try{
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			servicePropertyFilesConfiguration = (ServicePropertyFilesConfiguration) ctx.getBean("servicePropertyFilesConfiguration");
		}catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		/**CREACION DE ELEMENTOS PARA PODER REALIZAR LAS PRUEBAS**/
		
	}
	@AfterClass
	public static void tearDownClass() {
		
	}
	
	@Test
	public void testSaveProperties(){
		
		servicePropertyFilesConfiguration.saveDataBaseConfiguration("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/dneb", "root", "1234");
	}
	
	
	@Test
	public void loadDataBaseProperties(){
		
		ResourceBundle dataBaseProperties= servicePropertyFilesConfiguration.loadDataBaseConfiguration();
		
		String url= (String) dataBaseProperties.getObject("jdbc.driverClassName");
		
		LOG.info(url);
		
	}
	
	@Test
	public void createNewPropertyFileTest(){
		
		Properties propertyFile = new Properties();
		
		propertyFile.setProperty("kk", "kkString");
		
		servicePropertyFilesConfiguration.createNewPropertyFile(propertyFile, "kk2.properties");
	}
	
	public static Log getLog() {
		return LOG;
	}
}
