package es.ucm.si.dneb.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.springframework.context.ApplicationContext;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import es.ucm.si.dneb.domain.CargaDatos;
import es.ucm.si.dneb.domain.Survey;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.importData.ImportDDBBData;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;

public class ImportarDesdeBBDD extends JPanel {
	
	private JTable tableTasks;
	private DefaultTableModel modelo;
	
	private ImportDDBBData importDDBBData;
	private ServicioGestionTareas servicioGestionTareas;
	private final Map<Integer, SwingWorker<Integer, Integer>> workers = new HashMap<Integer, SwingWorker<Integer, Integer>>();
	
	public ImportarDesdeBBDD() {
		
		initComponents();
		initTable();
		
		ApplicationContext ctx = ContextoAplicacion.getApplicationContext();
		importDDBBData= (ImportDDBBData) ctx.getBean("importDDBBData");
		
        this.servicioGestionTareas = (ServicioGestionTareas)ctx.getBean("servicioGestionTareas");      
        ArrayList<Survey> surveys = (ArrayList<Survey>) servicioGestionTareas.getAllSurveys();
		
        DefaultListModel list = new DefaultListModel();
        for (Survey aux : surveys){
                list.addElement(aux.getDescripcion());
        }
        listSurvey.setModel(list);
        listSurvey.setSelectedIndex(0);

        rellenarTabla();
	}
	

	private void importarActionPerformed(ActionEvent e) {		
		Object[] survStrings= this.listSurvey.getSelectedValues();

		List<Survey> surveys= new ArrayList<Survey>();
		
		for(int i=0; i< survStrings.length;i++){
			
			Survey survey=importDDBBData.getSurveyByDesc((String) survStrings[i]);
			
			surveys.add(survey);
			
		}
		
		
		int seleccionados []= tableTasks.getSelectedRows();
		
		List<CargaDatos> datosACargar= new ArrayList<CargaDatos>();
		
		for(int i=0;i<seleccionados.length;i++){
			datosACargar.add(importDDBBData.getCargaDatosById((Long) modelo.getValueAt(i, 0)));
			
		}
		
		importDDBBData.generarTarea(datosACargar,surveys);
		
		JOptionPane.showMessageDialog(null,"Datos importados satisfactoriamente", "Operación satisfactoria", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("images/downconfig (Custom).JPG"));
		
		
	}
	
	private void rellenarTabla(){		
		List<CargaDatos> cargaDatos=importDDBBData.getAllDatosAImportar();
		
		int nFila = 0;
        TableColumn column;
        SwingWorker<Integer, Integer> worker;
        Object [] fila = new Object[tableTasks.getColumnCount()];
        for (CargaDatos carga : cargaDatos) {
        	fila[0] = carga.getIdPunto();
        	fila[1] = carga.getAlto();
        	fila[2] = carga.getAncho();
        	fila[3] = carga.getAscencionRecta();
        	fila[4] = carga.getDeclinacion();
        	fila[5] = carga.getDescripcion();
        	fila[6] = carga.getPath();
        	
        	modelo.addRow(fila);
            nFila++;
        }		
	}
	
	
	
	private void initTable(){
		tableTasks = new JTable();
		
		//---- tableTasks ----
		modelo = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Alto", "Ancho", "AR", "DEC", "Descripción", "Path"
			}
		) {
			Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			boolean[] columnEditable = new boolean[] {
				false, false, false, false, false, false, false
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
			cm.getColumn(0).setPreferredWidth(70);
			cm.getColumn(1).setPreferredWidth(70);
			cm.getColumn(2).setPreferredWidth(70);
			cm.getColumn(3).setPreferredWidth(70);
			cm.getColumn(4).setPreferredWidth(70);
			cm.getColumn(5).setPreferredWidth(580);
			cm.getColumn(6).setPreferredWidth(70);
			
		}
		tableTasks.setRowSelectionAllowed(true);
		tableTasks.setPreferredScrollableViewportSize(new Dimension(1000, 300));
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
    	
    	scrollPane1.setViewportView(tableTasks);
	}

	private void initComponents() {
		titulo = new JLabel();
		scrollPane1 = new JScrollPane();
		//table1 = new JTable();
		labelSurvey = new JLabel();
		importar = new JButton();
		listSurvey = new JList();
		
		//======== this ========
		setLayout(new GridLayoutManager(5, 1, new Insets(30, 60, 30, 60), 5, -1));
		
		titulo.setText("IMPORTAR DESDE BBDD");
		titulo.setFont(titulo.getFont().deriveFont(titulo.getFont().getSize() + 10f));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(titulo, new GridConstraints(0, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK ,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));

		add(scrollPane1, new GridConstraints(1, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		
		labelSurvey.setText("SURVEY");
		add(labelSurvey, new GridConstraints(2, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		listSurvey.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(listSurvey, new GridConstraints(3, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK,
			null, null, null));
		
		importar.setText("GENERAR TAREA");
		importar.setFont(new Font("Arial", Font.PLAIN, 11));
		importar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importarActionPerformed(e);
			}
		});
		
		add(importar, new GridConstraints(4, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				null, null, null));
	}

	private JLabel titulo;
	private JScrollPane scrollPane1;
	//private JTable table1;
	private JLabel labelSurvey;
	private JButton importar;
	private JList listSurvey;
}
