package es.ucm.si.dneb.gui;
import java.awt.event.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Wed Nov 25 23:44:44 CET 2009
 */



/**
 * @author Brainrain
 */
public class CreateNewDownload extends JPanel {
	public CreateNewDownload() {
		initComponents();
	}

	private void button1MouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void button2MouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void button3MouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void button4MouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label1 = new JLabel();
		textField1 = new JTextField();
		label2 = new JLabel();
		textField2 = new JTextField();
		textField3 = new JTextField();
		label3 = new JLabel();
		textField6 = new JTextField();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		textField7 = new JTextField();
		textField8 = new JTextField();
		label7 = new JLabel();
		label8 = new JLabel();
		label9 = new JLabel();
		comboBox1 = new JComboBox();
		comboBox2 = new JComboBox();
		checkBox1 = new JCheckBox();
		textField9 = new JTextField();
		label10 = new JLabel();
		button1 = new JButton();
		comboBox3 = new JComboBox();
		label11 = new JLabel();
		label12 = new JLabel();
		comboBox4 = new JComboBox();
		separator1 = new JSeparator();
		separator2 = new JSeparator();
		button2 = new JButton();
		separator3 = new JSeparator();
		button3 = new JButton();
		separator4 = new JSeparator();
		checkBox2 = new JCheckBox();
		comboBox5 = new JComboBox();
		textField4 = new JTextField();
		label13 = new JLabel();
		label14 = new JLabel();
		button4 = new JButton();

		//======== this ========

		//---- label1 ----
		label1.setText("ALIAS");

		//---- label2 ----
		label2.setText("DESCRIPCION");

		//---- label3 ----
		label3.setText("ALTO");

		//---- label4 ----
		label4.setText("SURVEY 1");

		//---- label5 ----
		label5.setText("SURVEY 2");

		//---- label6 ----
		label6.setText("ANCHO");

		//---- label7 ----
		label7.setText("ASCENSION RECTA");

		//---- label8 ----
		label8.setText("DECLINACION");

		//---- label9 ----
		label9.setText("PANTALLA DE CREACI\u00d3N DE DESCARGAS");
		label9.setFont(label9.getFont().deriveFont(label9.getFont().getSize() + 10f));

		//---- checkBox1 ----
		checkBox1.setText("INICIAR DESCARGA UNA VEZ CREADA ");

		//---- label10 ----
		label10.setText("RUTA");

		//---- button1 ----
		button1.setText("CREAR DESCARGA");
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button1MouseClicked(e);
			}
		});

		//---- label11 ----
		label11.setText("FORMATO FICHERO");

		//---- label12 ----
		label12.setText("FORMATO COORDENADAS");

		//---- button2 ----
		button2.setText("CARGAR VALORES POR DEFECTO");
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button2MouseClicked(e);
			}
		});

		//---- button3 ----
		button3.setText("GUARDAR COMO VALORES POR DEFECTO");
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button3MouseClicked(e);
			}
		});

		//---- checkBox2 ----
		checkBox2.setText("VISUALIZAR AL TERMINAR DESCARGA");

		//---- label13 ----
		label13.setText("ALIAS CONFIGURACION");

		//---- label14 ----
		label14.setText("VALORES POR DEFECTO");

		//---- button4 ----
		button4.setText("VOLVER AL MENU");
		button4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button1MouseClicked(e);
				button4MouseClicked(e);
			}
		});

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(106, 106, 106)
					.addGroup(layout.createParallelGroup()
						.addComponent(label1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addComponent(label2)
						.addComponent(label3)
						.addComponent(label7)
						.addComponent(label10, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
						.addComponent(textField7, GroupLayout.Alignment.LEADING)
						.addComponent(textField2, GroupLayout.Alignment.LEADING)
						.addComponent(textField1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
						.addComponent(textField3, GroupLayout.Alignment.LEADING)
						.addComponent(textField9, GroupLayout.Alignment.LEADING))
					.addGap(136, 136, 136)
					.addGroup(layout.createParallelGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(label4)
								.addComponent(label5))
							.addComponent(label8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(label6)
							.addComponent(label11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(label12))
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup()
							.addGap(19, 19, 19)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(comboBox3, 0, 101, Short.MAX_VALUE)
								.addComponent(textField8)
								.addComponent(textField6)
								.addComponent(comboBox1, 0, 101, Short.MAX_VALUE)
								.addComponent(comboBox2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(layout.createSequentialGroup()
							.addGap(18, 18, 18)
							.addComponent(comboBox4, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(36, Short.MAX_VALUE))
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator1, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 740, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator4, GroupLayout.PREFERRED_SIZE, 740, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
					.addGap(90, 90, 90)
					.addComponent(checkBox1, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
					.addComponent(checkBox2, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
					.addGap(63, 63, 63))
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator3, GroupLayout.PREFERRED_SIZE, 740, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addGap(70, 70, 70)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(label13, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18, 18, 18)
							.addComponent(textField4, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addComponent(button3, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
					.addGap(79, 79, 79)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
						.addGroup(layout.createSequentialGroup()
							.addComponent(label14, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(comboBox5, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addComponent(button2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(21, 21, 21))
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap(179, Short.MAX_VALUE)
					.addComponent(label9, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)
					.addGap(130, 130, 130))
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap(108, Short.MAX_VALUE)
					.addComponent(button4, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
					.addGap(171, 171, 171)
					.addComponent(button1, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
					.addGap(83, 83, 83))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(21, 21, 21)
					.addComponent(label9, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(separator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label4)
						.addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30, 30, 30)
					.addGroup(layout.createParallelGroup()
						.addComponent(label2)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label5)
							.addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(24, 24, 24)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(label3)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label6)))
					.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
							.addGap(37, 37, 37)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(textField7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label7)))
						.addGroup(layout.createSequentialGroup()
							.addGap(45, 45, 45)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(textField8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label8))))
					.addGap(44, 44, 44)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(textField9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label10)
						.addComponent(comboBox3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label11))
					.addGap(35, 35, 35)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label12)
						.addComponent(comboBox4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addComponent(separator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(comboBox5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label13)
						.addComponent(label14))
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(button3)
						.addComponent(button2))
					.addGap(18, 18, 18)
					.addComponent(separator3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(checkBox1)
						.addComponent(checkBox2))
					.addGap(18, 18, 18)
					.addComponent(separator4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(button4)
						.addComponent(button1))
					.addGap(46, 46, 46))
		);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JTextField textField1;
	private JLabel label2;
	private JTextField textField2;
	private JTextField textField3;
	private JLabel label3;
	private JTextField textField6;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JTextField textField7;
	private JTextField textField8;
	private JLabel label7;
	private JLabel label8;
	private JLabel label9;
	private JComboBox comboBox1;
	private JComboBox comboBox2;
	private JCheckBox checkBox1;
	private JTextField textField9;
	private JLabel label10;
	private JButton button1;
	private JComboBox comboBox3;
	private JLabel label11;
	private JLabel label12;
	private JComboBox comboBox4;
	private JSeparator separator1;
	private JSeparator separator2;
	private JButton button2;
	private JSeparator separator3;
	private JButton button3;
	private JSeparator separator4;
	private JCheckBox checkBox2;
	private JComboBox comboBox5;
	private JTextField textField4;
	private JLabel label13;
	private JLabel label14;
	private JButton button4;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
