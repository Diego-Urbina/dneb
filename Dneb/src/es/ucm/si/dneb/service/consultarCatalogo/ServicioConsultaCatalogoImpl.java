package es.ucm.si.dneb.service.consultarCatalogo;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.DoubleStarCatalog;
import es.ucm.si.dneb.service.math.SexagesimalCoordinate;

@Service("servicioConsultaCatalogo")
public class ServicioConsultaCatalogoImpl implements ServicioConsultaCatalogo{
	
	private static final Log LOG = LogFactory
	.getLog(ServicioConsultaCatalogoImpl.class);

	@PersistenceContext
	EntityManager manager;

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<DoubleStarCatalog> consultaCatalogo(int limNumObs,
			Date primeraObs, Date ultimaObs, double minMagnitud, double maxDist,
			double minVel) {
		// TODO Auto-generated method stub
		
		String query ="select d from DoubleStarCatalog d where numObservations<=? and firstObservation<=? and lastObservation<=? and firstStarMagnitude<=? and lastSeparation<=?";
		
		
		List<DoubleStarCatalog> dsc= manager.createQuery(query).setParameter(1, limNumObs).setParameter(2, primeraObs).setParameter(3, ultimaObs).setParameter(4, minMagnitud).setParameter(5, maxDist).getResultList();
		return dsc;
	}

	public static Log getLog() {
		return LOG;
	}

	@Override
	public DoubleStarCatalog findDSCById(Long id) {
		
		return manager.find(DoubleStarCatalog.class, id);
	}

	@Override
	public void crearTareaDescarga(List<DoubleStarCatalog> dscList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public DoubleStarCatalog findDSCBYCoordinates(
			SexagesimalCoordinate sexagesimalCoordinate) {
		
			List<DoubleStarCatalog> dscList= (List<DoubleStarCatalog>) manager.createQuery("select d from DoubleStarCatalog d where  arcsecondCoordinates2000=?").setParameter(1, sexagesimalCoordinate.paint()).getResultList();
			if(dscList.size()>0){
				return dscList.get(0);
			}else{
				throw new ServicioConsultaCatalogoException("No result found");
			}
	}
}
