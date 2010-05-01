/*
 * Created by JFormDesigner on Sat Sep 12 23:56:32 CEST 2009
 */

package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.intellij.uiDesigner.core.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.creacionTareas.ServicioCreacionTareas;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareasException;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;

/**
 * @author aa
 */
public class MapPanel extends JPanel {
	
	private VentanaPcpal principal;
	private int position;
	
	public MapPanel(VentanaPcpal pcpal,int position) {
		
		initComponents();
		this.position=position;
		principal = pcpal;
		
		this.buttonAnterior.setIcon(new ImageIcon("images/back_icon.png"));
		this.buttonSiguiente.setIcon(new ImageIcon("images/next_icon.png"));
	}

	private void buttonSiguienteActionPerformed(ActionEvent e) {
		// TODO add your code here
		principal.alto = this.textFieldAlto.getText();
		principal.ancho = this.textFieldAncho.getText();
		principal.arf = this.textFieldARF.getText();
		principal.ari = this.textFieldARI.getText();
		principal.eq = this.comboBoxEq.getSelectedItem().toString();
		principal.decf = this.textFieldDECF.getText();
		principal.deci = this.textFieldDECI.getText();
		principal.solapamiento = this.textFieldSolap.getText();
		
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setAcceptAllFileFilterUsed(false);
		int retval = fc.showOpenDialog(this);
		try {
			if (retval == JFileChooser.APPROVE_OPTION) {
				principal.ruta = fc.getSelectedFile().toString();
				ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
				ServicioCreacionTareas servicioCreacionTareas = (ServicioCreacionTareas)ctx.getBean("servicioCreacionTareas");
				servicioCreacionTareas.crearTarea(principal.ari, principal.arf, principal.deci, principal.decf, Double.parseDouble(principal.alto), Double.parseDouble(principal.ancho), Double.parseDouble(principal.solapamiento), principal.survey1, principal.survey2, "fits", principal.ruta);
			}
		} catch(ServicioGestionTareasException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
		
		principal.getPane().remove(position);
		
		JOptionPane.showMessageDialog(null,"Tarea creada satisfactoriamente", "Operación satisfactoria", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("images/TASKICON.JPG"));
		
		
	}

	private void buttonAnteriorActionPerformed(ActionEvent e) {
		// TODO add your code here
		//TODO
		//JPanel vent = new SurveyPanel(principal,0);
		//principal.getContentPane().remove(0);
		//principal.getContentPane().add(vent);
		//principal.pack();
		//vent.setVisible(true);

		JTabbedPane pane = principal.getPane();
        
		JPanel vent = new SurveyPanel(principal,position);
		
		principal.getPane().setComponentAt(position, vent);
		
		principal.initTabComponent(position);
	
		setVisible(true);
		
		vent.setVisible(true);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		textFieldARI = new JTextField();
		textFieldARF = new JTextField();
		textFieldDECI = new JTextField();
		textFieldDECF = new JTextField();
		textFieldAlto = new JTextField();
		textFieldAncho = new JTextField();
		textFieldSolap = new JTextField();
		buttonSiguiente = new JButton();
		buttonAnterior = new JButton();
		labelARI = new JLabel();
		labelDECI = new JLabel();
		labelAlto = new JLabel();
		labelAncho = new JLabel();
		labelEq = new JLabel();
		labelARF = new JLabel();
		labelDECF = new JLabel();
		labelSolap = new JLabel();
		comboBoxEq = new JComboBox();

		//======== this ========
		setLayout(new GridLayoutManager(5, 4, new Insets(20, 20, 20, 20), 5, -1));

		//---- textFieldARI ----
		textFieldARI.setFont(new Font("Arial", Font.PLAIN, 11));
		add(textFieldARI, new GridConstraints(0, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- textFieldARF ----
		textFieldARF.setFont(new Font("Arial", Font.PLAIN, 11));
		add(textFieldARF, new GridConstraints(0, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- textFieldDECI ----
		textFieldDECI.setFont(new Font("Arial", Font.PLAIN, 11));
		add(textFieldDECI, new GridConstraints(1, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- textFieldDECF ----
		textFieldDECF.setFont(new Font("Arial", Font.PLAIN, 11));
		add(textFieldDECF, new GridConstraints(1, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- textFieldAlto ----
		textFieldAlto.setFont(new Font("Arial", Font.PLAIN, 11));
		add(textFieldAlto, new GridConstraints(2, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- textFieldAncho ----
		textFieldAncho.setFont(new Font("Arial", Font.PLAIN, 11));
		add(textFieldAncho, new GridConstraints(3, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- textFieldSolap ----
		textFieldSolap.setFont(new Font("Arial", Font.PLAIN, 11));
		add(textFieldSolap, new GridConstraints(3, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
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
		add(buttonSiguiente, new GridConstraints(4, 2, 1, 2,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- buttonAnterior ----
		buttonAnterior.setText("ANTERIOR");
		buttonAnterior.setFont(new Font("Arial", Font.PLAIN, 11));
		buttonAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAnteriorActionPerformed(e);
			}
		});
		add(buttonAnterior, new GridConstraints(4, 0, 1, 2,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- labelARI ----
		labelARI.setText("AR INICIAL");
		labelARI.setFont(new Font("Arial", Font.PLAIN, 11));
		add(labelARI, new GridConstraints(0, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- labelDECI ----
		labelDECI.setText("DEC INICIAL");
		labelDECI.setFont(new Font("Arial", Font.PLAIN, 11));
		add(labelDECI, new GridConstraints(1, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- labelAlto ----
		labelAlto.setText("ALTO");
		labelAlto.setFont(new Font("Arial", Font.PLAIN, 11));
		add(labelAlto, new GridConstraints(2, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- labelAncho ----
		labelAncho.setText("ANCHO");
		labelAncho.setFont(new Font("Arial", Font.PLAIN, 11));
		add(labelAncho, new GridConstraints(3, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- labelEq ----
		labelEq.setText("EQUINOCIO");
		labelEq.setFont(new Font("Arial", Font.PLAIN, 11));
		add(labelEq, new GridConstraints(2, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- labelARF ----
		labelARF.setText("AR FINAL");
		labelARF.setFont(new Font("Arial", Font.PLAIN, 11));
		add(labelARF, new GridConstraints(0, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- labelDECF ----
		labelDECF.setText("DEC FINAL");
		labelDECF.setFont(new Font("Arial", Font.PLAIN, 11));
		add(labelDECF, new GridConstraints(1, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- labelSolap ----
		labelSolap.setText("SOLAPAMIENTO");
		labelSolap.setFont(new Font("Arial", Font.PLAIN, 11));
		add(labelSolap, new GridConstraints(3, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- comboBoxEq ----
		comboBoxEq.setModel(new DefaultComboBoxModel(new String[] {
			"J2000",
			"B1950"
		}));
		comboBoxEq.setFont(new Font("Arial", Font.PLAIN, 11));
		add(comboBoxEq, new GridConstraints(2, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JTextField textFieldARI;
	private JTextField textFieldARF;
	private JTextField textFieldDECI;
	private JTextField textFieldDECF;
	private JTextField textFieldAlto;
	private JTextField textFieldAncho;
	private JTextField textFieldSolap;
	private JButton buttonSiguiente;
	private JButton buttonAnterior;
	private JLabel labelARI;
	private JLabel labelDECI;
	private JLabel labelAlto;
	private JLabel labelAncho;
	private JLabel labelEq;
	private JLabel labelARF;
	private JLabel labelDECF;
	private JLabel labelSolap;
	private JComboBox comboBoxEq;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
