package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.*;

import es.ucm.si.dneb.service.image.app.ImageRegionApp;


public class VentanaPcpal extends JFrame{
	

	private static final long serialVersionUID = -1633763922217208939L;
	String survey1, survey2, ari, deci, arf, decf, eq, alto, ancho, solapamiento, ruta;
	
	//Control del número de pestañas de seguimiento abiertas
	private MonitorProcesamiento procesamientoDobles;
	private TaskPanel taskPanel;
	
	private int numTaskPanels=0;
	private int maxTaskPanels=1;
	
	private int numProces=0;
	private int maxProces=1;
	
	
	private JTabbedPane  pane= new JTabbedPane();
	
	public VentanaPcpal(){
		super("kk");
		
		this.getContentPane().setLayout(new BorderLayout());
	    initComponents();
	    
	    initIcons();
	    
	    this.getContentPane().add(pane);
			    
	    pane.removeAll();
        
	    pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        
	    pane.add("DNEB",new BackgroundPanel(pane));
	    
	    this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/staricon2.jpg"));
	    
	    this.initTabComponent(pane.getTabCount()-1);
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    setLocationRelativeTo(null);
	    
	    this.setExtendedState(Frame.MAXIMIZED_BOTH); 
	    
	    setVisible(true);

	}
	 public void initTabComponent(int i) {
	        pane.setTabComponentAt(i,
	                 new ButtonTabComponent(pane));
	}  
	
	private void initIcons(){
		
		ImageIcon taskIcon = new ImageIcon("images/TASKICON.JPG");
		this.menu5.setIcon(new ImageIcon("images/Utilitiesicon.png"));
		this.menu4.setIcon(new ImageIcon("images/import.png"));
		this.menu2.setIcon(new ImageIcon("images/download_icon3.jpg"));
		this.menu3.setIcon(new ImageIcon("images/Config-icon.png"));
		
		this.menu6.setIcon(new ImageIcon("images/export.png"));
		this.menu7.setIcon(new ImageIcon("images/help_icon.jpg"));
		this.menu1.setIcon(taskIcon);
		
		this.configBBDD.setIcon(new ImageIcon("images/database_icon.png"));
		this.configDownload.setIcon(new ImageIcon("images/downconfig (Custom).JPG"));
		this.crearDescarga.setIcon(new ImageIcon("images/download_icon4.png"));
		this.crearNuevaTarea.setIcon(new ImageIcon("images/addtask.png"));
		
		this.gestionarDescargasConfig.setIcon(new ImageIcon("images/downconfig (Custom).JPG"));
		this.gestorTareas.setIcon(new ImageIcon("images/vista-taskmanager-icon.png"));
		this.importBBDD.setIcon(new ImageIcon("images/Database Insert.jpg"));
		this.importXML.setIcon(new ImageIcon("images/xml_icon_gif.gif"));	
		
		this.buscar.setIcon(new ImageIcon("images/Process Icon.jpg"));	
		this.consultarCatalogo.setIcon(new ImageIcon("images/catalogIcon.jpg"));
		this.visualizador.setIcon(new ImageIcon("images/icon_viewer.png"));
		this.visualizadorDebug.setIcon(new ImageIcon("images/icon_viewer.png"));
		this.nueProcEstDob.setIcon(new ImageIcon("images/new Process Icon.JPG"));
		
		this.importInfo.setIcon(new ImageIcon("images/help_icon.jpg"));
		this.dnebInfo.setIcon(new ImageIcon("images/help_icon.jpg"));
		this.formaCoord.setIcon(new ImageIcon("images/help_icon.jpg"));
		
		this.distancias.setIcon(new ImageIcon("images/distance.jpg"));
		this.conversor.setIcon(new ImageIcon("images/Converter_icon.jpg"));
		
		this.menuCatalogoED.setIcon(new ImageIcon("images/catalogIcon.jpg"));
		this.exportRelevantXML.setIcon(new ImageIcon("images/xml_icon_gif.gif"));	
	}
	

