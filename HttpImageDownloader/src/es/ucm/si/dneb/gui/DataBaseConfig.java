package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sat Nov 28 10:27:43 CET 2009
 */



/**
 * @author Brainrain
 */
public class DataBaseConfig extends JPanel {
	public DataBaseConfig() {
		initComponents();
	}

	private void guardar(MouseEvent e) {
		// TODO add your code here
	}

	private void cargarValoresActuales(MouseEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label1 = new JLabel();
		label2 = new JLabel();
		driverClass = new JTextField();
		label3 = new JLabel();
		urlBBDD = new JTextField();
		label4 = new JLabel();
		usuario = new JTextField();
		label5 = new JLabel();
		contraseña = new JTextField();
		guardar = new JButton();
		cargarValoresActuales = new JButton();

		//======== this ========

		//---- label1 ----
		label1.setText("Configuraci\u00f3n de Base de Datos");
		label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() & ~Font.ITALIC, label1.getFont().getSize() + 7f));
		label1.setHorizontalAlignment(SwingConstants.CENTER);

		//---- label2 ----
		label2.setText("DRIVER CLASS NAME");

		//---- label3 ----
		label3.setText("URL BBDD");

		//---- label4 ----
		label4.setText("USUARIO");

		//---- label5 ----
		label5.setText("PASSWORD");

		//---- guardar ----
		guardar.setText("GUARDAR");
		guardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guardar(e);
			}
		});

		//---- cargarValoresActuales ----
		cargarValoresActuales.setText("CARGAR VALORES ACTUALES");
		cargarValoresActuales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarValoresActuales(e);
			}
		});

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(label1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(label3, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
								.addComponent(label2, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(layout.createParallelGroup()
								.addComponent(driverClass, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
								.addComponent(urlBBDD, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup()
								.addComponent(label5, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
								.addComponent(label4, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(layout.createParallelGroup()
								.addComponent(usuario, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
								.addComponent(contraseña, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)))
						.addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(cargarValoresActuales, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
							.addGap(34, 34, 34)
							.addComponent(guardar, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(46, 46, 46)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label2)
						.addComponent(driverClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20, 20, 20)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label3)
						.addComponent(urlBBDD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22, 22, 22)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label4)
						.addComponent(usuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22, 22, 22)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label5)
						.addComponent(contraseña, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(guardar)
						.addComponent(cargarValoresActuales))
					.addContainerGap())
		);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JLabel label2;
	private JTextField driverClass;
	private JLabel label3;
	private JTextField urlBBDD;
	private JLabel label4;
	private JTextField usuario;
	private JLabel label5;
	private JTextField contraseña;
	private JButton guardar;
	private JButton cargarValoresActuales;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
