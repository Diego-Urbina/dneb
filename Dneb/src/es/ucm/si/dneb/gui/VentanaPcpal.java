package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import es.ucm.si.dneb.service.image.histograma.DisplayHistogramApp;


public class VentanaPcpal extends JFrame{
	

	private static final long serialVersionUID = -1633763922217208939L;
	
	private MonitorProcesamiento procesamientoDobles;
	private TaskPanel taskPanel;	
	private JTabbedPane pane = new JTabbedPane();
	
	
	public VentanaPcpal(){
		
		this.getContentPane().setLayout(new BorderLayout());
	    initComponents();
	    
	    initIcons();
	    
	    this.getContentPane().add(pane);
			    
	    pane.removeAll();
        
	    pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        
	    pane.addTab("DNEB",new ImageIcon("images/help.png"),new BackgroundPanel(pane));
	    
	    this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/star.png"));
	    
	    this.initTabComponent(pane.getTabCount()-1);
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    setLocationRelativeTo(null);
	    
	    this.setExtendedState(Frame.MAXIMIZED_BOTH); 
	    this.setSize(1024, 768);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize = getSize();
		int windowX = Math.max(0, (screenSize.width  - windowSize.width ) / 2);
		int windowY = Math.max(0, (screenSize.height - windowSize.height) / 2);
		setLocation(windowX, windowY);
	}
	
	 public void initTabComponent(int i) {
	        pane.setTabComponentAt(i, new ButtonTabComponent(pane));
	}  
	 
	 public void initTabIcon(int i,ImageIcon icon){
		
		 	pane.setIconAt(i, icon);
	 }
	 
	
	private void initIcons(){
		
		this.menu1.setIcon(new ImageIcon("images/task.png"));
		this.menu2.setIcon(new ImageIcon("images/download.png"));
		this.menu3.setIcon(new ImageIcon("images/proc.png"));
		this.menu4.setIcon(new ImageIcon("images/preferences.png"));
		this.menu5.setIcon(new ImageIcon("images/export.png"));
		this.menu6.setIcon(new ImageIcon("images/import.png"));
		this.menu7.setIcon(new ImageIcon("images/configure.png"));
		this.menu8.setIcon(new ImageIcon("images/help.png"));
		
		this.crearNuevaTarea.setIcon(new ImageIcon("images/add.png"));
		this.gestorTareas.setIcon(new ImageIcon("images/task-monitor.png"));
		
		this.crearDescarga.setIcon(new ImageIcon("images/add2.png"));
		
		this.configDownload.setIcon(new ImageIcon("images/downloadconfig.png"));			
		
		this.exportRelevantXML.setIcon(new ImageIcon("images/xml.png"));
		
		this.importBBDD.setIcon(new ImageIcon("images/database-add.png"));	
		this.menuCatalogoED.setIcon(new ImageIcon("images/catalogo.png"));
		
		this.buscar.setIcon(new ImageIcon("images/monitor-proc.png"));	
		this.consultarCatalogo.setIcon(new ImageIcon("images/catalogo.png"));
		this.visualizador.setIcon(new ImageIcon("images/visor.png"));
		this.visualizadorDebug.setIcon(new ImageIcon("images/visor.png"));
		this.visorCentroides.setIcon(new ImageIcon("images/visor.png"));
		this.nueProcEstDob.setIcon(new ImageIcon("images/crear-proc.png"));		
		this.distancias.setIcon(new ImageIcon("images/distancia.png"));
		this.conversor.setIcon(new ImageIcon("images/coordenadas.png"));		
		this.histograma.setIcon(new ImageIcon("images/histograma.png"));			
		
		this.importInfo.setIcon(new ImageIcon("images/help.png"));
		this.dnebInfo.setIcon(new ImageIcon("images/help.png"));
		this.formaCoord.setIcon(new ImageIcon("images/help.png"));
	}
	

	// ------------- CREACION PESTAÑAS -------------
	
	// --------- Nueva tarea ---------
	private void crearNuevaTareaActionPerformed(ActionEvent e) {		
		//pane.add("Nueva Task",new SurveyPanel(this,pane.getTabCount()) );
		pane.addTab("Crear tarea",new ImageIcon("images/add.png"),new CreateTask() );
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		
		setVisible(true);
	}

