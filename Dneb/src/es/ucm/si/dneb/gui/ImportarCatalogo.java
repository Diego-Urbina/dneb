package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.springframework.context.ApplicationContext;
/*
 * Created by JFormDesigner on Tue Feb 02 17:35:35 CET 2010
 */

import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareasException;
import es.ucm.si.dneb.service.importData.ImportDoubleStarCatalog;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;



/**
 * @author Brainrain
 */
public class ImportarCatalogo extends JPanel {
	
	private ImportDoubleStarCatalog imporCatalog;
	
	public ImportarCatalogo() {
		initComponents();
		
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
		
		imporCatalog= (ImportDoubleStarCatalog) ctx.getBean("importDoubleStarCatalog");
	}

	private void cargarActionPerformed(ActionEvent e) {
		
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
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

	private void importarActionPerformed(ActionEvent e) {

		imporCatalog.loadCatalog(ruta.getText());
		
		JOptionPane.showMessageDialog(null,"Catálogo importado", "Operación satisfactoria", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("images/downconfig (Custom).JPG"));
		
		
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		titulo = new JLabel();
		ruta = new JTextField();
		cargar = new JButton();
		importar = new JButton();

		//======== this ========

		//---- titulo ----
		titulo.setText("Importar Cat\u00e1logo de Estrellas Dobles");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(titulo.getFont().deriveFont(titulo.getFont().getStyle() | Font.BOLD, titulo.getFont().getSize() + 6f));

		//---- ruta ----
		ruta.setEditable(false);

		//---- cargar ----
		cargar.setText("Abrir fichero");
		cargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarActionPerformed(e);
			}
		});

		//---- importar ----
		importar.setText("Importar");
		importar.setToolTipText("Importar borrando el cat\u00e1logo actual");
		importar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importarActionPerformed(e);
			}
		});

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(titulo, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(ruta, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(importar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cargar, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(titulo, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(75, 75, 75)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(ruta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cargar))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(importar)
					.addGap(111, 111, 111))
		);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	public void setImporCatalog(ImportDoubleStarCatalog imporCatalog) {
		this.imporCatalog = imporCatalog;
	}

	public ImportDoubleStarCatalog getImporCatalog() {
		return imporCatalog;
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel titulo;
	private JTextField ruta;
	private JButton cargar;
	private JButton importar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
