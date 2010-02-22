package es.ucm.si.dneb.service.calculoPosicion;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.ProcesamientoImagen;

@Service("serviceCalculoPosicion")
public class ServiceCalculoPosicionImpl implements ServiceCalculoPosicion{

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void calcularPosicion(ProcesamientoImagen pi) {
		// TODO Auto-generated method stub
		
	}

}
