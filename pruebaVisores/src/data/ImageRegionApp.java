/*
 * Created on Jun 17, 2005
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
package data;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.ROIShape;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import display.DisplayImageWithRegions;

/**
 * This class uses some ImageRegions to annotate an image. The annotated image is displayed in a
 * DisplayImageWithRegions component.
 */
public class ImageRegionApp
  {
  /**
   * The applications' entry point.
   * @param args the command-line arguments.
   */
  public static void main(String[] args)
    {
    // We don't need image file names, this app should run with the rio-ikonos.jpg image.
    // Read the image.
    PlanarImage image = JAI.create("fileload","kk.png");
    //
    float scale = 1000/100f;
    ParameterBlock pb = new ParameterBlock();
    pb.addSource(image);
    pb.add(scale);
    pb.add(scale);
    pb.add(0.0F);
    pb.add(0.0F);
    pb.add(new InterpolationNearest());
    // Creates a new, scaled image and uses it on the DisplayJAI component
    PlanarImage input = JAI.create("scale", pb);
    //
    
    // Create shapes, then ROIShapes, then ImageRegions.
    // First a 4-sided polygon over something on the right part of the image.
    int[] x1 = new int[]{0,0,30, 30};
    int[] y1 = new int[]{0,30,30, 0};
    Shape s1 = new Polygon(x1,y1,4);
    ImageRegion r1 = new ImageRegion(input,new ROIShape(s1)); 
    r1.setFillColor(new Color(255,255,0,100));
    r1.setBorderColor(new Color(255,255,0));
    // Then a 5-sided polygon over something on the bottom part of the image.
    int[] x2 = new int[]{1353,1376,1353,1296,1323};
    int[] y2 = new int[]{1841,1808,1710,1727,1817};
    Shape s2 = new Polygon(x2,y2,5);
    ImageRegion r2 = new ImageRegion(input,new ROIShape(s2)); 
    r2.setFillColor(new Color(255,0,150,50));
    r2.setBorderColor(new Color(255,0,200));
    // Finally a 3-sided polygon over some building on the top/left part of the image.
    int[] x3 = new int[]{326,395,324};
    int[] y3 = new int[]{521,645,658};
    Shape s3 = new Polygon(x3,y3,3);
    ImageRegion r3 = new ImageRegion(input,new ROIShape(s3)); 
    r3.setBorderColor(new Color(50,255,100));
    r3.setStroke(new BasicStroke(5));
    // Create a JFrame for displaying the results.
    JFrame frame = new JFrame();
    frame.setTitle("Image with regions");
    // Add to the JFrame's ContentPane an instance of DisplayImageWithRegions, 
    // which will contain the original image and its regions.
    DisplayImageWithRegions display = new DisplayImageWithRegions(input);
    display.addImageRegion(r1);
    display.addImageRegion(r2);
    display.addImageRegion(r3);
    frame.getContentPane().add(new JScrollPane(display));
    // Set the closing operation so the application is finished.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack(); // adjust the frame size using preferred dimensions.
    frame.setVisible(true); // show the frame.
    }

  }