	// --------- Gestor tareas ---------
	private void gestorTareasActionPerformed(ActionEvent e) {		
		JPanel vent = new TaskPanel();
		pane.addTab("Gestor de tareas",new ImageIcon("images/task-monitor.png"), vent);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		
		setVisible(true);		
	}
	
	
	
	
	
	// --------- Crear Descarga ---------
	private void crearDescargaActionPerformed(ActionEvent e) {
		JPanel vent = new CreateNewDownload();
		pane.addTab("Crear descarga",new ImageIcon("images/add2.png"), vent);
		this.initTabComponent(pane.getTabCount()-1);
		
		pane.setSelectedIndex(pane.getTabCount()-1);
		
		setVisible(true);
	}
	
	
	
	
	
	
	// --------- Configurar descargas ---------
	private void configDownloadActionPerformed(ActionEvent e) {
		DefaultDownloadSettingsConfig config = new DefaultDownloadSettingsConfig();
		pane.addTab("Configurar descargas",new ImageIcon("images/downloadconfig.png"), config);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		
		setVisible(true);
	}
	
	
	
	
	
	// --------- Exportar XML ---------
	private void exportRelevantXMLActionPerformed(ActionEvent e) {
		pane.addTab("Exportar XML",new ImageIcon("images/xml.png"), new ExportarXMl());
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		
		setVisible(true);		
	}
	
	
	
	
	
	// --------- Importar desde BBDD ---------
	private void importBBDDActionPerformed(ActionEvent e) {
		ImportarDesdeBBDD imptBBDD = new ImportarDesdeBBDD();
		pane.addTab("Importar desde BBDD",new ImageIcon("images/database-add.png"), imptBBDD);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		
		setVisible(true);
	}
	
	// --------- Importar catálogo ---------
	private void menuCatalogoEDActionPerformed(ActionEvent e) {
		pane.addTab("Importar catálogo",new ImageIcon("images/catalogo.png"), new ImportarCatalogo());
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		
		setVisible(true);
	}
	
	
	
	

	// --------- Visor estrellas ---------
	private void visualizadorActionPerformed(ActionEvent e) {
		pane.addTab("Visor estrellas",new ImageIcon("images/visor.png"), new StarsViewerPanel());
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		
		setVisible(true);
	}
	
	// --------- Visor dobles ---------
	private void visorCentroidesActionPerformed(ActionEvent e) {
		DoubleStarsViewerPanel  visu =new DoubleStarsViewerPanel();
		
		pane.addTab("Visor dobles",new ImageIcon("images/visor.png"),visu);
	    this.initTabComponent(pane.getTabCount()-1);
	    pane.setSelectedIndex(pane.getTabCount()-1);
	}
	
	// --------- Visor debug ---------
	private void visualizadorDebugActionPerformed(ActionEvent e) {
		FitsCoordinateViewerPanel visu =new FitsCoordinateViewerPanel();
		
		pane.addTab("Visor debug",new ImageIcon("images/visor.png"),visu);
	    this.initTabComponent(pane.getTabCount()-1);
	    pane.setSelectedIndex(pane.getTabCount()-1);
	}
	
	// --------- Crear procesamiento ---------
	private void nueProcEstDobActionPerformed(ActionEvent e) {
		pane.addTab("Crear procesamiento",new ImageIcon("images/crear-proc.png"),new CrearProcesamiento());
	    this.initTabComponent(pane.getTabCount()-1);
	    pane.setSelectedIndex(pane.getTabCount()-1);
	}

	// --------- Monitor de procesamientos ---------
	private void buscarActionPerformed(ActionEvent e) {
		MonitorProcesamiento prDobles= new MonitorProcesamiento();
		pane.addTab("Gestor de procesamientos",new ImageIcon("images/monitor-proc.png"), prDobles);
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		
		setVisible(true);
	}

	// --------- Consultar catálogo ---------
	private void consultarCatalogoActionPerformed(ActionEvent e) {
		pane.addTab("Consultar catálogo",new ImageIcon("images/catalogo.png"), new JScrollPane(new ConsultarCatalogo()));
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		
		setVisible(true);
	}
	
	// --------- Conversor de coordenadas ---------
	private void conversorActionPerformed(ActionEvent e) {
		pane.addTab("Conversor de coordenadas",new ImageIcon("images/coordenadas.png"),new CoordinateConverter());
	    this.initTabComponent(pane.getTabCount()-1);
	    pane.setSelectedIndex(pane.getTabCount()-1);
	}
	
