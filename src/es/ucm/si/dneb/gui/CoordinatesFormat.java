package es.ucm.si.dneb.gui;

import java.awt.*;
import javax.swing.*;
import com.intellij.uiDesigner.core.*;
/*
 * Created by JFormDesigner on Thu Mar 25 20:08:23 CET 2010
 */



/**
 * @author Brainrain
 */
public class CoordinatesFormat extends JPanel {
	public CoordinatesFormat() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		scrollPane1 = new JScrollPane();
		textPane1 = new JTextPane();
		label1 = new JLabel();

		//======== this ========
		setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 5, -1));

		//======== scrollPane1 ========
		{

			//---- textPane1 ----
			textPane1.setEditable(false);
			textPane1.setText("Enter the Right Ascension and Declination on which you would like the scan to be centered (you won't need to enter it if you use the Target Name Resolver), and select either J2000 or B1950 for the equinox of the coordinates.\n\nA number of formats are accepted for the RA and Dec. Here are some examples:\n\n    Decimal Degrees\n        185.63325 29.8959861111111\n \n    Hours, minutes and Seconds\n        12 22 31.98      29 53 45.55\n        12h22m31.98s     29d53m45.55s\n        12:22:31.98     +29:53:45.55\n        12h22'31.98\"     29d53'45.55\"\n        12h 22m 31.98s   29d 53m 45.55s\n        12h 22' 31.98\"   29d 53' 45.55\"\n        12h 22' 31.98\"  -29d 53' 45.55\"\n        12h22'31\".98    -29d53'45\".55\n        12h22m31s.98    -29o53m45s.55\n        12h 22' 31\".98  -29d 53' 45\".55\n     \n    Hours/Degrees and Minutes (no seconds)\n        12 22     29 53\n        12h22m   +29d53m\n        12h22m    29d53m\n        12:22m    29:53m\n        12h22'    29d53'\n        12h 22m   29d 53m\n        12h 22'   29d 53'\n        12h 22'  -29d 53'\n \n    The RA may be given in decimal degrees by indicating\n    a D or d after the degrees:\n        12d 22m   29d 53m\n\nSpacing is not important, as long as the value is unambiguous, and that you can delimit the hours/degrees, minutes, and (optional) seconds with letters, colons, spaces, or any character that's not a digit or a decimal point.\n\nNote also that seconds of the form 31\".98 or 31s.98 are accepted. This should make it easy to cut and paste values into these fields from electronic publications. ");
			scrollPane1.setViewportView(textPane1);
		}
		add(scrollPane1, new GridConstraints(1, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));

		//---- label1 ----
		label1.setText("Informaci\u00f3n sobre el formato de coordenadas aceptado por la aplicaci\u00f3n.");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label1, new GridConstraints(0, 0, 1, 1,
			GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			null, null, null));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane scrollPane1;
	private JTextPane textPane1;
	private JLabel label1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