	private void crearNuevaTareaActionPerformed(ActionEvent e) {		
		pane.add("Nueva Tarea",new SurveyPanel(this,pane.getTabCount()) );
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	private void gestorTareasActionPerformed(ActionEvent e) {
		
		
			JPanel vent = new TaskPanel(this);
			pane.addTab("Gestor de tareas", vent);
			this.initTabComponent(pane.getTabCount()-1);
			pane.setSelectedIndex(pane.getTabCount()-1);
			
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			setVisible(true);
			
		
	}
	
	private void crearProcesamientoActionPerformed(ActionEvent e) {
		JPanel panel = new CrearProcesamiento(this);
		pane.addTab("Crear procesamientos", panel);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	private void crearDescargaActionPerformed(ActionEvent e) {
		JPanel vent = new CreateNewDownload(this,pane.getTabCount());
		pane.addTab("Nueva Imagen", vent);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	private void gestionarDescargasConfigActionPerformed(ActionEvent e) {
		DefaultDownloadSettingsConfig config = new DefaultDownloadSettingsConfig(this);
		pane.addTab("Configurar Descargas", config);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	private void configBBDDActionPerformed(ActionEvent e) {
		DataBaseConfig dataBaseConfig = new DataBaseConfig(this);
		pane.addTab("Configurar Base de Datos", dataBaseConfig);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	private void configDownloadActionPerformed(ActionEvent e) {
		DefaultDownloadSettingsConfig config = new DefaultDownloadSettingsConfig(this);
		pane.addTab("Configurar Descargas", config);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	private void importBBDDActionPerformed(ActionEvent e) {

		ImportarDesdeBBDD imptBBDD = new ImportarDesdeBBDD(this,pane.getTabCount());
		pane.addTab("Configurar Descargas", imptBBDD);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
		
	}

	private void importXMLActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	public JTabbedPane getPane() {
		return pane;
	}
	public void setPane(JTabbedPane pane) {
		this.pane = pane;
	}

	private void visualizadorActionPerformed(ActionEvent e) {
		
		ImageRegionApp config = new ImageRegionApp(this);
		pane.addTab("Configurar Descargas", config);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
		
		
	}

	private void buscarActionPerformed(ActionEvent e) {
		MonitorProcesamiento prDobles= new MonitorProcesamiento(this);
		pane.addTab("Monitor de Procesamientos", prDobles);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	private void menuCatalogoEDActionPerformed(ActionEvent e) {
		
		ImportarCatalogo importarCatalogo = new ImportarCatalogo();
		
		pane.addTab("Importar catálogo", importarCatalogo);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	private void exportRelevantXMLActionPerformed(ActionEvent e) {
		
		ExportarXMl expXml = new ExportarXMl();
		
		pane.addTab("Exportar XML", expXml);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
		
		
	}

	private void consultarCatalogoActionPerformed(ActionEvent e) {

		ConsultarCatalogo consultarCatalogo = new ConsultarCatalogo();
		
		pane.addTab("Consultar catálogo", consultarCatalogo);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	private void importInfoActionPerformed(ActionEvent e) {
		
		WdsHelp wdsHelp = new WdsHelp();
		
		pane.addTab("WDSC", wdsHelp);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	private void dnebInfoActionPerformed(ActionEvent e) {

		pane.add("DNEB",new BackgroundPanel(pane));
	    this.initTabComponent(pane.getTabCount()-1);
	    pane.setSelectedIndex(pane.getTabCount()-1);
	}

	private void nueProcEstDobActionPerformed(ActionEvent e) {
		pane.add("CREAR PROCESAMIENTO",new CrearProcesamiento(this));
	    this.initTabComponent(pane.getTabCount()-1);
	    pane.setSelectedIndex(pane.getTabCount()-1);
	}

	private void formaCoordActionPerformed(ActionEvent e) {
		pane.add("INFORMACIÓN COORDENADAS",new CoordinatesFormat());
	    this.initTabComponent(pane.getTabCount()-1);
	    pane.setSelectedIndex(pane.getTabCount()-1);
	}

	private void conversorActionPerformed(ActionEvent e) {
		pane.add("CONVERSOR COORDENADAS",new CoordinateConverter());
	    this.initTabComponent(pane.getTabCount()-1);
	    pane.setSelectedIndex(pane.getTabCount()-1);
	}

	private void distanciasActionPerformed(ActionEvent e) {
		pane.add("CALCULADORA DISTANCIAS",new CalcularDistanciaForm());
	    this.initTabComponent(pane.getTabCount()-1);
	    pane.setSelectedIndex(pane.getTabCount()-1);
	}

	private void visualizadorDebugActionPerformed(ActionEvent e) {
		
		 		
		FitsCoordinateViewerPanel visu =new FitsCoordinateViewerPanel();
		
		pane.add("VISUALIZADOR DEBUG",visu);
	    this.initTabComponent(pane.getTabCount()-1);
	    pane.setSelectedIndex(pane.getTabCount()-1);
	}

	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		crearNuevaTarea = new JMenuItem();
		gestorTareas = new JMenuItem();
		menu2 = new JMenu();
		crearDescarga = new JMenuItem();
		gestionarDescargasConfig = new JMenuItem();
		menu3 = new JMenu();
		configBBDD = new JMenuItem();
		configDownload = new JMenuItem();
		menu6 = new JMenu();
		exportRelevantXML = new JMenuItem();
		menu4 = new JMenu();
		importBBDD = new JMenuItem();
		importXML = new JMenuItem();
		menuCatalogoED = new JMenuItem();
		menu5 = new JMenu();
		visualizador = new JMenuItem();
		visualizadorDebug = new JMenuItem();
		nueProcEstDob = new JMenuItem();
		buscar = new JMenuItem();
		consultarCatalogo = new JMenuItem();
		conversor = new JMenuItem();
		distancias = new JMenuItem();
		menu7 = new JMenu();
		importInfo = new JMenuItem();
		formaCoord = new JMenuItem();
		dnebInfo = new JMenuItem();

		//======== this ========
		setIconImage(null);
		setTitle("DNEB (DETECCI\u00d3N DE NUEVAS ESTRELLAS BINARIAS)");
		Container contentPane = getContentPane();
		

		//======== menuBar1 ========
		{

			//======== menu1 ========
			{
				menu1.setIcon(null);
				menu1.setText("TAREAS");

				//---- crearNuevaTarea ----
				crearNuevaTarea.setText("CREAR UNA NUEVA TAREA");
				crearNuevaTarea.setIcon(null);
				crearNuevaTarea.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						crearNuevaTareaActionPerformed(e);
					}
				});
				menu1.add(crearNuevaTarea);

				//---- gestorTareas ----
				gestorTareas.setText("GESTOR DE TAREAS");
				gestorTareas.setIcon(null);
				gestorTareas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						gestorTareasActionPerformed(e);
					}
				});
				menu1.add(gestorTareas);
			}
			menuBar1.add(menu1);

			//======== menu2 ========
			{
				menu2.setText("DESCARGAS");
				menu2.setIcon(null);

				//---- crearDescarga ----
				crearDescarga.setText("CREAR UNA DESCARGA");
				crearDescarga.setIcon(null);
				crearDescarga.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						crearDescargaActionPerformed(e);
					}
				});
				menu2.add(crearDescarga);

				//---- gestionarDescargasConfig ----
				gestionarDescargasConfig.setText("CREAR CONFIGURARION DE DESCARGA");
				gestionarDescargasConfig.setIcon(null);
				gestionarDescargasConfig.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						gestionarDescargasConfigActionPerformed(e);
					}
				});
				menu2.add(gestionarDescargasConfig);
			}
			menuBar1.add(menu2);

			//======== menu3 ========
			{
				menu3.setIcon(null);
				menu3.setText("CONFIGURACION");

				//---- configBBDD ----
				configBBDD.setText("CONFIGURAR CONEXI\u00d3N DE BASE DE DATOS");
				configBBDD.setIcon(null);
				configBBDD.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						configBBDDActionPerformed(e);
					}
				});
				menu3.add(configBBDD);

				//---- configDownload ----
				configDownload.setText("CREAR CONFIGURACION DE DESCARGA");
				configDownload.setIcon(null);
				configDownload.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						configDownloadActionPerformed(e);
					}
				});
				menu3.add(configDownload);
			}
			menuBar1.add(menu3);

			//======== menu6 ========
			{
				menu6.setText("EXPORTAR DATOS");

				//---- exportRelevantXML ----
				exportRelevantXML.setText("Exportar datos relevantes(XML)");
				exportRelevantXML.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						exportRelevantXMLActionPerformed(e);
					}
				});
				menu6.add(exportRelevantXML);
			}
			menuBar1.add(menu6);

			//======== menu4 ========
			{
				menu4.setText("IMPORTAR DATOS");
				menu4.setIcon(null);

				//---- importBBDD ----
				importBBDD.setText("DESDE BBDD");
				importBBDD.setIcon(null);
				importBBDD.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						importBBDDActionPerformed(e);
					}
				});
				menu4.add(importBBDD);

				//---- importXML ----
				importXML.setText("DESDE XML");
				importXML.setIcon(null);
				importXML.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						importXMLActionPerformed(e);
					}
				});
				menu4.add(importXML);

				//---- menuCatalogoED ----
				menuCatalogoED.setText("IMPORTAR CAT\u00c1LOGO ESTRELLAS DOBLES");
				menuCatalogoED.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						menuCatalogoEDActionPerformed(e);
					}
				});
				menu4.add(menuCatalogoED);
			}
			menuBar1.add(menu4);

			//======== menu5 ========
			{
				menu5.setText("UTILIDADES");

				//---- visualizador ----
				visualizador.setText("VISUALIZADOR DOBLES");
				visualizador.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						visualizadorActionPerformed(e);
					}
				});
				menu5.add(visualizador);

				//---- visualizadorDebug ----
				visualizadorDebug.setText("VISUALIZADOR DEBUG");
				visualizadorDebug.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						visualizadorDebugActionPerformed(e);
					}
				});
				menu5.add(visualizadorDebug);

				//---- nueProcEstDob ----
				nueProcEstDob.setText("CREAR PROCESAMIENTO");
				nueProcEstDob.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						nueProcEstDobActionPerformed(e);
					}
				});
				menu5.add(nueProcEstDob);

				//---- buscar ----
				buscar.setText("MONITOR DE PROCESAMIENTOS");
				buscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buscarActionPerformed(e);
					}
				});
				menu5.add(buscar);

				//---- consultarCatalogo ----
				consultarCatalogo.setText("CONSULTAR CAT\u00c1LOGO");
				consultarCatalogo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						consultarCatalogoActionPerformed(e);
					}
				});
				menu5.add(consultarCatalogo);

				//---- conversor ----
				conversor.setText("CONVERSOR COORDENADAS");
				conversor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						conversorActionPerformed(e);
					}
				});
				menu5.add(conversor);

				//---- distancias ----
				distancias.setText("CALCULO DISTANCIAS");
				distancias.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						distanciasActionPerformed(e);
					}
				});
				menu5.add(distancias);
			}
			menuBar1.add(menu5);

			//======== menu7 ========
			{
				menu7.setText("AYUDA");

				//---- importInfo ----
				importInfo.setText("IMPORTAR CAT\u00c1LOGO");
				importInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						importInfoActionPerformed(e);
					}
				});
				menu7.add(importInfo);

				//---- formaCoord ----
				formaCoord.setText("FORMATO COORDENADAS");
				formaCoord.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						formaCoordActionPerformed(e);
					}
				});
				menu7.add(formaCoord);

				//---- dnebInfo ----
				dnebInfo.setText("DNEB");
				dnebInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dnebInfoActionPerformed(e);
					}
				});
				menu7.add(dnebInfo);
			}
			menuBar1.add(menu7);
		}
		setJMenuBar(menuBar1);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		setSize(400, 300);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	public void setProcesamientoDobles(MonitorProcesamiento procesamientoDobles) {
		this.procesamientoDobles = procesamientoDobles;
	}
	public MonitorProcesamiento getProcesamientoDobles() {
		return procesamientoDobles;
	}

	public void setTaskPanel(TaskPanel taskPanel) {
		this.taskPanel = taskPanel;
	}
	public TaskPanel getTaskPanel() {
		return taskPanel;
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JMenuItem crearNuevaTarea;
	private JMenuItem gestorTareas;
	private JMenu menu2;
	private JMenuItem crearDescarga;
	private JMenuItem gestionarDescargasConfig;
	private JMenu menu3;
	private JMenuItem configBBDD;
	private JMenuItem configDownload;
	private JMenu menu6;
	private JMenuItem exportRelevantXML;
	private JMenu menu4;
	private JMenuItem importBBDD;
	private JMenuItem importXML;
	private JMenuItem menuCatalogoED;
	private JMenu menu5;
	private JMenuItem visualizador;
	private JMenuItem visualizadorDebug;
	private JMenuItem nueProcEstDob;
	private JMenuItem buscar;
	private JMenuItem consultarCatalogo;
	private JMenuItem conversor;
	private JMenuItem distancias;
	private JMenu menu7;
	private JMenuItem importInfo;
	private JMenuItem formaCoord;
	private JMenuItem dnebInfo;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
