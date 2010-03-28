package es.ucm.si.dneb.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.ucm.si.dneb.service.busquedaDobles.ServiceBusquedaDobles;

public class ServicioBusquedaDoblesTest {

	private static final  Log LOG = LogFactory.getLog(ServicioGestionTareasTest.class);
	static ServiceBusquedaDobles servicioBusquedaDobles;
	
	@BeforeClass
    public static void setUpClass() throws Exception {
        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            servicioBusquedaDobles =(ServiceBusquedaDobles)ctx.getBean("serviceBusquedaDobles");
                } catch (Throwable e) {
                        e.printStackTrace();
                }
    }

    @AfterClass
    public static void tearDownClass(){
    }
    
    
    @Test
    public void testPixelToCoordinatesConverter(){
    	LOG.info("asdasdasda");
    }
}
