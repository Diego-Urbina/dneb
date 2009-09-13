package es.ucm.si.dneb.gui;

import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.event.PopupMenuListener;

public class VentanaPcpal extends JFrame{
	
	MapPanel vent;
	
	
	
	
	public VentanaPcpal(int alto, int ancho){
		super("HttpImageDownloader"); 
		
		vent= new MapPanel();
		vent.setVisible(true);
		this.getContentPane().add(vent);
		
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    setSize(ancho, alto); 
	    setVisible(true); 
	    
	   
	    
	}

}
