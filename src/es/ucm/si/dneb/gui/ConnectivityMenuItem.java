package es.ucm.si.dneb.gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import es.ucm.si.dneb.service.inicializador.ConnectionTestObserver;

public class ConnectivityMenuItem extends JMenuItem implements Observer{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8929046531572864768L;

	public ConnectivityMenuItem(){
		this.setText("Desconectado");
		this.setIcon(new ImageIcon("images/discon.png"));
		this.validate();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		//this.setIcon()
		ConnectionTestObserver test= (ConnectionTestObserver)arg0;
		
		if(test.isConnected()){
			this.setText("Conectado");
			this.setIcon(new ImageIcon("images/conne.png"));
		}else{
			this.setText("Desconectado");
			this.setIcon(new ImageIcon("images/discon.png"));
		}
		this.validate();
	}

}
