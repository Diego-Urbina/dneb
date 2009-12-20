package es.ucm.si.dneb.gui;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.intellij.uiDesigner.core.*;

import org.springframework.context.ApplicationContext;

import es.ucm.si.dneb.domain.DownloadDefaultConfiguration;
import es.ucm.si.dneb.domain.FormatoFichero;
import es.ucm.si.dneb.domain.Survey;

import es.ucm.si.dneb.service.downloadDefaultConfig.ServiceDownloadDefaultConfig;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareasException;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;
/*
 * Created by JFormDesigner on Wed Nov 25 23:44:44 CET 2009
 */
/**
 * @author Brainrain
 */
public class CreateNewDownload extends JPanel {
	
	//private VentanaPcpal principal;
	private VentanaPcpal principal;
	
	private int position;
	
	private static final long serialVersionUID = 1459331863481818480L;
	
	private ServiceDownloadDefaultConfig serviceDownloadDefaultConfig;
	private ServicioGestionTareas servicioGestionTareas;
	
	private ArrayList<Survey> surveys;
	private ArrayList<FormatoFichero> formatosFichero; 
	private ArrayList<DownloadDefaultConfiguration> configsList;
	
	public CreateNewDownload(VentanaPcpal pcpal,int position) {
		principal = pcpal;
		this.position=position;
		initComponents();
		
		initIcons();
		//this.principal=principal;
		this.rellenarModel();
	}

	private void initIcons() {
		this.button1.setIcon(new ImageIcon("images/load-icon-2.jpg"));
	}
	
	private void rellenarModel() {
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
		serviceDownloadDefaultConfig = (ServiceDownloadDefaultConfig)ctx.getBean("serviceDownloadDefaultConfig");
		servicioGestionTareas = (ServicioGestionTareas) ctx.getBean("servicioGestionTareas");
		
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
        	listFormato.addElement(aux.getDescipcion());
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
		
		
		configsList = (ArrayList<DownloadDefaultConfiguration>) serviceDownloadDefaultConfig.getDownloadConfigs();
        if(configsList.size()==0){
        	return;
        	
        }
		
        DefaultComboBoxModel comboConfigList = new DefaultComboBoxModel();
        
        for (DownloadDefaultConfiguration aux : configsList){
        	comboConfigList.addElement(aux.getAlias());
        }
        
        this.comboBoxValoresPorDefecto.setModel(comboConfigList);
        this.comboBoxValoresPorDefecto.setSelectedIndex(0);
        this.comboBoxValoresPorDefecto.setModel(comboConfigList);
        this.comboBoxValoresPorDefecto.setSelectedIndex(0);
	}

