/*
 * Created by JFormDesigner on Sun Sep 13 12:04:49 CEST 2009
 */

package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.service.creacionTareas.ServicioCreacionTareas;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareasException;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;

/**
 * @author aa
 */
public class SurveyPanel extends JPanel {
	
	private VentanaPcpal principal;
	
	public SurveyPanel(VentanaPcpal pcpal) {
		initComponents();
		principal = pcpal;
		rellenarModel();
	}
	
	private void rellenarModel() {
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
        ServicioGestionTareas servicioGestionTareas = (ServicioGestionTareas)ctx.getBean("servicioGestionTareas");
        
        try {
        ArrayList<Survey> surveys = (ArrayList<Survey>) servicioGestionTareas.getAllSurveys();
		
        DefaultListModel list = new DefaultListModel();
        for (Survey aux : surveys){
        	list.addElement(aux.getDescripcion());
        }
        
        listSurvey.setModel(list);
        listSurvey.setSelectedIndex(0);
        listSurvey2.setModel(list);
        listSurvey2.setSelectedIndex(0);
        } catch(ServicioGestionTareasException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}

	private void buttonSiguienteActionPerformed(ActionEvent e) {
		// TODO add your code here
        
		principal.survey1 = (String) this.listSurvey.getSelectedValue();
		principal.survey2 = (String) this.listSurvey2.getSelectedValue();
		
		JPanel vent = new MapPanel(principal);
		principal.getContentPane().remove(0);
		principal.getContentPane().add(vent);
		principal.pack();
		vent.setVisible(true);
	}

	

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		labelSurvey = new JLabel();
		buttonSiguiente = new JButton();
		scrollPaneSurvey = new JScrollPane();
		listSurvey = new JList();
		scrollPaneSurvey2 = new JScrollPane();
		listSurvey2 = new JList();

		//======== this ========
		setPreferredSize(new Dimension(365, 300));

		//---- labelSurvey ----
		labelSurvey.setText("SURVEY");
		labelSurvey.setHorizontalAlignment(SwingConstants.CENTER);
		labelSurvey.setFont(new Font("Arial", Font.PLAIN, 14));

		//---- buttonSiguiente ----
		buttonSiguiente.setText("SIGUIENTE");
		buttonSiguiente.setFont(new Font("Arial", Font.PLAIN, 11));
		buttonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonSiguienteActionPerformed(e);
			}
		});

		//======== scrollPaneSurvey ========
		{

			//---- listSurvey ----
			listSurvey.setVisibleRowCount(5);
			listSurvey.setFont(new Font("Arial", Font.PLAIN, 11));
			listSurvey.setSelectedIndex(0);
			scrollPaneSurvey.setViewportView(listSurvey);
		}

		//======== scrollPaneSurvey2 ========
		{

			//---- listSurvey2 ----
			listSurvey2.setVisibleRowCount(5);
			listSurvey2.setFont(new Font("Arial", Font.PLAIN, 11));
			listSurvey2.setSelectedIndex(0);
			scrollPaneSurvey2.setViewportView(listSurvey2);
		}

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
					.addGap(10, 10, 10)
					.addComponent(scrollPaneSurvey, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(54, 54, 54)
					.addComponent(scrollPaneSurvey2, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addGap(339, 339, 339))
				.addGroup(layout.createSequentialGroup()
					.addGap(110, 110, 110)
					.addComponent(labelSurvey, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
					.addGap(435, 435, 435))
				.addGroup(layout.createSequentialGroup()
					.addGap(118, 118, 118)
					.addComponent(buttonSiguiente, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(456, Short.MAX_VALUE))
		);
		layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {labelSurvey, scrollPaneSurvey});
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(27, 27, 27)
					.addComponent(labelSurvey, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(36, 36, 36)
					.addGroup(layout.createParallelGroup()
						.addComponent(scrollPaneSurvey, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPaneSurvey2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
					.addGap(49, 49, 49)
					.addComponent(buttonSiguiente)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel labelSurvey;
	private JButton buttonSiguiente;
	private JScrollPane scrollPaneSurvey;
	private JList listSurvey;
	private JScrollPane scrollPaneSurvey2;
	private JList listSurvey2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
