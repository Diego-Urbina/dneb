package es.ucm.si.dneb.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.configurations.ServicePropertyFilesConfiguration;
import es.ucm.si.dneb.service.gestionHilos.GestorBusquedaDobles;
import es.ucm.si.dneb.service.gestionHilos.GestorCalculoPosicion;
import es.ucm.si.dneb.service.gestionHilos.GestorDescargas;

public class TestGestoresHilos {
	
	private static GestorDescargas gestorDescargas;
	private static GestorCalculoPosicion gestorCalculoPosicion;
	private static GestorBusquedaDobles gestorBusquedaDobles;
	
	private static final Log LOG = LogFactory.getLog(TestGestoresHilos.class);
	
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		
		try{
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			gestorDescargas = (GestorDescargas) ctx.getBean("gestorDescargas");
			gestorCalculoPosicion = (GestorCalculoPosicion) ctx.getBean("gestorCalculoPosicion");
			gestorBusquedaDobles = (GestorBusquedaDobles) ctx.getBean("gestorBusquedaDobles");
			
		}catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TestConfictosGestores(){
		
		Tarea tarea =new Tarea();
		tarea.setIdTarea(1);
		
		gestorDescargas.anadirHilo(tarea);
		
		LOG.debug("GESTOR DESCARGAS"+gestorDescargas.getHilos().size());
		LOG.debug("GESTOR bd"+gestorBusquedaDobles.getHilos().size());
		LOG.debug("GESTOR cp"+gestorCalculoPosicion.getHilos().size());
		
		
	}

}
