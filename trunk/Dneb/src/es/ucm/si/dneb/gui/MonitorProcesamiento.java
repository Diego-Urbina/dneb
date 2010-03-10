package es.ucm.si.dneb.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import org.springframework.context.ApplicationContext;

import es.ucm.si.dneb.domain.Tarea;
import es.ucm.si.dneb.domain.ProcTarea;
import es.ucm.si.dneb.service.gestionProcesamientos.ServicioGestionProcesamientos;
import es.ucm.si.dneb.service.gestionProcesamientos.ServicioGestionProcesamientosException;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareasException;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;
import es.ucm.si.dneb.util.ProgressRenderer;

public class MonitorProcesamiento extends JPanel{
	
	
	private static final long serialVersionUID = 2891828746598298710L;
	private VentanaPcpal principal;
	private DefaultTableModel modelo;
	private final Map<Integer, SwingWorker<Integer, Integer>> workers = new HashMap<Integer, SwingWorker<Integer, Integer>>();
	private ServicioGestionProcesamientos servicioGestionProcesamientos;
	
	public MonitorProcesamiento(VentanaPcpal pcpal) {
		
		initComponents();
		principal = pcpal;
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
        servicioGestionProcesamientos = (ServicioGestionProcesamientos)ctx.getBean("servicioGestionProcesamientos");
		
        initIcons();
        
        
        this.buttonReanudar.setSize(50, 300);
        this.buttonParar.setSize(50, 300);
        this.buttonEliminar.setSize(50, 300);
        
        
        rellenarTabla();
	}

	private void initIcons() {
		this.buttonReanudar.setIcon(new ImageIcon("images/starticon.jpg"));
        this.buttonParar.setIcon(new ImageIcon("images/stop_icon.png"));
        this.buttonEliminar.setIcon(new ImageIcon("images/deleteicon.png"));
	}
	
	private void rellenarTabla() {
		try {
	        ArrayList<ProcTarea> tareas = (ArrayList<ProcTarea>) servicioGestionProcesamientos.getProcesamientos();
	        
	        int nFila = 0;
	        TableColumn column;
	        SwingWorker<Integer, Integer> worker;
	        Object [] fila = new Object[tableTasks.getColumnCount()];
	        for (ProcTarea procesamiento : tareas) {
	        	
	        	fila[0] = procesamiento.getIdProcesamiento();
	        	fila[1] = procesamiento.getTarea().getAlto();
	        	fila[2] = procesamiento.getTarea().getAncho();
	        	fila[3] = procesamiento.getTarea().getArInicial();
	        	fila[4] = procesamiento.getTarea().getArFinal();
	        	fila[5] = procesamiento.getTarea().getDecInicial();
	        	fila[6] = procesamiento.getTarea().getDecFinal();
	        	fila[7] = procesamiento.getTarea().getSolpamiento();
	        	fila[8] = procesamiento.getFechaCreacion().toString();
	        	fila[9] = procesamiento.getTarea().getFechaUltimaActualizacion().toString();
	        	fila[10] = procesamiento.getTarea().getFormatoFichero().getAlias();
	        	fila[11] = procesamiento.getTarea().getRuta();
	        	fila[12] = procesamiento.getTarea().getSurveys().get(0).getDescripcion();
	        	fila[13] = procesamiento.getTarea().getSurveys().get(1).getDescripcion();
	        	fila[14] = procesamiento.getTipoProcesamiento().getAlias();
	        	fila[15] = servicioGestionProcesamientos.getPorcentajeCompletado(procesamiento.getIdProcesamiento());
	        	
	        	modelo.addRow(fila);
	        	column = tableTasks.getColumnModel().getColumn(15);
	            column.setCellRenderer(new ProgressRenderer());
	            worker = crearWorker(procesamiento.getIdProcesamiento(), nFila);
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
			servicioGestionProcesamientos.eliminarProcesamiento((Long) modelo.getValueAt(tableTasks.getSelectedRow(), 0));
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
			servicioGestionProcesamientos.pararProcesamiento((Long) modelo.getValueAt(tableTasks.getSelectedRow(), 0));
			tableTasks.setValueAt(-1, tableTasks.getSelectedRow(), 14);
		} catch(ServicioGestionTareasException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private void buttonReanudarActionPerformed(ActionEvent e) {
		try {		
			workers.get(tableTasks.getSelectedRow()).execute();
			servicioGestionProcesamientos.reanudarProcesamiento((Long) modelo.getValueAt(tableTasks.getSelectedRow(), 0));
			tableTasks.setValueAt(servicioGestionProcesamientos.getPorcentajeCompletado((Long) modelo.getValueAt(tableTasks.getSelectedRow(), 0)), tableTasks.getSelectedRow(), 15);
		} catch(ServicioGestionTareasException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch(ServicioGestionProcesamientosException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private SwingWorker<Integer, Integer> crearWorker(final long idTarea, final int nFila) {
		SwingWorker<Integer, Integer> worker = new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() {
                int porcentaje = servicioGestionProcesamientos.getPorcentajeCompletado(idTarea).intValue();
                while(porcentaje < 100) {
                    publish(porcentaje);
                    try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						break;
					}
                    porcentaje = servicioGestionProcesamientos.getPorcentajeCompletado(idTarea).intValue();
                }
                publish(porcentaje);
                return porcentaje;
            }
            @Override
            protected void process(java.util.List<Integer> c) {
            	modelo.setValueAt(c.get(c.size()-1), nFila, 15);
            }
        };
        
        return worker;
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		scrollPane = new JScrollPane();
		tableTasks = new JTable();
		buttonVolver = new JButton();
		buttonEliminar = new JButton();
		buttonParar = new JButton();
		buttonReanudar = new JButton();

		//======== this ========

		//======== scrollPane ========
		{

			//---- tableTasks ----
			modelo = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Iden", "Alto", "Ancho", "ARI", "ARF", "DECI", "DECF", "Solapamiento", "Fecha Creacion", "Fecha Actualizacion", "Formato", "Ruta", "Survey 1", "Survey 2", "Tipo","Completada"
				}
			) {
				Class[] columnTypes = new Class[] {
						Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
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
				cm.getColumn(11).setPreferredWidth(60);
				cm.getColumn(12).setPreferredWidth(60);
				cm.getColumn(13).setPreferredWidth(60);
				cm.getColumn(14).setPreferredWidth(47);
				cm.getColumn(15).setPreferredWidth(80);
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
	    	tableTasks.getColumnModel().getColumn(14).setCellRenderer(tcr);
			scrollPane.setViewportView(tableTasks);
		}

		//---- buttonVolver ----
		buttonVolver.setText("VOLVER");
		buttonVolver.setFont(new Font("Arial", Font.PLAIN, 11));
		buttonVolver.setVisible(false);

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

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addGroup(layout.createSequentialGroup()
							.addComponent(buttonReanudar, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGap(117, 117, 117)
							.addComponent(buttonParar, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGap(117, 117, 117)
							.addComponent(buttonEliminar, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGap(117, 117, 117)
							.addComponent(buttonVolver, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
					.addGap(37, 37, 37)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(buttonVolver)
						.addComponent(buttonEliminar)
						.addComponent(buttonParar)
						.addComponent(buttonReanudar))
					.addGap(40, 40, 40))
		);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane scrollPane;
	private JTable tableTasks;
	private JButton buttonVolver;
	private JButton buttonEliminar;
	private JButton buttonParar;
	private JButton buttonReanudar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

}
