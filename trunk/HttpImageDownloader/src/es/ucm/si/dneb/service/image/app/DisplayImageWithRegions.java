
package es.ucm.si.dneb.service.image.app;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.DataBuffer;
import java.awt.image.IndexColorModel;
import java.awt.image.RenderedImage;
import java.util.ArrayList;
import javax.media.jai.iterator.RandomIter;
import javax.media.jai.iterator.RandomIterFactory;
import com.sun.media.jai.widget.DisplayJAI;


/**
 * This class can be used to display images with associated ROIs (instances of ImageRegion).
 * The ROIs are stored in a list and its shapes/color/styles will be painted over a base image.
 * There are other nifty features on the class too.
 */
public class DisplayImageWithRegions extends DisplayJAI
  {
  protected ArrayList<ImageRegion> ROIs; // a list of ROIs that will be (non-interactively) drawn.
  protected StringBuffer pixelInfo; // the pixel information (formatted as a StringBuffer).
  private double[] dpixel; // the pixel information as an array of doubles.
  private int[] ipixel; // the pixel information as an array of integers.
  private boolean isDoubleType; // indicates which of the above arrays we will use.
  private RandomIter readIterator; // a RandomIter that allow us to get the data on a single pixel.
  private boolean isIndexed; // true if the image has a indexed color model.
  private short[][] lutData; // will contain the look-up table data if isIndexed is true.
  protected int width,height; // the dimensions of the image

 /**
  * The constructor of the class, which creates the arrays and instances needed
  * to obtain the image data and registers the class to listen to mouse motion
  * events.
  * @param image a RenderedImage for display
  */
  public DisplayImageWithRegions(RenderedImage image)
    {
    super(image); // calls the constructor for DisplayJAI
    readIterator = RandomIterFactory.create(image, null); // creates the iterator
    // Get some data about the image
    width = image.getWidth();
    height = image.getHeight();
    int dataType = image.getSampleModel().getDataType(); // gets the data type
    switch(dataType)
      {
      case DataBuffer.TYPE_BYTE:
      case DataBuffer.TYPE_SHORT:
      case DataBuffer.TYPE_USHORT:
      case DataBuffer.TYPE_INT: isDoubleType = false; break;
      case DataBuffer.TYPE_FLOAT:
      case DataBuffer.TYPE_DOUBLE: isDoubleType = true; break;
      }
    // Depending on the image data type, allocate the double or the int array.
    if (isDoubleType) dpixel = new double[image.getSampleModel().getNumBands()];
    else ipixel = new int[image.getSampleModel().getNumBands()];
    // Is the image color model indexed ?
    isIndexed = (image.getColorModel() instanceof IndexColorModel);
    if (isIndexed)
      {
      // Retrieve the index color model of the image.
      IndexColorModel icm = (IndexColorModel)image.getColorModel();
      // Get the number of elements in each band of the colormap.
      int mapSize = icm.getMapSize();
      // Allocate an array for the lookup table data.
      byte[][] templutData = new byte[3][mapSize];
      // Load the lookup table data from the IndexColorModel.
      icm.getReds(templutData[0]);
      icm.getGreens(templutData[1]);
      icm.getBlues(templutData[2]);
      // Load the lookup table data into a short array to avoid negative numbers.
      lutData = new short[3][mapSize];
      for(int entry=0;entry<mapSize;entry++)
        {
        lutData[0][entry] = templutData[0][entry] > 0 ? templutData[0][entry] : (short)(templutData[0][entry]+256);
        lutData[1][entry] = templutData[1][entry] > 0 ? templutData[1][entry] : (short)(templutData[1][entry]+256);
        lutData[2][entry] = templutData[2][entry] > 0 ? templutData[2][entry] : (short)(templutData[2][entry]+256);
        }
      }
    // Registers the mouse motion listener.
    addMouseMotionListener(this);
    // Create the StringBuffer instance for the pixel information.
    pixelInfo = new StringBuffer(50);
    // Create the list that will hold the ROIs.
    ROIs = new ArrayList<ImageRegion>();
    }

 /**
  * This method will be called when the mouse is moved over the image being displayed.
  * @param me the mouse event that caused the execution of this method.
  */
  public void mouseMoved(MouseEvent me)
    {
    pixelInfo.setLength(0); // clear the StringBuffer
    int x = me.getX();
    int y = me.getY();
    if ((x >= width) || (y >= height))
      {
      pixelInfo.append("No data!");
      return;
      }
    if (isDoubleType) // process the pixel as an array of double values
      {
      pixelInfo.append("(floating-point data) ");
      readIterator.getPixel(me.getX(),me.getY(),dpixel); // read the pixel
      for(int b=0;b<dpixel.length;b++) pixelInfo.append(dpixel[b]+","); // append to the StringBuffer
      pixelInfo = pixelInfo.deleteCharAt(pixelInfo.length()-1); // erase last comma
      }
    else // pixel type is not floating point, will be processed as integers.
      {
      if (isIndexed) // if color model is indexed
        {
        pixelInfo.append("(integer data with colormap) ");
        readIterator.getPixel(me.getX(),me.getY(),ipixel); // read the pixel
        // Assume ipixel.length = 1
        pixelInfo.append("Index: "+ipixel[0]);
        // Add also the RGB entry from the LUT.
        pixelInfo.append(" RGB:"+lutData[0][ipixel[0]]+","+lutData[1][ipixel[0]]+","+lutData[2][ipixel[0]]);
        }
      else // pixels are of integer type, but not indexed
        {
        pixelInfo.append("(integer data) ");
        readIterator.getPixel(me.getX(),me.getY(),ipixel); // read the pixel
        for(int b=0;b<ipixel.length;b++) pixelInfo.append(ipixel[b]+","); // append to the StringBuffer
        pixelInfo = pixelInfo.deleteCharAt(pixelInfo.length()-1); // erase last comma
        }
      }
    } // end of method mouseMoved
  
 /**
  * This method allows external classes access to the pixel info which was
  * obtained in the mouseMoved method.
  * @return the pixel information, formatted as a string
  */
  public String getPixelInfo()
    {
    return pixelInfo.toString();
    }

 /**
  * This method paints the component, calling the paint method of the ancestral
  * class and then painting all ROI shapes.
  */
  public void paint(Graphics g)
    {
    super.paint(g);
    Graphics2D g2d = (Graphics2D)g;
    // Paints each annotation on the list.
    for (int a=0;a<ROIs.size();a++)
      {
      ROIs.get(a).paint(g2d);
      }
    }

 /**
  * This method adds an instance of the ImageRegion class to the list of ROIs which 
  * will be painted on the image.
  */
  public void addImageRegion(ImageRegion a)
    {
    ROIs.add(a);
    }

 /**
  * This method returns the number of ROIs being used by the instance.
  */
  public int ROIsCount()
    {
    return ROIs.size();
    }
  
  }