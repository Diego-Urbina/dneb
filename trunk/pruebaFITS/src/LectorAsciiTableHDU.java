import nom.tam.fits.BasicHDU;
import nom.tam.fits.FitsException;


public class LectorAsciiTableHDU extends LectorHDU{
	
	// Matriz de datos
	Object[] arrayData;
	
	/* La matriz de datos es una matriz de floats, pero
	 * por algun motivo no puedo hacer casting a float[][].
	 * Haciendo debug compruebo que data esta definido como
	 * Object[] y cada componente del array como float[].
	 */
	
	public LectorAsciiTableHDU(BasicHDU bhdu) {
		super(bhdu);
	}

	@Override
	public void leerMatriz() throws FitsException {
		// Obtengo la matriz de pixeles (Data)
		arrayData =  (Object[]) HDU.getData().getData();
		
		// Recorro la matriz
		for(int i = 0; i < arrayData.length; i++){
			for(int j = 0; j < ((float[]) arrayData[0]).length; j++)
				System.out.print( ((float[]) arrayData[i])[j] + "   ");
			System.out.println();
		}
		System.out.println("\n");
		
	}

}
