package es.ucm.si.dneb.service.inicializador;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.FormatoFichero;
import es.ucm.si.dneb.domain.CargaDatos;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.domain.Tarea;

import es.ucm.si.dneb.service.creacionTareas.ServicioCreacionTareasException;
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
				
				List<Imagen> imagens=tarea.getDescargas();
				
				for(Imagen imagen :imagens){
					manager.remove(imagen);
				}
				
				manager.remove(tarea);	
			}
		}
	}
	
	

	
	
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void generarTareaSobreDatosManuales() {
		
		LOG.info("GENERANDO DATOS MANUALES DE DESCARGA");
		
		List<CargaDatos> cargaDatos=(List<CargaDatos>) manager.createNamedQuery("CargaDatos:dameTodosPuntosRelevantesNoProcesados").getResultList();
		
		if(cargaDatos.size()==0){
			return;
		}
		
		ResourceBundle resource= ResourceBundle.getBundle("es.ucm.si.dneb.resources.resources");
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
		
		FormatoFichero formato;
		
		try {
			formato = (FormatoFichero) manager.createNamedQuery(
					"FormatoFichero:dameFormatoPorDescripcion").setParameter(1,
							formatoFichero).getSingleResult();
		} catch (NoResultException e) {
			LOG
					.error("ProblemaQuery,FormatoFichero:dameFormatoPorDescripcion,No se Devuelve resultado");
			throw new ServicioCreacionTareasException(
					"Prolema al ejecutar query");
		} catch (NonUniqueResultException e) {
			LOG
					.error("ProblemaQuery,FormatoFichero:dameFormatoPorDescripcion,Se devuelve más de un resultado");
			throw new ServicioCreacionTareasException(
					"Prolema al ejecutar query");
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
		manager.persist(tarea);
		
		ArrayList<Imagen> imagens = new ArrayList<Imagen>();
		
		
		for(CargaDatos punto : cargaDatos){
			
			for(Survey survey : surveys){
			
				Imagen imagen = new Imagen();
				imagen.setAncho(Double.parseDouble(ancho));
				String ar =new Double(punto.getAscencionRecta()).toString();
				imagen.setAscensionRecta(ar);
				String dec = new Double(punto.getDeclinacion()).toString();
				imagen.setDeclinacion(dec);
				imagen.setDescargada(false);
				imagen.setRutaFichero(Util.creaRuta(tarea.getRuta(), survey.getDescripcion(),
						ar.toString(), dec.toString(),tarea.getFormatoFichero().getAlias()));
				imagen.setSurvey(survey);
				imagen.setTarea(tarea);
				
				manager.persist(imagen);
				
				imagens.add(imagen);
				
			}
			punto.setProcesado(true);
			manager.persist(punto);
		}
		tarea.setDescargas(imagens);
		
		manager.merge(tarea);
		
		LOG.info("FINALIZADA LA GENERACIÓN DE DESCARGAS MANUALES");
		
		
	}
	

}
