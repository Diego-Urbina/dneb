import java.awt.Point;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RasterFactory;
import javax.media.jai.TiledImage;

import com.sun.media.jai.widget.DisplayJAI;

import nom.tam.fits.*;
import nom.tam.util.ArrayFuncs;

public class Principal {
	
	public static void main(String[] args) {
		
		try {
			// Cargo la imagen
			String filename = "kk.fits";
			Fits imagenFITS = new Fits(new File(filename));			
			
			// Obtengo el primer HDU (la imagen) y creo con el
			// un LectorImageHDU
			BasicHDU imageHDU = imagenFITS.getHDU(0);
			LectorImageHDU l1 = new LectorImageHDU(imageHDU, filename);
			
			// Arreglo la matriz
			//l1.arreglarMatriz();  Ahora es private
			
			// La aplano
			int[] arrayDataAplanado = (int[]) ArrayFuncs.flatten(l1.getArrayData());
			
			// Codigo copiado de internet q no entiendo pero q me sirve para
			// guardar la imagen en PNG
			DataBufferInt dBuffer = new DataBufferInt(arrayDataAplanado, l1.getWidth()*l1.getHeight());
			SampleModel sm = RasterFactory.createBandedSampleModel(DataBuffer.TYPE_SHORT, l1.getWidth(), l1.getHeight(), 1);
			ColorModel cm = PlanarImage.createColorModel(sm);
			Raster raster = RasterFactory.createWritableRaster(sm, dBuffer, new Point(0,0));
			TiledImage tiledImage = new TiledImage(0,0,l1.getWidth(),l1.getHeight(),0,0,sm,cm);
			tiledImage.setData(raster);
			
			
			System.out.println("Filename:  " + l1.getFilename());
			System.out.println("Min:  " + l1.getMin());
			System.out.println("Max:  " + l1.getMax());
			System.out.println("Scale:  " + l1.getScale());
			System.out.println("Width:  " + l1.getWidth());
			System.out.println("Height:  " + l1.getHeight());
			
			
			
			// Almaceno 'tiledImage' en formato PNG
			JAI.create("filestore",tiledImage,"kk.png","PNG");
			// Y la cargo
			PlanarImage imagenCargada = JAI.create("fileload","kk.png");
			
			// Usar un displayJAI u otro dependiendo de si se quiere mostrar
			// la imagen cargada desde disco o la que ya tenemos
			DisplayJAI dis = new DisplayJAI(imagenCargada);
			//DisplayJAI dis = new DisplayJAI(tiledImage);
			
			new Ventana(dis);
				
		} catch (FitsException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
