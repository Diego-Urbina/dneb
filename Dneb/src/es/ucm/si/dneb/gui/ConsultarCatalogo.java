package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import com.intellij.uiDesigner.core.*;

import es.ucm.si.dneb.domain.DoubleStarCatalog;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.consultarCatalogo.ServicioConsultaCatalogo;
import es.ucm.si.dneb.service.creacionTareas.ServicioCreacionTareas;
/*
 * Created by JFormDesigner on Thu Feb 11 22:37:04 CET 2010
 */
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareasException;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;
import es.ucm.si.dneb.util.ProgressRenderer;



/**
 * @author Brainrain
 */
public class ConsultarCatalogo extends JPanel {
	
	DefaultTableModel modelo;
	
	private ServicioConsultaCatalogo servicioConsultaCatalogo;
	private ServicioCreacionTareas servicioCreacionTareas;
	
	List<DoubleStarCatalog> dsData= new ArrayList<DoubleStarCatalog>();

	private static final long serialVersionUID = -6003512136953182733L;
	public ConsultarCatalogo() {
		initComponents();
		
		servicioConsultaCatalogo= (ServicioConsultaCatalogo) ContextoAplicacion.getApplicationContext().getBean("servicioConsultaCatalogo");
		servicioCreacionTareas= (ServicioCreacionTareas) ContextoAplicacion.getApplicationContext().getBean("servicioCreacionTareas");
	}

	private void consultarActionPerformed(ActionEvent e) {
			
		Integer intlimobs=Integer.MAX_VALUE;
		Date dateprimObs=Date.valueOf(3000+"-01-01");   
		Date dateultobs=Date.valueOf(3000+"-01-01");
		Double doublemagmax=Double.MAX_VALUE;
		Double doubledistmax=Double.MAX_VALUE;
		
		if(!this.limnumobs.getText().equals("")){
			intlimobs=Integer.parseInt(this.limnumobs.getText());
		}
		if(!this.primobs.getText().equals("")){
			dateprimObs= Date.valueOf(this.primobs.getText()+"-01-01"); 
		}
		if(!this.ultobs.getText().equals("")){
			dateultobs= Date.valueOf(this.ultobs.getText()+"-01-01");   
		}
		if(!this.magmax.getText().equals("")){
			doublemagmax=Double.parseDouble(this.magmax.getText());
		}
		if(!this.distmax.getText().equals("")){
			doubledistmax= Double.parseDouble(this.distmax.getText());
		}
	
		dsData=servicioConsultaCatalogo.consultaCatalogo(intlimobs, dateprimObs ,dateultobs   ,doublemagmax,doubledistmax, 0);
		
		
		this.rellenarTabla();
		//
		
	}
	
