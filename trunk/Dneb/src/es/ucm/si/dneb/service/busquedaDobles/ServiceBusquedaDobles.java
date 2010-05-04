package es.ucm.si.dneb.service.busquedaDobles;

import java.util.List;

import javax.media.jai.PlanarImage;

import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.ProcImagen;
import es.ucm.si.dneb.service.image.segmentation.LectorImageHDU;
import es.ucm.si.dneb.service.image.util.Point;
import es.ucm.si.dneb.service.math.DecimalCoordinate;

public interface ServiceBusquedaDobles {
	
	public void iniciarProcesamiento(List<ProcImagen> procImgs);
	
	public Point[][] busquedaEstrellasMovimiento(double umbral, double brillo, String filename1, String filename2, Imagen im1, Imagen im2);
	
	public DecimalCoordinate pixelToCoordinatesConverter(Imagen imagen, int width, int height, double x, double y);

	public PlanarImage createPlanarImage(LectorImageHDU l);
}
