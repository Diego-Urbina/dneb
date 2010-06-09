package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import org.springframework.context.ApplicationContext;

import com.intellij.uiDesigner.core.*;
/*
 * Created by JFormDesigner on Sat Nov 28 10:41:50 CET 2009
 */

import es.ucm.si.dneb.domain.DownloadConfig;
import es.ucm.si.dneb.domain.FormatoFichero;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.service.downloadDefaultConfig.ServiceDownloadDefaultConfig;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareasException;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;

public class DefaultDownloadSettingsConfig extends JPanel {
	
	private ServiceDownloadDefaultConfig serviceDownloadDefaultConfig;
	private ServicioGestionTareas servicioGestionTareas;
	private ArrayList<Survey> surveys;
	private ArrayList<FormatoFichero> formatosFichero;
	private ArrayList<DownloadConfig> configsList;
	
	public DefaultDownloadSettingsConfig() {
		initComponents();
		
		this.rellenarModel();
		this.initIcons();
		this.elminarConfiguracion.setIcon(new ImageIcon("images/delete.png"));
		this.cargarValoresPorDefecto.setIcon(new ImageIcon("images/load.png"));
		this.guardarValoresPorDefecto.setIcon(new ImageIcon("images/save.png"));
	}
	
	private void initIcons() {
		this.button1.setIcon(new ImageIcon("images/carpeta.jpg"));
	}
	