	private void rellenarTabla() {
		try {
	       
	        int nFila = 0;
	      
	        Object [] fila = new Object[this.table1.getColumnCount()];
	        for (DoubleStarCatalog dsc : dsData) {
	        	
	        	fila[0] = dsc.getArcsecondCoordinates2000();
	        	fila[1] = dsc.getComponents();
	        	fila[2] = dsc.getCoordinates();
	        	fila[3] = dsc.getDiscovererAndNumber();
	        	fila[4] = dsc.getDurchmusterungNumber();
	        	fila[5] = dsc.getFirstObservation();
	        	fila[6] = dsc.getFirstPosAngle();
	        	fila[7] = dsc.getFirstSeparation();
	        	fila[8] = dsc.getFirstStarMagnitude();
	        	fila[9] = dsc.getLastObservation();
	        	fila[10] = dsc.getLastPosAnges();
	        	fila[11] =dsc.getLastSeparation();
	        	fila[12] = dsc.getNotes();
	        	fila[13] = dsc.getNumObservations();
	        	fila[14] = dsc.getPrimaryProperMotionDec();
	        	fila[15] = dsc.getPrimaryProperMotionRa();
	        	fila[16] = dsc.getSecondaryProperMotionDec();
	        	fila[17] = dsc.getSecondaryProperMotionRa();
	        	fila[18] = dsc.getSecondStarMagnitude();
	        	fila[19] = dsc.getSpectralType();
	        	fila[20] = dsc.getId();
	        	
	        	
	        	modelo.addRow(fila);
	        	
	            nFila++;
	        }
		} catch(ServicioGestionTareasException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}

	private void generarTareaAction(ActionEvent e) {
		
		// TODO add your code here
		
		int selection[]=this.table1.getSelectedRows();
		List<DoubleStarCatalog> dscs= new ArrayList<DoubleStarCatalog>();
		
		for(int i =0;i<selection.length; i++){
			
			DoubleStarCatalog dscSelected= servicioConsultaCatalogo.findDSCById((Long)modelo.getValueAt(selection[i],20));
			dscs.add(dscSelected);
		}
		
		servicioCreacionTareas.crearTarea(dscs);
		
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label1 = new JLabel();
		label2 = new JLabel();
		limnumobs = new JTextField();
		label6 = new JLabel();
		distmax = new JTextField();
		label3 = new JLabel();
		primobs = new JTextField();
		label4 = new JLabel();
		ultobs = new JTextField();
		label5 = new JLabel();
		magmax = new JTextField();
		button1 = new JButton();
		scrollPane1 = new JScrollPane();
		table1 = new JTable();
		listSurvey = new JList();
		button2 = new JButton();

		//======== this ========
		setLayout(new GridLayoutManager(24, 27, new Insets(0, 0, 0, 20), -1, -1));

		//---- label1 ----
		label1.setText("GENERAR TAREAS MEDIANTE CONSULTAS EN EL DSWC");
		add(label1, new GridConstraints(1, 4, 2, 20,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label2 ----
		label2.setText("Limite num obs");
		add(label2, new GridConstraints(4, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(limnumobs, new GridConstraints(4, 2, 1, 3,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label6 ----
		label6.setText("Distancia m\u00e1xima");
		add(label6, new GridConstraints(4, 12, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(distmax, new GridConstraints(4, 14, 1, 7,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label3 ----
		label3.setText("Primera obs");
		add(label3, new GridConstraints(6, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(primobs, new GridConstraints(6, 2, 1, 3,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label4 ----
		label4.setText("Ultima obs");
		add(label4, new GridConstraints(8, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ultobs, new GridConstraints(8, 2, 1, 3,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label5 ----
		label5.setText("Magnitud m\u00ednima");
		add(label5, new GridConstraints(10, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(magmax, new GridConstraints(10, 2, 1, 3,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- button1 ----
		button1.setText("Consultar");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarActionPerformed(e);
			}
		});
		add(button1, new GridConstraints(10, 12, 1, 6,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//======== scrollPane1 ========
		{

			//---- table1 ----
			this.modelo=new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"ARSEC2000COOR", "COMPONENTS", "COORDINATES", "DISCOVERERANDMARKER", "DURSCHNUMBER", "FIRSTOBS", "FIRSTPOSANG", "FIRSTSEP", "FIRSTSTARMAG", "LASTOBS", "LASTPOSANG", "LASTSEP", "NOTES", "NUMOBS", "PPMDEC", "PPMRA", "SPMDEC", "SPMRA", "SSMAGNITUDE", "SPECTYPE","ID"
				}
			);
			
			table1.setModel(modelo);
			table1.setName("tableModel");
			scrollPane1.setViewportView(table1);
		}
		add(scrollPane1, new GridConstraints(13, 1, 7, 18,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- listSurvey ----
		listSurvey.setVisibleRowCount(5);
		listSurvey.setFont(new Font("Arial", Font.PLAIN, 11));
		listSurvey.setSelectedIndex(0);
		listSurvey.setBorder(new BevelBorder(BevelBorder.LOWERED));
		add(listSurvey, new GridConstraints(20, 5, 1, 7,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- button2 ----
		button2.setText("Generar Tarea");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarTareaAction(e);
			}
		});
		add(button2, new GridConstraints(22, 8, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	public void setServicioConsultaCatalogo(ServicioConsultaCatalogo servicioConsultaCatalogo) {
		this.servicioConsultaCatalogo = servicioConsultaCatalogo;
	}

	public ServicioConsultaCatalogo getServicioConsultaCatalogo() {
		return servicioConsultaCatalogo;
	}

	public void setServicioCreacionTareas(ServicioCreacionTareas servicioCreacionTareas) {
		this.servicioCreacionTareas = servicioCreacionTareas;
	}

	public ServicioCreacionTareas getServicioCreacionTareas() {
		return servicioCreacionTareas;
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JLabel label2;
	private JTextField limnumobs;
	private JLabel label6;
	private JTextField distmax;
	private JLabel label3;
	private JTextField primobs;
	private JLabel label4;
	private JTextField ultobs;
	private JLabel label5;
	private JTextField magmax;
	private JButton button1;
	private JScrollPane scrollPane1;
	private JTable table1;
	private JList listSurvey;
	private JButton button2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
