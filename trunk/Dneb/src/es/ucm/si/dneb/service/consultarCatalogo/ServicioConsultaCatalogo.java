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
	
	public List<DoubleStarCatalog> consultaAvanzadaCatalogo(Integer ciobs,Integer csobs,Date ciDateprimObs,Date csDateprimObs,Date ciDateUltObs,Date csDateUltObs,Double cimag1,Double csmag1,Double ciDespAR1,Double csDespAR1,Double ciDespDEC1,Double csDespDEC1,Double ciDist,Double csDist,Double ciAng,Double csAng,Double cimag2,Double csmag2,Double ciDespAR2,Double csDespAR2,Double ciDespDEC2,Double csDespDEC2);
	
	
	public void crearTareaDescarga(List<DoubleStarCatalog> dscList);
	
	
}
