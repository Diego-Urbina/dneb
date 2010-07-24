package es.ucm.si.dneb.service.gestionHilos;

public interface GestorHilos<T> {
	
	public abstract void anadirHilo(T t);
	
	public void eliminarHilo(Long id) ;
	
	public void iniciarHilo(Long idHilo);	
	
	public void interrumpirHilo(Long idHilo);	
}
