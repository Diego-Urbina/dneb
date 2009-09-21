package es.ucm.si.dneb.service.creacionTareas;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import es.ucm.si.dneb.domain.Descarga;
import es.ucm.si.dneb.domain.FormatoFichero;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.domain.Tarea;




@Service("servicioCreacionTareas")
public class ServicioCreacionTareasImpl implements ServicioCreacionTareas {
	
	private static final  Log LOG = LogFactory.getLog(ServicioCreacionTareas.class);
	
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public void crearTarea(String arInicial,String arFinal,String decInicial,String decFinal,double alto,double ancho,double solapamiento,String surveyOld, String surveynNew,String formato){
		
		Tarea tarea = new Tarea();
		GregorianCalendar calendar = new GregorianCalendar();
		Date fechaActual= calendar.getTime();
		
		FormatoFichero formatoFichero;
		//OJO falta por capturar la excepcion no single result ?
		formatoFichero = (FormatoFichero) manager.createNamedQuery("").setParameter(1, formato).getSingleResult();
		
		ArrayList<Survey> surveys = new ArrayList<Survey>();
		
		Survey surveyViejo= (Survey) manager.createNamedQuery("").setParameter(1, surveyOld).getSingleResult();
		surveys.add(surveyViejo);
		Survey surveyActual= (Survey) manager.createNamedQuery("").setParameter(1, surveynNew).getSingleResult();
		surveys.add(surveyActual);
		
		tarea.setSurveys(surveys);
		
		tarea.setAlto(alto);
		tarea.setAncho(ancho);
		tarea.setArFinal(arFinal);
		tarea.setArInicial(arInicial);
		tarea.setDecInicial(decInicial);
		tarea.setDecFinal(decFinal);
		tarea.setSolpamiento(solapamiento);
		tarea.setFormatoFichero(formatoFichero);
		
		
		
		tarea.setFechaCreacion(fechaActual);
		tarea.setFechaUltimaActualizacion(fechaActual);
		
		ArrayList<Descarga> descargas = crearDescargas(arInicial,arFinal,decInicial,decFinal,alto,ancho,solapamiento,formato);
		
		tarea.setDescargas(descargas);
		
		
		
	}
	
	public ArrayList<Descarga> crearDescargas(String arInicial,String arFinal,String decInicial,String decFinal,double alto,double ancho,double solapamiento,String formato){
		
		return null;
	}

}
