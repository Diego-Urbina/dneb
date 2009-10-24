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
		
		
		chequeaSiValoresNoNulos(arInicial, arFinal, decInicial, decFinal, alto,
				ancho, solapamiento, surveyOld, surveynNew, formato);
		
		
		
		Date fechaActual;
		
		Tarea tarea = new Tarea();
		
		fechaActual = dameFechaActual();
		
		
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

	@Transactional(propagation = Propagation.SUPPORTS)
	private Date dameFechaActual() {
		Date fechaActual;
		GregorianCalendar calendar = new GregorianCalendar();
		fechaActual= calendar.getTime();
		
		LOG.debug("LA FECHA ACTUAL GENERADA"+fechaActual.toString());
		
		return fechaActual;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	private void chequeaSiValoresNoNulos(String arInicial, String arFinal,
			String decInicial, String decFinal, double alto, double ancho,
			double solapamiento, String surveyOld, String surveynNew,
			String formato) {
		if(arInicial==null || arFinal==null || decInicial== null || decFinal==null || alto<=0 ||  ancho<=0 || solapamiento<0 || surveyOld==null || surveynNew==null || formato==null){
			LOG.error("PARAMETROS NO VÁLIDOS");
			throw new ServicioCreacionTareasException("Parámetros ilegarles al crear tarea");
		}
		LOG.debug("CHEQUEO DE VALORES NULOS SUPERADO");
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
		
		LOG.debug("DATOS CREADOS CORRECTAMENTE");
		
		while(ar<=arfin){
			
			while(dec<=decfin){
					
				List<Survey> surveys= tarea.getSurveys();
				
				LOG.debug("OBTENIDAS TODAS LAS LISTAS DE SURVEYS");
				
				for(Survey survey : surveys){
				
					Descarga descarga= new Descarga();
					descarga.setAscensionRecta(ar.toString());
					descarga.setDeclinacion(dec.toString());
					descarga.setFinalizada(false);
					/**TODO OJO QUE ESTO ES UN CAMBIO IMPORTANTE HAY QUE PROBAR SI FUNCIONA**/
					descarga.setRutaFichero(creaRuta(tarea.getRuta(), survey.getDescripcion(),
							ar.toString(), dec.toString(),tarea.getFormatoFichero().getDescipcion()));
					descarga.setSurvey(survey);
					descarga.setTarea(tarea);
					descarga.setAncho((ancho*(Math.cos((dec-(alto/2))))));
					
					manager.persist(descarga);
					
					descargas.add(descarga);
					
					LOG.debug("AÑADIDA LA DESCARGA DEL SURVEY:"+descarga.getSurvey().getDescripcion());
					
				}
				
				dec = calculaDec(decini, decfin, alto, dec, solap);
				
			}
			dec=decini;
			anchoreal=ancho*(Math.cos(dec));
			ar = calculaAr(ar, solap, anchoreal);
			
		}
		return descargas;
	}
	@Transactional(propagation = Propagation.REQUIRED)
	private String creaRuta(String rutaBase, String survey,
			String ascensionRecta, String declinacion, String formato) {

		String ruta = rutaBase;
		String nombreFichero = null;

		if (ruta != null) {
			if (rutaBase.charAt(rutaBase.length() - 1) != '/') {
				rutaBase = rutaBase + "/";
			}
			nombreFichero = "AR" + ascensionRecta + "DEC" + declinacion
					+ "SURV" + survey + "." + formato;
			ruta = rutaBase + nombreFichero;
			return ruta;

		} else {
			return null;
		}

	}


	@Transactional(propagation = Propagation.SUPPORTS)
	private Double calculaAr(Double ar, Double solap, Double anchoreal) {
		ar=ar+(anchoreal)-(anchoreal*solap);
		return ar;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
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
