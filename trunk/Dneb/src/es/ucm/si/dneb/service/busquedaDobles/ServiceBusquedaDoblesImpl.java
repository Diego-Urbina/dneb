package es.ucm.si.dneb.service.busquedaDobles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import es.ucm.si.dneb.service.image.segmentation.RectStar;
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
		
		try {
			
			// Crear imagenes, buscar estrellas y obtener los N recuadros mas grandes
			Fits imagenFITS1 = new Fits(new File(filename1));			
			BasicHDU imageHDU1 = imagenFITS1.getHDU(0);
			LectorImageHDU l1 = new LectorImageHDU(imageHDU1, filename1);
			StarFinder sf = new StarFinder();
			List<ParamProcTarea> paramProcTareas = procImgs.get(0).getTareaProcesamiento().getParametros();
			double brillo = 0, umbral = 0;
			for (int i = 0; i < paramProcTareas.size(); i++) {
				if (paramProcTareas.get(i).getTipoParametro().getIdTipoParametro() == 1) // brillo
					brillo = paramProcTareas.get(i).getValorNum();
				if (paramProcTareas.get(i).getTipoParametro().getIdTipoParametro() == 2) // umbral
					umbral = paramProcTareas.get(i).getValorNum();
			}
			sf.buscarEstrellas(l1, new Float(brillo), new Float(umbral));
			System.out.println("Numero de estrellas encontradas: " + sf.getNumberOfStars());
			ArrayList<RectStar> masGrandes1 = sf.getNRecuadrosMasGrandes(4);
			
			Fits imagenFITS2 = new Fits(new File(filename2));			
			BasicHDU imageHDU2 = imagenFITS2.getHDU(0);
			LectorImageHDU l2 = new LectorImageHDU(imageHDU2, filename2);
			sf = new StarFinder();
			sf.buscarEstrellas(l2, new Float(brillo), new Float(umbral));
			System.out.println("Numero de estrellas encontradas: " + sf.getNumberOfStars());
			ArrayList<RectStar> masGrandes2 = sf.getNRecuadrosMasGrandes(4);
			
			// Calcular los centroides de los recuadros de ambas imagenes
			
			
			procImgs.get(0).setFinalizada(true);
			procImgs.get(1).setFinalizada(true);
			
			manager.merge(procImgs.get(0));
			manager.merge(procImgs.get(1));
		
		} catch (FitsException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
