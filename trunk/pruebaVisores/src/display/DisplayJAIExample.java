/*
 * Created on Jun 15, 2005
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
package display;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import com.sun.media.jai.widget.DisplayJAI;

/**
 * This application shows how one can use the DisplayJAI class with a
 * JScrollPane to display images.
 */
public class DisplayJAIExample
  {
  public static void main(String[] args)
    {
    // Load the image which file name was passed as the first argument to the
    // application.
    PlanarImage image = JAI.create("fileload", args[0]);
    // Get some information about the image
    String imageInfo = "Dimensions: "+image.getWidth()+"x"+image.getHeight()+
                       " Bands:"+image.getNumBands();
    // Create a frame for display.
    JFrame frame = new JFrame();
    frame.setTitle("DisplayJAI: "+args[0]);
    // Get the JFrame's ContentPane.
    Container contentPane = frame.getContentPane();
    contentPane.setLayout(new BorderLayout());
    // Create an instance of DisplayJAI.
    DisplayJAI dj = new DisplayJAI(image);
    // Add to the JFrame's ContentPane an instance of JScrollPane containing the
    // DisplayJAI instance.
    contentPane.add(new JScrollPane(dj),BorderLayout.CENTER);
    // Add a text label with the image information.
    contentPane.add(new JLabel(imageInfo),BorderLayout.SOUTH);
    // Set the closing operation so the application is finished.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400,400); // adjust the frame size.
    frame.setVisible(true); // show the frame.
    }

  }