package es.ucm.si.dneb.service.calculoPosicion;

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

import es.ucm.si.dneb.domain.ParamImg;
import es.ucm.si.dneb.domain.ParamProcTarea;
import es.ucm.si.dneb.domain.ProcImagen;
import es.ucm.si.dneb.domain.ProcTarea;
import es.ucm.si.dneb.service.creacionTareas.ServicioCreacionTareas;
import es.ucm.si.dneb.service.image.segmentation.LectorImageHDU;
import es.ucm.si.dneb.service.image.segmentation.RectStar;
import es.ucm.si.dneb.service.image.segmentation.StarFinder;

@Service("serviceCalculoPosicion")
public class ServiceCalculoPosicionImpl implements ServiceCalculoPosicion{
	
	private static final Log LOG = LogFactory
	.getLog(ServiceCalculoPosicionImpl.class);

	@PersistenceContext
	EntityManager manager;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void calcularPosicion(ProcImagen pi) {
		
		
		//Saco los parámetro de la imagen
		List<ParamImg> paramsImg =pi.getParams();
		
		//Busco las estrellas
		
		String filename1 = pi.getImagen().getRutaFichero();
		
		Fits imagenFITS;
		List<RectStar> recStars= new ArrayList<RectStar>();
		try {
			imagenFITS = new Fits(new File(filename1));			
			BasicHDU imageHDU = imagenFITS.getHDU(0);
			LectorImageHDU l = new LectorImageHDU(imageHDU, filename1);
			StarFinder sf = new StarFinder();
			List<ParamProcTarea> paramProcTareas = pi.getTareaProcesamiento().getParametros();
			double brillo = 0, umbral = 0;
			for (int i = 0; i < paramProcTareas.size(); i++) {
				if (paramProcTareas.get(i).getTipoParametro().getIdTipoParametro() == 1) // brillo
					brillo = paramProcTareas.get(i).getValorNum();
				if (paramProcTareas.get(i).getTipoParametro().getIdTipoParametro() == 2) // umbral
					umbral = paramProcTareas.get(i).getValorNum();
			}
			sf.buscarEstrellas(l, new Float(brillo), new Float(umbral));
			System.out.println("Numero de estrellas encontradas: " + sf.getNumberOfStars());
			recStars=sf.getRecuadros();
			
		} catch (FitsException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Calculo los centros
		
		//Filtro de candidatas por brillo
		
		//Ojo a los colores
		
		//Sobre las estrellas calculo las distancias por parejas???
		
		
	}

}
