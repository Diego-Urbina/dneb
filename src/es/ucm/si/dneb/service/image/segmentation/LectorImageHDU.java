package es.ucm.si.dneb.service.image.segmentation;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.FitsException;

public class LectorImageHDU extends LectorHDU {
	
	private int[][] arrayData;
	private double min, max, scale;
	private int width;
	private int height;
	private String filename;
	private DescriptiveStatistics calculosEstadisticos;
	
	public LectorImageHDU(BasicHDU bhdu, String f) {
		super(bhdu.getHeader());
		try {
			calculosEstadisticos = new DescriptiveStatistics();
			short[][] arrayDataAux = (short[][]) bhdu.getData().getData();
			min = Double.parseDouble(super.getValue("DATAMIN"));
			max = Double.parseDouble(super.getValue("DATAMAX"));
			scale = (max-min)/65535;
			width = Integer.parseInt(super.getValue("NAXIS1"));
			height = Integer.parseInt(super.getValue("NAXIS2"));
			filename = f;  			
			
			arreglarMatriz(arrayDataAux);
		} catch (FitsException e) {
			e.printStackTrace();
		}
	}
	
	// Getters and setters
	public int[][] getArrayData() {
		return arrayData;
	}

	public void setArrayData(int[][] arrayData) {
		this.arrayData = arrayData;
	}
	

	public double getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}


	public double getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public void printArrayData() throws FitsException {
		// Obtengo la matriz de pixeles (Data)
		
		// Recorro la matriz
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++)
				System.out.print(arrayData[i][j] + "   ");
			System.out.println();
		}
		System.out.println("\n");
	}	
	
	private void arreglarMatriz(short[][] arrayDataAux) {
		/* Simetrica de la matriz respecto del
		 * eje x. Es necesario hacer esto ya que
		 * en una imagen FITS, las coordenadas (0,0)
		 * pertenecen a la esquina inferior izquierda
		 * y por tanto la primera fila es la de abajo.
		 */
		short temp;
		for (int i=0; i<height/2; i++)
			for (int j=0; j<width; j++){
				temp = arrayDataAux[i][j];
				arrayDataAux[i][j] = arrayDataAux[height - 1 - i][j];
				arrayDataAux[height - 1 - i][j] = temp;
			}
		
		
		/* Cambio la escala para que el valor del pixel se mueva
		 * entre 0 y 65535
		 */		
		for (int i=0; i<height; i++)
			for (int j=0; j<width; j++){
				arrayDataAux[i][j] = (short) ((arrayDataAux[i][j] - min) / scale);
			}
		
		// Actualizo min, max y scale por haber cambiado la escala
		max = (max - min) / scale;
		min = (min - min) / scale;
		scale = (max-min)/65535;
		
		/* Almaceno los pixeles en un array de int para poder cubrir
		 * el rango 0 - 65535. Los valores negativos se corresponden con
		 * los mayores de 32767 y hay que transformarlos.
		 */
		arrayData = new int[height][width];
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++) {
				short aux = arrayDataAux[i][j];
				if (aux < 0)
					arrayData[i][j] = aux + 65536;
				else
					arrayData[i][j] = aux;
				
				calculosEstadisticos.addValue(arrayData[i][j]);
			}
	}
	
	public double brilloMedio(){
	    return calculosEstadisticos.getMean();
    }
	
	public double getNPercentile(double n) {
		return calculosEstadisticos.getPercentile(n);
	}
	
	public int getPixel(int x, int y) {
		return arrayData[y][x];
	}
	
	public int[][] getPorcionImagen(int xLeft, int yTop, int width, int height) {
		int[][] rect = new int[height][width];
		
		for (int i = 0; yTop + i < arrayData.length && i < height; i++)
			for (int j = 0; xLeft + j < arrayData[i].length && j < width; j++)
				rect[i][j] = arrayData[yTop + i][xLeft + j];
				
		return rect;
	}
}
