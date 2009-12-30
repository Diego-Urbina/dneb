package es.ucm.si.dneb.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.creacionTareas.ServicioCreacionTareas;
import es.ucm.si.dneb.service.gestionHilos.GestorDescargas;
import es.ucm.si.dneb.service.gestionHilos.Hilo;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareasImpl;
import es.ucm.si.dneb.service.inicializador.ServicioInicializador;

public class ServicioGestionTareasTest {

	private static final Log LOG = LogFactory
			.getLog(ServicioGestionTareasTest.class);
	private static ServicioGestionTareas servicioGestionTareas;
	private static ServicioCreacionTareas servicioCreacionTareas;
	private static ServicioInicializador servicioInicializador;
	
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			servicioGestionTareas = (ServicioGestionTareas) ctx
					.getBean("servicioGestionTareas");
			servicioCreacionTareas=(ServicioCreacionTareas) ctx
			.getBean("servicioCreacionTareas");
			
			servicioInicializador= (ServicioInicializador) ctx.getBean("servicioInicializador");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void tearDownClass() {
	}
	
	//@Test
	public void testJoinFetch(){
		
		List<Tarea> tareas=servicioGestionTareas.getTareas();
		for(Tarea tarea: tareas){
			List<Survey> surveys =tarea.getSurveys();
			for(Survey survey : surveys){
				survey.getIdSurvey();
			}
		}
		
	}
	
	//@Test
	public void testPorcentaje(){
		
		LOG.info("Y EL PORCENTAJE ES..... "+servicioGestionTareas.obtenerPorcentajeCompletado(35L));
	}
	
	
	//@Test
	public void testDameTareasSinFinalizar(){
		ArrayList<Tarea> tareas=(ArrayList<Tarea>) servicioGestionTareas.getTareasPendientes();
		for(Tarea tarea : tareas){
			System.out.println("IDTAREA:"+tarea.getIdTarea());
			
		}
	}
	
	@Test
	public void testGestionHilosAndCompany(){
		
		servicioInicializador.chequeoConsistencia();
		
		
		HashMap<Long, Hilo> hilos;
		
		hilos = servicioGestionTareas.getGestorDescargas().getHilos();
		
		debugThreads(hilos);
		
		servicioGestionTareas.anadirTareasAlGestor();
		
		hilos = servicioGestionTareas.getGestorDescargas().getHilos();
		
		
		debugThreads(hilos);
		
		
		//ES IMPORTANTE HACERLO DESPUES???
		
		servicioCreacionTareas.crearTarea("10", "25","10", "25", 2, 2, 5, "poss1_red", "poss1_blue", "fits","D:\\test\\");
		
		
		hilos = servicioGestionTareas.getGestorDescargas().getHilos();
		
		
		debugThreads(hilos);
		
		
		
		//hilos.get(key)
		//hilos.size()
		
		//servicioGestionTareas.iniciarTarea(16);
		
		//servicioGestionTareas.pararTarea(16);
		
		//servicioGestionTareas.eliminarTarea(16);
		
		
	}

	private void debugThreads(HashMap<Long, Hilo> hilos) {
		if(hilos!=null){
			
			Set<Long> keySet=hilos.keySet();
			
			for(Long clave: keySet){
				
				Hilo hilo=hilos.get(clave);
				LOG.debug("HILO: clave=" + clave );
				LOG.debug("HILO: id="+hilo.getId());
			}
			
		}else{
			
			LOG.debug("HashMap<Long, Hilo> hilos :hilos nulos");
			
		}
	}
	
	
	
	

}
