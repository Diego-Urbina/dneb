package es.ucm.si.dneb.test;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.ucm.si.dneb.domain.DownloadDefaultConfiguration;
import es.ucm.si.dneb.domain.FormatoFichero;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.service.configurations.ServicePropertyFilesConfiguration;
import es.ucm.si.dneb.service.downloadDefaultConfig.ServiceDownloadDefaultConfig;

public class ServicioConfigDownloadTest {
	
	
	static ServiceDownloadDefaultConfig serviceDownloadDefaultConfig;
	private static final Log LOG = LogFactory.getLog(ServicioConfigDownloadTest.class);
	@BeforeClass
	public static void setUpClass() throws Exception {
		/**CREACIÓN DE LOS BEANS DE SPRING**/
		try{
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			serviceDownloadDefaultConfig = (ServiceDownloadDefaultConfig) ctx.getBean("serviceDownloadDefaultConfig");
		}catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		/**CREACION DE ELEMENTOS PARA PODER REALIZAR LAS PRUEBAS**/
		
	}
	@AfterClass
	public static void tearDownClass() {
		
	}
	
	//@Test
	public void testSave(){
		
		DownloadDefaultConfiguration downloadDefaultConfiguration = new DownloadDefaultConfiguration();
		
		downloadDefaultConfiguration.setAlias("prueba2");
		downloadDefaultConfiguration.setAlto(30d);
		downloadDefaultConfiguration.setAncho(30d);
		
		List<Survey> surveys= serviceDownloadDefaultConfig.getAllSurveys();
		FormatoFichero formatoFichero= serviceDownloadDefaultConfig.getFormatosFichero().get(0);
		downloadDefaultConfiguration.setFormatoFichero(formatoFichero);
		downloadDefaultConfiguration.setPath("d:/");
		downloadDefaultConfiguration.setSurveys(surveys);
		
		serviceDownloadDefaultConfig.createNewDownloadDefaultConfig(downloadDefaultConfiguration);
	}
	
	@Test
	public void testLoadOK(){
		
		DownloadDefaultConfiguration downloadDefaultConfiguration  =serviceDownloadDefaultConfig.loadDownloadDefaultConfiguration("prueba1");
		
		LOG.info(downloadDefaultConfiguration.getAlias());
		LOG.info(downloadDefaultConfiguration.getAlto());
		LOG.info(downloadDefaultConfiguration.getAncho());
		LOG.info(downloadDefaultConfiguration.getFormatoFichero().getDescipcion());
	}
	
	
	@Test
	public void testLoadFAIL(){
		
		DownloadDefaultConfiguration downloadDefaultConfiguration  =serviceDownloadDefaultConfig.loadDownloadDefaultConfiguration("prue");
		
		LOG.info(downloadDefaultConfiguration.getAlias());
		LOG.info(downloadDefaultConfiguration.getAlto());
		LOG.info(downloadDefaultConfiguration.getAncho());
		LOG.info(downloadDefaultConfiguration.getFormatoFichero().getDescipcion());
	}
	
}
