package es.ucm.si.dneb.gui;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import org.springframework.context.ApplicationContext;

import es.ucm.si.dneb.domain.DownloadDefaultConfiguration;
import es.ucm.si.dneb.domain.FormatoFichero;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.downloadDefaultConfig.ServiceDownloadDefaultConfig;
import es.ucm.si.dneb.service.downloadDefaultConfig.ServiceDownloadDefaultConfigException;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareasException;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareasImpl;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;
/*
 * Created by JFormDesigner on Wed Nov 25 23:44:44 CET 2009
 */
/**
 * @author Brainrain
 */
public class CreateNewDownload extends JPanel {
	
	private VentanaPcpal principal;
	
	private static final long serialVersionUID = 1459331863481818480L;
	
	private ServiceDownloadDefaultConfig serviceDownloadDefaultConfig;
	private ServicioGestionTareas servicioGestionTareas;
	
	private ArrayList<Survey> surveys;
	private ArrayList<FormatoFichero> formatosFichero; 
	private ArrayList<DownloadDefaultConfiguration> configsList;
	
	public CreateNewDownload(VentanaPcpal principal) {
		initComponents();
		this.principal=principal;
		this.rellenarModel();
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
		
		
		
		boolean iniciarDescarga= this.INICIARDESCARGAALCREAR.isSelected();
		
		servicioGestionTareas.createSingleDownloadTask(this.ALIASINPUT.getText(),this.DESCRIPTIONINPUT.getText() ,Double.valueOf(this.ALTOINPUT.getText()),Double.valueOf(this.ANCHOINPUT.getText()), formatosFichero.get(formatoSeleccionado),this.ruta.getText(), selectedSurveys, Double.valueOf(this.ARINPUT.getText()), Double.valueOf(this.DECINPUT.getText()),iniciarDescarga);
		
		
		/**TODO FALTA OPCIONES DE INICIAR DESCARGA ETC**/
		
		boolean mostrar = this.visualizarAlTerminarDescarga.isSelected();
		
		/****/
		JPanel vent = new MenuPanel(principal);
		principal.getContentPane().remove(0);
		principal.getContentPane().add(vent);
		principal.pack();
		vent.setVisible(true);
	}

	private void cargarValoresDefectoEvent(MouseEvent e) {
		
		int configDefault = this.comboBoxValoresPorDefecto.getSelectedIndex();
		 
		DownloadDefaultConfiguration selectedDownloadDefaultConfiguration = configsList.get(configDefault);
		
		
		this.ALTOINPUT.setText(selectedDownloadDefaultConfiguration.getAlto().toString());
		
		this.ANCHOINPUT.setText(selectedDownloadDefaultConfiguration.getAncho().toString());
		
		this.ruta.setText(selectedDownloadDefaultConfiguration.getPath());
		
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
		
		DownloadDefaultConfiguration downloadDefaultConfiguration= new DownloadDefaultConfiguration();
		
		if(this.aliasNuevaConfig.getText()!=null){
			downloadDefaultConfiguration.setAlias(this.aliasNuevaConfig.getText());
		}
		
		if(this.ALTOINPUT.getText()!=null){
			downloadDefaultConfiguration.setAlto(Double.valueOf(this.ALTOINPUT.getText()));	
		}
		
		if(this.ANCHOINPUT.getText()!=null){
			downloadDefaultConfiguration.setAncho(Double.valueOf(this.ANCHOINPUT.getText()));
		}
		
		/**TODO**/
		
		int formatoSeleccionado = this.formatoFichero.getSelectedIndex();
		
		downloadDefaultConfiguration.setFormatoFichero(formatosFichero.get(formatoSeleccionado));
		
		/****/
		
		if(this.ruta.getText()!=null){
			downloadDefaultConfiguration.setPath(this.ruta.getText());
		}
		/**TODO**/
		
		int survey1 = this.comboBoxSURVEY1.getSelectedIndex();
		int survey2 = this.comboBoxSURVEY2.getSelectedIndex();
		
		ArrayList<Survey> selectedSurveys = new ArrayList<Survey>();
		
		selectedSurveys.add(this.surveys.get(survey1));
		selectedSurveys.add(this.surveys.get(survey2));
		
		/*****/
		
		
		downloadDefaultConfiguration.setSurveys(selectedSurveys);
		
		
		serviceDownloadDefaultConfig.createNewDownloadDefaultConfig(downloadDefaultConfiguration);
		
		updateConfigList();
		
	}


