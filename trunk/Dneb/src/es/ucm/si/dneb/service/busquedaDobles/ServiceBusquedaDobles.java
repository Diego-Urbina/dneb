package es.ucm.si.dneb.service.busquedaDobles;

import java.util.ArrayList;
import java.util.List;

import javax.media.jai.PlanarImage;

import es.ucm.si.dneb.domain.Image;
import es.ucm.si.dneb.domain.ImageProsec;
import es.ucm.si.dneb.service.image.segmentation.LectorImageHDU;
import es.ucm.si.dneb.service.image.util.Point;
import es.ucm.si.dneb.service.math.DecimalCoordinate;
import es.ucm.si.dneb.util.Pair;

public interface ServiceBusquedaDobles {
	
	public void iniciarProcesamiento(List<ImageProsec> procImgs);
	
	public ArrayList<Pair<Point>> busquedaEstrellasMovimiento(Image im1, Image im2);
	
	public ArrayList<Pair<Point>> busquedaEstrellasDobles(Image im1, Image im2);
	
	public DecimalCoordinate pixelToCoordinatesConverter(Image imagen, int width, int height, double x, double y);

	public PlanarImage createPlanarImage(LectorImageHDU l);
}
