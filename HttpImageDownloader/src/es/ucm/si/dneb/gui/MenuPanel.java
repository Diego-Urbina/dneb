package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Tue Sep 22 14:35:55 CEST 2009
 */



/**
 * @author Brainrain
 */
public class MenuPanel extends JPanel {
	
	private VentanaPcpal principal;
	
	public MenuPanel(VentanaPcpal pcpal) {
		initComponents();
		principal = pcpal;
	}

	private void buttonSiguienteActionPerformed(ActionEvent e) {
		// TODO add your code here
		JPanel vent;
		
		if (this.radioButtonNuevo.isSelected())
			vent = new SurveyPanel(principal);
		else {
			if(this.radioButtonEditar.isSelected()){
				vent = new TaskPanel(principal);
				principal.setSize(1000, 400);
			}else{
				if(this.radioButtonVer.isSelected()){
					//vent = new CreateNewDownload(principal);
					principal.setSize(1000, 400);
				}else{
					
					vent = new ConfigMEnu(principal);
					principal.setSize(1000, 400);
					
				}
			}
		}
		
		principal.getContentPane().remove(0);
		//principal.getContentPane().add(vent);
		principal.pack();
		principal.setLocationRelativeTo(null);
		//vent.setVisible(true);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		radioButtonNuevo = new JRadioButton();
		radioButtonEditar = new JRadioButton();
		buttonSiguiente = new JButton();
		radioButtonVer = new JRadioButton();
		radioButtonMenuConfig = new JRadioButton();

		//======== this ========

		//---- radioButtonNuevo ----
		radioButtonNuevo.setText("NUEVA TAREA");
		radioButtonNuevo.setSelected(true);
		radioButtonNuevo.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- radioButtonEditar ----
		radioButtonEditar.setText("EDITAR TAREAS");
		radioButtonEditar.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- buttonSiguiente ----
		buttonSiguiente.setText("SIGUIENTE");
		buttonSiguiente.setFont(new Font("Arial", Font.PLAIN, 11));
		buttonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonSiguienteActionPerformed(e);
			}
		});

		//---- radioButtonVer ----
		radioButtonVer.setText("CREAR DESCARGA");
		radioButtonVer.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- radioButtonMenuConfig ----
		radioButtonMenuConfig.setText("CONFIGURACION");
		radioButtonMenuConfig.setFont(new Font("Arial", Font.PLAIN, 11));

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(124, 124, 124)
					.addGroup(layout.createParallelGroup()
						.addComponent(buttonSiguiente, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
						.addComponent(radioButtonMenuConfig, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(radioButtonNuevo)
							.addComponent(radioButtonEditar)
							.addComponent(radioButtonVer)))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {radioButtonEditar, radioButtonNuevo, radioButtonVer});
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addGap(32, 32, 32)
					.addComponent(radioButtonNuevo)
					.addGap(32, 32, 32)
					.addComponent(radioButtonEditar)
					.addGap(35, 35, 35)
					.addComponent(radioButtonVer)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addComponent(radioButtonMenuConfig)
					.addGap(31, 31, 31)
					.addComponent(buttonSiguiente)
					.addGap(29, 29, 29))
		);
		layout.linkSize(SwingConstants.VERTICAL, new Component[] {radioButtonEditar, radioButtonNuevo, radioButtonVer});

		//---- buttonGroup ----
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButtonNuevo);
		buttonGroup.add(radioButtonEditar);
		buttonGroup.add(radioButtonVer);
		buttonGroup.add(radioButtonMenuConfig);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JRadioButton radioButtonNuevo;
	private JRadioButton radioButtonEditar;
	private JButton buttonSiguiente;
	private JRadioButton radioButtonVer;
	private JRadioButton radioButtonMenuConfig;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
