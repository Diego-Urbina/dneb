import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.media.jai.widget.DisplayJAI;


public class Ventana extends JFrame {
	
	public Ventana(DisplayJAI dis) {
		super();
		JPanel jpanel0 = new JPanel();
		jpanel0.add(dis);
		Container cp = getContentPane();
	    cp.add(jpanel0,BorderLayout.CENTER);
		pack();
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	}

}
