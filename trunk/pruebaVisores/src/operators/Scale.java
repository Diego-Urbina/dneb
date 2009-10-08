/*
 * Created on May 22, 2005
 * @author Rafael Santos (rafael.santos@lac.inpe.br)
 * 
 * Part of the Java Advanced Imaging Stuff site
 * (http://www.lac.inpe.br/~rafael.santos/Java/JAI)
 * 
 * STATUS: Complete.
 * 
 * Redistribution and usage conditions must be done under the
 * Creative Commons license:
 * English: http://creativecommons.org/licenses/by-nc-sa/2.0/br/deed.en
 * Portuguese: http://creativecommons.org/licenses/by-nc-sa/2.0/br/deed.pt
 * More information on design and applications are on the projects' page
 * (http://www.lac.inpe.br/~rafael.santos/Java/JAI).
 */
package operators;

import java.awt.BorderLayout;
import java.awt.image.renderable.ParameterBlock;
import java.util.Hashtable;
import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.sun.media.jai.widget.DisplayJAI;

import display.DisplayImageWithRegions;

/**
 * This class demonstrates the use of the scale operator. It allows the user
 * to set a scale (from 10 to 1000%) interactively.
 */
public class Scale extends JFrame implements ChangeListener
  {
  private JSlider slider; // a slider to set the scale
  private JCheckBox interactive; // will scaling be done interactively ?
  private PlanarImage image; // the original image
  private DisplayJAI display; // the display component

 /**
  * The constructor of the class creates the user interface and registers
  * the event listeners.
  * @param filename the file name of the image (we'll use it on the title bar)
  * @param image the PlanarImage to be rendered/scaled
  */
  public Scale(String filename,PlanarImage image)
    {
    super("Interactive scaling of image "+filename);
    this.image = image;
    // Set the content pane's layout
    getContentPane().setLayout(new BorderLayout());
    // Create and set the image display component
    /**TODO**/
    //DisplayImageWithRegions display = new DisplayImageWithRegions(image);
    display = new DisplayJAI(image);
    getContentPane().add(new JScrollPane(display),BorderLayout.CENTER);
    // Create a small control panel with a checkbox and the slider
    JPanel controlPanel = new JPanel(new BorderLayout());
    // Create and set the checkbox
    interactive = new JCheckBox("Interactive",false);
    controlPanel.add(interactive,BorderLayout.WEST);
    // Create the slider, and set its labels using a label table
    slider = new JSlider(10,1000,100); 
    Hashtable<Integer,JLabel> sliderLabels = new Hashtable<Integer,JLabel>();
    sliderLabels.put(new Integer(10),new JLabel("10"));
    for(int label=50;label<=1000;label+=50)
      sliderLabels.put(new Integer(label),new JLabel(""+label));
    slider.setLabelTable(sliderLabels);
    slider.setPaintLabels(true);
    // Registers the change listener for the slider and add it to the control panel.
    slider.addChangeListener(this);
    controlPanel.add(slider,BorderLayout.CENTER);
    // Add the control panel to the frame
    getContentPane().add(controlPanel,BorderLayout.NORTH);
    // Set the closing operation so the application is finished.
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack(); // adjust the frame size using preferred dimensions.
    setVisible(true); // show the frame.
    }

 /**
  * This method will be executed when the slider position changes.
  */
  public void stateChanged(ChangeEvent e)
    {
    // If interactivity is off and we're still adjusting, return.
    if (slider.getValueIsAdjusting() && !interactive.isSelected()) return;
    // Gets the scale (converting it to a percentage)
    float scale = slider.getValue()/100f;
    // Scales the original image
    ParameterBlock pb = new ParameterBlock();
    pb.addSource(image);
    pb.add(scale);
    pb.add(scale);
    pb.add(0.0F);
    pb.add(0.0F);
    pb.add(new InterpolationNearest());
    // Creates a new, scaled image and uses it on the DisplayJAI component
    PlanarImage scaledImage = JAI.create("scale", pb);
    display.set(scaledImage);
    }

 /**
  * The application entry point.
  * @param args the command line arguments.
  */
  public static void main(String[] args)
    {
    // We need one argument: the image filename.
    
	  
	 /* if (args.length != 1)
      {
      System.err.println("Usage: java operators.Scale image");
      System.exit(0);
      }*/
    
    // Read the image.
    PlanarImage image = JAI.create("fileload", "kk.png");
    // Create the GUI and start the application.
    new Scale("RIO_IKONOS",image);
    }

  } // end class