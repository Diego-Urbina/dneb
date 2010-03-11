package es.ucm.si.dneb.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.springframework.context.ApplicationContext;

import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.ParamProcTarea;
import es.ucm.si.dneb.domain.ProcImagen;
import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.domain.ProcTarea;
import es.ucm.si.dneb.service.gestionProcesamientos.ServicioGestionProcesamientos;
import es.ucm.si.dneb.service.gestionProcesamientos.ServicioGestionProcesamientosException;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareasException;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;

public class CrearProcesamiento extends JPanel {

	
	private static final long serialVersionUID = -320907160078131474L;
	private DefaultTableModel modelo;
	private ServicioGestionTareas servicioGestionTareas;
	private ServicioGestionProcesamientos servicioGestionProcesamientos;
	private JScrollPane scrollPane;
	private JTable tableTasks;
	private JButton buttonSiguiente;
	private JComboBox cbTipoProc;
	private JTextField textFieldUmbral, textFieldBrillo;
	private JLabel labelUmbral, labelBrillo;
	
	private VentanaPcpal principal;
	
	public CrearProcesamiento(VentanaPcpal pcpal) {
		
		this.principal=pcpal;
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
		servicioGestionTareas = (ServicioGestionTareas)ctx.getBean("servicioGestionTareas");
		servicioGestionProcesamientos = (ServicioGestionProcesamientos)ctx.getBean("servicioGestionProcesamientos");
		initComponents();
		rellenarTabla();
	}
	
