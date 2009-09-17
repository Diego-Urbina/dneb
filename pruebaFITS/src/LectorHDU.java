import java.util.Iterator;
import nom.tam.fits.BasicHDU;
import nom.tam.fits.FitsException;
import nom.tam.fits.Header;
import nom.tam.fits.HeaderCard;





public abstract class LectorHDU {

	BasicHDU HDU;
	
	public LectorHDU(BasicHDU bHDU){
		HDU = bHDU;
	}
	
	// Obtengo la cabecera (Header) y usando un 
	//iterador miro todos los pares atributo-valor
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
	
	public abstract void leerMatriz() throws FitsException;
}
