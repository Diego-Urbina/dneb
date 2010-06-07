package es.ucm.si.dneb.service.image.segmentation;

import java.io.File;
import java.io.IOException;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;


public class StarFinderApp {

	public static void main(String[] args) {		
		try {			
			// Cargo la imagen
			String filename = "AR10.491916666666667DEC45.12705555555556SURVposs1_blue.fits";
			Fits imagenFITS = new Fits(new File(filename));			
			
			// Obtengo el primer HDU (la imagen) y creo con el
			// un LectorImageHDU
			BasicHDU imageHDU = imagenFITS.getHDU(0);
			LectorImageHDU l1 = new LectorImageHDU(imageHDU, filename);
			
			System.out.println("Atributos imagen\n------------------");
			System.out.println("Filename:  " + l1.getFilename());
			System.out.println("Min:  " + l1.getMin());
			System.out.println("Max:  " + l1.getMax());
			System.out.println("Scale:  " + l1.getScale());
			System.out.println("Width:  " + l1.getWidth());
			System.out.println("Height:  " + l1.getHeight());
			System.out.println("Media:  " + l1.brilloMedio());
			System.out.println("Percentil:  " + l1.getNPercentile(99.55));
			
			StarFinder sf = new StarFinder();
			float umbral = new Float(l1.getNPercentile(99.55));
			float brilloEstrella = umbral + 2000;
			
			sf.buscarEstrellas(l1, brilloEstrella, umbral);
			
			System.out.println("\n\nRecuadros\n------------------");
			
			System.out.println("Numero de estrellas encontradas: " + sf.getNumberOfStars());
			
		} catch (FitsException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
