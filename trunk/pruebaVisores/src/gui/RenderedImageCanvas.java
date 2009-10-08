package gui;
/** RenderedImageCanavs Class
   * @version 1.0  18 apr 2000
   * @author Lawrence Rodrigues
   **/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.image.renderable.*;
import javax.media.jai.*;

/** A component class for drawing large rendered images. This class has several properties
  * to represent different attributes of a rendered image. These properties include
  * tile width, tile height, current transformation and color model.
  * When a new image is set, it is reformatted
  * using the "format" operator with the current tile width and height settings.
  *
  * This class implements the ScrollController
  * interface, so images displayed in this component can be scrolled. To scroll a large image,
  * only the tiles that are visible are computed. These tiles are converted into
  * a BufferedImage object, which are then drawn over the components graphics context.
  * @version 1.0 18 Apr 2000
  * @author Lawrence Rodrigues
  **/

public class RenderedImageCanvas extends JAIImageCanvas {
   protected int viewerWidth = 480, viewerHeight = 400;

   transient protected PlanarImage displayImage, origImage;
   protected int tileWidth = 256, tileHeight = 256;
   transient protected SampleModel sampleModel;
   protected ColorModel colorModel;

   protected int maxTileIndexX, maxTileIndexY;
   protected int maxTileCordX, maxTileCordY;
   protected int minTileIndexX, minTileIndexY;
   protected int minTileCordX, minTileCordY;
   protected int tileGridXOffset,tileGridYOffset;
   protected int imageWidth =0,imageHeight =0;
   protected TileCache tc;

   public RenderedImageCanvas() { }

   /** The RenderedImageCanvas constructor.
     * @param img the planar image.
     */

   public RenderedImageCanvas(PlanarImage img){
      this();
      setImage(img);
   }

   /** Sets the image.
     * @param img the planar image.
     */
   public void setImage(PlanarImage img){
      origImage = img;
      panX =0; panY =0;
      atx = AffineTransform.getTranslateInstance(0.0, 0.0);
      RenderedOp op = makeTiledImage(img);
      displayImage = op.createInstance();
      sampleModel = displayImage.getSampleModel();
      colorModel = displayImage.getColorModel();
      getTileInfo(displayImage);
      fireTilePropertyChange();
      imageDrawn = false;
      repaint();
   }

   /** Returns the displayed image.
     * @return the displayed image.
     */
   public PlanarImage getDisplayImage(){return displayImage;}

   protected RenderedOp makeTiledImage(PlanarImage img) {
      ImageLayout tileLayout = new ImageLayout(img);
      tileLayout.setTileWidth(tileWidth);
      tileLayout.setTileHeight(tileHeight);
      RenderingHints tileHints = new RenderingHints(JAI.KEY_IMAGE_LAYOUT, tileLayout);
      ParameterBlock pb = new ParameterBlock();
      pb.addSource(img);
      return JAI.create("format", pb, tileHints);
   }

   /** Computes tile information of the specified image.
     * @param img the planar image.
     */
   protected void getTileInfo(PlanarImage img) {
      imageWidth = img.getWidth();
      imageHeight = img.getHeight();
      tileWidth = img.getTileWidth();
      tileHeight = img.getTileHeight();
      maxTileIndexX = img.getMinTileX()+img.getNumXTiles()-1;
      maxTileIndexY = img.getMinTileY()+img.getNumYTiles()-1;
      maxTileCordX = img.getMaxX();
      maxTileCordY = img.getMaxY();
      minTileIndexX = img.getMinTileX();
      minTileIndexY = img.getMinTileY();
      minTileCordX = img.getMinX();
      minTileCordY = img.getMinY();
      tileGridXOffset = img.getTileGridXOffset();
      tileGridYOffset = img.getTileGridYOffset();
   }

   /** Sets the tile width of the formatted image.
     * @param int the tile width.
     */
   public void setTileWidth(int tw){
      tileWidth = tw;
      setImage(displayImage);
   }

   /** Sets the tile height of the formatted image.
     * @param int the tile height.
     */

   public void setTileHeight(int th){
      tileHeight = th;
      setImage(displayImage);
   }

