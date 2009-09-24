import java.awt.Point;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RasterFactory;
import javax.media.jai.TiledImage;

import nom.tam.fits.*;
import nom.tam.util.ArrayFuncs;

public class Principal {
	
	public static void main(String[] args) {
		
		try {
			// Cargo la imagen
			Fits imagenFITS = new Fits(new File("kk.fits"));			
			
			// Obtengo el primer HDU (la imagen)
			BasicHDU imageHDU = imagenFITS.getHDU(0);
			
			// Obtengo la matriz de datos
			LectorImageHDU l1 = new LectorImageHDU(imageHDU);
			short[][] arrayData = (short[][]) l1.getMatriz();
			int width = arrayData[0].length;
			int height = arrayData.length;
			
			// Arreglo la matriz
			l1.arreglarMatriz(
					arrayData, 									// Matriz de pixeles
					Float.parseFloat(l1.getValue("DATAMIN")),	// DataMin
					Float.parseFloat(l1.getValue("DATAMAX")));	// DataMax
			
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

			// Almacena 'tiledImage' en formato PNG
			JAI.create("filestore",tiledImage,"kk.png","PNG");
				
		} catch (FitsException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
