package es.ucm.si.dneb.service.gestionHilos;


public interface EjecutorTarea<Core>{
	
	public void ejecutar(Interrumpible inter);
	
	public Long getId();
	
	public void setId(Long id);
	
	public Core getCore();
	
	public void setCore(Core core);
	
	
}