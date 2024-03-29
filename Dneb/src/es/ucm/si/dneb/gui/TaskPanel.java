package es.ucm.si.dneb.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import org.springframework.context.ApplicationContext;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareasException;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;
import es.ucm.si.dneb.util.ProgressRenderer;

public class TaskPanel extends JPanel {
	
	private static final long serialVersionUID = -3364091451558723326L;
	private DefaultTableModel modelo;
	private final Map<Integer, SwingWorker<Integer, Integer>> workers = new HashMap<Integer, SwingWorker<Integer, Integer>>();
	private ServicioGestionTareas servicioGestionTareas;
	
	public TaskPanel() {
		initComponents();
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
        servicioGestionTareas = (ServicioGestionTareas)ctx.getBean("servicioGestionTareas");
		
        initIcons();
        
        this.buttonReanudar.setSize(50, 300);
        this.buttonParar.setSize(50, 300);
        this.buttonEliminar.setSize(50, 300);
        
        
        rellenarTabla();
	}

	private void initIcons() {
		this.buttonReanudar.setIcon(new ImageIcon("images/play-rojo.png"));
        this.buttonParar.setIcon(new ImageIcon("images/stop-rojo.png"));
        this.buttonEliminar.setIcon(new ImageIcon("images/delete-rojo.png"));
	}
	
	private void rellenarTabla() {
		try {
	        ArrayList<Tarea> tareas = (ArrayList<Tarea>) servicioGestionTareas.getTareasPendientes();
	        
	        int nFila = 0;
	        TableColumn column;
	        SwingWorker<Integer, Integer> worker;
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
	        		if(tarea.getSurveys().size()>1){
		        		fila[13] = tarea.getSurveys().get(1).getDescripcion();
		        	}
	        	}
	        	
	        	fila[14] = servicioGestionTareas.obtenerPorcentajeCompletado(tarea.getIdTarea());
	        	modelo.addRow(fila);
	        	column = tableTasks.getColumnModel().getColumn(14);
	            column.setCellRenderer(new ProgressRenderer());
	            worker = crearWorker(tarea.getIdTarea(), nFila);
	            workers.put(nFila, worker);
	            nFila++;
	        }
		} catch(ServicioGestionTareasException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}



	
	private void buttonEliminarActionPerformed(ActionEvent e) {
		try {
			SwingWorker<Integer, Integer> w = workers.get(tableTasks.getSelectedRow());
			if (!w.isCancelled())
				w.cancel(true);
			workers.remove(w);
			servicioGestionTareas.eliminarTarea((Long) modelo.getValueAt(tableTasks.getSelectedRow(), 0));
			modelo.removeRow(tableTasks.getSelectedRow());
		} catch(ServicioGestionTareasException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private void buttonPararActionPerformed(ActionEvent e) {
		try {
			SwingWorker<Integer, Integer> w = workers.get(tableTasks.getSelectedRow());
			if (!w.isCancelled())
				w.cancel(true);
			servicioGestionTareas.pararTarea((Long) modelo.getValueAt(tableTasks.getSelectedRow(), 0));
			tableTasks.setValueAt(-1, tableTasks.getSelectedRow(), 14);
		} catch(ServicioGestionTareasException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private void buttonReanudarActionPerformed(ActionEvent e) {
		try {
			workers.get(tableTasks.getSelectedRow()).execute();
			servicioGestionTareas.reanudarTarea((Long) modelo.getValueAt(tableTasks.getSelectedRow(), 0));
			tableTasks.setValueAt(servicioGestionTareas.obtenerPorcentajeCompletado((Long) modelo.getValueAt(tableTasks.getSelectedRow(), 0)), tableTasks.getSelectedRow(), 14);
		} catch(ServicioGestionTareasException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private SwingWorker<Integer, Integer> crearWorker(final long idTarea, final int nFila) {
		SwingWorker<Integer, Integer> worker = new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() {
                int porcentaje = servicioGestionTareas.obtenerPorcentajeCompletado(idTarea);
                while(porcentaje < 100) {
                    publish(porcentaje);
                    try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						break;
					}
                    porcentaje = servicioGestionTareas.obtenerPorcentajeCompletado(idTarea);
                }
                publish(porcentaje);
                return porcentaje;
            }
            @Override
            protected void process(java.util.List<Integer> c) {
            	modelo.setValueAt(c.get(c.size()-1), nFila, 14);
            }
        };
        
        return worker;
	}

	private void initComponents() {
		
		scrollPane = new JScrollPane();
		tableTasks = new JTable();
		buttonEliminar = new JButton();
		buttonParar = new JButton();
		buttonReanudar = new JButton();
		titulo = new JLabel();
		
		//======== this ========

		//======== scrollPane ========
		{

			//---- tableTasks ----
			modelo = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Iden", "Alto", "Ancho", "ARI", "ARF", "DECI", "DECF", "Solapamiento", "Fecha Creacion", "Fecha Actualizacion", "Formato", "Ruta", "Survey 1", "Survey 2", "Completada"
				}
			) {
				Class[] columnTypes = new Class[] {
						Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
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
				cm.getColumn(11).setPreferredWidth(107);
				cm.getColumn(12).setPreferredWidth(60);
				cm.getColumn(13).setPreferredWidth(60);
				cm.getColumn(14).setPreferredWidth(80);
			}
			tableTasks.setRowSelectionAllowed(true);
			tableTasks.setPreferredScrollableViewportSize(new Dimension(2000, 2000));
			//tableTasks.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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

		//---- buttonEliminar ----
		buttonEliminar.setText("ELIMINAR");
		buttonEliminar.setFont(new Font("Arial", Font.PLAIN, 11));
		buttonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonEliminarActionPerformed(e);
			}
		});
		
		//---- buttonParar ----
		buttonParar.setText("PARAR");
		buttonParar.setFont(new Font("Arial", Font.PLAIN, 11));
		buttonParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPararActionPerformed(e);
			}
		});
		
		//---- buttonReanudar ----
		buttonReanudar.setText("REANUDAR");
		buttonReanudar.setFont(new Font("Arial", Font.PLAIN, 11));
		buttonReanudar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonReanudarActionPerformed(e);
			}
		});
		
		setLayout(new GridLayoutManager(3, 3, new Insets(30, 60, 30, 60), 5, -1));
		
		titulo.setText("GESTOR DE TAREAS");
		titulo.setFont(titulo.getFont().deriveFont(titulo.getFont().getSize() + 10f));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(titulo, new GridConstraints(0, 0, 1, 3,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK ,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		add(scrollPane, new GridConstraints(1, 0, 1, 3,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		
		
		add(buttonReanudar, new GridConstraints(2, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		
		add(buttonParar, new GridConstraints(2, 1, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		
		add(buttonEliminar, new GridConstraints(2, 2, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));		
	}

	
	private JScrollPane scrollPane;
	private JTable tableTasks;
	private JButton buttonEliminar;
	private JButton buttonParar;
	private JButton buttonReanudar;
	private JLabel titulo;
}