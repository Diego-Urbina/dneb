/*
 * Created on June 8, 2005
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
package es.ucm.si.dneb.service.image.histograma;

import java.awt.BorderLayout;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.Histogram;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class uses the DisplayHistogram component in an application.
 */
public class DisplayHistogramApp extends JPanel 
  {
  private DisplayHistogram dh; // an instance of the DisplayHistogram component

 /**
  * The constructor for the class, which will create the user interface and
  * register the mouse motion events.
  */
  public DisplayHistogramApp(String name,Histogram histo)
    {
	  dh = new DisplayHistogram(histo,"Histogram of "+name);
	  
	 
	  
	  this.add(dh);
	  
	  dh.setVisible(true);

    setVisible(true); // show the frame.
    }

 /**
  * The application entry point.
  * @param args the command line arguments.
  */
  public static void main(String[] args)
    {
    PlanarImage image = JAI.create("fileload", args[0]);
    ParameterBlock pb = new ParameterBlock();
    pb.addSource(image);
    pb.add(null); // The ROI.
    pb.add(1); // Samplings.
    pb.add(1);
    pb.add(new int[]{5000}); // Num. bins.
    pb.add(new double[]{0}); // Min value to be considered.
    pb.add(new double[]{60000}); // Max value to be considered.
    // Creates the histogram.
    PlanarImage temp = JAI.create("histogram", pb);
    Histogram h = (Histogram)temp.getProperty("histogram");
    new DisplayHistogramApp(args[0],h);
    }

  } // end class