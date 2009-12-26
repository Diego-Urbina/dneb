package es.ucm.si.dneb.test;

import org.junit.Test;

import es.ucm.si.dneb.service.image.centroid.CalculateBookCentroid;
import es.ucm.si.dneb.service.image.util.Point;

public class CentroidTest {
	
	
	@Test
	public void testBookCentroid(){
		
		int puntos [][] = new int [7][7];
		
		puntos[0][0]=10;
		puntos[0][1]=11;
		puntos[0][2]=13;
		puntos[0][3]=20;
		puntos[0][4]=20;
		puntos[0][5]=17;
		puntos[0][6]=14;
		

		puntos[1][0]=10;
		puntos[1][1]=13;
		puntos[1][2]=24;
		puntos[1][3]=39;
		puntos[1][4]=38;
		puntos[1][5]=25;
		puntos[1][6]=17;
		

		puntos[2][0]=12;
		puntos[2][1]=26;
		puntos[2][2]=85;
		puntos[2][3]=152;
		puntos[2][4]=116;
		puntos[2][5]=44;
		puntos[2][6]=21;
	

		puntos[3][0]=14;
		puntos[3][1]=32;
		puntos[3][2]=108;
		puntos[3][3]=190;
		puntos[3][4]=139;
		puntos[3][5]=52;
		puntos[3][6]=24;
		

		puntos[4][0]=12;
		puntos[4][1]=24;
		puntos[4][2]=64;
		puntos[4][3]=101;
		puntos[4][4]=73;
		puntos[4][5]=38;
		puntos[4][6]=24;
		
		puntos[5][0]=10;
		puntos[5][1]=13;
		puntos[5][2]=22;
		puntos[5][3]=30;
		puntos[5][4]=27;
		puntos[5][5]=21;
		puntos[5][6]=19;

		puntos[6][0]=9;
		puntos[6][1]=10;
		puntos[6][2]=11;
		puntos[6][3]=10;
		puntos[6][4]=12;
		puntos[6][5]=13;
		puntos[6][6]=13;

		CalculateBookCentroid calculateBookCentroid= new CalculateBookCentroid();
		Point point =calculateBookCentroid.giveMeTheCentroid(puntos);
		System.out.println("X:"+point.getX());
		System.out.println("Y:"+point.getY());
		
	}

}
