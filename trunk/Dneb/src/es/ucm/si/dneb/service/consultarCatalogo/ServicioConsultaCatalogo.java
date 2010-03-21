package es.ucm.si.dneb.service.consultarCatalogo;

import java.util.Date;
import java.util.List;

import com.sun.xml.internal.fastinfoset.algorithm.HexadecimalEncodingAlgorithm;

import es.ucm.si.dneb.domain.DoubleStarCatalog;
import es.ucm.si.dneb.service.math.DecimalCoordinate;
import es.ucm.si.dneb.service.math.SexagesimalCoordinate;

public interface ServicioConsultaCatalogo {
	
	public List<DoubleStarCatalog> consultaCatalogo(int limNumObs,Date primeraObs,Date ultimaObs,double minMagnitud,double maxDist,double minVel);
	
	public DoubleStarCatalog findDSCById(Long id);
	
	public DoubleStarCatalog findDSCBYCoordinates(SexagesimalCoordinate sexagesimalCoordinate);
	
	
	
	public void crearTareaDescarga(List<DoubleStarCatalog> dscList);
	
	
}
