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
		
		
		this.modelo=new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"ARSEC2000COOR", "COMPONENTS", "COORDINATES", "DISCOVERERANDMARKER", "DURSCHNUMBER", "FIRSTOBS", "FIRSTPOSANG", "FIRSTSEP", "FIRSTSTARMAG", "LASTOBS", "LASTPOSANG", "LASTSEP", "NOTES", "NUMOBS", "PPMDEC", "PPMRA", "SPMDEC", "SPMRA", "SSMAGNITUDE", "SPECTYPE","ID"
				}
			);
			
			table1.setModel(modelo);
		//
		Integer ciobs= 	Integer.MIN_VALUE;
		if(!this.ciNumObs.getText().equals("")){
			ciobs=Integer.parseInt(this.ciNumObs.getText());
		}
		Integer csobs= 	Integer.MAX_VALUE;
		if(!this.csNumObs.getText().equals("")){
			csobs=Integer.parseInt(this.csNumObs.getText());
		}
		//	
		Date ciDateprimObs=Date.valueOf(0000+"-01-01");
		if(!this.ciPrimObs.getText().equals("")){
			ciDateprimObs= Date.valueOf(this.ciPrimObs.getText()+"-01-01"); 
		}
		Date csDateprimObs=Date.valueOf(3000+"-01-01");
		if(!this.csPrimObs.getText().equals("")){
			csDateprimObs= Date.valueOf(this.csPrimObs.getText()+"-01-01"); 
		}
		//
		Date ciDateUltObs=Date.valueOf(0000+"-01-01");
		if(!this.ciUlObs.getText().equals("")){
			ciDateUltObs= Date.valueOf(this.ciUlObs.getText()+"-01-01"); 
		}
		Date csDateUltObs=Date.valueOf(3000+"-01-01");
		if(!this.csUltObs.getText().equals("")){
			csDateUltObs= Date.valueOf(this.csUltObs.getText()+"-01-01"); 
		}
		//
		Double cimag1=Double.MIN_VALUE;
		if(!this.ciMag1.getText().equals("")){
			cimag1=Double.parseDouble(this.ciMag1.getText());
		}
		Double csmag1=Double.MAX_VALUE;
		if(!this.csMag1.getText().equals("")){
			csmag1=Double.parseDouble(this.csMag1.getText());
		}
		//
		Double ciDespAR1=Double.MIN_VALUE;
		if(!this.ciMov1Ar.getText().equals("")){
			ciDespAR1=Double.parseDouble(this.ciMov1Ar.getText());
		}
		Double csDespAR1=Double.MAX_VALUE;
		if(!this.csMov1AR.getText().equals("")){
			csDespAR1=Double.parseDouble(this.csMov1AR.getText());
		}
		//
		Double ciDespDEC1=Double.MIN_VALUE;
		if(!this.ciMov1DEC.getText().equals("")){
			ciDespDEC1=Double.parseDouble(this.ciMov1DEC.getText());
		}
		Double csDespDEC1=Double.MAX_VALUE;
		if(!this.csMov1DEC.getText().equals("")){
			csDespDEC1=Double.parseDouble(this.csMov1DEC.getText());
		}
		//
		Double ciDist=Double.MIN_VALUE;
		if(!this.ciDistLin.getText().equals("")){
			ciDist=Double.parseDouble(this.ciDistLin.getText());
		}
		Double csDist=Double.MAX_VALUE;
		if(!this.csDistLin.getText().equals("")){
			csDist=Double.parseDouble(this.csDistLin.getText());
		}
		//
		Double ciAng=Double.MIN_VALUE;
		if(!this.ciAng.getText().equals("")){
			ciAng=Double.parseDouble(this.ciAng.getText());
		}
		Double csAng=Double.MAX_VALUE;
		if(!this.csAng.getText().equals("")){
			csAng=Double.parseDouble(this.csAng.getText());
		}
		//
		Double cimag2=Double.MIN_VALUE;
		if(!this.ciMag2.getText().equals("")){
			cimag2=Double.parseDouble(this.ciMag2.getText());
		}
		Double csmag2=Double.MAX_VALUE;
		if(!this.csMag2.getText().equals("")){
			csmag2=Double.parseDouble(this.csMag2.getText());
		}
		//
		Double ciDespAR2=Double.MIN_VALUE;
		if(!this.ciMov2Ar.getText().equals("")){
			ciDespAR2=Double.parseDouble(this.ciMov2Ar.getText());
		}
		Double csDespAR2=Double.MAX_VALUE;
		if(!this.csMov2Ar.getText().equals("")){
			csDespAR2=Double.parseDouble(this.csMov2Ar.getText());
		}
		//
		Double ciDespDEC2=Double.MIN_VALUE;
		if(!this.ciMov2Dec.getText().equals("")){
			ciDespDEC2=Double.parseDouble(this.ciMov2Dec.getText());
		}
		Double csDespDEC2=Double.MAX_VALUE;
		if(!this.csMMov2Dec.getText().equals("")){
			csDespDEC2=Double.parseDouble(this.csMMov2Dec.getText());
		}
		
		
		dsData=servicioConsultaCatalogo.consultaAvanzadaCatalogo(ciobs,
			csobs,  ciDateprimObs,  csDateprimObs,
				 ciDateUltObs,  csDateUltObs,  cimag1,  csmag1,
				 ciDespAR1,  csDespAR1,  ciDespDEC1,
				 csDespDEC1,  ciDist,  csDist,  ciAng,
				 csAng,  cimag2,  csmag2,  ciDespAR2,
				 csDespAR2,  ciDespDEC2,  csDespDEC2 );
		
		
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

	private void selRutaActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label1 = new JLabel();
		label20 = new JLabel();
		label21 = new JLabel();
		label22 = new JLabel();
		label23 = new JLabel();
		label2 = new JLabel();
		ciNumObs = new JTextField();
		csNumObs = new JTextField();
		label6 = new JLabel();
		ciDistLin = new JTextField();
		csDistLin = new JTextField();
		label3 = new JLabel();
		ciPrimObs = new JTextField();
		csPrimObs = new JTextField();
		label7 = new JLabel();
		ciAng = new JTextField();
		csAng = new JTextField();
		label4 = new JLabel();
		ciUlObs = new JTextField();
		csUltObs = new JTextField();
		label8 = new JLabel();
		ciEspec = new JTextField();
		label5 = new JLabel();
		ciMag1 = new JTextField();
		csMag1 = new JTextField();
		label9 = new JLabel();
		ciMag2 = new JTextField();
		csMag2 = new JTextField();
		label13 = new JLabel();
		ciMov1Ar = new JTextField();
		csMov1AR = new JTextField();
		label14 = new JLabel();
		ciMov2Ar = new JTextField();
		csMov2Ar = new JTextField();
		label15 = new JLabel();
		ciMov1DEC = new JTextField();
		csMov1DEC = new JTextField();
		label16 = new JLabel();
		ciMov2Dec = new JTextField();
		csMMov2Dec = new JTextField();
		button1 = new JButton();
		separator3 = new JSeparator();
		label12 = new JLabel();
		scrollPane1 = new JScrollPane();
		table1 = new JTable();
		separator1 = new JSeparator();
		label11 = new JLabel();
		separator2 = new JSeparator();
		label10 = new JLabel();
		label17 = new JLabel();
		aliasTarea = new JTextField();
		label18 = new JLabel();
		rutaTarea = new JTextField();
		selRuta = new JButton();
		label19 = new JLabel();
		listSurvey = new JList();
		button2 = new JButton();

		//======== this ========
		setLayout(new GridLayoutManager(34, 6, new Insets(20, 20, 20, 20), -1, -1, true, false));

		//---- label1 ----
		label1.setText("GENERAR TAREAS MEDIANTE CONSULTAS EN EL DSWC");
		add(label1, new GridConstraints(0, 0, 2, 6,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label20 ----
		label20.setText("Cota Inferior");
		add(label20, new GridConstraints(3, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label21 ----
		label21.setText("Cota Superior");
		add(label21, new GridConstraints(3, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label22 ----
		label22.setText("Cota Inferior");
		add(label22, new GridConstraints(3, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label23 ----
		label23.setText("Cota Superior");
		add(label23, new GridConstraints(3, 5, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label2 ----
		label2.setText("N\u00famero Observaciones");
		add(label2, new GridConstraints(4, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ciNumObs, new GridConstraints(4, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(csNumObs, new GridConstraints(4, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label6 ----
		label6.setText("Distacancia lineal");
		add(label6, new GridConstraints(4, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ciDistLin, new GridConstraints(4, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(csDistLin, new GridConstraints(4, 5, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label3 ----
		label3.setText("Primera Observaci\u00f3n");
		add(label3, new GridConstraints(6, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ciPrimObs, new GridConstraints(6, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(csPrimObs, new GridConstraints(6, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label7 ----
		label7.setText("\u00c1ngulo");
		add(label7, new GridConstraints(6, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ciAng, new GridConstraints(6, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(csAng, new GridConstraints(6, 5, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label4 ----
		label4.setText("\u00daltima Observaci\u00f3n");
		add(label4, new GridConstraints(8, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ciUlObs, new GridConstraints(8, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(csUltObs, new GridConstraints(8, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label8 ----
		label8.setText("Espectro");
		add(label8, new GridConstraints(8, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- ciEspec ----
		ciEspec.setEditable(false);
		ciEspec.setEnabled(false);
		add(ciEspec, new GridConstraints(8, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label5 ----
		label5.setText("Magnitud 1");
		add(label5, new GridConstraints(10, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ciMag1, new GridConstraints(10, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(csMag1, new GridConstraints(10, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label9 ----
		label9.setText("Magnitud 2");
		add(label9, new GridConstraints(10, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ciMag2, new GridConstraints(10, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(csMag2, new GridConstraints(10, 5, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label13 ----
		label13.setText("Movimiento 1 AR");
		add(label13, new GridConstraints(12, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ciMov1Ar, new GridConstraints(12, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(csMov1AR, new GridConstraints(12, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label14 ----
		label14.setText("Movimiento 2 AR");
		add(label14, new GridConstraints(12, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ciMov2Ar, new GridConstraints(12, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(csMov2Ar, new GridConstraints(12, 5, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label15 ----
		label15.setText("Movimiento 1 DEC");
		add(label15, new GridConstraints(14, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ciMov1DEC, new GridConstraints(14, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(csMov1DEC, new GridConstraints(14, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label16 ----
		label16.setText("Movimiento 2 DEC");
		add(label16, new GridConstraints(14, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ciMov2Dec, new GridConstraints(14, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(csMMov2Dec, new GridConstraints(14, 5, 1, 1,
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
		add(button1, new GridConstraints(16, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(separator3, new GridConstraints(17, 0, 1, 6,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label12 ----
		label12.setText("DATOS OBTENIDOS EN LA CONSULTA");
		add(label12, new GridConstraints(18, 0, 1, 6,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(table1);
		}
		add(scrollPane1, new GridConstraints(19, 0, 7, 6,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(separator1, new GridConstraints(26, 0, 1, 6,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label11 ----
		label11.setText("ESTAD\u00cdSTICAS DE DATOS OBTENIDOS");
		add(label11, new GridConstraints(27, 0, 1, 6,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(separator2, new GridConstraints(28, 0, 1, 6,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label10 ----
		label10.setText("GENERACI\u00d3N DE TAREAS");
		add(label10, new GridConstraints(29, 0, 1, 6,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label17 ----
		label17.setText("Alias");
		add(label17, new GridConstraints(31, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(aliasTarea, new GridConstraints(31, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label18 ----
		label18.setText("Ruta");
		add(label18, new GridConstraints(31, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(rutaTarea, new GridConstraints(31, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- selRuta ----
		selRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selRutaActionPerformed(e);
			}
		});
		add(selRuta, new GridConstraints(31, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label19 ----
		label19.setText("SURVEYS");
		add(label19, new GridConstraints(32, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- listSurvey ----
		listSurvey.setVisibleRowCount(5);
		listSurvey.setFont(new Font("Arial", Font.PLAIN, 11));
		listSurvey.setSelectedIndex(0);
		listSurvey.setBorder(new BevelBorder(BevelBorder.LOWERED));
		add(listSurvey, new GridConstraints(32, 3, 1, 1,
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
		add(button2, new GridConstraints(33, 2, 1, 2,
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
	private JLabel label20;
	private JLabel label21;
	private JLabel label22;
	private JLabel label23;
	private JLabel label2;
	private JTextField ciNumObs;
	private JTextField csNumObs;
	private JLabel label6;
	private JTextField ciDistLin;
	private JTextField csDistLin;
	private JLabel label3;
	private JTextField ciPrimObs;
	private JTextField csPrimObs;
	private JLabel label7;
	private JTextField ciAng;
	private JTextField csAng;
	private JLabel label4;
	private JTextField ciUlObs;
	private JTextField csUltObs;
	private JLabel label8;
	private JTextField ciEspec;
	private JLabel label5;
	private JTextField ciMag1;
	private JTextField csMag1;
	private JLabel label9;
	private JTextField ciMag2;
	private JTextField csMag2;
	private JLabel label13;
	private JTextField ciMov1Ar;
	private JTextField csMov1AR;
	private JLabel label14;
	private JTextField ciMov2Ar;
	private JTextField csMov2Ar;
	private JLabel label15;
	private JTextField ciMov1DEC;
	private JTextField csMov1DEC;
	private JLabel label16;
	private JTextField ciMov2Dec;
	private JTextField csMMov2Dec;
	private JButton button1;
	private JSeparator separator3;
	private JLabel label12;
	private JScrollPane scrollPane1;
	private JTable table1;
	private JSeparator separator1;
	private JLabel label11;
	private JSeparator separator2;
	private JLabel label10;
	private JLabel label17;
	private JTextField aliasTarea;
	private JLabel label18;
	private JTextField rutaTarea;
	private JButton selRuta;
	private JLabel label19;
	private JList listSurvey;
	private JButton button2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
