package es.ucm.si.dneb.service.gestionHilos;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.domain.TareaProcesamiento;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;

@Service("gestorCalculoPosicion")
public class GestorCalculoPosicion implements GestorHilos<TareaProcesamiento>{
	
	
	private HashMap <Long,Hilo> hilos;
	
	private static final Log LOG = LogFactory
	.getLog(GestorCalculoPosicion.class);
	
	public GestorCalculoPosicion(){
		
	}

	@Override
	public void anadirHilo(TareaProcesamiento t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eleminarHilo(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void iniciarHilo(Long idHilo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interrumpirHilo(Long idHilo) {
		// TODO Auto-generated method stub
		
	}

	public void setHilos(HashMap <Long,Hilo> hilos) {
		this.hilos = hilos;
	}

	public HashMap <Long,Hilo> getHilos() {
		return hilos;
	}
	
	
	

	

}
