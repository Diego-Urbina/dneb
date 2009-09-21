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

import es.ucm.si.dneb.service.creacionTareas.ServicioCreacionTareas;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;

public class ServicioCreacionTareasTest{
	
	private static final  Log LOG = LogFactory.getLog(ServicioGestionTareasTest.class);
	static ServicioCreacionTareas servicioCreacionTareas;
	
	@BeforeClass
    public static void setUpClass() throws Exception {
        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            servicioCreacionTareas =(ServicioCreacionTareas)ctx.getBean("servicioCreacionTareas");
                } catch (Throwable e) {
                        e.printStackTrace();
                }
    }

    @AfterClass
    public static void tearDownClass(){
    }
    
    //"","43","43","J2000","5.0","5.0","fits","none","D:"
    @Test
    public void testCreacionTarea(){
    	servicioCreacionTareas.crearTarea("25", "43", "25", "43", 10, 10, 2,"poss1_red" , "poss1_red", "fits");
    }

	

}
