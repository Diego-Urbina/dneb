package es.ucm.si.dneb.service.image.segmentation;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Iterator;

import javax.media.jai.PlanarImage;
import javax.media.jai.ROIShape;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.ucm.si.dneb.service.image.app.DisplayImageWithRegions;
import es.ucm.si.dneb.service.image.app.ImageRegion;


public class StarFinder {
	
	private static final  Log LOG = LogFactory.getLog(StarFinder.class);

	private ArrayList<RectStar> recuadros;
	
	public StarFinder() {
		recuadros = new ArrayList<RectStar>();
	}
	
	public ArrayList<RectStar> getRecuadros() {
		return recuadros;
	}

	public void setRecuadros(ArrayList<RectStar> recuadros) {
		this.recuadros = recuadros;
	}
	
	public int getNumberOfStars() {
		return recuadros.size();
	}
	
	public void buscarEstrellas(LectorImageHDU l, float brilloEstrella, float umbral) {
		int brillo;
		for(int i=0; i<l.getHeight(); i++)
	    	for(int j=0; j<l.getWidth(); j++) {
	    		brillo = l.getPixel(j, i);
	    		if (brillo >= brilloEstrella && !incluido(recuadros,j,i)) {
		    			RectStar r = new RectStar(j,i);
		    			r.extender(l.getArrayData(),umbral);
		    			recuadros.add(r);
	    			}    		
	    	}
	}
	
	/* Devuelve true si el pixel (x,y) esta incluido en alguno
	 * de los RectStar
	 */
	private boolean incluido(ArrayList<RectStar> recuadros, int x, int y) {
		boolean incluido = false;
		Iterator<RectStar> it = recuadros.iterator();
		while (it.hasNext() && !incluido) {
			RectStar r = it.next();		
			incluido |= r.estaDentro(x,y);
		}
		return incluido;
	}
	
	public void printRectStars(PlanarImage input, DisplayImageWithRegions display) {
		Iterator<RectStar> it = recuadros.iterator();
		while (it.hasNext()) {
			RectStar r = it.next();		
			LOG.debug(r.toString()+"\n");
			Shape s = new Rectangle(r.getxLeft(), r.getyTop(), r.getWidth(), r.getHeight());
		    ImageRegion ir = new ImageRegion(input,new ROIShape(s));
		    ir.setBorderColor(new Color(255,255,0));
		    display.addImageRegion(ir);
		}
	}
}