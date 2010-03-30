package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.intellij.uiDesigner.core.*;

import es.ucm.si.dneb.service.math.*;



/**
 * @author Brainrain
 */
public class CoordinateConverter extends JPanel {
	
	private static final long serialVersionUID = 6657944572549232546L;
	public CoordinateConverter() {
		initComponents();
	}

	private void decToSexActionPerformed(ActionEvent e) {
		try{
			DecimalCoordinate dc= new DecimalCoordinate();
			dc.setAr(Double.parseDouble(this.ar.getText()));
			dc.setDec(Double.parseDouble(this.dec.getText()));
			
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
			sc.setArh(Integer.parseInt(this.arh.getText()));
			sc.setArmin(Integer.parseInt(this.armin.getText()));
			sc.setArsec(Double.parseDouble(this.arsec.getText()));
			
			sc.setDech(Integer.parseInt(this.decGrad.getText()));
			sc.setDecmin(Integer.parseInt(this.decMin.getText()));
			sc.setDecsec(Double.parseDouble(this.decSec.getText()));
			
			DecimalCoordinate dc= es.ucm.si.dneb.service.math.CoordinateConverter.sexagesimalToDecimalConverter(sc);
			
			ar.setText(""+dc.getAr());
			dec.setText(""+dc.getDec());
			
		}catch(Exception exc){
			JOptionPane.showMessageDialog(null, "Los datos introducidos no son validos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label9 = new JLabel();
		label10 = new JLabel();
		label1 = new JLabel();
		ar = new JTextField();
		label3 = new JLabel();
		arh = new JTextField();
		label2 = new JLabel();
		dec = new JTextField();
		label4 = new JLabel();
		armin = new JTextField();
		label5 = new JLabel();
		arsec = new JTextField();
		label6 = new JLabel();
		decGrad = new JTextField();
		label7 = new JLabel();
		decMin = new JTextField();
		label8 = new JLabel();
		decSec = new JTextField();
		separator1 = new JSeparator();
		decToSex = new JButton();
		SexToDec = new JButton();

		//======== this ========
		setLayout(new GridLayoutManager(31, 30, new Insets(0, 50, 50, 50), 4, 4));

		//---- label9 ----
		label9.setText("GRADOS DECIMALES");
		add(label9, new GridConstraints(1, 1, 1, 12,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label10 ----
		label10.setText("GRADOS SEXAGESIMALES");
		add(label10, new GridConstraints(1, 18, 1, 8,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label1 ----
		label1.setText("ASCENSI\u00d3N RECTA");
		add(label1, new GridConstraints(3, 1, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ar, new GridConstraints(3, 8, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label3 ----
		label3.setText("AR H");
		add(label3, new GridConstraints(3, 16, 1, 4,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(arh, new GridConstraints(3, 22, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label2 ----
		label2.setText("DECLINACI\u00d3N");
		add(label2, new GridConstraints(5, 2, 1, 4,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(dec, new GridConstraints(5, 8, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label4 ----
		label4.setText("AR MIN");
		add(label4, new GridConstraints(5, 16, 1, 4,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(armin, new GridConstraints(5, 22, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label5 ----
		label5.setText("AR SEC");
		add(label5, new GridConstraints(7, 16, 1, 4,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(arsec, new GridConstraints(7, 22, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label6 ----
		label6.setText("DEC GRAD");
		add(label6, new GridConstraints(9, 16, 1, 4,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(decGrad, new GridConstraints(9, 22, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label7 ----
		label7.setText("DEC MIN");
		add(label7, new GridConstraints(11, 16, 1, 4,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(decMin, new GridConstraints(11, 22, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label8 ----
		label8.setText("DEC SEC");
		add(label8, new GridConstraints(13, 16, 1, 4,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(decSec, new GridConstraints(13, 22, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(separator1, new GridConstraints(16, 0, 1, 30,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- decToSex ----
		decToSex.setText("DEC TO SEX");
		decToSex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decToSexActionPerformed(e);
			}
		});
		add(decToSex, new GridConstraints(18, 3, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- SexToDec ----
		SexToDec.setText("SEX TO DEC");
		SexToDec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SexToDecActionPerformed(e);
			}
		});
		add(SexToDec, new GridConstraints(18, 19, 1, 6,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label9;
	private JLabel label10;
	private JLabel label1;
	private JTextField ar;
	private JLabel label3;
	private JTextField arh;
	private JLabel label2;
	private JTextField dec;
	private JLabel label4;
	private JTextField armin;
	private JLabel label5;
	private JTextField arsec;
	private JLabel label6;
	private JTextField decGrad;
	private JLabel label7;
	private JTextField decMin;
	private JLabel label8;
	private JTextField decSec;
	private JSeparator separator1;
	private JButton decToSex;
	private JButton SexToDec;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
