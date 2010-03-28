package es.ucm.si.dneb.service.busquedaDobles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import Jama.Matrix;

import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.ParamProcTarea;
import es.ucm.si.dneb.domain.ProcImagen;
import es.ucm.si.dneb.service.gestionProcesamientos.ServicioGestionProcesamientosException;
import es.ucm.si.dneb.service.image.centroid.CalculateBookCentroid;
import es.ucm.si.dneb.service.image.segmentation.LectorImageHDU;
import es.ucm.si.dneb.service.image.segmentation.RectStar;
import es.ucm.si.dneb.service.image.segmentation.StarFinder;
import es.ucm.si.dneb.service.image.util.Point;
import es.ucm.si.dneb.service.math.DecimalCoordinate;

@Service("serviceBusquedaDobles")
public class ServiceBusquedaDoblesImpl implements ServiceBusquedaDobles{
	
	@PersistenceContext
	private EntityManager manager;
	
	
	private static final Log LOG = LogFactory
	.getLog(ServiceBusquedaDoblesImpl.class);
		
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void iniciarProcesamiento(List<ProcImagen> procImgs) {
		
		/* Algoritmo de busqueda de estrellas
		 * 
		 * 1 - Buscar todas las estrellas en las dos imagenes con el algoritmo de busqueda de estrellas
		 * 2 - Coger de ambas imagenes el minimo numero de estrellas encontradas. La imagen con menos estrellas
		 *     ser� la de los centroides a analizar y la otra la de los emparejamientos
		 * 3 - Obtener los centroides de las estrellas encontradas
		 * 4 - Comparar cada centroide de la primera imagen con los de la segunda e ir emparejandolos
		 * 4.1 - Se coge el centroide mas cercano al que se quiere emparejar y se toma como su pareja
		 * 4.2 - Si no se encuentra centroide dentro de un radio dado se desecha el centroide
		 * 4.3 - Si el porcentaje de centroides emparejados es menor a un numero dado se desechan las imagenes
		 * 4.4 - Si es mayor o igual se pasa al tratamiento de ambas imagenes
		 * 5 - Por ultimo, se calcula la matriz de transformacion para ajustar las dos imagenes con respecto a los centroides elegidos
		 */
		
		// Obtener nombre de ficheros y parametros configurables
		String filename1 = procImgs.get(0).getImagen().getRutaFichero(), filename2 = procImgs.get(1).getImagen().getRutaFichero();
		List<ParamProcTarea> paramProcTareas = procImgs.get(0).getTareaProcesamiento().getParametros();
		double brillo = 0, umbral = 0;
		for (int i = 0; i < paramProcTareas.size(); i++) {
			if (paramProcTareas.get(i).getTipoParametro().getIdTipoParametro() == 1) // brillo
				brillo = paramProcTareas.get(i).getValorNum();
			if (paramProcTareas.get(i).getTipoParametro().getIdTipoParametro() == 2) // umbral
				umbral = paramProcTareas.get(i).getValorNum();
		}
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("Log.txt"));
			bw.write("\r\n***** Informaci�n de ejecuci�n *****\r\n");
			bw.write("\r\n\r\n1) Archivos:\r\n\tImagen 1: " + filename1 + "\r\n\tImagen 2: " + filename2);
			bw.write("\r\n\r\n2) Par�metros:\r\n\tUmbral: " + umbral + "\r\n\tBrillo: " + brillo);
			
			// Crear imagenes y buscar estrellas
			Fits imagenFITS1 = new Fits(new File(filename1));			
			BasicHDU imageHDU1 = imagenFITS1.getHDU(0);
			LectorImageHDU l1 = new LectorImageHDU(imageHDU1, filename1);
			StarFinder sf1 = new StarFinder();
			sf1.buscarEstrellas(l1, new Float(brillo), new Float(umbral));
			bw.write("\r\n\r\n3) N�mero de estrellas encontradas:\r\n\tImagen 1: " + sf1.getNumberOfStars());
			
			Fits imagenFITS2 = new Fits(new File(filename2));			
			BasicHDU imageHDU2 = imagenFITS2.getHDU(0);
			LectorImageHDU l2 = new LectorImageHDU(imageHDU2, filename2);
			StarFinder sf2 = new StarFinder();
			sf2.buscarEstrellas(l2, new Float(brillo), new Float(umbral));
			bw.write("\r\n\tImagen 2: " + sf2.getNumberOfStars());

