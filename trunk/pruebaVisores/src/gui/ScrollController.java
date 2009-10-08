package gui;
/* @(#) ScrollController.java 1.1 99/01/11
 * Copyright (c) 1999 Lawrence Rodrigues
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.beans.*;
/** Specifies methods to scroll an image on a canvas.
  * @version 1.0  1 Nov 1999
  * @author Lawrence Rodrigues
  **/
 public interface ScrollController {
    /** Gets the panOffset property.
      * @param panOffset the offset by which the currently displayed image is moved
      * from the previous position. 
      */
    public void setPanOffset(Point panOffset);

    /** Returns the panOffset property. 
      * @return the panOffset.
      */
    public Point getPanOffset();
      /**Starts the scroll and sets the anchor point.
     * @param x the x coordinate of the scroll anchor.
     * @param y the y coordinate of the scroll anchor.
     **/
    public void startScroll(int x, int y);

    /**Scrolls the image.
     * @param x the x coordinate of the current position.
     * @param y the y coordinate of the current position.
     **/
    public void scroll(int x, int y);

    /** Stops scroll.
      */
    public void stopScroll();
 }