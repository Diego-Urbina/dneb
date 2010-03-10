package es.ucm.si.dneb.service.creacionTareas;

import java.util.List;

import es.ucm.si.dneb.domain.DoubleStarCatalog;

public interface ServicioCreacionTareas {
	
	public void crearTarea(String arInicial,String arFinal,String decInicial,String decFinal,double alto,double ancho,double solapamiento,String surveyOld, String surveynNew,String formato,String ruta);
	
	public void crearTarea(List<DoubleStarCatalog> dsc);
}
