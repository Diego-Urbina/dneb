import nom.tam.fits.BasicHDU;
import nom.tam.fits.FitsException;


public class LectorImageHDU extends LectorHDU {

	// Matriz de pixeles
	private short[][] arrayData;
	private short brilloMedio=-1;
	private short brilloMaximo=-1;
	private short brilloMinimo=-1;
	




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
	//Devuelve el brillo medio
	private short brilloMedio(){
		
		long suma=0;
		
		for(int i = 0; i < arrayData.length; i++){
			for(int j = 0; j < arrayData[0].length; j++){
				suma+=arrayData[i][j];
			}
		}
		return (short) (suma/((arrayData.length)*(arrayData[0].length)));
	}
	
	private short brilloMaximo(){
		
		short maximo=-1;
		
		for(int i = 0; i < arrayData.length; i++){
			for(int j = 0; j < arrayData[0].length; j++){
				if(arrayData[i][j]>maximo){
					maximo=arrayData[i][j];
				}
			}
		}
		return maximo;
	}
	
	private short brilloMinimo(){
		
		short minimo=-1;
		
		for(int i = 0; i < arrayData.length; i++){
			for(int j = 0; j < arrayData[0].length; j++){
				if(arrayData[i][j]<minimo){
					minimo=arrayData[i][j];
				}
			}
		}
		return minimo;
	}
	
	public short[][] getArrayData() {
		return arrayData;
	}

	public void setArrayData(short[][] arrayData) {
		this.arrayData = arrayData;
	}


	public short getBrilloMedio() {
		//Si el brillo no se ha solitado todavía lo calculo, si ya se ha calculado devuelvo el valor
		if(brilloMedio==-1){
			brilloMedio= brilloMedio();
			return brilloMedio;
		}else{
			return brilloMedio;
		}
		
	}

	public short getBrilloMaximo() {
		
		if(brilloMaximo==-1){
			brilloMaximo= brilloMaximo();
			return brilloMaximo;
		}else{
			return brilloMaximo;
		}
	}

	public short getBrilloMinimo() {
		
		if(brilloMinimo==-1){
			brilloMinimo= brilloMinimo();
			return brilloMinimo;
		}else{
			return brilloMinimo;
		}
	}
	
	
	
}
