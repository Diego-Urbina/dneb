package es.ucm.si.dneb.service.inicializador;

import javax.swing.UnsupportedLookAndFeelException;

public interface ServicioInicializador {
	
	public void winLookAndFeel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException;
	
	public void inicializarContexto();
	
	public void chequeoConsistencia();
	
	public void initLookAndFeel(String theme);

}
