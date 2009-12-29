package operators;

import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import com.sun.media.jai.widget.DisplayJAI;

/**
 * This class demonstrates the use of the crop operator.
 */
public class Crop
  {
  public static void main(String[] args)
    {
    // We need five arguments: the image filename and four values that
    // define the area for cropping: x, y, width and height.

    // Parse the parameters.
    float x = 10;
    float y = 10;
    float width = 100;
    float height = 100;
    // Read the image.
    PlanarImage input = JAI.create("fileload", "kk.png");
    // Create a ParameterBlock with information for the cropping.
    ParameterBlock pb = new ParameterBlock();
    pb.addSource(input);
    pb.add(x);
    pb.add(y);
    pb.add(width);
    pb.add(height);
    // Create the output image by cropping the input image.
    PlanarImage output = JAI.create("crop",pb,null);
    // A cropped image will have its origin set to the (x,y) coordinates,
    // and with the display method we use it will cause bands on the top
    // and left borders. A simple way to solve this is to shift or
    // translate the image by (-x,-y) pixels.
    pb = new ParameterBlock();
    pb.addSource(output);
    pb.add(-x);
    pb.add(-y);
    // Create the output image by translating itself.
    output = JAI.create("translate",pb,null);
    // Create a JFrame for displaying the results.
    JFrame frame = new JFrame();
    frame.setTitle("Cropping image kk.png"+
                   " starting at ("+x+","+y+"), size "+width+" x "+height);
    // Add to the JFrame's ContentPane an instance of DisplayJAI with the
    // processed image.
    frame.getContentPane().add(new JScrollPane(new DisplayJAI(output)));
    // Save the image on a file.
    JAI.create("filestore",output,"cropped.tif","TIFF");
    // Set the closing operation so the application is finished.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack(); // adjust the frame size using preferred dimensions.
    frame.setVisible(true); // show the frame.
    }
  }

