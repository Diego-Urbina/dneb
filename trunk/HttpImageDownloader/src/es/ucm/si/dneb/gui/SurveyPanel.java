/*
 * Created by JFormDesigner on Sun Sep 13 12:04:49 CEST 2009
 */

package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.intellij.uiDesigner.core.*;

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
	private int position;
	
	public SurveyPanel(VentanaPcpal pcpal, int position) {
		initComponents();
		this.position=position;
		principal = pcpal;
		buttonSiguiente.setIcon(new ImageIcon("images/next_icon.png"));
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
        
		principal.survey1 = (String) this.listSurvey.getSelectedValue();
		principal.survey2 = (String) this.listSurvey2.getSelectedValue();
		
		JTabbedPane pane = principal.getPane();
        
		JPanel vent = new MapPanel(principal,position);
		
		principal.getPane().setComponentAt(position, vent);
		
		principal.initTabComponent(position);
	
		setVisible(true);
		
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
		setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), 5, -1));

		//---- labelSurvey ----
		labelSurvey.setText("SURVEY");
		labelSurvey.setHorizontalAlignment(SwingConstants.CENTER);
		labelSurvey.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelSurvey, new GridConstraints(0, 0, 1, 2,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- buttonSiguiente ----
		buttonSiguiente.setText("SIGUIENTE");
		buttonSiguiente.setFont(new Font("Arial", Font.PLAIN, 11));
		buttonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonSiguienteActionPerformed(e);
			}
		});
		add(buttonSiguiente, new GridConstraints(2, 0, 1, 2,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//======== scrollPaneSurvey ========
		{

			//---- listSurvey ----
			listSurvey.setVisibleRowCount(5);
			listSurvey.setFont(new Font("Arial", Font.PLAIN, 11));
			listSurvey.setSelectedIndex(0);
			listSurvey.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPaneSurvey.setViewportView(listSurvey);
		}
		add(scrollPaneSurvey, new GridConstraints(1, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//======== scrollPaneSurvey2 ========
		{

			//---- listSurvey2 ----
			listSurvey2.setVisibleRowCount(5);
			listSurvey2.setFont(new Font("Arial", Font.PLAIN, 11));
			listSurvey2.setSelectedIndex(0);
			listSurvey2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPaneSurvey2.setViewportView(listSurvey2);
		}
		add(scrollPaneSurvey2, new GridConstraints(1, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
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