	private void button5MouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void button6MouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void volverAlMenuEvent(MouseEvent e) {
		JPanel vent = new MenuPanel(principal);
		principal.getContentPane().remove(0);
		principal.getContentPane().add(vent);
		principal.pack();
		vent.setVisible(true);
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
		crearDescarga = new JButton();
		formatoFichero = new JComboBox();
		label11 = new JLabel();
		label12 = new JLabel();
		formatoCoordenadas = new JComboBox();
		separator1 = new JSeparator();
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
		volverAlMenubutton = new JButton();
		dialog1 = new JDialog();
		label15 = new JLabel();
		button5 = new JButton();
		dialog2 = new JDialog();
		label16 = new JLabel();
		button6 = new JButton();

		//======== this ========

		//---- label1 ----
		label1.setText("ALIAS");

		//---- label2 ----
		label2.setText("DESCRIPCION");

		//---- label3 ----
		label3.setText("ALTO");

		//---- label4 ----
		label4.setText("SURVEY 1");

		//---- label5 ----
		label5.setText("SURVEY 2");

		//---- label6 ----
		label6.setText("ANCHO");

		//---- label7 ----
		label7.setText("ASCENSION RECTA");

		//---- label8 ----
		label8.setText("DECLINACION");

		//---- label9 ----
		label9.setText("PANTALLA DE CREACI\u00d3N DE DESCARGAS");
		label9.setFont(label9.getFont().deriveFont(label9.getFont().getSize() + 10f));

		//---- INICIARDESCARGAALCREAR ----
		INICIARDESCARGAALCREAR.setText("INICIAR DESCARGA UNA VEZ CREADA ");

		//---- ruta ----
		ruta.setEditable(false);
		ruta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rutaMouseClicked(e);
			}
		});

		//---- label10 ----
		label10.setText("RUTA");

		//---- crearDescarga ----
		crearDescarga.setText("CREAR DESCARGA");
		crearDescarga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				crearDescargaEvent(e);
			}
		});

		//---- label11 ----
		label11.setText("FORMATO FICHERO");

		//---- label12 ----
		label12.setText("FORMATO COORDENADAS");

		//---- cargarValoresPorDefecto ----
		cargarValoresPorDefecto.setText("CARGAR VALORES POR DEFECTO");
		cargarValoresPorDefecto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarValoresDefectoEvent(e);
			}
		});

		//---- guardarValoresPorDefecto ----
		guardarValoresPorDefecto.setText("GUARDAR COMO VALORES POR DEFECTO");
		guardarValoresPorDefecto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guardarValoresPorDefectoEvent(e);
			}
		});

		//---- visualizarAlTerminarDescarga ----
		visualizarAlTerminarDescarga.setText("VISUALIZAR AL TERMINAR DESCARGA");

		//---- label13 ----
		label13.setText("ALIAS CONFIGURACION");

		//---- label14 ----
		label14.setText("VALORES POR DEFECTO");

		//---- volverAlMenubutton ----
		volverAlMenubutton.setText("VOLVER AL MENU");
		volverAlMenubutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				volverAlMenuEvent(e);
			}
		});

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(106, 106, 106)
					.addGroup(layout.createParallelGroup()
						.addComponent(label1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addComponent(label2)
						.addComponent(label3)
						.addComponent(label7)
						.addComponent(label10, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
						.addComponent(ARINPUT, GroupLayout.Alignment.LEADING)
						.addComponent(DESCRIPTIONINPUT, GroupLayout.Alignment.LEADING)
						.addComponent(ALIASINPUT, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
						.addComponent(ALTOINPUT, GroupLayout.Alignment.LEADING)
						.addComponent(ruta, GroupLayout.Alignment.LEADING))
					.addGap(136, 136, 136)
					.addGroup(layout.createParallelGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(label4)
								.addComponent(label5))
							.addComponent(label8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(label6)
							.addComponent(label11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(label12))
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup()
							.addGap(19, 19, 19)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(formatoFichero, 0, 101, Short.MAX_VALUE)
								.addComponent(DECINPUT)
								.addComponent(ANCHOINPUT)
								.addComponent(comboBoxSURVEY1, 0, 101, Short.MAX_VALUE)
								.addComponent(comboBoxSURVEY2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(layout.createSequentialGroup()
							.addGap(18, 18, 18)
							.addComponent(formatoCoordenadas, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(35, Short.MAX_VALUE))
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator1, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 740, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator4, GroupLayout.PREFERRED_SIZE, 740, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
					.addGap(90, 90, 90)
					.addComponent(INICIARDESCARGAALCREAR, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
					.addComponent(visualizarAlTerminarDescarga, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
					.addGap(63, 63, 63))
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator3, GroupLayout.PREFERRED_SIZE, 740, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addGap(70, 70, 70)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(label13, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18, 18, 18)
							.addComponent(aliasNuevaConfig, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addComponent(guardarValoresPorDefecto, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
					.addGap(79, 79, 79)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
						.addGroup(layout.createSequentialGroup()
							.addComponent(label14, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(comboBoxValoresPorDefecto, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addComponent(cargarValoresPorDefecto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(21, 21, 21))
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap(181, Short.MAX_VALUE)
					.addComponent(label9, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)
					.addGap(130, 130, 130))
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap(110, Short.MAX_VALUE)
					.addComponent(volverAlMenubutton, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
					.addGap(171, 171, 171)
					.addComponent(crearDescarga, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
					.addGap(83, 83, 83))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(21, 21, 21)
					.addComponent(label9, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(separator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(ALIASINPUT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label4)
						.addComponent(comboBoxSURVEY1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30, 30, 30)
					.addGroup(layout.createParallelGroup()
						.addComponent(label2)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(DESCRIPTIONINPUT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label5)
							.addComponent(comboBoxSURVEY2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(24, 24, 24)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(label3)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(ALTOINPUT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(ANCHOINPUT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label6)))
					.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
							.addGap(37, 37, 37)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(ARINPUT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label7)))
						.addGroup(layout.createSequentialGroup()
							.addGap(45, 45, 45)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(DECINPUT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label8))))
					.addGap(44, 44, 44)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(ruta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label10)
						.addComponent(formatoFichero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label11))
					.addGap(35, 35, 35)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label12)
						.addComponent(formatoCoordenadas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addComponent(separator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(comboBoxValoresPorDefecto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(aliasNuevaConfig, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label13)
						.addComponent(label14))
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(guardarValoresPorDefecto)
						.addComponent(cargarValoresPorDefecto))
					.addGap(18, 18, 18)
					.addComponent(separator3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(INICIARDESCARGAALCREAR)
						.addComponent(visualizarAlTerminarDescarga))
					.addGap(18, 18, 18)
					.addComponent(separator4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(volverAlMenubutton)
						.addComponent(crearDescarga))
					.addGap(46, 46, 46))
		);

		//======== dialog1 ========
		{
			Container dialog1ContentPane = dialog1.getContentPane();

			//---- label15 ----
			label15.setText("LOS DATOS DE ASCENSI\u00d3N RECTA Y DECLINACI\u00d3N NO SON VALIDOS");

			//---- button5 ----
			button5.setText("OK");
			button5.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					button5MouseClicked(e);
				}
			});

			GroupLayout dialog1ContentPaneLayout = new GroupLayout(dialog1ContentPane);
			dialog1ContentPane.setLayout(dialog1ContentPaneLayout);
			dialog1ContentPaneLayout.setHorizontalGroup(
				dialog1ContentPaneLayout.createParallelGroup()
					.addGroup(dialog1ContentPaneLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(dialog1ContentPaneLayout.createParallelGroup()
							.addGroup(dialog1ContentPaneLayout.createSequentialGroup()
								.addComponent(label15, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(GroupLayout.Alignment.TRAILING, dialog1ContentPaneLayout.createSequentialGroup()
								.addComponent(button5)
								.addGap(176, 176, 176))))
			);
			dialog1ContentPaneLayout.setVerticalGroup(
				dialog1ContentPaneLayout.createParallelGroup()
					.addGroup(dialog1ContentPaneLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(label15)
						.addGap(18, 18, 18)
						.addComponent(button5)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			dialog1.pack();
			dialog1.setLocationRelativeTo(dialog1.getOwner());
		}

		//======== dialog2 ========
		{
			Container dialog2ContentPane = dialog2.getContentPane();

			//---- label16 ----
			label16.setText("EL ALTO Y EL ANCHO HAN DE ESTAR COMPRENDIDOS ENTRE 1 Y 60");

			//---- button6 ----
			button6.setText("OK");
			button6.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					button6MouseClicked(e);
				}
			});

			GroupLayout dialog2ContentPaneLayout = new GroupLayout(dialog2ContentPane);
			dialog2ContentPane.setLayout(dialog2ContentPaneLayout);
			dialog2ContentPaneLayout.setHorizontalGroup(
				dialog2ContentPaneLayout.createParallelGroup()
					.addGroup(dialog2ContentPaneLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(dialog2ContentPaneLayout.createParallelGroup()
							.addGroup(dialog2ContentPaneLayout.createSequentialGroup()
								.addComponent(label16, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(GroupLayout.Alignment.TRAILING, dialog2ContentPaneLayout.createSequentialGroup()
								.addComponent(button6)
								.addGap(176, 176, 176))))
			);
			dialog2ContentPaneLayout.setVerticalGroup(
				dialog2ContentPaneLayout.createParallelGroup()
					.addGroup(dialog2ContentPaneLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(label16)
						.addGap(18, 18, 18)
						.addComponent(button6)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			dialog2.pack();
			dialog2.setLocationRelativeTo(dialog2.getOwner());
		}
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
	private JButton crearDescarga;
	private JComboBox formatoFichero;
	private JLabel label11;
	private JLabel label12;
	private JComboBox formatoCoordenadas;
	private JSeparator separator1;
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
	private JButton volverAlMenubutton;
	private JDialog dialog1;
	private JLabel label15;
	private JButton button5;
	private JDialog dialog2;
	private JLabel label16;
	private JButton button6;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
