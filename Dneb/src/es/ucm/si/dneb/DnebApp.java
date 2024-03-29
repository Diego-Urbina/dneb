package es.ucm.si.dneb;

import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import es.ucm.si.dneb.gui.VentanaPcpal;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;
import es.ucm.si.dneb.service.inicializador.ServicioInicializador;

public class DnebApp {
	
	private static final Log LOG = LogFactory
	.getLog(DnebApp.class);
	/**
	 * @param args size
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		LOG.debug("INICIO DE LA APLICACION");
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();//new ClassPathXmlApplicationContext("applicationContext.xml");
		ServicioInicializador servicioInicializador=(ServicioInicializador) ctx.getBean("servicioInicializador");
		servicioInicializador.chequeoConsistencia();
		//servicioInicializador.generarTareaSobreDatosManuales();
		
		ServicioGestionTareas servicioGestionTareas = (ServicioGestionTareas) ctx.getBean("servicioGestionTareas");
		servicioGestionTareas.anadirTareasAlGestor();
		
		VentanaPcpal vent = new VentanaPcpal();
		vent.setVisible(true);
	}

}
