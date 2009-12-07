import java.io.File;
import java.io.IOException;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;


public class StarFinderApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		try {			
			// Cargo la imagen
			String filename = "kk.fits";
			Fits imagenFITS = new Fits(new File(filename));			
			
			// Obtengo el primer HDU (la imagen) y creo con el
			// un LectorImageHDU
			BasicHDU imageHDU = imagenFITS.getHDU(0);
			LectorImageHDU l1 = new LectorImageHDU(imageHDU, filename);
			
			System.out.println("Atributos imagen\n------------------");
			System.out.println("Filename:  " + l1.getFilename());
			System.out.println("Min:  " + l1.getMin());
			System.out.println("Max:  " + l1.getMax());
			System.out.println("Average:  " + l1.getAverage());
			System.out.println("Scale:  " + l1.getScale());
			System.out.println("Width:  " + l1.getWidth());
			System.out.println("Height:  " + l1.getHeight());
			
			RectStar r = new RectStar(56,151);
			float umbral = 10000;
			r.extender(l1.getArrayData(), umbral);
			System.out.println("\n\nRecuadros\n------------------");
			System.out.println("Esquina sup izq: (" + r.getxLeft()+","+r.getyTop()+")");
			System.out.println("Esquina inf der: (" + r.getxRight()+","+r.getyBot()+")");
			
			//System.out.println(l1.getPixel(91,114));
		} catch (FitsException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
