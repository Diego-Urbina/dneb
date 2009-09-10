package es.ucm.si.dneb.gui;

import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.event.PopupMenuListener;

public class VentanaPcpal extends JFrame{
	
	JLabel arLabel;
	JTextField arField;
	
	JLabel decLabel;
	JTextField decField;
	
	JRadioButton mer1;
	JRadioButton mer2;
	ButtonGroup mer;
	
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
	    
	    mer1= new JRadioButton("mer1");
	    mer2= new JRadioButton("mer2");
	    mer = new ButtonGroup();
	    
	    mer.add(mer1);
	    mer.add(mer2);
	    
	    add(mer1);
	    add(mer2);
	    
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    setSize(ancho, alto); 
	    setVisible(true); 	
	}

}
