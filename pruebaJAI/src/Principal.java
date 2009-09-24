import java.awt.Point;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RasterFactory;
import javax.media.jai.TiledImage;

import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;
import nom.tam.fits.ImageHDU;
import nom.tam.util.ArrayFuncs;

public class Principal {
	
	public static void main(String[] args) {
		
		try {
			// Cargo la imagen
			File file = new File("kk.fits");
			Fits imagenFITS = new Fits(file);			
			
			// Obtengo el primer HDU (la imagen)
			ImageHDU imageHDU = (ImageHDU) imagenFITS.getHDU(0);
			
			// Obtengo la matriz de datos
			LectorHDU l1 = new LectorImageHDU(imageHDU);
			short[][] arrayData = (short[][]) l1.getMatriz();
			int width = arrayData[0].length;
			int height = arrayData.length;
			
			
			float dataMin = Float.parseFloat(l1.getValue("DATAMIN"));
			float dataMax = Float.parseFloat(l1.getValue("DATAMAX"));
			
			arreglarMatriz(arrayData, dataMax, dataMin);
			
			// La aplano
			short[] arrayDataAplanado = (short[]) ArrayFuncs.flatten(arrayData);
			
			// Codigo copiado de internet q no entiendo pero q me sirve para
			// guardar la imagen en PNG
			DataBufferShort dBuffer = new DataBufferShort(arrayDataAplanado, width*height);
			SampleModel sm = RasterFactory.createBandedSampleModel(DataBuffer.TYPE_SHORT, width, height, 1);
			ColorModel cm = PlanarImage.createColorModel(sm);
			Raster raster = RasterFactory.createWritableRaster(sm, dBuffer, new Point(0,0));
			TiledImage tiledImage = new TiledImage(0,0,width,height,0,0,sm,cm);
			tiledImage.setData(raster);

			JAI.create("filestore",tiledImage,"kk.png","PNG");
				
		} catch (FitsException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void arreglarMatriz(short[][] m, float dataMax, float dataMin) {
		/* Simetrica de la matriz 'm' respecto del
		 * eje x. Es necesario hacer esto ya que
		 * en una imagen FITS, las coordenadas (0,0)
		 * pertenecen a la esquina inferior izquierda
		 * y por tanto la primera fila es la de abajo.
		 */
		int numFilas = m.length;
		short temp;
		for (int i=0; i<numFilas/2; i++)
			for (int j=0; j<m[i].length; j++){
				temp = m[i][j];
				m[i][j] = m[numFilas - 1 - i][j];
				m[numFilas - 1 - i][j] = temp;
			}
		
		
		/* Cambio la escala para que el valor del pixel se mueva
		 * entre 0 y 65535
		 */
		float dif = dataMax - dataMin;
		float scale = (dif) / (2*32767);
		
		for (int i=0; i<m.length; i++)
			for (int j=0; j<m[i].length; j++){
				m[i][j] = (short) ((m[i][j] - dataMin) / scale);
			}
	}
}