	// --------- Calculadora distancias ---------
	private void distanciasActionPerformed(ActionEvent e) {
		pane.addTab("Calculadora distancias",new ImageIcon("images/distancia.png"),new CalcularDistanciaForm());
	    this.initTabComponent(pane.getTabCount()-1);
	    pane.setSelectedIndex(pane.getTabCount()-1);
	}
	
	// --------- Histograma ---------
	private void histogramaActionPerformed(ActionEvent e) {
		JScrollPane sp= new JScrollPane(new DisplayHistogramApp());
		    
	    pane.addTab("Histograma",new ImageIcon("images/histograma.png"),sp);
	    this.initTabComponent(pane.getTabCount()-1);
	    pane.setSelectedIndex(pane.getTabCount()-1);	
	}
	
	
	
	
	
	// --------- WDSC ---------
	private void importInfoActionPerformed(ActionEvent e) {
		pane.addTab("WDSC",new ImageIcon("images/help.png"), new WdsHelp());
		this.initTabComponent(pane.getTabCount()-1);
		pane.setSelectedIndex(pane.getTabCount()-1);
		
		setVisible(true);
	}

	private void formaCoordActionPerformed(ActionEvent e) {
		pane.addTab("Información coordenadas",new ImageIcon("images/help.png"),new CoordinatesFormat());
	    this.initTabComponent(pane.getTabCount()-1);
	    pane.setSelectedIndex(pane.getTabCount()-1);
	}

	private void dnebInfoActionPerformed(ActionEvent e) {
		pane.addTab("DNEB",new ImageIcon("images/help.png"),new BackgroundPanel(pane));
	    this.initTabComponent(pane.getTabCount()-1);
	    pane.setSelectedIndex(pane.getTabCount()-1);
	}
	
	
	
	
	
	
	public JTabbedPane getPane() {
		return pane;
	}
	public void setPane(JTabbedPane pane) {
		this.pane = pane;
	}
	
	private void initComponents() {

		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		crearNuevaTarea = new JMenuItem();
		gestorTareas = new JMenuItem();
		menu2 = new JMenu();
		crearDescarga = new JMenuItem();
		menu3 = new JMenu();
		configDownload = new JMenuItem();
		menu6 = new JMenu();
		exportRelevantXML = new JMenuItem();
		menu4 = new JMenu();
		importBBDD = new JMenuItem();
		menuCatalogoED = new JMenuItem();
		menu5 = new JMenu();
		visualizador = new JMenuItem();
		visorCentroides = new JMenuItem();
		visualizadorDebug = new JMenuItem();
		nueProcEstDob = new JMenuItem();
		buscar = new JMenuItem();
		consultarCatalogo = new JMenuItem();
		conversor = new JMenuItem();
		distancias = new JMenuItem();
		histograma = new JMenuItem();
		menu7 = new JMenu();
		importInfo = new JMenuItem();
		formaCoord = new JMenuItem();
		dnebInfo = new JMenuItem();
		menu8 = new JMenu();

		//======== this ========
		setIconImage(null);
		setTitle("DNEB (DETECCIÓN DE NUEVAS ESTRELLAS BINARIAS)");
		//Container contentPane = getContentPane();
		//contentPane.setLayout(null);

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

			}
			menuBar1.add(menu2);
			
