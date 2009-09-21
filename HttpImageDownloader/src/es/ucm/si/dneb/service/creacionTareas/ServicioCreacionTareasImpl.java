package es.ucm.si.dneb.service.creacionTareas;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;




@Service("servicioCreacionTareas")
public class ServicioCreacionTareasImpl implements ServicioCreacionTareas {
	
	private static final  Log LOG = LogFactory.getLog(ServicioCreacionTareas.class);
	
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public void crearTarea(String arInicial,String arFinal,String decInicial,String decFinal,double alto,double ancho,double solapamiento){
		// TODO Auto-generated method stub
		
	}

}
