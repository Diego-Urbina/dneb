package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Tue Feb 02 17:35:35 CET 2010
 */



/**
 * @author Brainrain
 */
public class ImportarCatalogo extends JPanel {
	public ImportarCatalogo() {
		initComponents();
	}

	private void cargarActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void importarActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		titulo = new JLabel();
		ruta = new JTextField();
		cargar = new JButton();
		importar = new JButton();
		textField1 = new JTextField();
		label1 = new JLabel();

		//======== this ========

		//---- titulo ----
		titulo.setText("Importar Cat\u00e1logo de Estrellas Dobles");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(titulo.getFont().deriveFont(titulo.getFont().getStyle() | Font.BOLD, titulo.getFont().getSize() + 6f));

		//---- ruta ----
		ruta.setEditable(false);

		//---- cargar ----
		cargar.setText("Abrir fichero");
		cargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarActionPerformed(e);
			}
		});

		//---- importar ----
		importar.setText("Importar");
		importar.setToolTipText("Importar borrando el cat\u00e1logo actual");
		importar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importarActionPerformed(e);
			}
		});

		//---- textField1 ----
		textField1.setEditable(false);

		//---- label1 ----
		label1.setText("\u00daltima vez que se import\u00f3 un cat\u00e1logo ");

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(titulo, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup()
										.addComponent(ruta, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
										.addComponent(label1, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(textField1)
										.addComponent(cargar, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))))
							.addContainerGap())
						.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
							.addComponent(importar)
							.addGap(156, 156, 156))))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(titulo, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label1))
					.addGap(37, 37, 37)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(ruta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cargar))
					.addGap(98, 98, 98)
					.addComponent(importar)
					.addGap(20, 20, 20))
		);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel titulo;
	private JTextField ruta;
	private JButton cargar;
	private JButton importar;
	private JTextField textField1;
	private JLabel label1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
