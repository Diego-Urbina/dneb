package es.ucm.si.dneb.service.gestionHilos;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.domain.TareaProcesamiento;

@Service("ejecutorCalculoPosicion")
@Scope("prototype")
@Transactional(propagation=Propagation.SUPPORTS)
public class EjecutorCalculoPosicion implements EjecutorTarea{
	
	
	private TareaProcesamiento tareaProcesamiento;
	private Long tareaProcesamientoId ;
	

	
	@Override
	public void ejecutar(Interrumpible inter) {
		// TODO Auto-generated method stub
		
	}

	public void setTareaProcesamiento(TareaProcesamiento tareaProcesamiento) {
		this.tareaProcesamiento = tareaProcesamiento;
	}

	public TareaProcesamiento getTareaProcesamiento() {
		return tareaProcesamiento;
	}

	public void setTareaProcesamientoId(Long tareaProcesamientoId) {
		this.tareaProcesamientoId = tareaProcesamientoId;
	}

	public Long getTareaProcesamientoId() {
		return tareaProcesamientoId;
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

	@Override
	public void setCore(Object core) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

	

	

}
