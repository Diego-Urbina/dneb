package es.ucm.si.dneb.service.gestionHilos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.Tarea;

@Service("ejecutorBusquedaDoble")
@Scope("prototype")
@Transactional(propagation=Propagation.SUPPORTS)
public class EjecutorBusquedaDoble implements EjecutorTarea{
	
	private static final Log LOG = LogFactory
    .getLog(EjecutorBusquedaDoble.class);
    private Long idTarea;
    private Tarea tarea;
    

	@Override
	public void ejecutar(Interrumpible inter) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object getCore() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}



	public Long getIdTarea() {
		return idTarea;
	}


	public void setIdTarea(Long idTarea) {
		this.idTarea = idTarea;
	}


	public Tarea getTarea() {
		return tarea;
	}


	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}


	public static Log getLog() {
		return LOG;
	}
	@Override
	public void setCore(Object core) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}



	

}
