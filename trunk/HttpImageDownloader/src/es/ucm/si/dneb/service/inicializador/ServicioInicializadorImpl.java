package es.ucm.si.dneb.service.inicializador;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.Descarga;
import es.ucm.si.dneb.domain.PuntosRelevantes;
import es.ucm.si.dneb.domain.Tarea;

@Service("servicioInicializador")
public class ServicioInicializadorImpl implements ServicioInicializador {
	
	@PersistenceContext
	EntityManager manager;
	
	final static String THEME = "Test";

	@Transactional(propagation=Propagation.REQUIRED)
	public void chequeoConsistencia() {
		
		List<Tarea> tareas =manager.createNamedQuery("Tarea:DameTodasTareasActivas").getResultList();
		
		for(Tarea tarea: tareas){
			
			if(tarea.isActiva()==true){
				tarea.setActiva(false);
			}
			
		}
		
		
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void eleminarTareasHistoricas(Date fecha){
		
		
		List<Tarea> tareas= manager.createNamedQuery("Tarea:DameTodasTareasActualizadasAntesFecha").setParameter(1, fecha).getResultList();
		
		for(Tarea tarea: tareas){
			if(tarea.isActiva()==false && tarea.isFinalizada()==true){
				
				List<Descarga> descargas=tarea.getDescargas();
				
				for(Descarga descarga :descargas){
					manager.remove(descarga);
				}
				
				manager.remove(tarea);	
			}
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	private Date dameFechaActual() {
		Date fechaActual;
		GregorianCalendar calendar = new GregorianCalendar();
		fechaActual= calendar.getTime();
		return fechaActual;
	}
	

	@Override
	public void inicializarContexto() {
		// TODO Auto-generated method stub

	}
	public void winLookAndFeel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

	}
	
	@Override
	public void initLookAndFeel(String theme) {

		String LOOKANDFEEL = theme;
		String lookAndFeel = null;

		if (LOOKANDFEEL != null) {
			if (LOOKANDFEEL.equals("Metal")) {
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
				// an alternative way to set the Metal L&F is to replace the
				// previous line with:
				// lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";

			}

			else if (LOOKANDFEEL.equals("System")) {
				lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			}

			else if (LOOKANDFEEL.equals("Motif")) {
				lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			}

			else if (LOOKANDFEEL.equals("GTK")) {
				lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
			}

			else {
				System.err
						.println("Unexpected value of LOOKANDFEEL specified: "
								+ LOOKANDFEEL);
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			}

			try {

				UIManager.setLookAndFeel(lookAndFeel);

				// If L&F = "Metal", set the theme

				if (LOOKANDFEEL.equals("Metal")) {
					if (THEME.equals("DefaultMetal"))
						MetalLookAndFeel
								.setCurrentTheme(new DefaultMetalTheme());
					else {
						MetalLookAndFeel.setCurrentTheme(new OceanTheme());
					}
					UIManager.setLookAndFeel(new MetalLookAndFeel());
				}

			}

			catch (ClassNotFoundException e) {
				System.err
						.println("Couldn't find class for specified look and feel:"
								+ lookAndFeel);
				System.err
						.println("Did you include the L&F library in the class path?");
				System.err.println("Using the default look and feel.");
			}

			catch (UnsupportedLookAndFeelException e) {
				System.err.println("Can't use the specified look and feel ("
						+ lookAndFeel + ") on this platform.");
				System.err.println("Using the default look and feel.");
			}

			catch (Exception e) {
				System.err.println("Couldn't get specified look and feel ("
						+ lookAndFeel + "), for some reason.");
				System.err.println("Using the default look and feel.");
				e.printStackTrace();
			}
		}
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void generarTareaSobreDatosManuales() {
		List<PuntosRelevantes> puntosRelevantes= manager.createNamedQuery("PuntosRelevantes:dameTodosPuntosRelevantesNoProcesados").getResultList();
		
		/**TODO**/
	}

}
