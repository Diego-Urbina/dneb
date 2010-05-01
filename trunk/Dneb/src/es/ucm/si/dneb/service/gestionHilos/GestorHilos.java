package es.ucm.si.dneb.service.gestionHilos;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;

public interface GestorHilos<T>{

	
	public abstract void anadirHilo(T t);

	
	public void eliminarHilo(Long id) ;

	
	public void iniciarHilo(Long idHilo);
	
	
	public void interrumpirHilo(Long idHilo);



	
}
