package es.ucm.si.dneb.service.inicializador;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.Descarga;
import es.ucm.si.dneb.domain.FormatoFichero;
import es.ucm.si.dneb.domain.PuntosRelevantes;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.creacionTareas.ServicioCreacionTareas;
import es.ucm.si.dneb.util.Util;

@Service("servicioInicializador")
public class ServicioInicializadorImpl implements ServicioInicializador {
	
	@PersistenceContext
	EntityManager manager;

	private static final  Log LOG = LogFactory.getLog(ServicioInicializadorImpl.class);
	
	
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
		
		LOG.info("GENERANDO DATOS MANUALES DE DESCARGA");
		
		List<PuntosRelevantes> puntosRelevantes=(List<PuntosRelevantes>) manager.createNamedQuery("PuntosRelevantes:dameTodosPuntosRelevantesNoProcesados").getResultList();
		
		ResourceBundle resource= ResourceBundle.getBundle("es.ucm.si.dneb.resources");
		String survey1=resource.getString("survey1");
		String survey2=resource.getString("survey2");
		
		ArrayList<Survey> surveys= new ArrayList<Survey>();
		Survey surveyold = (Survey) manager.createNamedQuery("Survey:dameSurveyPorDescripcion").setParameter(1, survey1).getSingleResult();
		Survey surveynew = (Survey) manager.createNamedQuery("Survey:dameSurveyPorDescripcion").setParameter(1, survey2).getSingleResult();
		
		surveys.add(surveyold);
		surveys.add(surveynew);
		
		String ruta=resource.getString("directoriodescargas");
		String ancho=resource.getString("anchopordefecto");
		String alto=resource.getString("altopordefecto");
		String solapamiento=resource.getString("solpamientopordefecto");
		String formatoFichero= resource.getString("formatodefecto");
		FormatoFichero formato = (FormatoFichero) manager.createNamedQuery("FormatoFichero:dameFormatoPorDescripcion").setParameter(1, formatoFichero).getSingleResult();
		if(formato==null){
			LOG.error("FORMATO NO EXISTENTE");
			
		}
		
		Tarea tarea = new Tarea();
		tarea.setActiva(false);
		tarea.setAlto(Double.parseDouble(alto));
		tarea.setAncho(Double.parseDouble(ancho));
		tarea.setArFinal("0");
		tarea.setArInicial("0");
		tarea.setDecInicial("0");
		tarea.setDecFinal("0");
		tarea.setFechaCreacion(Util.dameFechaActual());
		tarea.setFechaUltimaActualizacion(Util.dameFechaActual());
		tarea.setFinalizada(false);
		tarea.setFormatoFichero(formato);
		tarea.setRuta(ruta);
		tarea.setSolpamiento(Double.parseDouble(solapamiento));
		tarea.setSurveys(surveys);
		/**TODO**/
		ArrayList<Descarga> descargas = new ArrayList<Descarga>();
		
		for(PuntosRelevantes punto : puntosRelevantes){
			
			for(Survey survey : surveys){
			
				Descarga descarga = new Descarga();
				descarga.setAncho(Double.parseDouble(ancho));
				String ar =new Double(punto.getAscencionRecta()).toString();
				descarga.setAscensionRecta(ar);
				String dec = new Double(punto.getDeclinacion()).toString();
				descarga.setDeclinacion(dec);
				descarga.setFinalizada(false);
				descarga.setRutaFichero(Util.creaRuta(tarea.getRuta(), survey.getDescripcion(),
						ar.toString(), dec.toString(),tarea.getFormatoFichero().getDescipcion()));
				descarga.setSurvey(survey);
				descargas.add(descarga);
			}
			
		}
		tarea.setDescargas(descargas);
		
		manager.persist(tarea);
		
		LOG.info("FINALIZADA LA GENERACIÓN DE DESCARGAS MANUALES");
		
		
	}
	

}
