package es.ucm.si.dneb.service.calculoPosicion;

import java.util.List;

import es.ucm.si.dneb.domain.Image;
import es.ucm.si.dneb.domain.ImageProsec;
import es.ucm.si.dneb.service.image.util.Point;

public interface ServiceCalculoPosicion {
	
	public void calcularPosicion(ImageProsec pi);
	
	public List<Point> algotirmoCalculoPosicion(double brillo, double umbral,int maxCandidatos, int maxEstrellas,double margenAngulo,double margenDistancia, double distanciaMinima,
			Image imagen);
	



}
