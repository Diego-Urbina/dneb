package es.ucm.si.dneb.service.image.segmentation;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.ucm.si.dneb.service.importData.ImportDoubleStarCatalogImpl;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;


public class StarFinderApp {
	
	private static final  Log LOG = LogFactory.getLog(StarFinderApp.class);

	public static void main(String[] args) {		
		try {			
			// Cargo la imagen
			String filename = "images/pos2.fits";
			Fits imagenFITS = new Fits(new File(filename));			
			
			// Obtengo el primer HDU (la imagen) y creo con el
			// un LectorImageHDU
			BasicHDU imageHDU = imagenFITS.getHDU(0);
			LectorImageHDU l1 = new LectorImageHDU(imageHDU, filename);
			
			LOG.debug("Atributos imagen\n------------------");
			LOG.debug("Filename:  " + l1.getFilename());
			LOG.debug("Min:  " + l1.getMin());
			LOG.debug("Max:  " + l1.getMax());
			LOG.debug("Scale:  " + l1.getScale());
			LOG.debug("Width:  " + l1.getWidth());
			LOG.debug("Height:  " + l1.getHeight());
			
			StarFinder sf = new StarFinder();
			float umbral = 10000;
			float brilloEstrella = 40000;
			
			sf.buscarEstrellas(l1, brilloEstrella, umbral);
			
			System.out.println("\n\nRecuadros\n------------------");
			//sf.printRectStars();
			
			System.out.println("Numero de estrellas encontradas: " + sf.getNumberOfStars());
			
		} catch (FitsException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
