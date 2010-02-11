package es.ucm.si.dneb.service.importData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.CargaDatos;

@Service("importDDBBData")
public class ImportDDBBDataImpl implements ImportDDBBData{
	
	@PersistenceContext
	EntityManager manager;
	
	private static final  Log LOG = LogFactory.getLog(ImportDDBBDataImpl.class);
	

	@Transactional(propagation = Propagation.REQUIRED)
	public void generarTarea(List<CargaDatos> cargaDatos) {
		// TODO Auto-generated method stub
		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CargaDatos> getAllDatosAImportar() {
		return manager.createQuery("select c from CargaDatos c where c.procesado=false").getResultList();
		
	}

	@Override
	public CargaDatos getCargaDatosById(Long id) {
		
		return manager.find(CargaDatos.class, id);
	}

}
