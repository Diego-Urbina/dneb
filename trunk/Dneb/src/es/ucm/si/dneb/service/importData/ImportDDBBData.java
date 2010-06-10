package es.ucm.si.dneb.service.importData;

import java.util.List;

import es.ucm.si.dneb.domain.LoadData;
import es.ucm.si.dneb.domain.Survey;

public interface ImportDDBBData {
	
	public List<LoadData> getAllDatosAImportar();
	
	public LoadData getCargaDatosById(Long id);
	
	public void generarTarea(List<LoadData> cargaDatos,List<Survey> surveys);
	
	public Survey getSurveyByDesc(String survey);

}
