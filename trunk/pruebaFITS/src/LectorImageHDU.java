import nom.tam.fits.BasicHDU;
import nom.tam.fits.FitsException;


public class LectorImageHDU extends LectorHDU {

	// Matriz de pixeles
	short[][] arrayData;
	
	public LectorImageHDU(BasicHDU bhdu) {
		super(bhdu);
	}

	@Override
	public void leerMatriz() throws FitsException {
		// Obtengo la matriz de pixeles (Data)
		arrayData = (short[][]) HDU.getData().getData();
		
		// Recorro la matriz
		for(int i = 0; i < arrayData.length; i++){
			for(int j = 0; j < arrayData[0].length; j++)
				System.out.print(arrayData[i][j] + "   ");
			System.out.println();
		}
		System.out.println("\n");
	}

}