	private void crearDescargaEvent(MouseEvent e) {
		
		int formatoSeleccionado = this.formatoFichero.getSelectedIndex();
		int survey1 = this.comboBoxSURVEY1.getSelectedIndex();
		int survey2 = this.comboBoxSURVEY2.getSelectedIndex();
		
		ArrayList<Survey> selectedSurveys = new ArrayList<Survey>();
		
		selectedSurveys.add(this.surveys.get(survey1));
		selectedSurveys.add(this.surveys.get(survey2));
		
		/**TODO*/
		
		boolean iniciarDescarga= this.INICIARDESCARGAALCREAR.isSelected();
		
		String alias = this.ALIASINPUT.getText();
		String description = this.DESCRIPTIONINPUT.getText();
		
		Double height;
		try{
			height = Double.valueOf(this.ALTOINPUT.getText());	
		}catch(NumberFormatException ex){
			showAlertMessage("INTRODUZCA UN NUMERO EN EL CAMPO ALTO");
			return;
		}
		Double weight;
		try{
			weight = Double.valueOf(this.ANCHOINPUT.getText());
		}catch(NumberFormatException ex){
			showAlertMessage("INTRODUZCA UN NUMERO EN EL CAMPO ANCHO");
			return;
		}
		Double ar;
		try{
			ar = Double.valueOf(this.ARINPUT.getText());	
		}catch(NumberFormatException ex){
			showAlertMessage("INTRODUZCA UN NUMERO EN EL CAMPO AR");
			return;
		}	
		
		Double dec;
		try{
			dec = Double.valueOf(this.DECINPUT.getText());
		}catch(NumberFormatException ex){
			showAlertMessage("INTRODUZCA UN NUMERO EN EL CAMPO DEC");
			return;
		}
		
		String path = this.ruta.getText();
		
		if(this.ruta.getText().equals("")){
			showAlertMessage("INTRODUZCA UNA RUTA");
			return;
		}
		
		
		
		/**TODO FALTA OPCIONES DE INICIAR DESCARGA ETC**/
		
		///boolean mostrar = this.visualizarAlTerminarDescarga.isSelected();
		
		
		try{
			servicioGestionTareas.createSingleDownloadTask(alias,description ,height,weight, formatosFichero.get(formatoSeleccionado),path, selectedSurveys, ar, dec,iniciarDescarga);
			
		}catch(ServicioGestionTareasException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
		
		principal.getPane().remove(position);
		
		JOptionPane.showMessageDialog(null,"Descarga creada satisfactoriamente", "Operación satisfactoria", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("images/downconfig (Custom).JPG"));
		
		
		
		
	}

	private void cargarValoresDefectoEvent(MouseEvent e) {
		
		int configDefault = this.comboBoxValoresPorDefecto.getSelectedIndex();
		 
		DownloadDefaultConfiguration selectedDownloadDefaultConfiguration = configsList.get(configDefault);
		
		if(selectedDownloadDefaultConfiguration.getAlto()!=null){
			this.ALTOINPUT.setText(selectedDownloadDefaultConfiguration.getAlto().toString());
		}	
		if(selectedDownloadDefaultConfiguration.getAncho()!=null){
			this.ANCHOINPUT.setText(selectedDownloadDefaultConfiguration.getAncho().toString());
		}
		if(selectedDownloadDefaultConfiguration.getPath()!=null){
			this.ruta.setText(selectedDownloadDefaultConfiguration.getPath());
		}
		
		selectedDownloadDefaultConfiguration.getSurveys();
		
		//this.comboBoxSURVEY1.setSelectedIndex();
		//this.comboBoxSURVEY2.setSelectedIndex();
		
		
		FormatoFichero formFich= selectedDownloadDefaultConfiguration.getFormatoFichero();
		
		
		
		for(int i= 0 ; i<=this.formatosFichero.size()-1 ;i++){
			
			FormatoFichero aux= formatosFichero.get(i);
			
			if(aux.getDescipcion().equals(formFich.getDescipcion())){
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

	private void guardarValoresPorDefectoEvent(MouseEvent e) {
		
		String aliasConfig = aliasNuevaConfig.getText();
		
		
		DownloadDefaultConfiguration downloadDefaultConfiguration= new DownloadDefaultConfiguration();
		
		
		
		if(!this.aliasNuevaConfig.getText().equals("")){
			downloadDefaultConfiguration.setAlias(this.aliasNuevaConfig.getText());
		}else{
			showAlertMessage("INTRODUZCA UN ALIAS DE CONFIGURACION");
			return;
		}
		
		
		if(serviceDownloadDefaultConfig.existsConfig(aliasConfig)){
			showAlertMessage("YA EXISTE UNA CONFIGURACION CON ESE ALIAS");
			return;
			
		}
		
		if(!this.ALTOINPUT.getText().equals("")){
			downloadDefaultConfiguration.setAlto(Double.valueOf(this.ALTOINPUT.getText()));	
		}
		
		if(!this.ANCHOINPUT.getText().equals("")){
			downloadDefaultConfiguration.setAncho(Double.valueOf(this.ANCHOINPUT.getText()));
		}
		
		int formatoSeleccionado = this.formatoFichero.getSelectedIndex();
		
		downloadDefaultConfiguration.setFormatoFichero(formatosFichero.get(formatoSeleccionado));
		
		if(!this.ruta.getText().equals("")){
			downloadDefaultConfiguration.setPath(this.ruta.getText());
		}
		
		int survey1 = this.comboBoxSURVEY1.getSelectedIndex();
		int survey2 = this.comboBoxSURVEY2.getSelectedIndex();
		
		ArrayList<Survey> selectedSurveys = new ArrayList<Survey>();
		
		selectedSurveys.add(this.surveys.get(survey1));
		selectedSurveys.add(this.surveys.get(survey2));
	
		
		
		downloadDefaultConfiguration.setSurveys(selectedSurveys);
		
		
		serviceDownloadDefaultConfig.createNewDownloadDefaultConfig(downloadDefaultConfiguration);
		
		updateConfigList();
		
	}

	private void showAlertMessage(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}




	private void volverAlMenuEvent(MouseEvent e) {
		//JPanel vent = new MenuPanel(principal);
		//principal.getContentPane().remove(0);
		//principal.getContentPane().add(vent);
		//principal.pack();
		//vent.setVisible(true);
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
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label1 = new JLabel();
		ALIASINPUT = new JTextField();
		label2 = new JLabel();
		DESCRIPTIONINPUT = new JTextField();
		ALTOINPUT = new JTextField();
		label3 = new JLabel();
		ANCHOINPUT = new JTextField();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		ARINPUT = new JTextField();
		DECINPUT = new JTextField();
		label7 = new JLabel();
		label8 = new JLabel();
		label9 = new JLabel();
		comboBoxSURVEY1 = new JComboBox();
		comboBoxSURVEY2 = new JComboBox();
		INICIARDESCARGAALCREAR = new JCheckBox();
		ruta = new JTextField();
		label10 = new JLabel();
		formatoFichero = new JComboBox();
		label11 = new JLabel();
		label12 = new JLabel();
		formatoCoordenadas = new JComboBox();
		separator1 = new JSeparator();
		button1 = new JButton();
		separator2 = new JSeparator();
		cargarValoresPorDefecto = new JButton();
		separator3 = new JSeparator();
		guardarValoresPorDefecto = new JButton();
		separator4 = new JSeparator();
		visualizarAlTerminarDescarga = new JCheckBox();
		comboBoxValoresPorDefecto = new JComboBox();
		aliasNuevaConfig = new JTextField();
		label13 = new JLabel();
		label14 = new JLabel();
		crearDescarga = new JButton();

		//======== this ========
		setLayout(new GridLayoutManager(16, 5, new Insets(60, 60, 60, 60), 5, -1));

		//---- label1 ----
		label1.setText("ALIAS");
		add(label1, new GridConstraints(3, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ALIASINPUT, new GridConstraints(3, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label2 ----
		label2.setText("DESCRIPCION");
		add(label2, new GridConstraints(4, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(DESCRIPTIONINPUT, new GridConstraints(4, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ALTOINPUT, new GridConstraints(5, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label3 ----
		label3.setText("ALTO");
		add(label3, new GridConstraints(5, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ANCHOINPUT, new GridConstraints(5, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label4 ----
		label4.setText("SURVEY 1");
		add(label4, new GridConstraints(3, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label5 ----
		label5.setText("SURVEY 2");
		add(label5, new GridConstraints(4, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label6 ----
		label6.setText("ANCHO");
		add(label6, new GridConstraints(5, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(ARINPUT, new GridConstraints(6, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(DECINPUT, new GridConstraints(6, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label7 ----
		label7.setText("ASCENSION RECTA");
		add(label7, new GridConstraints(6, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label8 ----
		label8.setText("DECLINACION");
		add(label8, new GridConstraints(6, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label9 ----
		label9.setText("PANTALLA DE CREACI\u00d3N DE DESCARGAS");
		label9.setFont(label9.getFont().deriveFont(label9.getFont().getSize() + 10f));
		label9.setHorizontalAlignment(SwingConstants.CENTER);
		add(label9, new GridConstraints(1, 0, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(comboBoxSURVEY1, new GridConstraints(3, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(comboBoxSURVEY2, new GridConstraints(4, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- INICIARDESCARGAALCREAR ----
		INICIARDESCARGAALCREAR.setText("INICIAR DESCARGA UNA VEZ CREADA ");
		add(INICIARDESCARGAALCREAR, new GridConstraints(13, 0, 1, 3,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- ruta ----
		ruta.setEditable(false);
		ruta.setToolTipText("PULSE PARA SELECCIONAR UN DIRECTORIO.");
		ruta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rutaMouseClicked(e);
			}
		});
		add(ruta, new GridConstraints(7, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label10 ----
		label10.setText("RUTA");
		add(label10, new GridConstraints(7, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(formatoFichero, new GridConstraints(7, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label11 ----
		label11.setText("FORMATO FICHERO");
		add(label11, new GridConstraints(7, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label12 ----
		label12.setText("FORMATO COORDENADAS");
		add(label12, new GridConstraints(8, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- formatoCoordenadas ----
		formatoCoordenadas.setEnabled(false);
		add(formatoCoordenadas, new GridConstraints(8, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(separator1, new GridConstraints(2, 0, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- button1 ----
		button1.setToolTipText("Seleccione ruta");
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rutaMouseClicked(e);
			}
		});
		add(button1, new GridConstraints(7, 1, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_FIXED,
			GridConstraints.SIZEPOLICY_FIXED,
			new Dimension(25, 25), null, new Dimension(25, 25)));
		add(separator2, new GridConstraints(9, 0, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- cargarValoresPorDefecto ----
		cargarValoresPorDefecto.setText("CARGAR VALORES POR DEFECTO");
		cargarValoresPorDefecto.setToolTipText("PULSE AQUI PARA CARGAR LA CONFIGURACION POR DEFECTO SELECCIONADA");
		cargarValoresPorDefecto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarValoresDefectoEvent(e);
			}
		});
		add(cargarValoresPorDefecto, new GridConstraints(11, 3, 1, 2,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(separator3, new GridConstraints(12, 0, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- guardarValoresPorDefecto ----
		guardarValoresPorDefecto.setText("GUARDAR COMO VALORES POR DEFECTO");
		guardarValoresPorDefecto.setToolTipText("PULSE AQUI PARA GUARDAR SU CONFIGURACI\u00d3N CON EL ALIAS DESIGNADO");
		guardarValoresPorDefecto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guardarValoresPorDefectoEvent(e);
			}
		});
		add(guardarValoresPorDefecto, new GridConstraints(11, 0, 1, 3,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(separator4, new GridConstraints(14, 0, 1, 5,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- visualizarAlTerminarDescarga ----
		visualizarAlTerminarDescarga.setText("VISUALIZAR AL TERMINAR DESCARGA");
		add(visualizarAlTerminarDescarga, new GridConstraints(13, 3, 1, 2,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(comboBoxValoresPorDefecto, new GridConstraints(10, 4, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		add(aliasNuevaConfig, new GridConstraints(10, 2, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label13 ----
		label13.setText("ALIAS CONFIGURACION");
		add(label13, new GridConstraints(10, 0, 1, 2,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label14 ----
		label14.setText("VALORES POR DEFECTO");
		add(label14, new GridConstraints(10, 3, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- crearDescarga ----
		crearDescarga.setText("CREAR DESCARGA");
		crearDescarga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				crearDescargaEvent(e);
			}
		});
		add(crearDescarga, new GridConstraints(15, 2, 1, 2,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	public void setServicioGestionTareas(ServicioGestionTareas servicioGestionTareas) {
		this.servicioGestionTareas = servicioGestionTareas;
	}

	public ServicioGestionTareas getServicioGestionTareas() {
		return servicioGestionTareas;
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JTextField ALIASINPUT;
	private JLabel label2;
	private JTextField DESCRIPTIONINPUT;
	private JTextField ALTOINPUT;
	private JLabel label3;
	private JTextField ANCHOINPUT;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JTextField ARINPUT;
	private JTextField DECINPUT;
	private JLabel label7;
	private JLabel label8;
	private JLabel label9;
	private JComboBox comboBoxSURVEY1;
	private JComboBox comboBoxSURVEY2;
	private JCheckBox INICIARDESCARGAALCREAR;
	private JTextField ruta;
	private JLabel label10;
	private JComboBox formatoFichero;
	private JLabel label11;
	private JLabel label12;
	private JComboBox formatoCoordenadas;
	private JSeparator separator1;
	private JButton button1;
	private JSeparator separator2;
	private JButton cargarValoresPorDefecto;
	private JSeparator separator3;
	private JButton guardarValoresPorDefecto;
	private JSeparator separator4;
	private JCheckBox visualizarAlTerminarDescarga;
	private JComboBox comboBoxValoresPorDefecto;
	private JTextField aliasNuevaConfig;
	private JLabel label13;
	private JLabel label14;
	private JButton crearDescarga;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
