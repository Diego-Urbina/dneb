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
public class ServicioConsultaCatalogoImpl implements ServicioConsultaCatalogo {

	private static final Log LOG = LogFactory
			.getLog(ServicioConsultaCatalogoImpl.class);

	@PersistenceContext
	EntityManager manager;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<DoubleStarCatalog> consultaCatalogo(int limNumObs,
			Date primeraObs, Date ultimaObs, double minMagnitud,
			double maxDist, double minVel) {
		// TODO Auto-generated method stub

		String query = "select d from DoubleStarCatalog d where numObservations<=? and firstObservation<=? and lastObservation<=? and firstStarMagnitude<=? and lastSeparation<=?";

		List<DoubleStarCatalog> dsc = manager.createQuery(query).setParameter(
				1, limNumObs).setParameter(2, primeraObs).setParameter(3,
				ultimaObs).setParameter(4, minMagnitud)
				.setParameter(5, maxDist).getResultList();
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
	@Transactional(propagation = Propagation.SUPPORTS)
	public DoubleStarCatalog findDSCBYCoordinates(
			SexagesimalCoordinate sexagesimalCoordinate) {

		List<DoubleStarCatalog> dscList = (List<DoubleStarCatalog>) manager
				.createQuery(
						"select d from DoubleStarCatalog d where  arcsecondCoordinates2000=?")
				.setParameter(1, sexagesimalCoordinate.paint()).getResultList();
		if (dscList.size() > 0) {
			return dscList.get(0);
		} else {
			throw new ServicioConsultaCatalogoException("No result found");
		}
	}

	@Override
	public List<DoubleStarCatalog> consultaAvanzadaCatalogo(Integer ciobs,
			Integer csobs, Date ciDateprimObs, Date csDateprimObs,
			Date ciDateUltObs, Date csDateUltObs, Double cimag1, Double csmag1,
			Double ciDespAR1, Double csDespAR1, Double ciDespDEC1,
			Double csDespDEC1, Double ciDist, Double csDist, Double ciAng,
			Double csAng, Double cimag2, Double csmag2, Double ciDespAR2,
			Double csDespAR2, Double ciDespDEC2, Double csDespDEC2) {
		
		
		
		
		
		List<DoubleStarCatalog> dsc= manager.createNamedQuery("Dsc:AdvancedSearch").setParameter(1, ciobs).setParameter(2,csobs ).setParameter(3, ciDateprimObs).setParameter(4, csDateprimObs).setParameter(5,ciDateUltObs ).setParameter(6,csDateUltObs ).setParameter(7, cimag1).setParameter(8, csmag1).setParameter(9,ciDespAR1 ).setParameter(10,csDespAR1 ).setParameter(11,ciDespDEC1 ).setParameter(12,csDespDEC1 ).setParameter(13,ciDist ).setParameter(14,csDist ).setParameter(15,ciAng ).setParameter(16, csAng).setParameter(17, cimag2).setParameter(18, csmag2).setParameter(19,ciDespAR2 ).setParameter(20,csDespAR2 ).setParameter(21,ciDespDEC2 ).setParameter(22,csDespDEC2 ).getResultList();
		LOG.info("Se han obtenido"+dsc.size()+" estrellas del catálogo");
		return dsc;
		
	}
}
