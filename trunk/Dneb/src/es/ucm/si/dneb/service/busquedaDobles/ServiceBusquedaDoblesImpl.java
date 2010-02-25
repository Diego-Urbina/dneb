package es.ucm.si.dneb.service.busquedaDobles;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.ParamProcTarea;
import es.ucm.si.dneb.domain.ProcImagen;
import es.ucm.si.dneb.service.image.segmentation.LectorImageHDU;
import es.ucm.si.dneb.service.image.segmentation.StarFinder;

@Service("serviceBusquedaDobles")
public class ServiceBusquedaDoblesImpl implements ServiceBusquedaDobles{
	
	@PersistenceContext
	private EntityManager manager;
	
	
	private static final Log LOG = LogFactory
	.getLog(ServiceBusquedaDoblesImpl.class);
		
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void iniciarProcesamiento(List<ProcImagen> procImgs) {
		// TODO Auto-generated method stub
		
		
		// Algoritmo de busqueda de estrellas
		String filename1 = procImgs.get(0).getImagen().getRutaFichero(), filename2 = procImgs.get(1).getImagen().getRutaFichero();
		
		Fits imagenFITS;
		try {
			imagenFITS = new Fits(new File(filename1));			
			BasicHDU imageHDU = imagenFITS.getHDU(0);
			LectorImageHDU l = new LectorImageHDU(imageHDU, filename1);
			StarFinder sf = new StarFinder();
			List<ParamProcTarea> paramProcTareas = procImgs.get(0).getTareaProcesamiento().getParametros();
			double brillo = 0, umbral = 0;
			for (int i = 0; i < paramProcTareas.size(); i++) {
				if (paramProcTareas.get(i).getTipoParametro().getIdTipoParametro() == 1) // brillo
					brillo = paramProcTareas.get(i).getValorNum();
				if (paramProcTareas.get(i).getTipoParametro().getIdTipoParametro() == 2) // umbral
					umbral = paramProcTareas.get(i).getValorNum();
			}
			sf.buscarEstrellas(l, new Float(brillo), new Float(umbral));
			System.out.println("Numero de estrellas encontradas: " + sf.getNumberOfStars());
			
			imagenFITS = new Fits(new File(filename2));			
			imageHDU = imagenFITS.getHDU(0);
			l = new LectorImageHDU(imageHDU, filename2);
			sf = new StarFinder();
			sf.buscarEstrellas(l, new Float(brillo), new Float(umbral));
			System.out.println("Numero de estrellas encontradas: " + sf.getNumberOfStars());
		} catch (FitsException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		procImgs.get(0).setFinalizada(true);
		procImgs.get(1).setFinalizada(true);
		
		manager.merge(procImgs.get(0));
		manager.merge(procImgs.get(1));
		
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public static Log getLog() {
		return LOG;
	}
	
	

}
