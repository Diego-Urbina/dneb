package es.ucm.si.dneb.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

public class ServicioGestionTareasTest {

	private static final Log LOG = LogFactory
			.getLog(ServicioGestionTareasTest.class);
	static ServicioGestionTareas servicioGestionTareas;
	static ServicioCreacionTareas servicioCreacionTareas;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			servicioGestionTareas = (ServicioGestionTareas) ctx
					.getBean("servicioGestionTareas");
			servicioCreacionTareas=(ServicioCreacionTareas) ctx
			.getBean("servicioCreacionTareas");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void tearDownClass() {
	}
	
	//@Test
	public void pruebaGeneralConHilos(){
		/**Primero asigno al gestor de hilos todas las tareas en la base de datos**
		 * 
		 */
		servicioGestionTareas.anadirTareasAlGestor();
		
		/**Pinto los hilos que tiene**/
		
		//GestorDescargas gestor= servicioGestionTareas.getGestorHilos();
		
		
		//HashMap<Long,Hilo> hilos=gestor.getHilos();
		/**Genero una tarea**/
		
		servicioCreacionTareas.crearTarea("25", "43", "25", "43", 10, 10, 2,"poss1_red" , "poss1_red", "fits","D:\\");
		
		//servicioCreacionTareas.crearTarea(arInicial, arFinal, decInicial, decFinal, alto, ancho, solapamiento, surveyOld, surveynNew, formato, ruta);
		
		/**Compruebo que se asigna al gestor**/
		
		List<Tarea> tareas =servicioGestionTareas.getTareas();
		
		
		servicioGestionTareas.iniciarTarea(tareas.get(2).getIdTarea());
		/**Pruebo a iniciarla**/
		
		//servicioGestionTareas.reanudarTarea(tareaId);
		/**Pruebo a pararla**/
	}
	@Test
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
		
		Integer porcentaje=servicioGestionTareas.obtenerPorcentajeCompletado(1L);
	}
	
	
	@Test
	public void testDameTareasSinFinalizar(){
		ArrayList<Tarea> tareas=(ArrayList<Tarea>) servicioGestionTareas.getTareasPendientes();
		for(Tarea tarea : tareas){
			System.out.println("IDTAREA:"+tarea.getIdTarea());
			
		}
	}
	

}
