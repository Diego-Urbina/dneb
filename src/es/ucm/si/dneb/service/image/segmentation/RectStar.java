package es.ucm.si.dneb.service.image.segmentation;

public class RectStar {
	
	private int xLeft, xRight, yTop, yBot;
	
	public RectStar(int x, int y) {
		xLeft = x;
		xRight = x;
		yTop = y;
		yBot = y;
	}

	public int getxLeft() {
		return xLeft;
	}

	public void setxLeft(int xLeft) {
		this.xLeft = xLeft;
	}

	public int getxRight() {
		return xRight;
	}

	public void setxRight(int xRight) {
		this.xRight = xRight;
	}

	public int getyTop() {
		return yTop;
	}

	public void setyTop(int yTop) {
		this.yTop = yTop;
	}

	public int getyBot() {
		return yBot;
	}

	public void setyBot(int yBot) {
		this.yBot = yBot;
	}
	
	public int getWidth() {
		return xRight - xLeft + 1;
	}
	
	public int getHeight() {
		return yBot - yTop + 1;
	}
	
	public int getArea() {
		return getWidth() * getHeight();
	}
	
	public void extender(int[][] imagen, float umbral) {
		boolean extendido = false;
		
		int i=yTop;
		while (i<=yBot && !extendido) {
			int j=xLeft;
			while (j<=xRight && !extendido) {
				if (esBorde(j,i))
					extendido = extenderArriba(i,j,imagen,umbral) || extenderAbajo(i,j,imagen,umbral) || 
								extenderIzq(i,j,imagen,umbral) || extenderDer(i,j,imagen,umbral);
				j++;
			}
			i++;
		}
		
		// Si el rectangulo ha sido extendido, volvemos a intentar extenderlo
		// mediante recursividad. Si no, hemos terminado
		if (extendido) extender(imagen,umbral);
	}
	
	public boolean estaDentro(int x, int y) {
		return (y >= yTop) && (y <= yBot) && (x >= xLeft) && (x <= xRight);
	}
	
	private boolean esBorde(int x, int y) {
		return (y == yTop) || (y == yBot) || (x == xLeft) || (x == xRight);
	}
	
	private boolean extenderArriba(int i, int j, int[][] imagen, float umbral) {
		if (yTop == 0)
			return false;
		else {
			while (i != 0 && imagen[i-1][j] > umbral) i--;
			if (i < yTop) { yTop = i; return true; }
			else return false;
		}		
	}
	
	private boolean extenderAbajo(int i, int j, int[][] imagen, float umbral) {
		if (yBot == imagen.length-1)
			return false;
		else {
			while (i != imagen.length-1 && imagen[i+1][j] > umbral) i++;
			if (i > yBot) { yBot = i; return true; }
			else return false;
		}		
	}
	
	private boolean extenderIzq(int i, int j, int[][] imagen, float umbral) {
		if (xLeft == 0)
			return false;
		else {
			while (j != 0 && imagen[i][j-1] > umbral) j--;
			if (j < xLeft) { xLeft = j; return true; }
			else return false;
		}		
	}
	
	private boolean extenderDer(int i, int j, int[][] imagen, float umbral) {
		if (xRight == imagen[0].length-1)
			return false;
		else {
			while (j != imagen[0].length-1 && imagen[i][j+1] > umbral) j++;
			if (j > xRight) { xRight = j; return true; }
			else return false;		
		}		
	}

	@Override
	public String toString() {
		return "Esquina sup izq: (" +  xLeft + "," + yTop + ")\n" + 
			   "Esquina inf der: (" + xRight + "," + yBot + ")";
	}
	

}
