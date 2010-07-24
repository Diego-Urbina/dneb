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
		selRuta = new JButton();
		ruta = new JTextField();
		ruta.setEditable(false);
		label1 = new JLabel();
		fileName = new JTextField();
		button2 = new JButton();
		titulo = new JLabel();
		separator1 = new JSeparator();

		//======== this ========
		setLayout(new GridLayoutManager(5, 5, new Insets(30, 60, 60, 60), 5, -1));

		
		//---- titulo ----
		titulo.setText("EXPORTAR A XML");
		titulo.setFont(titulo.getFont().deriveFont(titulo.getFont().getSize() + 10f));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(titulo, new GridConstraints(0, 0, 1, 5,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK ,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				null, null, null));
		
		
		
		
		
		
		//---- separador1 ----
		add(separator1, new GridConstraints(1, 0, 1, 5,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				null, null, null));
		
		
		//---- selRuta ----
		selRuta.setText("SELECCIONE RUTA");
		selRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selRutaActionPerformed(e);
			}
		});
		add(selRuta, new GridConstraints(2, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ruta, new GridConstraints(2, 1, 1, 2,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label1 ----
		label1.setText("NOMBRE DEL FICHERO");
		add(label1, new GridConstraints(3, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(fileName, new GridConstraints(3, 1, 1, 2,
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
		add(button2, new GridConstraints(4, 0, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
	}

	private JButton selRuta;
	private JTextField ruta;
	private JLabel label1;
	private JTextField fileName;
	private JButton button2;
	private JLabel titulo;
	private JSeparator separator1;
}
