import java.util.ArrayList;
import java.util.Iterator;


public class StarFinder {

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
	
	public void printRectStars() {
		Iterator<RectStar> it = recuadros.iterator();
		while (it.hasNext()) {
			RectStar r = it.next();		
			System.out.println(r.toString()+"\n");
		}
	}
}
