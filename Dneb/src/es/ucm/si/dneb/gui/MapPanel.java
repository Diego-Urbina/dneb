package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.intellij.uiDesigner.core.*;

import org.springframework.context.ApplicationContext;

import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.service.creacionTareas.ServicioCreacionTareas;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareasException;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;

public class MapPanel extends JPanel {
	
	private VentanaPcpal principal;
	private int position;
	
	public MapPanel(VentanaPcpal pcpal,int position) {		
		initComponents();
		this.position=position;
		principal = pcpal;
		rellenarModel();
	}

	private void initComponents() {
		textFieldARI = new JTextField();
		textFieldARF = new JTextField();
		textFieldDECI = new JTextField();
		textFieldDECF = new JTextField();
		textFieldAlto = new JTextField();
		textFieldAncho = new JTextField();
		textFieldSolap = new JTextField();
		buttonSiguiente = new JButton();
		labelARI = new JLabel();
		labelDECI = new JLabel();
		labelAlto = new JLabel();
		labelAncho = new JLabel();
		labelEq = new JLabel();
		labelARF = new JLabel();
		labelDECF = new JLabel();
		labelSolap = new JLabel();
		titulo = new JLabel();
		survey1 = new JLabel();
		survey2 = new JLabel();
		comboBoxEq = new JComboBox();
		separator1 = new JSeparator();
		separator2 = new JSeparator();
		comboBoxSurvey1 = new JComboBox();
		comboBoxSurvey2 = new JComboBox();

		//======== this ========
		setLayout(new GridLayoutManager(16, 5, new Insets(0, 60, 0, 60), 5, -1));
		
		//---- titulo ----
		titulo.setText("CREACIÓN DE TAREAS");
		titulo.setFont(titulo.getFont().deriveFont(titulo.getFont().getSize() + 10f));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(titulo, new GridConstraints(1, 0, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		
		
		
		
		
		//---- separator1 ----
		add(separator1, new GridConstraints(2, 0, 1, 5,
				GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		
		
		
		
		
		
		//---- survey1 ----
		survey1.setText("SURVEY 1");
		add(survey1, new GridConstraints(3, 0, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		//---- comboBoxSurvey1 ----
		add(comboBoxSurvey1, new GridConstraints(3, 2, 1, 1,
				GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		
		//---- survey2 ----
		survey2.setText("SURVEY 2");
		add(survey2, new GridConstraints(3, 3, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		//---- comboBoxSurvey2 ----
		add(comboBoxSurvey2, new GridConstraints(3, 4, 1, 1,
				GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		
		
		
		
		
		
		//---- separator2 ----
		add(separator2, new GridConstraints(4, 0, 1, 5,
				GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		
		
		
		
		
		
		//---- labelARI ----
		labelARI.setText("ASCENSIÓN RECTA INICIAL");
		add(labelARI, new GridConstraints(5, 0, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- textFieldARI ----
		add(textFieldARI, new GridConstraints(5, 2, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- labelARF ----
		labelARF.setText("ASCENSIÓN RECTA FINAL");
		add(labelARF, new GridConstraints(5, 3, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		//---- textFieldARF ----
		add(textFieldARF, new GridConstraints(5, 4, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		
		
		
		
				
		//---- labelDECI ----
		labelDECI.setText("DECLINACIÓN INICIAL");
		add(labelDECI, new GridConstraints(6, 0, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		//---- textFieldDECI ----
		add(textFieldDECI, new GridConstraints(6, 2, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- labelDECF ----
		labelDECF.setText("DECLINACIÓN FINAL");
		add(labelDECF, new GridConstraints(6, 3, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		//---- textFieldDECF ----
		add(textFieldDECF, new GridConstraints(6, 4, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		
		
		
		
		
		//---- labelAlto ----
		labelAlto.setText("ALTO");
		add(labelAlto, new GridConstraints(7, 0, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- textFieldAlto ----
		add(textFieldAlto, new GridConstraints(7, 2, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- labelAncho ----
		labelAncho.setText("ANCHO");
		add(labelAncho, new GridConstraints(7, 3, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		//---- textFieldAncho ----
		add(textFieldAncho, new GridConstraints(7, 4, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		
		
		
		
		
		//---- labelSolap ----
		labelSolap.setText("SOLAPAMIENTO");
		add(labelSolap, new GridConstraints(8, 0, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		//---- textFieldSolap ----
		add(textFieldSolap, new GridConstraints(8, 2, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		//---- labelEq ----
		labelEq.setText("EQUINOCIO");
		add(labelEq, new GridConstraints(8, 3, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		//---- comboBoxEq ----
		comboBoxEq.setModel(new DefaultComboBoxModel(new String[] {
			"J2000",
			"B1950"
		}));
		add(comboBoxEq, new GridConstraints(8, 4, 1, 1,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		
		
		
		
		
		//---- buttonSiguiente ----
		buttonSiguiente.setText("CREAR TAREA");
		buttonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonSiguienteActionPerformed(e);
			}
		});
		add(buttonSiguiente, new GridConstraints(9, 2, 1, 2,
			GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
	}
	
	private void rellenarModel() {
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
		ServicioGestionTareas servicioGestionTareas = (ServicioGestionTareas)ctx.getBean("servicioGestionTareas");
		
        try {        	
        	ArrayList<Survey> surveys = (ArrayList<Survey>) servicioGestionTareas.getAllSurveys();
			
	        DefaultComboBoxModel list1 = new DefaultComboBoxModel();        
	        for (Survey aux : surveys){
	        	list1.addElement(aux.getDescripcion());
	        }
	        
	        this.comboBoxSurvey1.setModel(list1);
	        this.comboBoxSurvey1.setSelectedIndex(0);
	        this.comboBoxSurvey1.setModel(list1);
	        this.comboBoxSurvey1.setSelectedIndex(0);   
	        
	        DefaultComboBoxModel list2 = new DefaultComboBoxModel();
	        for (Survey aux : surveys){
	        	list2.addElement(aux.getDescripcion());
	        }
	        
	        this.comboBoxSurvey2.setModel(list2);
	        this.comboBoxSurvey2.setSelectedIndex(0);
	        this.comboBoxSurvey2.setModel(list2);
	        this.comboBoxSurvey2.setSelectedIndex(0);
        } catch(ServicioGestionTareasException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private void buttonSiguienteActionPerformed(ActionEvent e) {
		String survey1, survey2, ari, deci, arf, decf, eq, alto, ancho, solapamiento, ruta;
		
		survey1 = (String) comboBoxSurvey1.getSelectedItem();
		survey2 = (String) comboBoxSurvey2.getSelectedItem();
		alto = this.textFieldAlto.getText();
		ancho = this.textFieldAncho.getText();
		arf = this.textFieldARF.getText();
		ari = this.textFieldARI.getText();
		eq = (String) this.comboBoxEq.getSelectedItem();
		decf = this.textFieldDECF.getText();
		deci = this.textFieldDECI.getText();
		solapamiento = this.textFieldSolap.getText();
		
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setAcceptAllFileFilterUsed(false);
		int retval = fc.showOpenDialog(this);
		try {
			if (retval == JFileChooser.APPROVE_OPTION) {
				ruta = fc.getSelectedFile().toString();
				ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
				ServicioCreacionTareas servicioCreacionTareas = (ServicioCreacionTareas)ctx.getBean("servicioCreacionTareas");
				servicioCreacionTareas.crearTarea(ari, arf, deci, decf, Double.parseDouble(alto), Double.parseDouble(ancho), Double.parseDouble(solapamiento), survey1, survey2, "fits", ruta);
				principal.getPane().remove(position);
				JOptionPane.showMessageDialog(null,"Tarea creada satisfactoriamente", "Operación satisfactoria", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("images/TASKICON.JPG"));
			}
		} catch(ServicioGestionTareasException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }		
	}

	private JTextField textFieldARI;
	private JTextField textFieldARF;
	private JTextField textFieldDECI;
	private JTextField textFieldDECF;
	private JTextField textFieldAlto;
	private JTextField textFieldAncho;
	private JTextField textFieldSolap;
	private JButton buttonSiguiente;
	private JLabel titulo;
	private JLabel labelARI;
	private JLabel labelDECI;
	private JLabel labelAlto;
	private JLabel labelAncho;
	private JLabel labelEq;
	private JLabel labelARF;
	private JLabel labelDECF;
	private JLabel labelSolap;
	private JLabel survey1;
	private JLabel survey2;
	private JComboBox comboBoxEq;
	private JSeparator separator1;
	private JSeparator separator2;
	private JComboBox comboBoxSurvey1;
	private JComboBox comboBoxSurvey2;
}
