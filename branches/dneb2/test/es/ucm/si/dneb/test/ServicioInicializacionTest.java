package es.ucm.si.dneb.test;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.ucm.si.dneb.service.inicializador.ServicioInicializador;

public class ServicioInicializacionTest {
	
	static ServicioInicializador servicioInicializador;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		/**CREACIÓN DE LOS BEANS DE SPRING**/
		try{
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			servicioInicializador = (ServicioInicializador) ctx
					.getBean("servicioInicializador");
		}catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		/**CREACION DE ELEMENTOS PARA PODER REALIZAR LAS PRUEBAS**/
		
	}
	@AfterClass
	public static void tearDownClass() {
		
	}
	
	//@Test
	public void testChequeoConsistencia(){
		
		servicioInicializador.chequeoConsistencia();
		
	}
	//@Test
	public void testEleminarHistoricos(){
		//servicioInicializador.eleminarTareasHistoricas(Date fecha);
	}
	
	@Test
	public void testcargabbdd(){
		
		servicioInicializador.generarTareaSobreDatosManuales();
		
	}

}
