package es.ucm.si.dneb;

import javax.swing.UnsupportedLookAndFeelException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.ucm.si.dneb.gui.VentanaPcpal;
import es.ucm.si.dneb.service.inicializador.ServicioInicializador;

public class HttpImageDownloaderApp {

	/**
	 * @param args
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ServicioInicializador servicioInicializador=(ServicioInicializador) ctx.getBean("servicioInicializador");
		servicioInicializador.winLookAndFeel();
		
		VentanaPcpal vent = new VentanaPcpal();
	}

}
