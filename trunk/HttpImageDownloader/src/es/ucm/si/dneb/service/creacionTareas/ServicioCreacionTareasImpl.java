package es.ucm.si.dneb.service.creacionTareas;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.Descarga;
import es.ucm.si.dneb.domain.FormatoFichero;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.gestionHilos.GestorDescargas;




@Service("servicioCreacionTareas")
public class ServicioCreacionTareasImpl implements ServicioCreacionTareas {
	
	private static final  Log LOG = LogFactory.getLog(ServicioCreacionTareas.class);
	
	@PersistenceContext
	EntityManager manager;
	
	@Resource
	private GestorDescargas gestorDescargas;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void crearTarea(String arInicial,String arFinal,String decInicial,String decFinal,double alto,double ancho,double solapamiento,String surveyOld, String surveynNew,String formato,String ruta){
		
		if(arInicial==null || arFinal==null || decInicial== null || decFinal==null || alto<=0 ||  ancho<=0 || solapamiento<0 || surveyOld==null || surveynNew==null || formato==null){
			LOG.error("Parámetro ilegales");
			throw new ServicioCreacionTareasException("Parámetros ilegarles al crear tarea");
		}
		
		Tarea tarea = new Tarea();
		GregorianCalendar calendar = new GregorianCalendar();
		Date fechaActual= calendar.getTime();
		
		FormatoFichero formatoFichero;
		
		try{
			formatoFichero = (FormatoFichero) manager.createNamedQuery("FormatoFichero:dameFormatoPorDescripcion").setParameter(1, formato).getSingleResult();
		}catch(NoResultException e){
			LOG.error("ProblemaQuery,FormatoFichero:dameFormatoPorDescripcion,No se Devuelve resultado");
			throw new ServicioCreacionTareasException("Prolema al ejecutar query");
		}catch(NonUniqueResultException e){
			LOG.error("ProblemaQuery,FormatoFichero:dameFormatoPorDescripcion,Se devuelve más de un resultado");
			throw new ServicioCreacionTareasException("Prolema al ejecutar query");
		}
	    
		ArrayList<Survey> surveys = new ArrayList<Survey>();
		
		
		try{
			Survey surveyViejo= (Survey) manager.createNamedQuery("Survey:dameSurveyPorDescripcion").setParameter(1, surveyOld).getSingleResult();
			surveys.add(surveyViejo);
		}catch(NoResultException e){
			LOG.error("ProblemaQuery,Survey:dameSurveyPorDescripcion,No se Devuelve resultado");
			throw new ServicioCreacionTareasException("Prolema al ejecutar query");
		}catch(NonUniqueResultException e){
			LOG.error("ProblemaQuery,Survey:dameSurveyPorDescripcion,Se devuelve más de un resultado");
			throw new ServicioCreacionTareasException("Prolema al ejecutar query");
		}
		
		
		try{
			Survey surveyActual= (Survey) manager.createNamedQuery("Survey:dameSurveyPorDescripcion").setParameter(1, surveynNew).getSingleResult();
			surveys.add(surveyActual);
		}catch(NoResultException e){
			LOG.error("ProblemaQuery,Survey:dameSurveyPorDescripcion,No se Devuelve resultado");
			throw new ServicioCreacionTareasException("Prolema al ejecutar query");
		}catch(NonUniqueResultException e){
			LOG.error("ProblemaQuery,Survey:dameSurveyPorDescripcion,Se devuelve más de un resultado");
			throw new ServicioCreacionTareasException("Prolema al ejecutar query");
		}
		
		tarea.setSurveys(surveys);
		
		tarea.setAlto(alto);
		tarea.setAncho(ancho);
		tarea.setArFinal(arFinal);
		tarea.setArInicial(arInicial);
		tarea.setDecInicial(decInicial);
		tarea.setDecFinal(decFinal);
		tarea.setSolpamiento(solapamiento);
		tarea.setFormatoFichero(formatoFichero);
		tarea.setRuta(ruta);
		tarea.setActiva(false);
		tarea.setFinalizada(false);
		
		tarea.setFechaCreacion(fechaActual);
		tarea.setFechaUltimaActualizacion(fechaActual);
		
		manager.persist(tarea);
		
		List<Descarga> descargas = crearDescargas(tarea);
		
		tarea.setDescargas(descargas);
		
		
		manager.merge(tarea);
		
		gestorDescargas.anadirHilo(tarea);
		
		
		
		
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Descarga> crearDescargas(Tarea tarea){
		
		ArrayList<Descarga> descargas = new ArrayList<Descarga>();
		
		String ariniS=tarea.getArInicial();
		Double arini=  Double.parseDouble(ariniS);
		
		String deciniS=tarea.getDecInicial();
		Double decini=  Double.parseDouble(deciniS);
		
		String arfinS=tarea.getArFinal();
		Double arfin=  Double.parseDouble(arfinS);
		
		String decfinS=tarea.getDecFinal();
		Double decfin=  Double.parseDouble(decfinS);
		
		Double alto=tarea.getAlto();
		Double ancho=tarea.getAncho();
		
		Double ar=arini;
		Double dec=decini;
		
		Double solap = tarea.getSolpamiento()/100;
		Double anchoreal=new Double(1D);
		
		while(ar<=arfin){
			
			while(dec<=decfin){
					
				List<Survey> surveys= tarea.getSurveys();
				
				for(Survey survey : surveys){
				
					Descarga descarga= new Descarga();
					descarga.setAscensionRecta(ar.toString());
					descarga.setDeclinacion(dec.toString());
					descarga.setFinalizada(false);
					descarga.setRutaFichero(tarea.getRuta());
					descarga.setSurvey(survey);
					descarga.setTarea(tarea);
					manager.persist(descarga);
					
					descargas.add(descarga);
				
				}
				
				dec = calculaDec(decini, decfin, alto, dec, solap);
				
			}
			dec=decini;
			anchoreal=ancho*(Math.cos(dec));
			ar=ar+(anchoreal)-(anchoreal*solap);
			
		}
		return descargas;
	}


	private Double calculaDec(Double decini, Double decfin, Double alto,
			Double dec, Double solap) {
		if(decini>decfin){
			dec=dec-alto+(alto*solap);
		}else{
			dec=dec+alto-(alto*solap);
		}
		return dec;
	}
	public void setGestorHilos(GestorDescargas gestorDescargas) {
		this.gestorDescargas = gestorDescargas;
	}
	public GestorDescargas getGestorHilos() {
		return gestorDescargas;
	}

}
