package es.ucm.si.dneb.util;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ProgressRenderer extends DefaultTableCellRenderer {

	private JProgressBar bar = new JProgressBar(0, 100);
	
    public ProgressRenderer() {
        super();
        setOpaque(true);
        bar.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        bar.setStringPainted(true);
    }
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        Integer i = (Integer)value;
        String s = "Parada";
        if (i != -1) {
        	s = i + "%";	
        }
        bar.setString(s);
    	bar.setValue(i);
    	return bar;
    }

}
