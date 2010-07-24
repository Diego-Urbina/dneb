package es.ucm.si.dneb.service.image.segmentation;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


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
	
	public void escalarYTrasladarRectangulos(int scale, int porcentajeZoom) {
		Iterator<RectStar> it = recuadros.iterator();
		RectStar r;
		float antiguo;
		while (it.hasNext()) {
			r = it.next();
			
			antiguo = (r.getyTop() * 100) / (scale + porcentajeZoom);
			if (porcentajeZoom > 0) // zoom -
				r.setyTop(Math.round(antiguo * scale/100));
			else // zoom +
				r.setyTop((int) Math.ceil(antiguo * scale/100));

			antiguo = (r.getyBot() * 100) / (scale + porcentajeZoom);
			if (porcentajeZoom > 0) // zoom -
				r.setyBot(Math.round(antiguo * scale/100));
			else // zoom +
				r.setyBot((int) Math.ceil(antiguo * scale/100));
			
			antiguo = (r.getxLeft() * 100) / (scale + porcentajeZoom);
			if (porcentajeZoom > 0) // zoom -
				r.setxLeft(Math.round(antiguo * scale/100));
			else // zoom +
				r.setxLeft((int) Math.ceil(antiguo * scale/100));
			
			antiguo = (r.getxRight() * 100) / (scale + porcentajeZoom);
			if (porcentajeZoom > 0) // zoom -
				r.setxRight(Math.round(antiguo * scale/100));
			else // zoom +
				r.setxRight((int) Math.ceil(antiguo * scale/100));
		}
	}
	
	public void printRectStars() {
		Iterator<RectStar> it = recuadros.iterator();
		while (it.hasNext()) {
			RectStar r = it.next();		
			LOG.debug(r.toString()+"\n");
		}
	}
	
	public void eliminarRecuadros() {
		recuadros.clear();
	}
}
