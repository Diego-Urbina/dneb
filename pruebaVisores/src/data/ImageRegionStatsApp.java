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

import java.awt.Polygon;
import java.awt.Rectangle;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.ROIShape;

/**
 * This class uses some ImageRegions to get some statistics on an image.
 */
public class ImageRegionStatsApp
  {
  /**
   * The applications' entry point.
   * @param args the command-line arguments.
   */
  public static void main(String[] args)
    {
    // We don't need image file names, this app should run with the malaria.jpg image.
    PlanarImage input = JAI.create("fileload","malaria.jpg"); // Read the image.
    // Create shapes, then ROIShapes, then ImageRegions for three different
    // targets on the image.
    // Image background.
    ImageRegion background1 = 
      new ImageRegion(input,new ROIShape(new Rectangle(220,301,37,66))); 
    int[] x = new int[]{343,359,372};
    int[] y = new int[]{263,309,254};
    ImageRegion background2 = 
      new ImageRegion(input,new ROIShape(new Polygon(x,y,3)));
    // Print mean and stddev for the two regions
    printData("Background, region 1",background1);
    printData("Background, region 2",background2);
    // Red cells.
    x = new int[]{172,188,205};    
    y = new int[]{211,247,218};    
    ImageRegion cell1 = new ImageRegion(input,new ROIShape(new Polygon(x,y,3)));
    x = new int[]{343,378,377};    
    y = new int[]{384,412,365};    
    ImageRegion cell2 = new ImageRegion(input,new ROIShape(new Polygon(x,y,3)));
    // Print mean and stddev for the two regions
    printData("Cell, region 1",cell1);
    printData("Cell, region 2",cell2);
    // Malaria cells.
    ImageRegion malaria1 = new ImageRegion(input,new ROIShape(new Rectangle(584,356,23,39)));
    ImageRegion malaria2 = new ImageRegion(input,new ROIShape(new Rectangle(138,115,13,15)));
    // Print mean and stddev for the two regions
    printData("Malaria, region 1",malaria1);
    printData("Malaria, region 2",malaria2);
    }

 /*
  * This method prints the mean and standard deviation data of a ImageRegion.
  */
  private static void printData(String title,ImageRegion r)
    {
    System.out.println("===================================================");
    System.out.println(title);
    System.out.print("Mean: ");
    double[] mean = r.getMean();
    for(int d=0;d<mean.length;d++) 
      System.out.print(String.format("%7.3f ",new Object[]{new Float(mean[d])}));
    System.out.println();
    System.out.print("StdDev: ");
    double[] stddev = r.getStdDev();
    for(int d=0;d<stddev.length;d++) 
      System.out.print(String.format("%10.4f ",new Object[]{new Float(stddev[d])}));
    System.out.println();
    }
  
  }