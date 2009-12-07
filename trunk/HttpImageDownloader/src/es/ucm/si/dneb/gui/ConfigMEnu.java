package es.ucm.si.dneb.gui;

import java.awt.event.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sun Nov 29 23:57:48 CET 2009
 */



/**
 * @author Brainrain
 */
public class ConfigMEnu extends JPanel {
	
	private VentanaPcpal principal;
	
	public ConfigMEnu(VentanaPcpal pcpal) {
		initComponents();
		
		principal = pcpal;
	}

	private void volverMenu(MouseEvent e) {
		JPanel vent = new MenuPanel(principal);
		principal.getContentPane().remove(0);
		principal.getContentPane().add(vent);
		principal.pack();
		vent.setVisible(true);
	}

	private void siguiente(MouseEvent e) {
		
		JPanel vent;
		if(this.configBaseDatos.isSelected()){
			
			vent = new DataBaseConfig(principal);
			principal.setSize(1000, 400);
			
		}else{
			
			vent = new DefaultDownloadSettingsConfig(principal);
			principal.setSize(1000, 400);
		}
		
		principal.getContentPane().remove(0);
		principal.getContentPane().add(vent);
		principal.pack();
		principal.setLocationRelativeTo(null);
		vent.setVisible(true);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		configBaseDatos = new JRadioButton();
		configDescargas = new JRadioButton();
		button2 = new JButton();
		button3 = new JButton();

		//======== this ========

		//---- configBaseDatos ----
		configBaseDatos.setText("CONFIGURAR BASE DE DATOS");

		//---- configDescargas ----
		configDescargas.setText("CREAR NUEVA CONFIG DE DESCARGA");

		//---- button2 ----
		button2.setText("VOLVER AL MENU");
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				volverMenu(e);
			}
		});

		//---- button3 ----
		button3.setText("SIGUIENTE");
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				siguiente(e);
			}
		});

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
							.addGap(74, 74, 74)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(configBaseDatos, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
								.addComponent(configDescargas)))
						.addGroup(layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(button2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
							.addComponent(button3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(58, 58, 58)
					.addComponent(configBaseDatos)
					.addGap(18, 18, 18)
					.addComponent(configDescargas)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(button2)
						.addComponent(button3))
					.addContainerGap())
		);

		//---- buttonGroup1 ----
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(configBaseDatos);
		buttonGroup1.add(configDescargas);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JRadioButton configBaseDatos;
	private JRadioButton configDescargas;
	private JButton button2;
	private JButton button3;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
