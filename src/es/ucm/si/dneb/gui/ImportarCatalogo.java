package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.springframework.context.ApplicationContext;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
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
	

	private static final long serialVersionUID = -6491542979983449271L;
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

	private void eliminarActionPerformed(ActionEvent e) {
		this.imporCatalog.deleteCatalog();
	}

	private void initComponents() {
		titulo = new JLabel();
		ruta = new JTextField();
		cargar = new JButton();
		importar = new JButton();
		eliminar = new JButton();
		separator1 = new JSeparator();

		//======== this ========
		setLayout(new GridLayoutManager(3, 2, new Insets(30, 60, 30, 60), 5, -1));
		
		//---- titulo ----
		titulo.setText("IMPORTAR CATÁLOGO DE ESTRELLAS DOBLES");
		titulo.setFont(titulo.getFont().deriveFont(titulo.getFont().getSize() + 10f));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(titulo, new GridConstraints(0, 0, 1, 2,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		
		
		
		
		
		//---- separator1 ----
		add(separator1, new GridConstraints(1, 0, 1, 2,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				null, null, null));

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

		//---- eliminar ----
		eliminar.setText("Elminar Todos los Datos");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarActionPerformed(e);
			}
		});

		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(ruta, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(importar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cargar, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addComponent(eliminar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGap(75, 75, 75)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(ruta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cargar))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(importar)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(eliminar)
					.addGap(78, 78, 78))
		);
		
		add(panel, new GridConstraints(2, 0, 1, 2,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		
	}

	public void setImporCatalog(ImportDoubleStarCatalog imporCatalog) {
		this.imporCatalog = imporCatalog;
	}

	public ImportDoubleStarCatalog getImporCatalog() {
		return imporCatalog;
	}

	private JLabel titulo;
	private JTextField ruta;
	private JButton cargar;
	private JButton importar;
	private JButton eliminar;
	private JSeparator separator1;
}