			ArrayList<RectStar> recuadros1, recuadros2;
			int nRecuadros = Math.min(sf1.getNumberOfStars(), sf2.getNumberOfStars());
			LectorImageHDU aux;
			String numMinIm;
			if (nRecuadros == sf1.getNumberOfStars()) {
				recuadros1 = sf1.getRecuadros();
				recuadros2 = sf2.getRecuadros();
				numMinIm = "imagen 1";
			} else {
				aux = l1;
				l1 = l2;
				l2 = aux;
				recuadros1 = sf2.getRecuadros();
				recuadros2 = sf1.getRecuadros();
				numMinIm = "imagen 2";
			}
			
			// Calcular los centroides de los recuadros de ambas imagenes
			ArrayList<Point> centroides1 = new ArrayList<Point>(), centroides2 = new ArrayList<Point>();
			CalculateBookCentroid cc = new CalculateBookCentroid();
			Point cent1, cent2;
			
			BufferedWriter bwc = new BufferedWriter(new FileWriter("LogCentroides.txt"));
			
			for (int i = 0; i < nRecuadros; i++) {
				cent1 = cc.giveMeTheCentroid(l1.getPorcionImagen(recuadros1.get(i).getxLeft(), recuadros1.get(i).getyTop(), 
						recuadros1.get(i).getWidth(), recuadros1.get(i).getHeight()));
				cent2 = cc.giveMeTheCentroid(l2.getPorcionImagen(recuadros2.get(i).getxLeft(), recuadros2.get(i).getyTop(), 
						recuadros2.get(i).getWidth(), recuadros2.get(i).getHeight()));
				
				cent1.setX(recuadros1.get(i).getxLeft() + cent1.getX());
				cent1.setY(recuadros1.get(i).getyTop() + cent1.getY());
				cent2.setX(recuadros2.get(i).getxLeft() + cent2.getX());
				cent2.setY(recuadros2.get(i).getyTop() + cent2.getY());
				
				bwc.write("\r\n\r\nCentroide " + i + " de la imagen 1:\r\n\tX: " + cent1.getX() + "\r\n\tY: " + cent1.getY());
				bwc.write("\r\n\r\nRect�ngulo " + i + " de la imagen 1:\r\n\txLeft: " + recuadros1.get(i).getxLeft()
						+ "\r\n\txRight: " + recuadros1.get(i).getxRight()
						+ "\r\n\tyTop: " + recuadros1.get(i).getyTop()
						+ "\r\n\tyBot: " + recuadros1.get(i).getyBot());
				
				bwc.write("\r\n\r\nCentroide " + i + " de la imagen 2:\r\n\tX: " + cent2.getX() + "\r\n\tY: " + cent2.getY());
				bwc.write("\r\n\r\nRect�ngulo " + i + " de la imagen 2:\r\n\txLeft: " + recuadros2.get(i).getxLeft()
						+ "\r\n\txRight: " + recuadros2.get(i).getxRight()
						+ "\r\n\tyTop: " + recuadros2.get(i).getyTop()
						+ "\r\n\tyBot: " + recuadros2.get(i).getyBot());
				
				centroides1.add(cent1);
				centroides2.add(cent2);
			}
			
			bwc.close();
			
			// Hacer coincidir los nRecuadros centroides
			double scaleW, scaleH;
			scaleW = (double)l2.getWidth()/l1.getWidth();
			scaleH = (double)l2.getHeight()/l1.getHeight();
			double porcentaje = 0;
			Point centroide, elegido;
			ArrayList<Point> elegidos = new ArrayList<Point>();
			ArrayList<Point> centroides = new ArrayList<Point>();
			for (int i = 0; i < nRecuadros; i++) {
				// Escalar el centroide
				centroide = centroides1.get(i);
				centroide.setX(centroide.getX() * scaleW);
				centroide.setY(centroide.getY() * scaleH);
				elegido = getCentroideEmparejado(centroide, recuadros1.get(i), centroides2, recuadros2);
				
				if (elegido != null) { // se ha encontrado coincidente
					
					// Comprobar si el centroide ya esta en el array
					if (elegidos.contains(elegido)) {
						int pos = elegidos.indexOf(elegido);
						
						// Si la distancia del nuevo centroide es menor que la del antiguo se descarta el antiguo
						if (centroide.getDistancia(elegido) <
								centroides.get(pos).getDistancia(elegido)) {
							centroides.remove(pos);
							elegidos.remove(pos);
							porcentaje--;
						}
					}
					
					centroides.add(centroide);
					elegidos.add(elegido);
					porcentaje++;
				}
			}
			