   /** Returns the tile width of the formatted image.
     * @return the tile width of the formatted image.
     */
   public int getTileWidth(){ return tileWidth;}

   /** Returns the tile height of the formatted image.
     * @return the tile height of the formatted image.
     */
   public int getTileHeight(){ return tileHeight;}

   /** Returns the maxTileIndexX property of the current image.
     * @return the maxTileIndexX property of the current image.
     */
   public int getMaxTileIndexX(){return maxTileIndexX;}

   /** Returns the maxTileIndexY property of the current image.
     * @return the maxTileIndexY property of the current image.
     */
   public int getMaxTileIndexY(){return maxTileIndexY;}

   /** Returns the width of the current image.
     * @return the width of the current image.
     */
   public int getImageWidth(){return imageWidth;}

   /** Returns the height of the current image.
     * @return the height of the current image.
     */
   public int getImageHeight(){return imageHeight;}

   /** Fires property change events for the following properties:
     * maxTileIndexX, maxTileIndexY,tileWidth,tileHeight, and transform.
     */
   protected void fireTilePropertyChange() {
      firePropertyChange("maxTileIndexX",null, new Integer(maxTileIndexX));
      firePropertyChange("maxTileIndexY",null, new Integer(maxTileIndexY));
      firePropertyChange("tileWidth",null, new Integer(tileWidth));
      firePropertyChange("tileHeight",null, new Integer(tileWidth));
      firePropertyChange("transform", null, atx);
   }

   /** Paints the tiles that are visible.
     * This method first computes only the tiles that are visible. Each of the tiles is converted into
     * a BufferedImage object, which is then drawn over the components graphics context.
     * @param gc the graphics context.
     */
   public void paintComponent(Graphics gc){
      Graphics2D g = (Graphics2D)gc;
      Rectangle rect = this.getBounds();
      if((viewerWidth != rect.width) || (viewerHeight != rect.height)){
         viewerWidth = rect.width;
         viewerHeight = rect.height;
      }
      g.setColor(Color.black);
      g.fillRect(0,0,viewerWidth, viewerHeight);
      if(displayImage == null) return;
      int ti =0,tj = 0;
      Rectangle bounds = new Rectangle(0,0,rect.width, rect.height);
      bounds.translate(-panX, -panY);

      int leftIndex =  displayImage.XToTileX(bounds.x);
      if(leftIndex <  minTileIndexX) leftIndex = minTileIndexX;
      if(leftIndex > maxTileIndexX) leftIndex = maxTileIndexX;

      int rightIndex = displayImage.XToTileX(bounds.x + bounds.width - 1);
      if(rightIndex <  minTileIndexX) rightIndex = minTileIndexX;
      if(rightIndex > maxTileIndexX) rightIndex = maxTileIndexX;

      int topIndex = displayImage.YToTileY(bounds.y);
      if(topIndex <  minTileIndexY) topIndex = minTileIndexY;
      if(topIndex > maxTileIndexY) topIndex = maxTileIndexY;

      int bottomIndex = displayImage.YToTileY(bounds.y + bounds.height - 1);
      if(bottomIndex <  minTileIndexY) bottomIndex = minTileIndexY;
      if(bottomIndex > maxTileIndexY) bottomIndex = maxTileIndexY;

      for(tj = topIndex; tj <= bottomIndex; tj++) {
          for(ti = leftIndex; ti <= rightIndex; ti++) {
              Raster tile = displayImage.getTile(ti, tj);
              DataBuffer dataBuffer = tile.getDataBuffer();
              WritableRaster wr = tile.createWritableRaster(sampleModel,
                                            dataBuffer, new Point(0,0));
              BufferedImage bi = new BufferedImage(colorModel,
                                            wr,
                                            colorModel.isAlphaPremultiplied(),
                                            null);
              if(bi == null) continue;
              int xInTile = displayImage.tileXToX(ti);
              int yInTile = displayImage.tileYToY(tj);
              AffineTransform tx = AffineTransform.getTranslateInstance(xInTile+panX,
                                                                     yInTile+panY);
              g.drawRenderedImage(bi, tx);
          }
      }
      imageDrawn = true;
   }
}
