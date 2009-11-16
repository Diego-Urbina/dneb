/*
 * Created on Jun 9, 2005
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
package es.ucm.si.dneb.service.image.app;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.Arrays;
import javax.media.jai.PlanarImage;
import javax.media.jai.ROIShape;
import javax.media.jai.iterator.RandomIter;
import javax.media.jai.iterator.RandomIterFactory;

/**
 * An ImageRegion contains a PlanarImage and a ROIShape which bounds the
 * ROI (region-of-interest) pixels.
 * We can use some methods of ImageRegion to get information of the image
 * pixels inside the ROIShape, and we can also display it on some components.
 */
public class ImageRegion
  {
  private PlanarImage image; // The image to be displayed on the component.
  private ROIShape roi; // The bounding polygon.
  private Color borderColor,fillColor; // Colors to draw/paint this polygon.
  private Stroke stroke;
  // Some convenience fields (we just need to get their values once).
  private int width,height,dimensions,numberOfPixels;
  private Rectangle polygonBounds;
  // Fields to iterate on the image.
  private RandomIter iterator;
  private float[] aPixel;
  
 /**
  * The constructor for this class, which gets a PlanarImage and a Polygon as
  * parameters.
  * @param i the PlanarImage.
  * @param r the bounding ROIShape.
  */
  public ImageRegion(PlanarImage i,ROIShape r)
    {
    image = i;
    roi = r;
    width = image.getWidth();
    height = image.getHeight();
    dimensions = image.getNumBands();
    // Get an iterator for the image.
    iterator = RandomIterFactory.create(image, null);
    aPixel = new float[dimensions];
    // Calculate the number of points on that region.
    numberOfPixels = 0;
    polygonBounds = roi.getBounds();
    // For all pixels on the polygon bounds.
    for(int h=polygonBounds.y;h<polygonBounds.y+polygonBounds.height;h++)
      for(int w=polygonBounds.x;w<polygonBounds.x+polygonBounds.width;w++)
        if (roi.contains(w,h))
          numberOfPixels++;
    // Sets some of the paint/fill values with defaults.
    borderColor = new Color(255,255,255);
    fillColor = null;
    stroke = new BasicStroke(1);
    }

 /**
  * @return Returns the image.
  */
  public PlanarImage getImage()
    {
    return image;
    }

 /**
  * Return the number of bands on the image (dimensions).
  * @return the number of bands on the image.
  */
  public int getImageBands()
    {
    return dimensions;
    }
  
 /**
  * Return the ROI shape as an instance of ROIShape.
  * @return Returns the roi.
  */
  public ROIShape getROI()
    {
    return roi;
    }

 /**
  * Return the pixels on this image which are within the polygon.
  * @return the image's pixels.
  */
  public float[][] getPixels()
    {
    float[][] pixels = new float[numberOfPixels][dimensions];
    int count=0;
    // For all pixels on the image and polygon bounds
    for(int h=polygonBounds.y;h<polygonBounds.y+polygonBounds.height;h++)
      for(int w=polygonBounds.x;w<polygonBounds.x+polygonBounds.width;w++)
        if (roi.contains(w,h))
          iterator.getPixel(w,h,pixels[count++]);
    return pixels; 
    }
  
 /**
  * Return the number of valid pixels in this region.
  * @return the number of pixels in this region.
  */
  public int getNumberOfPixels()
    {
    return numberOfPixels;
    }
  
 /**
  * Return the mean value of all pixels on the image and inside the polygon.
  * @return the mean value of that region.
  */
  public double[] getMean()
    {
    double[] mean = new double[dimensions];
    // For all pixels on the image and polygon bounds
    for(int h=polygonBounds.y;h<polygonBounds.y+polygonBounds.height;h++)
      for(int w=polygonBounds.x;w<polygonBounds.x+polygonBounds.width;w++)
        {
        // Is this point inside the polygon ?
        if (roi.contains(w,h))
          {
          // Get the array of values for the pixel on the w,h coordinate.
          iterator.getPixel(w,h,aPixel);
          for(int b=0;b<dimensions;b++) mean[b] += aPixel[b];
          }
        }
    for(int b=0;b<dimensions;b++) mean[b] /= numberOfPixels;
    return mean;
    }

 /**
  * Return the standard deviation of all pixels on the image and inside the polygon.
  * @return the standard deviation of that region.
  */
  public double[] getStdDev()
    {
    double[] mean = getMean();
    double[] stddev = new double[dimensions];
    // For all pixels on the image and polygon bounds
    for(int h=polygonBounds.y;h<polygonBounds.y+polygonBounds.height;h++)
      for(int w=polygonBounds.x;w<polygonBounds.x+polygonBounds.width;w++)
        {
        // Is this point inside the polygon ?
        if (roi.contains(w,h))
          {
          // Get the array of values for the pixel on the w,h coordinate.
          iterator.getPixel(w,h,aPixel);
          for(int b=0;b<dimensions;b++) stddev[b] += (aPixel[b]-mean[b])*(aPixel[b]-mean[b]);
          }
        }  
    for(int b=0;b<dimensions;b++) stddev[b] = Math.sqrt(stddev[b]/numberOfPixels);
    return stddev;
    }
  
  
 /**
  * Return the covariance matrix of all pixels on the image and inside the polygon.
  * @return the covariance matrix of that region.
  */
  public double[][] getCovarianceMatrix()
    {
    double[][] covar = new double[dimensions][dimensions];
    double[] mean = getMean();
    double[] diffMean = new double[dimensions];
    // For all pixels on the image and polygon bounds
    for(int h=polygonBounds.y;h<polygonBounds.y+polygonBounds.height;h++)
      for(int w=polygonBounds.x;w<polygonBounds.x+polygonBounds.width;w++)
        {
        // Is this point inside the polygon ?
        if (roi.contains(w,h))
          {
          // Get the array of values for the pixel on the w,h coordinate.
          iterator.getPixel(w,h,aPixel);
          Arrays.fill(diffMean,0);
          for(int d=0;d<dimensions;d++) diffMean[d] = aPixel[d]-mean[d];
          for(int d1=0;d1<dimensions;d1++)
            for(int d2=0;d2<dimensions;d2++)
              covar[d1][d2] += diffMean[d1]*diffMean[d2];
          }
        }
    for(int d1=0;d1<dimensions;d1++)
      for(int d2=0;d2<dimensions;d2++)
        covar[d1][d2] /= (numberOfPixels-1);
    return covar;
    }

 /**
  * Get all the extrema for all bands on the image. Extrema are returned as a 2-dimensional
  * array, each array have the minimum and maximum values for a band on the image.
  * @return the extrema of the image.
  */
  public float[][] getAllExtrema()
    {
    float[][] extrema = new float[dimensions][2];
    for(int d=0;d<dimensions;d++) 
      {
      extrema[d][0] = Float.MAX_VALUE;
      extrema[d][1] = Float.MIN_VALUE;
      }
    // For all pixels on the image and polygon bounds
    for(int h=polygonBounds.y;h<polygonBounds.y+polygonBounds.height;h++)
      for(int w=polygonBounds.x;w<polygonBounds.x+polygonBounds.width;w++)
        {
        // Is this point inside the polygon ?
        if (roi.contains(w,h))
          {
          // Get the array of values for the pixel on the w,h coordinate.
          iterator.getPixel(w,h,aPixel);
          for(int d=0;d<dimensions;d++) 
            {
            extrema[d][0] = Math.min(extrema[d][0],aPixel[d]);
            extrema[d][1] = Math.max(extrema[d][1],aPixel[d]);
            }
          }
        }
    return extrema;
    }

 /**
  * This method will paint the embedded ROIShape on a graphical context.
  * @param g2d the graphical context
  */
  public void paint(Graphics2D g2d)
    {
    if (fillColor != null)
      {
      g2d.setColor(fillColor);
      g2d.fill(roi.getAsShape());
      }
    g2d.setStroke(stroke);
    g2d.setColor(borderColor);
    g2d.draw(roi.getAsShape());
    }

 /**
  * This method allows the modification of the color used to draw the border.
  * @param borderColor the new border color
  */
  public void setBorderColor(Color borderColor)
    {
    this.borderColor = borderColor;
    }

 /**
  * This method allows the modification of the color used to fill the region.
  * @param borderColor the new fill. color
  */
  public void setFillColor(Color fillColor)
    {
    this.fillColor = fillColor;
    }

 /**
  * This method allows the modification of the stroke used to draw the border.
  * @param borderColor the new border stroke.
  */
  public void setStroke(Stroke stroke)
    {
    this.stroke = stroke;
    }
    
  }