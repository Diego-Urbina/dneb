/*
 * Created by JFormDesigner on Sat Sep 12 23:56:32 CEST 2009
 */

package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author aa
 */
public class MapPanel extends JPanel {
	
	private VentanaPcpal principal;
	
	public MapPanel(VentanaPcpal pcpal) {
		initComponents();
		principal = pcpal;
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
		if (retval == JFileChooser.APPROVE_OPTION) {
			principal.ruta = fc.getSelectedFile().toString();
		}
	}

	private void buttonAnteriorActionPerformed(ActionEvent e) {
		// TODO add your code here
		JPanel vent = new SurveyPanel(principal);
		principal.getContentPane().remove(0);
		principal.getContentPane().add(vent);
		principal.pack();
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

		//---- textFieldARI ----
		textFieldARI.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- textFieldARF ----
		textFieldARF.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- textFieldDECI ----
		textFieldDECI.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- textFieldDECF ----
		textFieldDECF.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- textFieldAlto ----
		textFieldAlto.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- textFieldAncho ----
		textFieldAncho.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- textFieldSolap ----
		textFieldSolap.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- buttonSiguiente ----
		buttonSiguiente.setText("SIGUIENTE");
		buttonSiguiente.setFont(new Font("Arial", Font.PLAIN, 11));
		buttonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonSiguienteActionPerformed(e);
			}
		});

		//---- buttonAnterior ----
		buttonAnterior.setText("ANTERIOR");
		buttonAnterior.setFont(new Font("Arial", Font.PLAIN, 11));
		buttonAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAnteriorActionPerformed(e);
			}
		});

		//---- labelARI ----
		labelARI.setText("AR INICIAL");
		labelARI.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- labelDECI ----
		labelDECI.setText("DEC INICIAL");
		labelDECI.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- labelAlto ----
		labelAlto.setText("ALTO");
		labelAlto.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- labelAncho ----
		labelAncho.setText("ANCHO");
		labelAncho.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- labelEq ----
		labelEq.setText("EQUINOCIO");
		labelEq.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- labelARF ----
		labelARF.setText("AR FINAL");
		labelARF.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- labelDECF ----
		labelDECF.setText("DEC FINAL");
		labelDECF.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- labelSolap ----
		labelSolap.setText("SOLAPAMIENTO");
		labelSolap.setFont(new Font("Arial", Font.PLAIN, 11));

		//---- comboBoxEq ----
		comboBoxEq.setModel(new DefaultComboBoxModel(new String[] {
			"J2000",
			"B1950"
		}));
		comboBoxEq.setFont(new Font("Arial", Font.PLAIN, 11));

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(35, 35, 35)
					.addComponent(buttonAnterior, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
					.addComponent(buttonSiguiente, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGap(37, 37, 37))
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup()
						.addComponent(labelARI, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelDECI, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup()
						.addComponent(textFieldARI, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldDECI, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
					.addGap(13, 13, 13)
					.addGroup(layout.createParallelGroup()
						.addComponent(labelARF, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelDECF, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
						.addComponent(textFieldDECF, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
						.addComponent(textFieldARF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
							.addComponent(labelAlto, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(textFieldAlto, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(labelAncho, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(textFieldAncho, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
					.addGap(13, 13, 13)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(labelEq, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelSolap, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup()
						.addComponent(comboBoxEq, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldSolap, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {comboBoxEq, textFieldARF, textFieldARI, textFieldAlto, textFieldAncho, textFieldDECF, textFieldDECI, textFieldSolap});
		layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {labelARF, labelDECF, labelEq, labelSolap});
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
							.addGap(37, 37, 37)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(textFieldARI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelARI))
							.addGap(18, 18, 18)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(textFieldDECI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelDECI)))
						.addGroup(layout.createSequentialGroup()
							.addGap(35, 35, 35)
							.addGroup(layout.createParallelGroup()
								.addGroup(layout.createSequentialGroup()
									.addComponent(labelARF)
									.addGap(18, 18, 18)
									.addComponent(labelDECF))
								.addGroup(layout.createSequentialGroup()
									.addComponent(textFieldARF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18, 18, 18)
									.addComponent(textFieldDECF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
							.addGap(2, 2, 2)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(textFieldAlto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelAlto))
							.addGap(18, 18, 18)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(textFieldAncho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelAncho)))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(labelEq)
								.addComponent(comboBoxEq, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18, 18, 18)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(textFieldSolap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelSolap))))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(buttonAnterior)
						.addComponent(buttonSiguiente))
					.addGap(26, 26, 26))
		);
		layout.linkSize(SwingConstants.VERTICAL, new Component[] {textFieldARF, textFieldARI});
		layout.linkSize(SwingConstants.VERTICAL, new Component[] {comboBoxEq, labelARF, labelARI, labelAlto, labelAncho, labelDECF, labelDECI, labelEq, labelSolap, textFieldAlto, textFieldAncho, textFieldDECF, textFieldDECI, textFieldSolap});
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
