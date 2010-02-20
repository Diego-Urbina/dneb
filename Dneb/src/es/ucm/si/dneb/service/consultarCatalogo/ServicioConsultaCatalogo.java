package es.ucm.si.dneb.service.consultarCatalogo;

import java.util.Date;
import java.util.List;

import es.ucm.si.dneb.domain.DoubleStarCatalog;

public interface ServicioConsultaCatalogo {
	
	public List<DoubleStarCatalog> consultaCatalogo(int limNumObs,Date primeraObs,Date ultimaObs,long minMagnitud,long maxDist,long minVel);
	
	
}
