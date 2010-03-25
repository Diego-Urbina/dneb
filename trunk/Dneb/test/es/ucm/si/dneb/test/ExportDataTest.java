package es.ucm.si.dneb.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.ucm.si.dneb.service.downloadDefaultConfig.ServiceDownloadDefaultConfig;
import es.ucm.si.dneb.service.exportData.ServiceExportData;

public class ExportDataTest {
	
	
	static ServiceExportData serviceExportData;
	private static final Log LOG = LogFactory.getLog(ServicioConfigDownloadTest.class);
	@BeforeClass
	public static void setUpClass() throws Exception {
		/**CREACIÓN DE LOS BEANS DE SPRING**/
		try{
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			serviceExportData = (ServiceExportData) ctx.getBean("serviceExportData");
		}catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		/**CREACION DE ELEMENTOS PARA PODER REALIZAR LAS PRUEBAS**/
		
	}
	@AfterClass
	public static void tearDownClass() {
		
	}
	
	@Test
	public void testExport(){
		serviceExportData.exportRelevantData("kk","d:\\");
	}
	

}
