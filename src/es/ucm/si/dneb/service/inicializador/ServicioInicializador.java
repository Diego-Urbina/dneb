package es.ucm.si.dneb.service.inicializador;

import java.util.Date;


public interface ServicioInicializador {
	public void inicializar();
	
	public void eleminarTareasHistoricas(Date fecha);
	
	public void chequeoConsistencia();
	
	public void generarTareaSobreDatosManuales();

}