	private void rellenarModel() {
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
		serviceDownloadDefaultConfig = (ServiceDownloadDefaultConfig)ctx.getBean("serviceDownloadDefaultConfig");
		servicioGestionTareas=((ServicioGestionTareas) ctx.getBean("servicioGestionTareas"));
		
        try {
        	
        //INCIALIZACION DE SURVEYS
        	
        surveys = (ArrayList<Survey>) serviceDownloadDefaultConfig.getAllSurveys();
		
        DefaultComboBoxModel list = new DefaultComboBoxModel();
        
        for (Survey aux : surveys){
        	list.addElement(aux.getDescripcion());
        }
        
        this.comboBoxSURVEY1.setModel(list);
        this.comboBoxSURVEY1.setSelectedIndex(0);
        this.comboBoxSURVEY1.setModel(list);
        this.comboBoxSURVEY1.setSelectedIndex(0);
        
        DefaultComboBoxModel list1 = new DefaultComboBoxModel();
        
        for (Survey aux : surveys){
        	list1.addElement(aux.getDescripcion());
        }
        
        
        this.comboBoxSURVEY2.setModel(list1);
        this.comboBoxSURVEY2.setSelectedIndex(0);
        this.comboBoxSURVEY2.setModel(list1);
        this.comboBoxSURVEY2.setSelectedIndex(0);
        
        //INICIALIZACION DE FORMATOS DE FICHERO
        
        formatosFichero = (ArrayList<FormatoFichero>) serviceDownloadDefaultConfig.getFormatosFichero();
        
        DefaultComboBoxModel listFormato = new DefaultComboBoxModel();
        
        
        for (FormatoFichero aux : formatosFichero){
        	listFormato.addElement(aux.getAlias());
        }
        
        this.formatoFichero.setModel(listFormato);
        this.formatoFichero.setSelectedIndex(0);
        this.formatoFichero.setModel(listFormato);
        this.formatoFichero.setSelectedIndex(0);
        
        //INICIALIZACION DE CONFIGURACIONES
        
        updateConfigList();
        
        
        } catch(ServicioGestionTareasException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private void updateConfigList() {		
		
		configsList = (ArrayList<DownloadConfig>) serviceDownloadDefaultConfig.getDownloadConfigs();
        if(configsList.size()==0){
        	return;        	
        }
		
        DefaultComboBoxModel comboConfigList = new DefaultComboBoxModel();
        
        for (DownloadConfig aux : configsList){
        	comboConfigList.addElement(aux.getAlias());
        }
        
        this.comboBoxValoresPorDefecto.setModel(comboConfigList);
        this.comboBoxValoresPorDefecto.setSelectedIndex(0);
        this.comboBoxValoresPorDefecto.setModel(comboConfigList);
        this.comboBoxValoresPorDefecto.setSelectedIndex(0);
	}
	
	private void cargarValoresPorDEfecto(MouseEvent e) {
		int configDefault = this.comboBoxValoresPorDefecto.getSelectedIndex();
		 
		DownloadConfig selectedDownloadDefaultConfiguration = configsList.get(configDefault);
		
		if(selectedDownloadDefaultConfiguration.getAlto()!=null){
			this.ALTOINPUT.setText(selectedDownloadDefaultConfiguration.getAlto().toString());
		} else this.ALTOINPUT.setText("");
		if(selectedDownloadDefaultConfiguration.getAncho()!=null){
			this.ANCHOINPUT.setText(selectedDownloadDefaultConfiguration.getAncho().toString());
		} else this.ANCHOINPUT.setText("");
		if(selectedDownloadDefaultConfiguration.getPath()!=null){
			this.ruta.setText(selectedDownloadDefaultConfiguration.getPath());
		} else this.ruta.setText("");
		
		this.aliasNuevaConfig.setText(selectedDownloadDefaultConfiguration.getAlias());
		
		selectedDownloadDefaultConfiguration.getSurveys();
		
		//this.comboBoxSURVEY1.setSelectedIndex();
		//this.comboBoxSURVEY2.setSelectedIndex();
		
		
		FormatoFichero formFich= selectedDownloadDefaultConfiguration.getFormatoFichero();
		
		
		
		for(int i= 0 ; i<=this.formatosFichero.size()-1 ;i++){
			
			FormatoFichero aux= formatosFichero.get(i);
			
			if(aux.getAlias().equals(formFich.getAlias())){
				formatoFichero.setSelectedIndex(i);
			}
		}
		
		Survey survey1= selectedDownloadDefaultConfiguration.getSurveys().get(0);
		Survey survey2=selectedDownloadDefaultConfiguration.getSurveys().get(1);
		
		
		for(int i= 0 ; i<=this.surveys.size()-1 ;i++){
			
			Survey aux= surveys.get(i);
			
			if(aux.getDescripcion().equals(survey1.getDescripcion())){
				
				comboBoxSURVEY1.setSelectedIndex(i);

			}
			if(aux.getDescripcion().equals(survey2.getDescripcion())){
				
				comboBoxSURVEY2.setSelectedIndex(i);

			}
			
		}
		
	}

	private void guardarValoresPorDefecto(MouseEvent e) {

		String aliasConfig = aliasNuevaConfig.getText();		
		
		DownloadConfig downloadConfig= new DownloadConfig();
		
		
		if(!this.aliasNuevaConfig.getText().equals("")){
			downloadConfig.setAlias(this.aliasNuevaConfig.getText());
		}else{
			showAlertMessage("INTRODUZCA UN ALIAS DE CONFIGURACION");
			return;
		}
		
		if(!this.ALTOINPUT.getText().equals("")){
			downloadConfig.setAlto(Double.valueOf(this.ALTOINPUT.getText()));	
		}
		
		if(!this.ANCHOINPUT.getText().equals("")){
			downloadConfig.setAncho(Double.valueOf(this.ANCHOINPUT.getText()));
		}
		
		int formatoSeleccionado = this.formatoFichero.getSelectedIndex();
		
		downloadConfig.setFormatoFichero(formatosFichero.get(formatoSeleccionado));
		
		if(!this.ruta.getText().equals("")){
			downloadConfig.setPath(this.ruta.getText());
		}
		
		int survey1 = this.comboBoxSURVEY1.getSelectedIndex();
		int survey2 = this.comboBoxSURVEY2.getSelectedIndex();
		
		ArrayList<Survey> selectedSurveys = new ArrayList<Survey>();
		
		selectedSurveys.add(this.surveys.get(survey1));
		selectedSurveys.add(this.surveys.get(survey2));		
		
		downloadConfig.setSurveys(selectedSurveys);
		
		if(serviceDownloadDefaultConfig.existsConfig(aliasConfig)){
			// Lo borro	
			DownloadConfig aux = serviceDownloadDefaultConfig.loadDownloadDefaultConfiguration(aliasConfig);
			serviceDownloadDefaultConfig.deleteConfig(aux.getId());
		}		
		
		serviceDownloadDefaultConfig.createNewDownloadDefaultConfig(downloadConfig);
		
		updateConfigList();
		showAlertMessage("Configuración " + aliasConfig + " guardada con exito");
	}
	
	private void showAlertMessage(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	

	private void elminarConfiguracionMouseClicked(MouseEvent e) {
		int configDefault = this.comboBoxValoresPorDefecto.getSelectedIndex();
		 
		DownloadConfig selectedDownloadDefaultConfiguration = configsList.get(configDefault);
		String name = selectedDownloadDefaultConfiguration.getAlias();
		
		serviceDownloadDefaultConfig.deleteConfig(selectedDownloadDefaultConfiguration.getId());
		
		this.configsList.remove(selectedDownloadDefaultConfiguration);
		
		updateConfigList();
		showAlertMessage("Configuración " + name + " eliminada con exito");
	}

	private void rutaMouseClicked(MouseEvent e) {
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

	private void initComponents() {
		label3 = new JLabel();
		ALTOINPUT = new JTextField();
		ANCHOINPUT = new JTextField();
		label6 = new JLabel();
		label5 = new JLabel();
		comboBoxSURVEY2 = new JComboBox();
		comboBoxSURVEY1 = new JComboBox();
		label4 = new JLabel();
		ruta = new JTextField();
		formatoFichero = new JComboBox();
		label11 = new JLabel();
		label10 = new JLabel();
		label13 = new JLabel();
		aliasNuevaConfig = new JTextField();
		label14 = new JLabel();
		comboBoxValoresPorDefecto = new JComboBox();
		guardarValoresPorDefecto = new JButton();
		separator1 = new JSeparator();
		separator2 = new JSeparator();
		titulo = new JLabel();
		button1 = new JButton();
		cargarValoresPorDefecto = new JButton();
		elminarConfiguracion = new JButton();

		//======== this ========
		setLayout(new GridLayoutManager(8, 5, new Insets(30, 60, 0, 60), 5, -1));

		//---- titulo ----
		titulo.setText("CONFIGURAR DESCARGA");
		titulo.setFont(titulo.getFont().deriveFont(titulo.getFont().getSize() + 10f));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(titulo, new GridConstraints(0, 0, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK ,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		
		
		
		
		
		//---- separador1 ----
		add(separator1, new GridConstraints(1, 0, 1, 5,
				GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK ,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				null, null, null));
		
		
		
		
		
		
		//---- survey1 ----
		label4.setText("SURVEY 1");
		add(label4, new GridConstraints(2, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(comboBoxSURVEY1, new GridConstraints(2, 1, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		//---- survey2 ----
		label5.setText("SURVEY 2");
		add(label5, new GridConstraints(2, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(comboBoxSURVEY2, new GridConstraints(2, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		
		
		
		
		
		//---- ancho ----
		label6.setText("ANCHO");
		add(label6, new GridConstraints(3, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ANCHOINPUT, new GridConstraints(3, 4, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		//---- alto ----
		label3.setText("ALTO");
		add(label3, new GridConstraints(3, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ALTOINPUT, new GridConstraints(3, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		
		
		
		
		
		//---- ruta ----
		label10.setText("RUTA");
		add(label10, new GridConstraints(4, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		ruta.setEditable(false);
		add(ruta, new GridConstraints(4, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		//---- button1 ----
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rutaMouseClicked(e);
			}
		});
		add(button1, new GridConstraints(4, 2, 1, 1,
			GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_FIXED,
			GridConstraints.SIZEPOLICY_FIXED,
			new Dimension(20, 20), new Dimension(20, 20), new Dimension(20, 20)));
		//---- formato fichero ----
		label11.setText("FORMATO FICHERO");
		add(label11, new GridConstraints(4, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(formatoFichero, new GridConstraints(4, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		
		
		
		
		
		//---- separador2 ----
		add(separator2, new GridConstraints(5, 0, 1, 5,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				null, null, null));
		

		

		//---- label13 ----
		label13.setText("ALIAS CONFIGURACION");
		add(label13, new GridConstraints(6, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(aliasNuevaConfig, new GridConstraints(6, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label14 ----
		label14.setText("VALORES POR DEFECTO");
		add(label14, new GridConstraints(6, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(comboBoxValoresPorDefecto, new GridConstraints(6, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		
		
		
		
		
		//---- guardarValoresPorDefecto ----
		guardarValoresPorDefecto.setText("GUARDAR CONFIGURACION");
		guardarValoresPorDefecto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guardarValoresPorDefecto(e);
			}
		});
		add(guardarValoresPorDefecto, new GridConstraints(7, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));		

		//---- cargarValoresPorDefecto ----
		cargarValoresPorDefecto.setText("CARGAR CONFIGURACION");
		cargarValoresPorDefecto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarValoresPorDEfecto(e);
			}
		});
		add(cargarValoresPorDefecto, new GridConstraints(7, 1, 1, 3,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- elminarConfiguracion ----
		elminarConfiguracion.setText("ELIMINAR CONFIGURACION");
		elminarConfiguracion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				elminarConfiguracionMouseClicked(e);
			}
		});
		add(elminarConfiguracion, new GridConstraints(7, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
	}

	public void setServicioGestionTareas(ServicioGestionTareas servicioGestionTareas) {
		this.servicioGestionTareas = servicioGestionTareas;
	}

	public ServicioGestionTareas getServicioGestionTareas() {
		return servicioGestionTareas;
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label3;
	private JTextField ALTOINPUT;
	private JTextField ANCHOINPUT;
	private JLabel label6;
	private JLabel label5;
	private JComboBox comboBoxSURVEY2;
	private JComboBox comboBoxSURVEY1;
	private JLabel label4;
	private JTextField ruta;
	private JComboBox formatoFichero;
	private JLabel label11;
	private JLabel label10;
	private JLabel label13;
	private JTextField aliasNuevaConfig;
	private JLabel label14;
	private JComboBox comboBoxValoresPorDefecto;
	private JButton guardarValoresPorDefecto;
	private JSeparator separator1;
	private JSeparator separator2;
	private JLabel titulo;
	private JButton button1;
	private JButton cargarValoresPorDefecto;
	private JButton elminarConfiguracion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