			bw.write("\r\n\r\n4) N�mero de centroides elegidos: " + porcentaje);
			porcentaje = (porcentaje/nRecuadros) * 100;
			bw.write("\r\n\r\n5) Porcentaje de centroides elegidos: " + porcentaje);
			
			// Construir las matrices de ambas listas y encontrar la matriz de transformacion
			// Y a la vez se calcula el error inicial
			double errorInicial = 0;
			if (porcentaje >= 50) {
				double[][] m1 = new double[centroides.size()][3], m2 = new double[elegidos.size()][3];
				for (int i = 0; i < centroides.size(); i++) {
					centroide = centroides.get(i);
					m1[i][0] = centroide.getX();
					m1[i][1] = centroide.getY();
					m1[i][2] = 1;
					
					centroide = elegidos.get(i);
					m2[i][0] = centroide.getX();
					m2[i][1] = centroide.getY();
					m2[i][2] = 1;
					
					errorInicial += Math.sqrt(Math.pow((m1[i][0] - m2[i][0]),2) +
									Math.pow((m1[i][1] - m2[i][1]),2));
				}
				bw.write("\r\n\r\n6) Error inicial: " + errorInicial);
				
				Matrix P = new Matrix(m1);
				Matrix Q = new Matrix(m2);
				Matrix X = P.solve(Q);
				Matrix R = P.times(X);
			    double errorFinal = 0;
			    for (int i = 0; i < R.getRowDimension(); i++) {
			    	errorFinal += Math.sqrt(Math.pow((R.get(i, 0) - Q.get(i, 0)),2) +
							Math.pow((R.get(i, 1) - Q.get(i, 1)),2));
			    }
			    bw.write("\r\n\r\n7) Error final: " + errorFinal);
			
			    // Comparar errores y si el final es mayor que el inicial descartar
				if (errorFinal <= errorInicial) {
					
					// Aplicar matriz a todos los puntos de la imagen uno y buscar emparejamiento
					m1 = new double[centroides1.size()][3];
					for (int i = 0; i < centroides1.size(); i++) {
						centroide = centroides1.get(i);
						m1[i][0] = centroide.getX();
						m1[i][1] = centroide.getY();
						m1[i][2] = 1;
					}
					P = new Matrix(m1);
					Q = P.times(X);
					
					// Emparejamiento
					elegidos.clear();
					centroides.clear();
					porcentaje = 0.;
					for (int i = 0; i < centroides1.size(); i++) {
						centroide = new Point();
						centroide.setX(Q.get(i, 0));
						centroide.setY(Q.get(i, 1));
						elegido = getCentroideEmparejado(centroide, recuadros1.get(i), centroides2, recuadros2);
						
						if (elegido != null) { // se ha encontrado coincidente
							
							// Comprobar si el centroide ya esta en el array
							if (elegidos.contains(elegido)) {
								int pos = elegidos.indexOf(elegido);
								
								// Si la distancia del nuevo centroide es menor que la del antiguo se descarta el antiguo
								if (centroide.getDistancia(elegido) <
										centroides.get(pos).getDistancia(elegido)) {
									centroides.remove(pos);
									elegidos.remove(pos);
									porcentaje--;
								}
							}
							
							centroides.add(centroide);
							elegidos.add(elegido);
							porcentaje++;
						}
					}
					
					bw.write("\r\n\r\n8) N�mero de centroides elegidos despu�s de aplicar la matriz: " + porcentaje);
					porcentaje = (porcentaje/centroides1.size()) * 100;
					bw.write("\r\n\r\n9) Porcentaje de centroides elegidos despu�s de aplicar la matriz: " + porcentaje);
					
					// Si el porcentaje es mayor o igual que 50 calcular errores y ver cual es mayor que 2*desviacion tipica (candidato a que se haya movido)
					double media = 0., varianza = 0., error, desviacion;
					double[] errores = new double[centroides.size()];
					if (porcentaje >= 50) {
						// Calculo de la media, varianza y desviacion tipica
						for (int i = 0; i < centroides.size(); i++) {
							error = Math.sqrt(Math.pow((centroides.get(i).getX() - elegidos.get(i).getX()),2) +
									Math.pow((centroides.get(i).getY() - elegidos.get(i).getY()),2));
							media += error;
							varianza += Math.pow(error, 2);
							errores[i] = error;
						}
						media = media / centroides.size();
						varianza = varianza / centroides.size() - Math.pow(media, 2);
						desviacion = Math.sqrt(varianza);
						bw.write("\r\n\r\n10) Desviaci�n t�pica: " + desviacion);
						
						// Calcular candidatos a haberse movido
						bw.write("\r\n\r\n11) Lista de candidatos a haberse movido en la " + numMinIm + ":");
						int cont = 1;
						for (int i = 0; i < centroides.size(); i++) {
							if (errores[i] > 2*desviacion) {
								centroide = centroides.get(i);
								bw.write("\r\n\tCandidato " + cont + " -> X: " + Math.round(centroide.getX()) + " Y: " + Math.round(centroide.getY()) + "\r\n");
								cont++;
							}
						}
					}
				}
			}
			
