package es.ucm.si.dneb.service.calculoPosicion;

import java.util.List;

import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.ProcImagen;
import es.ucm.si.dneb.service.image.util.Point;

public interface ServiceCalculoPosicion {
	
	public void calcularPosicion(ProcImagen pi);
	
	public List<Point> calcularPosicion(Imagen imagen,double brillo, double umbral);
	



}
