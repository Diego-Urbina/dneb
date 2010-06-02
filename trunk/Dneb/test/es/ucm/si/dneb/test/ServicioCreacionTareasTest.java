package es.ucm.si.dneb.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.ucm.si.dneb.service.creacionTareas.ServicioCreacionTareas;

public class ServicioCreacionTareasTest{
	
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
    
    @Test
    public void testCreacionTarea(){
    	servicioCreacionTareas.crearTarea("25", "43", "25", "43", 10, 10, 2,"poss1_red" , "poss1_red", "fits","D:\\");
    }

	

}
