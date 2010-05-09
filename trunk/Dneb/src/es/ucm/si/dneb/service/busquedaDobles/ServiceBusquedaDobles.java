package es.ucm.si.dneb.service.busquedaDobles;

import java.util.ArrayList;
import java.util.List;

import javax.media.jai.PlanarImage;

import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.ProcImagen;
import es.ucm.si.dneb.service.image.segmentation.LectorImageHDU;
import es.ucm.si.dneb.service.image.util.Point;
import es.ucm.si.dneb.service.math.DecimalCoordinate;
import es.ucm.si.dneb.util.Pair;

public interface ServiceBusquedaDobles {
	
	public void iniciarProcesamiento(List<ProcImagen> procImgs);
	
	public ArrayList<Pair<Point>> busquedaEstrellasMovimiento(Imagen im1, Imagen im2);
	
	public ArrayList<Pair<Point>> busquedaEstrellasDobles(Imagen im1, Imagen im2);
	
	public DecimalCoordinate pixelToCoordinatesConverter(Imagen imagen, int width, int height, double x, double y);

	public PlanarImage createPlanarImage(LectorImageHDU l);
}