	private void rellenarTabla() {
		try {
	        ArrayList<Tarea> tareas = (ArrayList<Tarea>) servicioGestionTareas.getTareasFinalizadas();
	        
	        Object [] fila = new Object[tableTasks.getColumnCount()];
	        for (Tarea tarea : tareas) {
	        	fila[0] = tarea.getIdTarea();
	        	fila[1] = tarea.getAlto();
	        	fila[2] = tarea.getAncho();
	        	fila[3] = tarea.getArInicial();
	        	fila[4] = tarea.getArFinal();
	        	fila[5] = tarea.getDecInicial();
	        	fila[6] = tarea.getDecFinal();
	        	fila[7] = tarea.getSolpamiento();
	        	fila[8] = tarea.getFechaCreacion().toString();
	        	fila[9] = tarea.getFechaUltimaActualizacion().toString();
	        	fila[10] = tarea.getFormatoFichero().getAlias();
	        	fila[11] = tarea.getRuta();
	        	if(tarea.getSurveys().size()>0){

	        		fila[12] = tarea.getSurveys().get(0).getDescripcion();
	        	}
	        	if(tarea.getSurveys().size()>1){
	        		fila[13] = tarea.getSurveys().get(1).getDescripcion();
	        	}
	        	modelo.addRow(fila);
	        }
		} catch(ServicioGestionTareasException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private void buttonSiguienteActionPerformed(ActionEvent e) {
		// crear procesamiento 
		
		try {
		
			ArrayList<ParamProcTarea> paramProcTareas = new ArrayList<ParamProcTarea>();
			ProcTarea procesamiento=new ProcTarea();
			String tipoProc = (String) cbTipoProc.getSelectedItem();
			
			if (tipoProc.equals("Procesamiento busqueda dobles")) {
				
				double brillo = Double.parseDouble(textFieldBrillo.getText());
				double umbral = Double.parseDouble(textFieldUmbral.getText());
				
				if (umbral <= 0 || brillo <= 0)
					throw new Exception("Los parámetros deben ser mayores que 0");
				
				ParamProcTarea p1 = new ParamProcTarea(), p2 = new ParamProcTarea();
				
				p1.setTareaProcesamiento(procesamiento);
				p1.setTipoParametro(servicioGestionProcesamientos.getTipoParametroById(1L));
				p1.setValorNum(brillo);
				
				p2.setTareaProcesamiento(procesamiento);
				p2.setTipoParametro(servicioGestionProcesamientos.getTipoParametroById(2L));
				p2.setValorNum(umbral);
				
				paramProcTareas.add(p1);
				paramProcTareas.add(p2);
			}
			
			if (tipoProc.equals("Procesamiento calculo distancia")) {
				
				double brillo = Double.parseDouble(textFieldBrillo.getText());
				double umbral = Double.parseDouble(textFieldUmbral.getText());
				
				if (umbral <= 0 || brillo <= 0)
					throw new Exception("Los parámetros deben ser mayores que 0");
				
				ParamProcTarea p1 = new ParamProcTarea(), p2 = new ParamProcTarea();
				
				p1.setTareaProcesamiento(procesamiento);
				p1.setTipoParametro(servicioGestionProcesamientos.getTipoParametroById(1L));
				p1.setValorNum(brillo);
				
				p2.setTareaProcesamiento(procesamiento);
				p2.setTipoParametro(servicioGestionProcesamientos.getTipoParametroById(2L));
				p2.setValorNum(umbral);
				
				paramProcTareas.add(p1);
				paramProcTareas.add(p2);
			}
			
			procesamiento.setActiva(false);
			
			//LA IDEA ES PORDERLOS DAR NORMBRE EN LA INTERFAZ PERO TMP ES PRIORITARIO
			procesamiento.setAlias("DEF_ALIAS");
			procesamiento.setDescription("DEF_PROC");
			
			GregorianCalendar calendar = new GregorianCalendar();
			Date fechaActual = calendar.getTime();
			procesamiento.setFechaCreacion(fechaActual);
			
			procesamiento.setFechaUltimaAct(null);
			procesamiento.setFinalizada(false);
			
			Tarea tarea=servicioGestionTareas.getTareaById((Long) modelo.getValueAt(tableTasks.getSelectedRow(), 0));
			procesamiento.setTarea(tarea);
			
			procesamiento.setTipoProcesamiento(servicioGestionProcesamientos.getTipoProcesamientoByAlias(tipoProc));
			
			ArrayList<ProcImagen> procesamientoImagenes= new ArrayList<ProcImagen>();
			
			List<Imagen> descargas = servicioGestionTareas.getDescargasTarea(tarea.getIdTarea());
			
			for(Imagen imagen: descargas){
				
				ProcImagen procImagen= new ProcImagen();
				procImagen.setImagen(imagen);
				procImagen.setFinalizada(false);
				procImagen.setTareaProcesamiento(procesamiento);
				if(tipoProc.equals("Procesamiento calculo distancia")){
					
				}
				
				procesamientoImagenes.add(procImagen);
				
			}
			procesamiento.setProcesamientoImagenes(procesamientoImagenes);
			
			procesamiento.setParametros(paramProcTareas);
			
			//TODO FALTA RELLENAR EL PROCESAMIENTO CON LOS DATOS INTRODUCIDOS
			
			//OJO TMB HAY QUE ASIGNARLE LOS PARÁMETROS
			
			servicioGestionProcesamientos.crearProcesamiento(procesamiento);
			
			JOptionPane.showMessageDialog(null,"Procesamiento creado satisfactoriamente", "Operación satisfactoria", JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "El valor de los parámetros es incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cbTipoProcActionPerformed(ActionEvent e) {
		if (cbTipoProc.getSelectedIndex() == 0) { // busqueda estrellas dobles
			labelUmbral.setVisible(true);
		    labelBrillo.setVisible(true);
			textFieldUmbral.setVisible(true);
		    textFieldBrillo.setVisible(true);
		}
		else { // calculo de distancias
			labelUmbral.setVisible(true);
		    labelBrillo.setVisible(true);
			textFieldUmbral.setVisible(true);
		    textFieldBrillo.setVisible(true);
		}
	}
	
	private void initComponents() {
		scrollPane = new JScrollPane();
		tableTasks = new JTable();
		buttonSiguiente = new JButton();
		cbTipoProc = new JComboBox(servicioGestionProcesamientos.getTiposProcesamiento().toArray());
		labelUmbral = new JLabel("Umbral");
	    labelBrillo = new JLabel("Brillo");
		textFieldUmbral = new JTextField("20000");
	    textFieldBrillo = new JTextField("30000");
		
		{
			modelo = new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Iden", "Alto", "Ancho", "ARI", "ARF", "DECI", "DECF", "Solapamiento", "Fecha Creacion", "Fecha Actualizacion", "Formato", "Ruta", "Survey 1", "Survey 2"
					}
				) {
					Class[] columnTypes = new Class[] {
							Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
					};
					boolean[] columnEditable = new boolean[] {
						false, false, false, false, false, false, false, false, false, false, false, false, false, false
					};
					@Override
					public Class<?> getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					@Override
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return columnEditable[columnIndex];
					}
				};
				
				tableTasks.setModel(modelo);
				{
					TableColumnModel cm = tableTasks.getColumnModel();
					cm.getColumn(0).setPreferredWidth(35);
					cm.getColumn(1).setPreferredWidth(35);
					cm.getColumn(2).setPreferredWidth(45);
					cm.getColumn(3).setPreferredWidth(35);
					cm.getColumn(4).setPreferredWidth(35);
					cm.getColumn(5).setPreferredWidth(40);
					cm.getColumn(6).setPreferredWidth(40);
					cm.getColumn(7).setPreferredWidth(80);
					cm.getColumn(8).setPreferredWidth(145);
					cm.getColumn(9).setPreferredWidth(145);
					cm.getColumn(10).setPreferredWidth(55);
					cm.getColumn(11).setPreferredWidth(187);
					cm.getColumn(12).setPreferredWidth(60);
					cm.getColumn(13).setPreferredWidth(60);
				}
				tableTasks.setRowSelectionAllowed(true);
				tableTasks.setPreferredScrollableViewportSize(new Dimension(1000, 300));
				tableTasks.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		    	tcr.setHorizontalAlignment(SwingConstants.CENTER);
		    	tableTasks.setAutoCreateRowSorter(true);
		    	tableTasks.getColumnModel().getColumn(0).setCellRenderer(tcr);
		    	tableTasks.getColumnModel().getColumn(1).setCellRenderer(tcr);
		    	tableTasks.getColumnModel().getColumn(2).setCellRenderer(tcr);
		    	tableTasks.getColumnModel().getColumn(3).setCellRenderer(tcr);
		    	tableTasks.getColumnModel().getColumn(4).setCellRenderer(tcr);
		    	tableTasks.getColumnModel().getColumn(5).setCellRenderer(tcr);
		    	tableTasks.getColumnModel().getColumn(6).setCellRenderer(tcr);
		    	tableTasks.getColumnModel().getColumn(7).setCellRenderer(tcr);
		    	tableTasks.getColumnModel().getColumn(8).setCellRenderer(tcr);
		    	tableTasks.getColumnModel().getColumn(9).setCellRenderer(tcr);
		    	tableTasks.getColumnModel().getColumn(10).setCellRenderer(tcr);
		    	tableTasks.getColumnModel().getColumn(12).setCellRenderer(tcr);
		    	tableTasks.getColumnModel().getColumn(13).setCellRenderer(tcr);
				scrollPane.setViewportView(tableTasks);
		}

		buttonSiguiente.setFont(new Font("Arial", Font.PLAIN, 11));
		buttonSiguiente.setSize(50, 300);
		buttonSiguiente.setIcon(new ImageIcon("images/next_icon.png"));
		buttonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonSiguienteActionPerformed(e);
			}
		});
		
		cbTipoProc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbTipoProcActionPerformed(e);
			}
		});

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(buttonSiguiente, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(cbTipoProc, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
							.addGap(50, 50, 50)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addGroup(layout.createSequentialGroup()
										.addComponent(labelUmbral, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						        		.addComponent(textFieldUmbral, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(labelBrillo, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								        .addComponent(textFieldBrillo, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
					.addGap(37, 37, 37)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(cbTipoProc, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(labelUmbral)
								.addComponent(textFieldUmbral, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGap(10, 10, 10)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					    		.addComponent(labelBrillo)
					    		.addComponent(textFieldBrillo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))))
					.addGap(40, 40, 40)
					.addComponent(buttonSiguiente)
					.addGap(40, 40, 40))
		);
	}

}
