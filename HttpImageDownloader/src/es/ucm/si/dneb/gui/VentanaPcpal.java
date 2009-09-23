package es.ucm.si.dneb.gui;

import javax.swing.*;
import es.ucm.si.dneb.service.gestionTareas.*;

public class VentanaPcpal extends JFrame{
	
	String survey1, survey2, ari, deci, arf, decf, eq, alto, ancho, solapamiento, ruta;
	
	public VentanaPcpal(){
		super("HttpImageDownloader"); 
				
		JPanel vent = new MenuPanel(this);
		vent.setVisible(true);
		this.getContentPane().add(vent);
		
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    setSize(390, 300);
	    this.setResizable(false);
	    this.pack();
	    setVisible(true); 
	    
	}

}
