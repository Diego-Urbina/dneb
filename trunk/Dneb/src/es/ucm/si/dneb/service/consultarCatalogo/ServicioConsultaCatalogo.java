package es.ucm.si.dneb.service.consultarCatalogo;

import java.util.Date;
import java.util.List;

import es.ucm.si.dneb.domain.DoubleStarCatalog;

public interface ServicioConsultaCatalogo {
	
	public List<DoubleStarCatalog> consultaCatalogo(int limNumObs,Date primeraObs,Date ultimaObs,double minMagnitud,double maxDist,double minVel);
	
	public DoubleStarCatalog findDSCById(Long id);
	
	public void crearTareaDescarga(List<DoubleStarCatalog> dscList);
	
	
}
