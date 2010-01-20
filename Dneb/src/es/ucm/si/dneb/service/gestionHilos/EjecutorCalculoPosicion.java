package es.ucm.si.dneb.service.gestionHilos;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.Tarea;

@Service("ejecutorCalculoPosicion")
@Scope("prototype")
@Transactional(propagation=Propagation.SUPPORTS)
public class EjecutorCalculoPosicion implements EjecutorTarea{

	@Override
	public void ejecutar(Interrumpible inter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getIdTarea() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tarea getTarea() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIdTarea(Long idTarea) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTarea(Tarea tarea) {
		// TODO Auto-generated method stub
		
	}

}
