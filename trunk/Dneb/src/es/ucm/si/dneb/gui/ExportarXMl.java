package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.intellij.uiDesigner.core.*;
/*
 * Created by JFormDesigner on Thu Mar 25 18:29:24 CET 2010
 */

import es.ucm.si.dneb.service.exportData.ServiceExportData;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareasException;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;



/**
 * @author Brainrain
 */
public class ExportarXMl extends JPanel {

	
	private static final long serialVersionUID = -2463002191795071823L;
	public ExportarXMl() {
		initComponents();
		
		
	}

	private void selRutaActionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setAcceptAllFileFilterUsed(false);
		int retval = fc.showOpenDialog(this);
		try {
			if (retval == JFileChooser.APPROVE_OPTION) {
				this.ruta.setText( fc.getSelectedFile().toString());
			}
		} catch(ServicioGestionTareasException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}

	private void button2ActionPerformed(ActionEvent e) {
		ServiceExportData serviceExportData= (ServiceExportData) ContextoAplicacion.getApplicationContext().getBean("serviceExportData");
		String filename= this.fileName.getText();
		if(filename.equals("")){
			filename="relevant_data";
		}
		
		String path = this.ruta.getText();
		if(path.equals("")){
			JOptionPane.showMessageDialog(null,"Es necesario elegir el directorio donde crear el fichero", "Operación fallida", JOptionPane.INFORMATION_MESSAGE);
		}else{
			serviceExportData.exportRelevantData(filename, path);
			JOptionPane.showMessageDialog(null,"Fichero creado", "Operación satisfactoria", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		selRuta = new JButton();
		ruta = new JTextField();
		label1 = new JLabel();
		fileName = new JTextField();
		button2 = new JButton();

		//======== this ========
		setLayout(new GridLayoutManager(20, 20, new Insets(100, 100, 100, 100), 5, -1));

		//---- selRuta ----
		selRuta.setText("SELECCIONE RUTA");
		selRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selRutaActionPerformed(e);
			}
		});
		add(selRuta, new GridConstraints(6, 3, 1, 6,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ruta, new GridConstraints(6, 10, 1, 4,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label1 ----
		label1.setText("NOMBRE DEL FICHERO");
		add(label1, new GridConstraints(8, 3, 1, 6,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(fileName, new GridConstraints(8, 10, 1, 4,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- button2 ----
		button2.setText("EXPORTAR XML");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button2ActionPerformed(e);
			}
		});
		add(button2, new GridConstraints(10, 7, 1, 4,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JButton selRuta;
	private JTextField ruta;
	private JLabel label1;
	private JTextField fileName;
	private JButton button2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
