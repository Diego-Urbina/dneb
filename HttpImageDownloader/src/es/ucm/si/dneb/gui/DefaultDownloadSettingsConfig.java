package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sat Nov 28 10:41:50 CET 2009
 */



/**
 * @author Brainrain
 */
public class DefaultDownloadSettingsConfig extends JPanel {
	public DefaultDownloadSettingsConfig() {
		initComponents();
	}

	private void button2MouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void button3MouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void button1MouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void button4MouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label3 = new JLabel();
		ALTOINPUT = new JTextField();
		ANCHOINPUT = new JTextField();
		label6 = new JLabel();
		label5 = new JLabel();
		comboBoxSURVEY2 = new JComboBox();
		comboBoxSURVEY1 = new JComboBox();
		label4 = new JLabel();
		ruta = new JTextField();
		formatoFichero = new JComboBox();
		label11 = new JLabel();
		label10 = new JLabel();
		label13 = new JLabel();
		aliasNuevaConfig = new JTextField();
		label14 = new JLabel();
		comboBoxValoresPorDefecto = new JComboBox();
		cargarValoresPorDefecto = new JButton();
		guardarValoresPorDefecto = new JButton();
		separator1 = new JSeparator();
		volverAlMenubutton = new JButton();
		TITULO = new JLabel();

		//======== this ========

		//---- label3 ----
		label3.setText("ALTO");

		//---- label6 ----
		label6.setText("ANCHO");

		//---- label5 ----
		label5.setText("SURVEY 2");

		//---- label4 ----
		label4.setText("SURVEY 1");

		//---- label11 ----
		label11.setText("FORMATO FICHERO");

		//---- label10 ----
		label10.setText("RUTA");

		//---- label13 ----
		label13.setText("ALIAS CONFIGURACION");

		//---- label14 ----
		label14.setText("VALORES POR DEFECTO");

		//---- cargarValoresPorDefecto ----
		cargarValoresPorDefecto.setText("CARGAR VALORES POR DEFECTO");
		cargarValoresPorDefecto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button2MouseClicked(e);
			}
		});

		//---- guardarValoresPorDefecto ----
		guardarValoresPorDefecto.setText("GUARDAR COMO VALORES POR DEFECTO");
		guardarValoresPorDefecto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button3MouseClicked(e);
			}
		});

		//---- volverAlMenubutton ----
		volverAlMenubutton.setText("VOLVER AL MENU");
		volverAlMenubutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button1MouseClicked(e);
				button4MouseClicked(e);
			}
		});

		//---- TITULO ----
		TITULO.setText("Configuracion de valores por defecto");
		TITULO.setFont(TITULO.getFont().deriveFont(TITULO.getFont().getStyle() & ~Font.ITALIC, TITULO.getFont().getSize() + 7f));
		TITULO.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addComponent(separator1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
							.addGap(22, 22, 22)
							.addComponent(guardarValoresPorDefecto, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
							.addGap(53, 53, 53)
							.addComponent(cargarValoresPorDefecto, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
									.addGap(22, 22, 22)
									.addComponent(label13)
									.addGap(18, 18, 18)
									.addComponent(aliasNuevaConfig, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
									.addComponent(label14, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
									.addGap(59, 59, 59)
									.addGroup(layout.createParallelGroup()
										.addComponent(label10, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
										.addComponent(label3)
										.addComponent(label6))
									.addGap(39, 39, 39)
									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(ALTOINPUT, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
										.addComponent(ruta, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
										.addComponent(ANCHOINPUT))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
									.addGroup(layout.createParallelGroup()
										.addComponent(label4)
										.addComponent(label5)
										.addComponent(label11))))
							.addGroup(layout.createParallelGroup()
								.addGroup(layout.createSequentialGroup()
									.addGap(57, 57, 57)
									.addGroup(layout.createParallelGroup()
										.addComponent(comboBoxSURVEY1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBoxSURVEY2, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
										.addComponent(formatoFichero, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup()
									.addGap(18, 18, 18)
									.addComponent(comboBoxValoresPorDefecto, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))))
					.addGap(35, 35, 35))
				.addGroup(layout.createSequentialGroup()
					.addGap(232, 232, 232)
					.addComponent(volverAlMenubutton, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(283, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(TITULO, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(TITULO, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(25, 25, 25)
					.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
							.addGap(2, 2, 2)
							.addComponent(label10))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(ruta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label4)
							.addComponent(comboBoxSURVEY1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(37, 37, 37)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(ANCHOINPUT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label6)
						.addComponent(label5)
						.addComponent(comboBoxSURVEY2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30, 30, 30)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(ALTOINPUT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label3)
						.addComponent(label11)
						.addComponent(formatoFichero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31, 31, 31)
					.addComponent(separator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(32, 32, 32)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(aliasNuevaConfig, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label14)
						.addComponent(comboBoxValoresPorDefecto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label13))
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(cargarValoresPorDefecto)
						.addComponent(guardarValoresPorDefecto))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addComponent(volverAlMenubutton)
					.addContainerGap())
		);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label3;
	private JTextField ALTOINPUT;
	private JTextField ANCHOINPUT;
	private JLabel label6;
	private JLabel label5;
	private JComboBox comboBoxSURVEY2;
	private JComboBox comboBoxSURVEY1;
	private JLabel label4;
	private JTextField ruta;
	private JComboBox formatoFichero;
	private JLabel label11;
	private JLabel label10;
	private JLabel label13;
	private JTextField aliasNuevaConfig;
	private JLabel label14;
	private JComboBox comboBoxValoresPorDefecto;
	private JButton cargarValoresPorDefecto;
	private JButton guardarValoresPorDefecto;
	private JSeparator separator1;
	private JButton volverAlMenubutton;
	private JLabel TITULO;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
