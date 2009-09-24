import java.io.File;
import java.io.IOException;
import nom.tam.fits.*;

public class Principal {

	public static void main(String[] args) {
		/* Un fichero FITS esta formado por uno o varios
		 * HDU (Header/Data Unit). A su vez, cada HDU esta
		 * compuesto por la cabecera (Header, obligatoria) y 
		 * los datos (Data, opcionales).
		 * 
		 * La cabecera son una serie de pares atributo-valor
		 * y los datos son un array multidimensional (en este
		 * caso es de 2 dimensiones por ser una imagen).
		 * 
		 * Otra cosa a tener en cuenta es que en una imagen
		 * FITS el pixel (0,0) es el de abajo a la izquierda.
		 * CUIDADO con esto que las imagenes pueden salir al reves.
		 * 
		 * Ademas, el tamaño de la cabecera tiene que ser multiplo
		 * de 2880 bytes, añadiendo relleno para conseguirlo.
		 * Estos elementos de relleno estan a null pero el iterador
		 * los recorre. CUIDADO con esto.
		 * 
		 * En este ejemplo, cargo la imagen "kk.fits", obtengo
		 * el primer HDU (la imagen (el segundo es una tabla que
		 * no se que contiene)) y recorro la cabecera y los datos.
		 * 
		 * 
		 * Puedo comparar los resultados con los obtenidos aqui:
		 *   http://fits.gsfc.nasa.gov/fits_verify.html
		 * o con esta aplicacion:
		 *   http://heasarc.gsfc.nasa.gov/docs/software/ftools/fv/
		 */
		
			
		try {
			
			// Cargo la imagen
			File file = new File("kk.fits");
			System.out.println(file.getAbsolutePath());
			Fits imagenFITS;
			imagenFITS = new Fits(file);
			
			
			
			// Obtengo el primer HDU (la imagen)
			ImageHDU imageHDU = (ImageHDU) imagenFITS.getHDU(0);
			System.out.println(imageHDU.toString());
			
			// Leo los datos y la cabecera del primer HDU
			LectorImageHDU l1 = new LectorImageHDU(imageHDU);
			l1.leerMatriz();
			l1.leerCabecera();
			
			System.out.println("maximo "+l1.getBrilloMaximo());
			System.out.println("medio "+l1.getBrilloMedio());
			System.out.println("minimo " +l1.getBrilloMinimo());
			System.out.println(l1.numPuntosQueSuperanUnCiertoBrillo((short) 5000));
			System.out.println(l1.PuntosQueSuperanUnCiertoBrillo((short) 5000).size());
			
			l1.getPicosPorFilas();
			
			
			// Comprobando el segundo HDU
			/*
			// Obtengo el segundo HDU (la tabla)
			AsciiTableHDU asciiTableHDU = (AsciiTableHDU) imagenFITS.getHDU(1);
			System.out.println(asciiTableHDU.toString());
			
			// Leo los datos y la cabecera del segundo HDU
			LectorHDU l2 = new LectorAsciiTableHDU(asciiTableHDU);
			l2.leerMatriz();
			l2.leerCabecera();
			*/
			
			
		} catch (FitsException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

}
