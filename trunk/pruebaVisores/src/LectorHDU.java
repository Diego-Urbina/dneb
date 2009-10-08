import java.util.Iterator;
import nom.tam.fits.BasicHDU;
import nom.tam.fits.FitsException;
import nom.tam.fits.Header;
import nom.tam.fits.HeaderCard;

public abstract class LectorHDU {

	protected Header header;


	public LectorHDU(Header h){
		header = h;
	}
	
	
		
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}





	/**
	 * Imprime todos los pares atributo-valor de la
	 * cabecera
	 */
	public void printHeader(){
		HeaderCard hc;
		Iterator iterator = header.iterator();
		int i = 1;
		while (iterator.hasNext()){
			hc = (HeaderCard) iterator.next();
			if (hc == null) break; // El final del header puede ser null por el relleno
			System.out.println(i + " - " + hc.getKey() + " = " + hc.getValue() + 
					"  --->  " + hc.getComment());
			i++;
		}
		System.out.println("\n");
	}
	
	
	/**
	 * Imprime la matriz de datos
	 * @throws FitsException
	 */
	public abstract void printArray() throws FitsException;
	
	
	/**
	 * Devuelve el valor asociado a la keyword dada
	 * @param keyword
	 * @return
	 */
	public String getValue(String keyword){
		HeaderCard hc = header.findCard(keyword);
		return hc.getValue();
	}
}
