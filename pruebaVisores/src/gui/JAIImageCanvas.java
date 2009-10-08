package gui;
/** JAIImageCanvas Class
   * @version 1.0  18 apr 2000
   * @author Lawrence Rodrigues
   **/


import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.*;
import javax.media.jai.*;

/** A component class for drawing rendered images. This class implements the ScrollController
  * interface, so images displayed in this component can be scrolled.
  * @version 1.0  18 Apr 2000
  * @author Lawrence Rodrigues
  **/

public class JAIImageCanvas extends JComponent implements ScrollController{
   public final static int MAX_WIDTH = 2048;
   public final static int MAX_HEIGHT = 2048;
   protected int width, height;

   transient protected PlanarImage image;
   protected AffineTransform atx = new AffineTransform();

   protected boolean imageDrawn = false;

   protected int panX =0, panY =0;
   protected Point scrollAnchor  = new Point(0,0);
   protected boolean scrollOn = true;
   protected Point vpPos = new Point(0,0);
   protected Point panOffset = new Point(0,0);

   public JAIImageCanvas() { }
   public JAIImageCanvas(PlanarImage img){setImage(img);}
   public synchronized void setImage(PlanarImage img){
      reset();
      image = img;
      int wid = img.getWidth();
      int ht =  img.getHeight();
      width =  (wid > MAX_WIDTH ) ? MAX_WIDTH : wid;
      height =  (ht > MAX_WIDTH ) ? MAX_WIDTH : ht;
      setPreferredSize(new Dimension(width, height));
      imageDrawn = false;
      repaint();
   }
   public PlanarImage getImage(){ return image;}
   public PlanarImage getDisplayImage(){return image;}

   public boolean isImageDrawn(){ return imageDrawn;}

   public void setPanOffset(Point panOffset){
      firePropertyChange("PanOffset",this.panOffset,panOffset);
      this.panOffset = panOffset;
      panX = panOffset.x;
      panY = panOffset.y;
   }

   public Point getPanOffset(){ return panOffset;}

   public void setScrollOn(boolean onOff){
      scrollOn = onOff;
      panX =0; panY =0;
      vpPos = new Point(panX, panY);
   }

   public  boolean getScrollOn(){ return scrollOn;}

   public void startScroll(int x, int y){
      scrollAnchor.x = x- panX;
      scrollAnchor.y = y- panY;
      setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
      repaint();
   }

   public void scroll(int x, int y){
      if((x <0 )|| (y<0)) return;
      int panx = x-scrollAnchor.x;
      int pany = y-scrollAnchor.y;
      setPanOffset(new Point(panx, pany));
      setViewportPosition(new Point(-panx, -pany));
      repaint();
   }

   public void stopScroll(){ setCursor(Cursor.getDefaultCursor());}

   public void pan(double x, double y){
      setPanOffset(new Point((int)x, (int)y));
      repaint();
   }

   public Point getViewportPosition(){return vpPos;}

   public void setViewportPosition(Point vpPos){
      firePropertyChange("viewportPosition",this.vpPos, vpPos);
      this.vpPos = vpPos;
   }

   public void setTransform(AffineTransform at){atx = at;}
   public AffineTransform getTransform(){ return atx;};

   public void reset(){
      atx = new AffineTransform();
      panX =0; panY =0;
   }

   public void paintComponent(Graphics gc){
      Graphics2D g = (Graphics2D)gc;
      Rectangle rect = this.getBounds();
      if((width != rect.width) || (height != rect.height)){
         double magx = rect.width/(double)width ;
         double magy = rect.height/(double)height ;
      }
      g.setColor(Color.black);
      g.fillRect(0,0,rect.width, rect.height);
      atx =  AffineTransform.getTranslateInstance(panX,panY);
      if(image != null) g.drawRenderedImage(image, atx);
      imageDrawn = true;
   }
}