			bw.close();
			JOptionPane.showMessageDialog(null, "Fichero de log creado en " + System.getProperty("user.dir"), "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
			
			procImgs.get(0).setFinalizada(true);
			procImgs.get(1).setFinalizada(true);
			
			manager.merge(procImgs.get(0));
			manager.merge(procImgs.get(1));
		
		} catch (FitsException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServicioGestionProcesamientosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private Point getCentroideEmparejado(Point punto, RectStar rec, ArrayList<Point> listaPuntos,
			ArrayList<RectStar> listaRecs) {
		
		// Devuelve el indice de la estrella mas cercana y con semejante brillo dentro de un radio de 20 pixeles
		
		int radioBusqueda = 20; // Numero de pixeles de radio de busqueda. Esto puede ser un parametro a configurar
		int tamRecLim = 2; // El area del rectangulo debera estar entre la mitad y el doble del rectangulo que se pasa
		Point aux;
		double distancia, distanciaMin = 0, brillo, brilloParecido = 0;
		Point elegido = null;
		
		for (int i = 0; i < listaPuntos.size(); i++) {
			aux = listaPuntos.get(i);
			distancia = punto.getDistancia(aux);
			brillo = listaRecs.get(i).getArea();
			if (elegido == null) {
				if (distancia <= radioBusqueda &&
						brillo <= tamRecLim*rec.getArea() && brillo >= rec.getArea()/tamRecLim) {
					distanciaMin = distancia;
					brilloParecido = brillo;
					elegido = aux;
				}
			} else {
				if (distancia <= radioBusqueda &&
						Math.abs(rec.getArea() - brillo) <= Math.abs(rec.getArea() - brilloParecido)
						&& distancia < distanciaMin) {
					brilloParecido = brillo;
					distanciaMin = distancia;
					elegido = aux;
				}
			}
		}
		
		return elegido;
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public static Log getLog() {
		return LOG;
	}

	/**
	 * width y height son el alcho y el alto de la imagen en pixeles.
	 * x e y son las coordenadas que queremos trasnformar
	 */
	@Override
	public DecimalCoordinate pixelToCoordinatesConverter(Imagen imagen, int width, int height, double x, double y) {
		double ancho = imagen.getAncho();
		double alto = imagen.getTarea().getAlto();
		double ar = Double.parseDouble(imagen.getAscensionRecta());
		double dec = Double.parseDouble(imagen.getDeclinacion());
		double incX = x - width/2;
		double incY = y - height/2;
		
		double ar1 = ar + incX*(ancho/width);
		double dec1 = dec + incY*(alto*height);
		if (dec1 > 90) { // Cruzo el polo norte
			dec1 = 180 - dec1;
			ar1 = ar1 - 180;
		}
		if (dec1 < 0) { // Cruzo el polo sur
			dec1 = Math.abs(dec1);
			ar1 = ar1 - 180;
		}
		if (ar1 > 360) ar1 -= 360;
		if (ar1 < 0) ar1 += 360;
		
		return new DecimalCoordinate(ar1, dec1);
	} 

}