			//======== menu3 ========
			{
				menu3.setText("PROCESAMIENTOS");

				//---- nueProcEstDob ----
				nueProcEstDob.setText("CREAR PROCESAMIENTO");
				nueProcEstDob.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						nueProcEstDobActionPerformed(e);
					}
				});
				menu3.add(nueProcEstDob);

				//---- buscar ----
				buscar.setText("GESTOR DE PROCESAMIENTOS");
				buscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buscarActionPerformed(e);
					}
				});
				menu3.add(buscar);
			}
			menuBar1.add(menu3);

			//======== menu4 ========
			{
				//menu4.setIcon(null);
				menu4.setText("CONFIGURACION");

				//---- configDownload ----
				configDownload.setText("CREAR CONFIGURACION DE DESCARGA");
				configDownload.setIcon(null);
				configDownload.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						configDownloadActionPerformed(e);
					}
				});
				menu4.add(configDownload);
			}
			menuBar1.add(menu4);

			//======== menu5 ========
			{
				menu5.setText("EXPORTAR DATOS");

				//---- exportRelevantXML ----
				exportRelevantXML.setText("EXPORTAR DATOS RELEVANTES(XML)");
				exportRelevantXML.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						exportRelevantXMLActionPerformed(e);
					}
				});
				menu5.add(exportRelevantXML);
			}
			menuBar1.add(menu5);

			//======== menu6 ========
			{
				menu6.setText("IMPORTAR DATOS");
				menu6.setIcon(null);

				//---- importBBDD ----
				importBBDD.setText("DESDE BBDD");
				importBBDD.setIcon(null);
				importBBDD.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						importBBDDActionPerformed(e);
					}
				});
				menu6.add(importBBDD);

				//---- menuCatalogoED ----
				menuCatalogoED.setText("IMPORTAR CATÁLOGO ESTRELLAS DOBLES");
				menuCatalogoED.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						menuCatalogoEDActionPerformed(e);
					}
				});
				menu6.add(menuCatalogoED);
			}
			menuBar1.add(menu6);

			//======== menu7 ========
			{
				menu7.setText("UTILIDADES");

				//---- visualizador ----
				visualizador.setText("VISOR ESTRELLAS");
				visualizador.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						visualizadorActionPerformed(e);
					}
				});
				menu7.add(visualizador);

				//---- visorCentroides ----
				visorCentroides.setText("VISOR DOBLES");
				visorCentroides.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						visorCentroidesActionPerformed(e);
					}
				});
				menu7.add(visorCentroides);

				//---- visualizadorDebug ----
				visualizadorDebug.setText("VISOR DEBUG");
				visualizadorDebug.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						visualizadorDebugActionPerformed(e);
					}
				});
				menu7.add(visualizadorDebug);

				//---- consultarCatalogo ----
				consultarCatalogo.setText("CONSULTAR CATÁLOGO");
				consultarCatalogo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						consultarCatalogoActionPerformed(e);
					}
				});
				menu7.add(consultarCatalogo);

				//---- conversor ----
				conversor.setText("CONVERSOR COORDENADAS");
				conversor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						conversorActionPerformed(e);
					}
				});
				menu7.add(conversor);

				//---- distancias ----
				distancias.setText("CÁLCULO DISTANCIAS");
				distancias.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						distanciasActionPerformed(e);
					}
				});
				menu7.add(distancias);

				//---- histograma ----
				histograma.setText("HISTOGRAMA");
				histograma.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						histogramaActionPerformed(e);
					}
				});
				menu7.add(histograma);
			}
			menuBar1.add(menu7);

			//======== menu8 ========
			{
				menu8.setText("AYUDA");

				//---- importInfo ----
				importInfo.setText("IMPORTAR CATÁLOGO");
				importInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						importInfoActionPerformed(e);
					}
				});
				menu8.add(importInfo);

				//---- formaCoord ----
				formaCoord.setText("FORMATO COORDENADAS");
				formaCoord.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						formaCoordActionPerformed(e);
					}
				});
				menu8.add(formaCoord);

				//---- dnebInfo ----
				dnebInfo.setText("DNEB");
				dnebInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dnebInfoActionPerformed(e);
					}
				});
				menu8.add(dnebInfo);
			}
			menuBar1.add(menu8);
		}
		setJMenuBar(menuBar1);
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

	
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JMenuItem crearNuevaTarea;
	private JMenuItem gestorTareas;
	private JMenu menu2;
	private JMenuItem crearDescarga;
	private JMenu menu3;
	private JMenuItem configDownload;
	private JMenu menu6;
	private JMenuItem exportRelevantXML;
	private JMenu menu4;
	private JMenuItem importBBDD;
	private JMenuItem menuCatalogoED;
	private JMenu menu5;
	private JMenuItem visualizador;
	private JMenuItem visorCentroides;
	private JMenuItem visualizadorDebug;
	private JMenuItem nueProcEstDob;
	private JMenuItem buscar;
	private JMenuItem consultarCatalogo;
	private JMenuItem conversor;
	private JMenuItem distancias;
	private JMenuItem histograma;
	private JMenu menu7;
	private JMenuItem importInfo;
	private JMenuItem formaCoord;
	private JMenuItem dnebInfo;
	private JMenu menu8;
}
