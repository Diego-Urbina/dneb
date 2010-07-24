package es.ucm.si.dneb.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

class BackgroundPanel extends JPanel{

	private static final long serialVersionUID = 5000145994083961664L;

	private Image image;
	
	public BackgroundPanel(final JTabbedPane pane) {
		image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("deneb_med.jpg"));
	}	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); 
		if (image != null)
				g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	}	
}
