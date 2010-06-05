package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sat Nov 28 10:27:43 CET 2009
 */

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;



/**
 * @author Brainrain
 */
public class DataBaseConfig extends JPanel {
	
	public DataBaseConfig() {
		initComponents();
	}

	private void guardar() {
		// TODO add your code here
	}

	private void cargarValoresActuales() {
		// TODO add your code here
	}

	private void initComponents() {
		titulo = new JLabel();
		driver = new JLabel();
		driverClass = new JTextField();
		url = new JLabel();
		urlBBDD = new JTextField();
		user = new JLabel();
		usuario = new JTextField();
		pass = new JLabel();
		contraseña = new JTextField();
		guardar = new JButton();
		cargarValoresActuales = new JButton();
		separator1 = new JSeparator();
		
		guardar.setIcon(new ImageIcon("images/save.png"));
		cargarValoresActuales.setIcon(new ImageIcon("images/load.png"));

		//======== this ========
		setLayout(new GridLayoutManager(7, 5, new Insets(30, 60, 60, 60), 5, -1));
		
		
		//---- titulo ----
		titulo.setText("CONFIGURACIÓN DE BASE DE DATOS");
		titulo.setFont(titulo.getFont().deriveFont(titulo.getFont().getSize() + 10f));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(titulo, new GridConstraints(0, 0, 1, 5,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				null, null, null));
		
		
		
		
		
		
		//---- separador1 ----
		add(separator1, new GridConstraints(1, 0, 1, 5,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				null, null, null));
		
		
		
		
		
		
		//---- label2 ----
		driver.setText("DRIVER CLASS NAME");
		add(driver, new GridConstraints(2, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		add(driverClass, new GridConstraints(2, 1, 1, 4,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		
		
		
		
		
		
		//---- label3 ----
		url.setText("URL BBDD");
		add(url, new GridConstraints(3, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		add(urlBBDD, new GridConstraints(3, 1, 1, 4,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		
		
		
		
		
		
		//---- label4 ----
		user.setText("USUARIO");
		add(user, new GridConstraints(4, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		add(usuario, new GridConstraints(4, 1, 1, 4,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		
		
		
		
		
		
		//---- label5 ----
		pass.setText("PASSWORD");
		add(pass, new GridConstraints(5, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		add(contraseña, new GridConstraints(5, 1, 1, 4,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));

		//---- guardar ----
		guardar.setText("GUARDAR");
		guardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guardar();
			}
		});
		add(guardar, new GridConstraints(6, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				new Dimension(300, 40), null, new Dimension(300, 40)));

		//---- cargarValoresActuales ----
		cargarValoresActuales.setText("CARGAR VALORES ACTUALES");
		cargarValoresActuales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarValoresActuales();
			}
		});
		add(cargarValoresActuales, new GridConstraints(6, 3, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				new Dimension(300, 40), null, new Dimension(300, 40)));
	}

	private JLabel titulo;
	private JLabel driver;
	private JTextField driverClass;
	private JLabel url;
	private JTextField urlBBDD;
	private JLabel user;
	private JTextField usuario;
	private JLabel pass;
	private JTextField contraseña;
	private JButton guardar;
	private JButton cargarValoresActuales;
	private JSeparator separator1;
}
