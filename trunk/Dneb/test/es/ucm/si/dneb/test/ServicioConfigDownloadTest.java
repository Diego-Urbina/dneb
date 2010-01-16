package es.ucm.si.dneb.test;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.ucm.si.dneb.domain.DownloadConfig;
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
		
		DownloadConfig downloadConfig = new DownloadConfig();
		
		downloadConfig.setAlias("prueba2");
		downloadConfig.setAlto(30d);
		downloadConfig.setAncho(30d);
		
		List<Survey> surveys= serviceDownloadDefaultConfig.getAllSurveys();
		FormatoFichero formatoFichero= serviceDownloadDefaultConfig.getFormatosFichero().get(0);
		downloadConfig.setFormatoFichero(formatoFichero);
		downloadConfig.setPath("d:/");
		downloadConfig.setSurveys(surveys);
		
		serviceDownloadDefaultConfig.createNewDownloadDefaultConfig(downloadConfig);
	}
	
	@Test
	public void testLoadOK(){
		
		DownloadConfig downloadConfig  =serviceDownloadDefaultConfig.loadDownloadDefaultConfiguration("prueba1");
		
		LOG.info(downloadConfig.getAlias());
		LOG.info(downloadConfig.getAlto());
		LOG.info(downloadConfig.getAncho());
		LOG.info(downloadConfig.getFormatoFichero().getAlias());
	}
	
	
	@Test
	public void testLoadFAIL(){
		
		DownloadConfig downloadConfig  =serviceDownloadDefaultConfig.loadDownloadDefaultConfiguration("prue");
		
		LOG.info(downloadConfig.getAlias());
		LOG.info(downloadConfig.getAlto());
		LOG.info(downloadConfig.getAncho());
		LOG.info(downloadConfig.getFormatoFichero().getAlias());
	}
	
}
