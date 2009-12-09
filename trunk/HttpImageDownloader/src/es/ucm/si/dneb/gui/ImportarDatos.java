package es.ucm.si.dneb.gui;

import java.awt.*;
import javax.swing.*;
import com.intellij.uiDesigner.core.*;
/*
 * Created by JFormDesigner on Tue Dec 08 11:06:01 CET 2009
 */



/**
 * @author aa
 */
public class ImportarDatos extends JFrame {
	public ImportarDatos() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label1 = new JLabel();
		tabbedPane1 = new JTabbedPane();
		panel1 = new JPanel();
		scrollPane1 = new JScrollPane();
		table1 = new JTable();
		panel2 = new JPanel();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayoutManager(10, 10, new Insets(0, 0, 0, 0), -1, -1));

		//---- label1 ----
		label1.setText("IMPORTAR DATOS DE DESCARGAS");
		contentPane.add(label1, new GridConstraints(0, 0, 1, 10,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//======== tabbedPane1 ========
		{

			//======== panel1 ========
			{
				panel1.setLayout(new GridLayoutManager(10, 10, new Insets(0, 0, 0, 0), -1, -1));

				//======== scrollPane1 ========
				{
					scrollPane1.setViewportView(table1);
				}
				panel1.add(scrollPane1, new GridConstraints(1, 1, 6, 8,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
					GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
					GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
					null, null, null));
			}
			tabbedPane1.addTab("IMPORTAR DATOS DE BBDD", panel1);


			//======== panel2 ========
			{
				panel2.setLayout(new GridLayoutManager(10, 10, new Insets(0, 0, 0, 0), -1, -1));
			}
			tabbedPane1.addTab("IMPORTAR DATOS DESDE FICHERO XML", panel2);

		}
		contentPane.add(tabbedPane1, new GridConstraints(1, 0, 9, 10,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JTabbedPane tabbedPane1;
	private JPanel panel1;
	private JScrollPane scrollPane1;
	private JTable table1;
	private JPanel panel2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
