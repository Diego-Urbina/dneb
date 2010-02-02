package es.ucm.si.dneb.service.gestionHilos;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import es.ucm.si.dneb.domain.TareaProcesamiento;

@Service("gestorCalculoPosicion")
public class GestorCalculoPosicion implements GestorHilos<TareaProcesamiento>{
	
	
	private Map<Long,Hilo> hilos;
	
	private static final Log LOG = LogFactory
	.getLog(GestorCalculoPosicion.class);
	
	public GestorCalculoPosicion(){
		
		hilos = new HashMap<Long, Hilo>();
		
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

	public Map<Long, Hilo> getHilos() {
		return hilos;
	}

	public void setHilos(Map<Long, Hilo> hilos) {
		this.hilos = hilos;
	}

	public static Log getLog() {
		return LOG;
	}

	
	
	
	

	

}
