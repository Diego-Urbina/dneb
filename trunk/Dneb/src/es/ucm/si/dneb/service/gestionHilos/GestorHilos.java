package es.ucm.si.dneb.service.gestionHilos;

import java.util.List;

import es.ucm.si.dneb.domain.Tarea;

public interface GestorHilos {

	public void anadirHilo(Tarea tarea);
	public void crearHilosParaTodasLasTareas(List<Tarea> tareas);
	public void eleminarHilo(Long id);
	public void iniciarHilo(Long idHilo);
	public void interrumpirHilo(Long idHilo);
	
}
