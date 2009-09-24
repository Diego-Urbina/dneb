import nom.tam.fits.BasicHDU;
import nom.tam.fits.FitsException;

public class LectorImageHDU extends LectorHDU {
	
	public LectorImageHDU(BasicHDU bhdu) {
		super(bhdu);
	}

	@Override
	public void leerMatriz() throws FitsException {
		// Obtengo la matriz de pixeles (Data)
		short[][] arrayData = (short[][]) HDU.getData().getData();
		
		// Recorro la matriz
		for(int i = 0; i < arrayData.length; i++){
			for(int j = 0; j < arrayData[i].length; j++)
				System.out.print(arrayData[i][j] + "   ");
			System.out.println();
		}
		System.out.println("\n");
	}
	
	
	public void arreglarMatriz(short[][] m, float dataMax, float dataMin) {
		/* Simetrica de la matriz 'm' respecto del
		 * eje x. Es necesario hacer esto ya que
		 * en una imagen FITS, las coordenadas (0,0)
		 * pertenecen a la esquina inferior izquierda
		 * y por tanto la primera fila es la de abajo.
		 */
		int numFilas = m.length;
		short temp;
		for (int i=0; i<numFilas/2; i++)
			for (int j=0; j<m[i].length; j++){
				temp = m[i][j];
				m[i][j] = m[numFilas - 1 - i][j];
				m[numFilas - 1 - i][j] = temp;
			}
		
		
		/* Cambio la escala para que el valor del pixel se mueva
		 * entre 0 y 65535
		 */
		float dif = dataMax - dataMin;
		float scale = (dif) / (2*32767);
		
		for (int i=0; i<m.length; i++)
			for (int j=0; j<m[i].length; j++){
				m[i][j] = (short) ((m[i][j] - dataMin) / scale);
			}
	}

}
