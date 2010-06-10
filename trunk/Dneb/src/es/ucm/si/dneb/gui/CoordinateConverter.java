package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.intellij.uiDesigner.core.*;

import es.ucm.si.dneb.service.math.*;

public class CoordinateConverter extends JPanel {
	
	private static final long serialVersionUID = 6657944572549232546L;
	public CoordinateConverter() {
		initComponents();
	}

	private void decToSexActionPerformed(ActionEvent e) {
		try{
			DecimalCoordinate dc= new DecimalCoordinate();
			String s;
			
			s = this.ar.getText();
			if (s.equals(""))
				dc.setAr(0);
			else
				dc.setAr(Double.parseDouble(s));
			
			s = this.dec.getText();
			if (s.equals(""))
				dc.setDec(0);
			else
				dc.setDec(Double.parseDouble(s));
			
			SexagesimalCoordinate sc = es.ucm.si.dneb.service.math.CoordinateConverter.decimalToSexagesimalConverter(dc);
			
			arh.setText(""+sc.getArh());
			armin.setText(""+sc.getArmin());
			arsec.setText(""+sc.getArsec());
			
			decGrad.setText(""+sc.getDech());
			decMin.setText(""+sc.getDecmin());
			decSec.setText(""+sc.getDecsec());			
			
		}catch(Exception exc){
			JOptionPane.showMessageDialog(null, "Los datos introducidos no son validos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void SexToDecActionPerformed(ActionEvent e) {
		try{
			SexagesimalCoordinate sc= new SexagesimalCoordinate();
			String s;
			
			s = this.arh.getText();
			if (s.equals(""))
				sc.setArh(0);
			else
				sc.setArh(Double.parseDouble(s));
			
			s = this.armin.getText();
			if (s.equals(""))
				sc.setArmin(0);
			else
				sc.setArmin(Double.parseDouble(s));
			
			s = this.arsec.getText();
			if (s.equals(""))
				sc.setArsec(0);
			else
				sc.setArsec(Double.parseDouble(s));

			s = this.decGrad.getText();
			if (s.equals(""))
				sc.setDech(0);
			else
				sc.setDech(Double.parseDouble(s));
			
			s = this.decMin.getText();
			if (s.equals(""))
				sc.setDecmin(0);
			else
				sc.setDecmin(Double.parseDouble(s));
			
			s = this.decSec.getText();
			if (s.equals(""))
				sc.setDecsec(0);
			else
				sc.setDecsec(Double.parseDouble(s));
			
			DecimalCoordinate dc= es.ucm.si.dneb.service.math.CoordinateConverter.sexagesimalToDecimalConverter(sc);
			
			ar.setText(""+dc.getAr());
			dec.setText(""+dc.getDec());
			
		}catch(Exception exc){
			JOptionPane.showMessageDialog(null, "Los datos introducidos no son validos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cleanActionPerformed(ActionEvent e) {			
		ar.setText("");
		dec.setText("");
		
		arh.setText("");
		armin.setText("");
		arsec.setText("");
		
		decGrad.setText("");
		decMin.setText("");
		decSec.setText("");
	}

	private void initComponents() {
		gradosDec = new JLabel();
		gradosSex = new JLabel();
		arLabel = new JLabel();
		ar = new JTextField();
		arhLabel = new JLabel();
		arh = new JTextField();
		decLabel = new JLabel();
		dec = new JTextField();
		arminLabel = new JLabel();
		armin = new JTextField();
		arsecLabel = new JLabel();
		arsec = new JTextField();
		decgradLabel = new JLabel();
		decGrad = new JTextField();
		decminLabel = new JLabel();
		decMin = new JTextField();
		decsecLabel = new JLabel();
		decSec = new JTextField();
		separator1 = new JSeparator();
		separator2 = new JSeparator();
		decToSex = new JButton();
		SexToDec = new JButton();
		clean = new JButton();
		titulo = new JLabel();

		//======== this ========
		//setLayout(new GridLayoutManager(31, 30, new Insets(0, 50, 50, 50), 4, 4));
		setLayout(new GridLayoutManager(12, 3, new Insets(30, 60, 0, 60), 5, -1));

		//---- titulo ----
		titulo.setText("CONVERSOR DE COORDENADAS");
		titulo.setFont(titulo.getFont().deriveFont(titulo.getFont().getSize() + 10f));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(titulo, new GridConstraints(0, 0, 1, 3,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK ,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		
		
		
		
		
		//---- separator1 ----
		add(separator1, new GridConstraints(1, 0, 1, 3,
				GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK ,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				null, null, null));
		
		
		
		
		
		
		//---- label9 ----
		gradosDec.setText("GRADOS DECIMALES");
		gradosDec.setFont(gradosDec.getFont().deriveFont(gradosDec.getFont().getSize() + 6f));
		add(gradosDec, new GridConstraints(2, 0, 1, 3,
			GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		
		
		
		
		
		//---- label1 ----
		arLabel.setText("ASCENSIÓN RECTA");
		add(arLabel, new GridConstraints(3, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		//---- label2 ----
		decLabel.setText("DECLINACIÓN");
		add(decLabel, new GridConstraints(3, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		
		
		
		
		// Textfield ar
		add(ar, new GridConstraints(4, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				null, null, null));

		// Textfield dec
		add(dec, new GridConstraints(4, 1, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				null, null, null));
		
		
		
		
		
		
		//---- label10 ----
		gradosSex.setText("GRADOS SEXAGESIMALES");
		gradosSex.setFont(gradosSex.getFont().deriveFont(gradosSex.getFont().getSize() + 6f));
		add(gradosSex, new GridConstraints(5, 0, 1, 3,
			GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		
		
		
		
		
		//---- label3 ----
		arhLabel.setText("AR H");
		add(arhLabel, new GridConstraints(6, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		//---- label4 ----
		arminLabel.setText("AR MIN");
		add(arminLabel, new GridConstraints(6, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		//---- label5 ----
		arsecLabel.setText("AR SEC");
		add(arsecLabel, new GridConstraints(6, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		
		
		
		
		
		add(arh, new GridConstraints(7, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		add(armin, new GridConstraints(7, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		add(arsec, new GridConstraints(7, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		
		
		
		

		//---- label6 ----
		decgradLabel.setText("DEC GRAD");
		add(decgradLabel, new GridConstraints(8, 0, 1, 1,
			GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		//---- label7 ----
		decminLabel.setText("DEC MIN");
		add(decminLabel, new GridConstraints(8, 1, 1, 1,
			GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		//---- label8 ----
		decsecLabel.setText("DEC SEC");
		add(decsecLabel, new GridConstraints(8, 2, 1, 1,
			GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		
		
		
		
		
		add(decGrad, new GridConstraints(9, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		add(decMin, new GridConstraints(9, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		add(decSec, new GridConstraints(9, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		
		
		
		
		
		add(separator2, new GridConstraints(10, 0, 1, 3,
			GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		
		
		
		
		
		//---- decToSex ----
		decToSex.setText("A SEXAGESIMAL");
		decToSex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decToSexActionPerformed(e);
			}
		});
		add(decToSex, new GridConstraints(11, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		//---- clean ----
		clean.setText("LIMPIAR CAMPOS");
		clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanActionPerformed(e);
			}
		});
		add(clean, new GridConstraints(11, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- SexToDec ----
		SexToDec.setText("A DECIMAL");
		SexToDec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SexToDecActionPerformed(e);
			}
		});
		add(SexToDec, new GridConstraints(11, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
	}

	private JLabel titulo;
	private JLabel gradosDec;
	private JLabel gradosSex;
	private JLabel arLabel;
	private JLabel arhLabel;
	private JLabel decLabel;
	private JLabel arminLabel;
	private JLabel arsecLabel;
	private JLabel decgradLabel;
	private JLabel decminLabel;
	private JLabel decsecLabel;
	private JTextField ar;
	private JTextField arh;
	private JTextField armin;
	private JTextField arsec;
	private JTextField dec;
	private JTextField decGrad;
	private JTextField decMin;
	private JTextField decSec;
	private JSeparator separator1;
	private JSeparator separator2;
	private JButton decToSex;
	private JButton SexToDec;
	private JButton clean;
}
