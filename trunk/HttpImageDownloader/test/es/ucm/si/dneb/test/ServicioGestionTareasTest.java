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

import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;

public class ServicioGestionTareasTest {
	
	
	private static final  Log LOG = LogFactory.getLog(ServicioGestionTareasTest.class);
	static ServicioGestionTareas servicioGestionTareas;
	
	@BeforeClass
    public static void setUpClass() throws Exception {
        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            servicioGestionTareas =(ServicioGestionTareas)ctx.getBean("servicioGestionTareas");
                } catch (Throwable e) {
                        e.printStackTrace();
                }
    }

    @AfterClass
    public static void tearDownClass(){
    }

    @Test
    public void pos1RedTest() throws ClientProtocolException, IOException{
    	servicioGestionTareas.downloadImage("poss1_red","43","43","J2000","5.0","5.0","fits","none");
    	
    }
    

}
