package es.ucm.si.dneb.service.calculoPosicion;

import java.util.List;

import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.ProcImagen;
import es.ucm.si.dneb.service.image.util.Point;

public interface ServiceCalculoPosicion {
	
	public void calcularPosicion(ProcImagen pi);
	
	public List<Point> algotirmoCalculoPosicion(double brillo, double umbral,
			Imagen imagen);
	



}
