package es.ucm.si.dneb.service.importData;

import java.util.List;

import es.ucm.si.dneb.domain.CargaDatos;

public interface ImportDDBBData {
	
	public List<CargaDatos> getAllDatosAImportar();
	
	public CargaDatos getCargaDatosById(Long id);
	
	public void generarTarea(List<CargaDatos> cargaDatos);

}
