package es.ucm.si.dneb.test;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;

public class ServicioGestionTareasTest {

	private static final Log LOG = LogFactory
			.getLog(ServicioGestionTareasTest.class);
	static ServicioGestionTareas servicioGestionTareas;

	@BeforeClass
	public static void setUpClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			servicioGestionTareas = (ServicioGestionTareas) ctx
					.getBean("servicioGestionTareas");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void tearDownClass() {
	}

	/** Prueba test poss1_red **/
	@Test
	public void poss1RedTest() throws ClientProtocolException, IOException {
		servicioGestionTareas.downloadImage("poss1_red", "43", "43", "J2000",
				"11", "11", "fits", "none", "D:\\");

	}

	@Test
	public void poss1BlueTest() throws ClientProtocolException, IOException {
		servicioGestionTareas.downloadImage("poss1_blue", "43", "43", "J2000",
				"11", "11", "fits", "none", "D:\\");

	}

	@Test
	public void poss2BlueTest() throws ClientProtocolException, IOException {
		servicioGestionTareas.downloadImage("poss2ukstu_blue", "43", "43",
				"J2000", "0.5", "0.5", "fits", "none", "D:\\");

	}

	@Test
	public void poss2IRTest() throws ClientProtocolException, IOException {
		servicioGestionTareas.downloadImage("poss2ukstu_ir", "43", "43",
				"J2000", "0.5", "0.5", "fits", "none", "D:\\");

	}

	@Test
	public void poss2RedTest() throws ClientProtocolException, IOException {
		servicioGestionTareas.downloadImage("poss2ukstu_red", "43", "43",
				"J2000", "0.5", "0.5", "fits", "none", "D:\\");

	}

	@Test
	public void quickvTest() throws ClientProtocolException, IOException {
		servicioGestionTareas.downloadImage("quickv&r", "43", "43", "J2000",
				"0.5", "0.5", "fits", "none", "D:\\");

	}

	@Test
	public void phase2_gsc2Test() throws ClientProtocolException, IOException {
		servicioGestionTareas.downloadImage("phase2_gsc2", "43", "43", "J2000",
				"0.5", "0.5", "fits", "none", "D:\\");

	}

	@Test
	public void phase2_gsc1Test() throws ClientProtocolException, IOException {
		servicioGestionTareas.downloadImage("phase2_gsc1", "43", "43", "J2000",
				"0.5", "0.5", "fits", "none", "D:\\");

	}
 	
	@Test
	public void pruebaProcesoDescarga(){
		/**Prueba chapuza**/
		servicioGestionTareas.procesoDescarga(new Tarea());
	}

}
