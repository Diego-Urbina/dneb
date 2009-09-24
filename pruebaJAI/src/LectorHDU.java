import java.util.Iterator;
import nom.tam.fits.BasicHDU;
import nom.tam.fits.FitsException;
import nom.tam.fits.Header;
import nom.tam.fits.HeaderCard;

public abstract class LectorHDU {

	protected BasicHDU HDU;
	
	
	public BasicHDU getHDU() {
		return HDU;
	}


	public void setHDU(BasicHDU hdu) {
		HDU = hdu;
	}


	public LectorHDU(BasicHDU bHDU){
		HDU = bHDU;
	}
	
	
	/**
	 * Imprime todos los pares atributo-valor de la
	 * cabecera
	 */
	public void leerCabecera(){
		Header cabecera = HDU.getHeader();
		HeaderCard hc;
		Iterator iterator = cabecera.iterator();
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
	public abstract void leerMatriz() throws FitsException;
	
	
	/**
	 * Devuelve la matriz de datos
	 * @return
	 * @throws FitsException
	 */
	public Object getMatriz() throws FitsException {
		return HDU.getData().getData();
	}
	
	
	/**
	 * Devuelve el valor asociado a la keyword dada
	 * @param keyword
	 * @return
	 */
	public String getValue(String keyword){
		Header cabecera = HDU.getHeader();
		HeaderCard hc = cabecera.findCard(keyword);
		return hc.getValue();
	}
}
