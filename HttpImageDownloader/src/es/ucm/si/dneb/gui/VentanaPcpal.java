package es.ucm.si.dneb.gui;

import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.event.PopupMenuListener;

public class VentanaPcpal extends JFrame{
	
	JLabel arLabel;
	JTextField arField;
	
	JLabel decLabel;
	JTextField decField;
	
	JRadioButton mer;
	
	JButton download;
	
	public VentanaPcpal(int alto, int ancho){
		super("HttpImageDownloader"); 
		setLayout(new FlowLayout()); 
	    arLabel = new JLabel("Ascensión recta"); 
	    add(arLabel);
	    arField= new JTextField("fdfd");    
	    add(arField);
	    
	    download=new JButton("DOWNLOAD");
	    add(download);
	    
	    mer= new JRadioButton();
	    
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    setSize(ancho, alto); 
	    setVisible(true); 	
	}

}
