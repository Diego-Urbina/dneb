package es.ucm.si.dneb.service.gestionHilos;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.ProcesamientoImagen;
import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.domain.TareaProcesamiento;

@Service("ejecutorCalculoPosicion")
@Scope("prototype")
@Transactional(propagation=Propagation.SUPPORTS)
public class EjecutorProcesamiento implements EjecutorTarea<TareaProcesamiento>{
	
	
	private TareaProcesamiento tareaProcesamiento;
	private Long tareaProcesamientoId ;
	
	@Override
	public void ejecutar(Interrumpible inter) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public TareaProcesamiento getCore() {
		return this.tareaProcesamiento;
	}
	@Override
	public Long getId() {
		return this.tareaProcesamientoId;
	}
	@Override
	public void setCore(TareaProcesamiento core) {
		this.tareaProcesamiento=core;
	}
	@Override
	public void setId(Long id) {
		this.tareaProcesamientoId=id;
	}
	
	
}