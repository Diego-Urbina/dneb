package es.ucm.si.dneb.service.busquedaDobles;

import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.media.jai.PlanarImage;
import javax.media.jai.RasterFactory;
import javax.media.jai.TiledImage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;
import nom.tam.util.ArrayFuncs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import Jama.Matrix;

import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.ProcImagen;
import es.ucm.si.dneb.service.gestionProcesamientos.ServicioGestionProcesamientosException;
import es.ucm.si.dneb.service.image.centroid.CalculateBookCentroid;
import es.ucm.si.dneb.service.image.segmentation.LectorImageHDU;
import es.ucm.si.dneb.service.image.segmentation.RectStar;
import es.ucm.si.dneb.service.image.segmentation.StarFinder;
import es.ucm.si.dneb.service.image.util.Point;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;
import es.ucm.si.dneb.service.math.CoordinateConverter;
import es.ucm.si.dneb.service.math.DecimalCoordinate;
import es.ucm.si.dneb.service.math.Distance;
import es.ucm.si.dneb.service.math.MathService;
import es.ucm.si.dneb.service.math.SexagesimalCoordinate;
import es.ucm.si.dneb.util.Pair;

@Service("serviceBusquedaDobles")
public class ServiceBusquedaDoblesImpl implements ServiceBusquedaDobles{
	
	@PersistenceContext
	private EntityManager manager;
	
	private LectorImageHDU l1, l2;
	
	private static final Log LOG = LogFactory
	.getLog(ServiceBusquedaDoblesImpl.class);
		
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void iniciarProcesamiento(List<ProcImagen> procImgs) {
		
		// Obtener nombre de ficheros y parametros configurables
		Imagen im1 = procImgs.get(0).getImagen(), im2 = procImgs.get(1).getImagen();
		
		busquedaEstrellasDobles(im1, im2);
		
		procImgs.get(0).setFinalizada(true);
		procImgs.get(1).setFinalizada(true);
		
		manager.merge(procImgs.get(0));
		manager.merge(procImgs.get(1));
		
	}
	
	public ArrayList<Pair<Point>> busquedaEstrellasMovimiento(Imagen im1, Imagen im2) {
		
		/* Algoritmo de busqueda de estrellas candidatas a haberse movido
		 * 
		 * 1 - Buscar todas las estrellas en las dos imagenes con el algoritmo de busqueda de estrellas
		 * 2 - Coger de ambas imagenes el minimo numero de estrellas encontradas. La imagen con menos estrellas
		 *     será la de los centroides a analizar y la otra la de los emparejamientos
		 * 3 - Obtener los centroides de las estrellas encontradas
		 * 4 - Comparar cada centroide de la primera imagen con los de la segunda e ir emparejandolos
		 * 4.1 - Se coge el centroide mas cercano al que se quiere emparejar y se toma como su pareja
		 * 4.2 - Si no se encuentra centroide dentro de un radio dado se desecha el centroide
		 * 4.3 - Si el porcentaje de centroides emparejados es menor a un numero dado se desechan las imagenes
		 * 4.4 - Si es mayor o igual se pasa al tratamiento de ambas imagenes
		 * 5 - Por ultimo, se calcula la matriz de transformacion para ajustar las dos imagenes con respecto a los centroides elegidos
		 */
		
		try {
			
			String filename1 = im1.getRutaFichero();
			String filename2 = im2.getRutaFichero();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("Log.txt"));
			bw.write("\r\n***** Información de ejecución *****\r\n");
			bw.write("\r\n\r\n1) Archivos:\r\n\tImagen 1: " + filename1 + "\r\n\tImagen 2: " + filename2);
			
			float[] brillos = new float[50];
			float[] umbrales = new float[50];
			ArrayList<Pair<Point>> resultado = new ArrayList<Pair<Point>>();
			
			for (int i = 0; i < 50; i++) {
				Random r = new Random();
				do {
					brillos[i] = (r.nextFloat()%0.5f) + 99.5f;
					umbrales[i] = (r.nextFloat()%0.5f) + 99.5f;
				} while (brillos[i] < umbrales[i]);
			}
			
			for (int iter = 0; iter < brillos.length; iter++) {
			
				bw.write("\r\n\r\n\r\nIteración " + (iter+1) + ":");
				
				float umbral1, umbral2, brillo1, brillo2;
				
				// Crear imagenes y buscar estrellas
				Fits imagenFITS1 = new Fits(new File(filename1));			
				BasicHDU imageHDU1 = imagenFITS1.getHDU(0);
				l1 = new LectorImageHDU(imageHDU1, filename1);
				StarFinder sf1 = new StarFinder();
				umbral1 = new Float(l1.getNPercentile(umbrales[iter]));
				brillo1 = new Float(l1.getNPercentile(brillos[iter]));
				sf1.buscarEstrellas(l1, brillo1, umbral1);
				bw.write("\r\n\r\n2.1) Parámetros imagen 1:\r\n\tBrillo: " + brillo1 + "\r\n\tUmbral: " + umbral1);
				bw.write("\r\n\r\n2.2) Número de estrellas encontradas imagen 1: " + sf1.getNumberOfStars());
				
				Fits imagenFITS2 = new Fits(new File(filename2));			
				BasicHDU imageHDU2 = imagenFITS2.getHDU(0);
				l2 = new LectorImageHDU(imageHDU2, filename2);
				StarFinder sf2 = new StarFinder();
				umbral2 = new Float(l2.getNPercentile(umbrales[iter]));
				brillo2 = new Float(l2.getNPercentile(brillos[iter]));
				sf2.buscarEstrellas(l2, brillo2, umbral2);
				bw.write("\r\n\r\n3.1) Parámetros imagen 2:\r\n\tBrillo: " + brillo2 + "\r\n\tUmbral: " + umbral2);
				bw.write("\r\n\r\n3.2) Número de estrellas encontradas imagen 2: " + sf2.getNumberOfStars());
	
				ArrayList<RectStar> recuadros1 = sf1.getRecuadros();
				ArrayList<RectStar> recuadros2 = sf2.getRecuadros();
				int nRecuadros = Math.max(sf1.getNumberOfStars(), sf2.getNumberOfStars());
				
				// Calcular los centroides de los recuadros de ambas imagenes
				ArrayList<Point> centroides1 = new ArrayList<Point>(), centroides2 = new ArrayList<Point>();
				CalculateBookCentroid cc = new CalculateBookCentroid();
				Point cent1, cent2;
				
				BufferedWriter bwc = new BufferedWriter(new FileWriter("LogCentroides.txt"));
				
				for (int i = 0; i < nRecuadros; i++) {
					
					if (i < recuadros1.size()) {
						cent1 = cc.giveMeTheCentroid(l1.getPorcionImagen(recuadros1.get(i).getxLeft(), recuadros1.get(i).getyTop(), 
								recuadros1.get(i).getWidth(), recuadros1.get(i).getHeight()));
						
						cent1.setX(recuadros1.get(i).getxLeft() + cent1.getX());
						cent1.setY(recuadros1.get(i).getyTop() + cent1.getY());
						
						bwc.write("\r\n\r\nCentroide " + i + " de la imagen 1" + ":\r\n\tX: " + cent1.getX() + "\r\n\tY: " + cent1.getY());
						bwc.write("\r\n\r\nRectángulo " + i + " de la imagen 1" + ":\r\n\txLeft: " + recuadros1.get(i).getxLeft()
								+ "\r\n\txRight: " + recuadros1.get(i).getxRight()
								+ "\r\n\tyTop: " + recuadros1.get(i).getyTop()
								+ "\r\n\tyBot: " + recuadros1.get(i).getyBot());
						
						centroides1.add(cent1);
					}
					
					if (i < recuadros2.size()) {
						cent2 = cc.giveMeTheCentroid(l2.getPorcionImagen(recuadros2.get(i).getxLeft(), recuadros2.get(i).getyTop(), 
								recuadros2.get(i).getWidth(), recuadros2.get(i).getHeight()));
						
						cent2.setX(recuadros2.get(i).getxLeft() + cent2.getX());
						cent2.setY(recuadros2.get(i).getyTop() + cent2.getY());
						
						bwc.write("\r\n\r\nCentroide " + i + " de la imagen 2" + ":\r\n\tX: " + cent2.getX() + "\r\n\tY: " + cent2.getY());
						bwc.write("\r\n\r\nRectángulo " + i + " de la imagen 2" + ":\r\n\txLeft: " + recuadros2.get(i).getxLeft()
								+ "\r\n\txRight: " + recuadros2.get(i).getxRight()
								+ "\r\n\tyTop: " + recuadros2.get(i).getyTop()
								+ "\r\n\tyBot: " + recuadros2.get(i).getyBot());
						
						centroides2.add(cent2);
					}
				}
				
				bwc.close();
				
				LectorImageHDU auxLI;
				Imagen auxIm;
				ArrayList<Point> auxCentroides;
				ArrayList<RectStar> auxRecuadros;
				if (centroides1.size() > centroides2.size()) { // todo lo que lleve un 1 sera el que tenga menor numero
					auxLI = l1;
					l1 = l2;
					l2 = auxLI;
					auxIm = im1;
					im1 = im2;
					im2 = auxIm;
					auxCentroides = centroides1;
					centroides1 = centroides2;
					centroides2 = auxCentroides;
					auxRecuadros = recuadros1;
					recuadros1 = recuadros2;
					recuadros2 = auxRecuadros;
				}
				
				// Hacer coincidir los nRecuadros centroides
				double scaleW, scaleH;
				scaleW = (double)l2.getWidth()/l1.getWidth();
				scaleH = (double)l2.getHeight()/l1.getHeight();
				double porcentaje = 0;
				Point centroide, elegido;
				ArrayList<Point> elegidos = new ArrayList<Point>();
				ArrayList<Point> centroidesFin = new ArrayList<Point>();
				for (int i = 0; i < centroides1.size(); i++) {
					// Escalar el centroide
					centroide = centroides1.get(i).clone();
					centroide.setX(centroide.getX() * scaleW);
					centroide.setY(centroide.getY() * scaleH);
					elegido = getCentroideEmparejado(centroide, recuadros1.get(i), centroides2, recuadros2, scaleW, scaleH);
					
					if (elegido != null) { // se ha encontrado coincidente
						
						// Comprobar si el centroide ya esta en el array
						if (elegidos.contains(elegido)) {
							int pos = elegidos.indexOf(elegido);
							
							// Si la distancia del nuevo centroide es menor que la del antiguo se descarta el antiguo
							if (centroide.getDistancia(elegido) < centroidesFin.get(pos).getDistancia(elegido)) {
								centroidesFin.remove(pos);
								elegidos.remove(pos);
								porcentaje--;
							}
						}
	
						centroidesFin.add(centroides1.get(i));
						elegidos.add(elegido);
						porcentaje++;
					}
				}
				
				bw.write("\r\n\r\n4) Número de centroides elegidos: " + porcentaje);
				porcentaje = (porcentaje/centroides1.size()) * 100;
				bw.write("\r\n\r\n5) Porcentaje de centroides elegidos: " + porcentaje);
				
				// Construir las matrices de ambas listas y encontrar la matriz de transformacion
				// Y a la vez se calcula el error cuadratico medio inicial
				double errorInicial = 0;
				Point p1 = new Point(), p2 = new Point();
				if (porcentaje >= 50) {
					double[][] m1 = new double[centroidesFin.size()][3], m2 = new double[elegidos.size()][3];
					for (int i = 0; i < centroidesFin.size(); i++) {
						centroide = centroidesFin.get(i);
						m1[i][0] = centroide.getX();
						m1[i][1] = centroide.getY();
						m1[i][2] = 1;
						
						centroide = elegidos.get(i);
						m2[i][0] = centroide.getX();
						m2[i][1] = centroide.getY();
						m2[i][2] = 1;
						
						p1.setX(m1[i][0] * scaleW);
						p1.setY(m1[i][1] * scaleH);
						p2.setX(m2[i][0]);
						p2.setY(m2[i][1]);
						errorInicial += Math.pow(p1.getDistancia(p2), 2);
					}
					
					errorInicial = Math.sqrt(errorInicial/centroidesFin.size());
					bw.write("\r\n\r\n6) Error cuadrático medio inicial: " + errorInicial);
					
					Matrix P = new Matrix(m1);
					Matrix Q = new Matrix(m2);
					Matrix X = P.solve(Q);
					Matrix R = P.times(X);
				    double errorFinal = 0;
				    for (int i = 0; i < R.getRowDimension(); i++) {
				    	p1.setX(R.get(i, 0));
						p1.setY(R.get(i, 1));
						centroide = elegidos.get(i);
				    	errorFinal += Math.pow(p1.getDistancia(centroide),2);
				    }
				    
				    errorFinal = Math.sqrt(errorFinal/R.getRowDimension());
				    bw.write("\r\n\r\n7) Error cuadrático medio final: " + errorFinal);
				
				    // Comparar errores y si el final es mayor que el inicial descartar
					if (errorFinal < errorInicial) {
						
						// Aplicar matriz a todos los puntos de la imagen con menos recuadros y buscar emparejamiento
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
						centroidesFin.clear();
						ArrayList<Point> centroidesIni = new ArrayList<Point>();
						porcentaje = 0.;
						for (int i = 0; i < centroides1.size(); i++) {
							if (recuadros1.get(i).getArea() <= 5) continue; // desechamos las estrellas muy pequeñas
							
							centroide = new Point();
							centroide.setX(Q.get(i, 0));
							centroide.setY(Q.get(i, 1));
							elegido = getCentroideEmparejado(centroide, recuadros1.get(i), centroides2, recuadros2, scaleW, scaleH);
							
							if (elegido != null) { // se ha encontrado coincidente
								
								// Comprobar si el centroide ya esta en el array
								if (elegidos.contains(elegido)) {
									int pos = elegidos.indexOf(elegido);
									
									// Si la distancia del nuevo centroide es menor que la del antiguo se descarta el antiguo
									if (centroide.getDistancia(elegido) < centroidesFin.get(pos).getDistancia(elegido)) {
										centroidesIni.remove(pos);
										centroidesFin.remove(pos);
										elegidos.remove(pos);
										porcentaje--;
									}
								}
								
								centroidesIni.add(centroides1.get(i));
								centroidesFin.add(centroide);
								elegidos.add(elegido);
								porcentaje++;
							}
						}
						
						bw.write("\r\n\r\n8) Número de centroides elegidos después de aplicar la matriz: " + porcentaje);
						porcentaje = (porcentaje/centroides1.size()) * 100;
						bw.write("\r\n\r\n9) Porcentaje de centroides elegidos después de aplicar la matriz: " + porcentaje);
						
						// Si el porcentaje es mayor o igual que 50 calcular errores y ver cual es mayor que 2*desviacion tipica (candidato a que se haya movido)
						double error, desviacion;
						DescriptiveStatistics calcEstadisticos = new DescriptiveStatistics();
						double[] errores = new double[centroidesFin.size()];
						if (porcentaje >= 50) {
							// Calculo de la media, varianza y desviacion tipica
							for (int i = 0; i < centroidesFin.size(); i++) {
								p1.setX(centroidesFin.get(i).getX());
								p1.setY(centroidesFin.get(i).getY());
								p2.setX(elegidos.get(i).getX());
								p2.setY(elegidos.get(i).getY());
								error = p1.getDistancia(p2);
								calcEstadisticos.addValue(error);
								errores[i] = error;
							}
							desviacion = calcEstadisticos.getStandardDeviation();
							bw.write("\r\n\r\n10) Desviación típica: " + desviacion);
							
							// Calcular candidatos a haberse movido
							bw.write("\r\n\r\n11) Lista de candidatos a haberse movido:");
							int cont = 0;
							DecimalCoordinate dc;
							PlanarImage pi = createPlanarImage(l1);
							ArrayList<Pair<Point>> aux = new ArrayList<Pair<Point>>();
							for (int i = 0; i < centroidesIni.size(); i++) {
								if (errores[i] > 2*desviacion) {
									centroide = centroidesIni.get(i);
									if (sf1.getNumberOfStars()>sf2.getNumberOfStars())
										aux.add(new Pair<Point>(elegidos.get(i), centroide));
									else
										aux.add(new Pair<Point>(centroide, elegidos.get(i)));
									dc = pixelToCoordinatesConverter(im1, pi.getWidth(), pi.getHeight(), centroide.getX(), centroide.getY());
									bw.write("\r\n\tCandidato " + (cont+1) + " -> AR: " + dc.getAr() + " DEC: " + dc.getDec() + "\r\n");
									cont++;
								}
							}
							union(resultado, aux);
						}
					}
				}
				if (centroides1.size() > centroides2.size()) { // restaurar punteros
					auxLI = l1;
					l1 = l2;
					l2 = auxLI;
					auxIm = im1;
					im1 = im2;
					im2 = auxIm;
				}
			}
			bw.close();
			JOptionPane.showMessageDialog(null, "Fichero de log creado en " + System.getProperty("user.dir"), "Información", JOptionPane.INFORMATION_MESSAGE);
			return resultado;
		
		} catch (FitsException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ServicioGestionProcesamientosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	private void union(ArrayList<Pair<Point>> p1, ArrayList<Pair<Point>> p2){
		Point c1, e1, c2, e2;
		boolean encontrado = false;
		int limite = 3;
		int nElem = p1.size();
		
		for (int i = 0; i < p2.size(); i++) {
			c2 = p2.get(i).getA();
			e2 = p2.get(i).getB();
			for (int j = 0; j < nElem && !encontrado; j++) {
				c1 = p1.get(j).getA();
				e1 = p1.get(j).getB();
				encontrado = c1.getX()-limite <= c2.getX() && c1.getX()+limite >= c2.getX()
							&& c1.getY()-limite <= c2.getY() && c1.getY()+limite >= c2.getY()
							&& e1.getX()-limite <= e2.getX() && e1.getX()+limite >= e2.getX()
							&& e1.getY()-limite <= e2.getY() && e1.getY()+limite >= e2.getY();
			}
			if (!encontrado) p1.add(new Pair<Point>(c2, e2));
		}
	}
	
	public ArrayList<Pair<Point>> busquedaEstrellasDobles(Imagen im1, Imagen im2) {
		
		/* Algoritmo de busqueda de estrellas candidatas a ser dobles
		 * 
		 * 1 - Obtener la lista de estrellas candidatas a haberse movido
		 * 2 - Emparejar las estrellas de la lista con el criterio de que se hayan movido en la misma direccion
		 * 3 - Para ello calcularemos el vector director de cada una y veremos cuales coinciden en dirección, sentido y módulo
		 */
		
		ArrayList<Pair<Point>> candidatas = busquedaEstrellasMovimiento(im1, im2);
		
		ArrayList<Pair<Point>> resultado = new ArrayList<Pair<Point>>();
		DecimalCoordinate dc1, dc2;
		MathService ms = (MathService) ContextoAplicacion.getApplicationContext().getBean("mathService");
		PlanarImage pi1 = createPlanarImage(l1);
		Point p1, p2, e1, e2;
		Distance d;
		boolean[] usados = new boolean[candidatas.size()];
		double modulo1, modulo2, direccion1, direccion2;
		double difModulo = 0.3; // diferencia entre modulos de un 25%
		double difDireccion = Math.toRadians(15); // diferencia de 10 grados entre ambas direcciones
		
		for (int i = 0; i < candidatas.size(); i++) {
			p1 = candidatas.get(i).getA();
			e1 = candidatas.get(i).getB();
			dc1 = pixelToCoordinatesConverter(im1, pi1.getWidth(), pi1.getHeight(), p1.getX(), p1.getY());
			modulo1 = p1.getDistancia(e1);
			direccion1 = p1.getDireccion(e1);
			
			for (int j = i+1; j < candidatas.size(); j++) {
				p2 = candidatas.get(j).getA();
				e2 = candidatas.get(j).getB();
				dc2 = pixelToCoordinatesConverter(im1, pi1.getWidth(), pi1.getHeight(), p2.getX(), p2.getY());
				d = ms.calculateDecimalDistance(dc1.getAr(), dc1.getDec(), dc2.getAr(), dc2.getDec());
				if (d.getDistanceSeconds() > 180) continue; // distancia entre estrellas mayor de 3 minutos
				
				modulo2 = p2.getDistancia(e2);
				direccion2 = p2.getDireccion(e2);
				
				if ((modulo2 <= modulo1*(1+difModulo) && modulo2 >= modulo1*(1-difModulo) ||
					modulo1 <= modulo2*(1+difModulo) && modulo1 >= modulo2*(1-difModulo)) &&
					(direccion2 <= direccion1+difDireccion && direccion2 >= direccion1-difDireccion ||
					direccion1 <= direccion2+difDireccion && direccion1 >= direccion2-difDireccion)) {
					// Si los modulos y las direcciones son semejantes mirar que los sentidos sean iguales
					
					// Si el producto escalar es positivo tienen el mismo sentido
					if ((e1.getX()-p1.getX())*(e2.getX()-p2.getX()) + (e1.getY()-p1.getY())*(e2.getY()-p2.getY()) > 0) {
						if (!usados[i]) {
							usados[i] = true;
							resultado.add(new Pair<Point>(p1, e1));
						}
						if (!usados[j]) {
							usados[j] = true;
							resultado.add(new Pair<Point>(p2, e2));
						}
					}
				}
			}
		}
		
		return resultado;
	}
	
	private Point getCentroideEmparejado(Point punto, RectStar rec, ArrayList<Point> listaPuntos,
			ArrayList<RectStar> listaRecs, double scaleW, double scaleH) {
		
		// Devuelve el indice de la estrella mas cercana y con semejante brillo dentro de un radio de 20 pixeles
		
		double radioMaxBusqueda = 8; // Numero de pixeles de radio de busqueda. Esto puede ser un parametro a configurar
		double tamRecLim = 1.5; // El area del rectangulo debera estar entre un rango determinado
		double distancia, distanciaMin = 5, brillo, brilloParecido = 0, brilloEscalado;
		Point elegido = null;
		Point aux;
		
		for (int i = 0; i < listaPuntos.size(); i++) {
			aux = listaPuntos.get(i);
			distancia = punto.getDistancia(aux);
			brillo = listaRecs.get(i).getArea();
			brilloEscalado = rec.getWidth()*scaleW * rec.getHeight()*scaleH;
			if (elegido == null) {
				if (distancia <= distanciaMin || (distancia <= radioMaxBusqueda &&
						brillo <= tamRecLim*brilloEscalado && brillo >= brilloEscalado/tamRecLim)) {
					distanciaMin = distancia;
					brilloParecido = brillo;
					elegido = aux;
				}
			} else {
				if (distancia <= distanciaMin || (distancia <= radioMaxBusqueda &&
						Math.abs(brilloEscalado - brillo) <= Math.abs(brilloEscalado - brilloParecido))) {
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
	 * width y height son el ancho y el alto de la imagen en pixeles.
	 * x e y son las coordenadas que queremos transformar
	 */
	@Override
	public DecimalCoordinate pixelToCoordinatesConverter(Imagen imagen, int width, int height, double x, double y) {
		SexagesimalCoordinate sc;
		DecimalCoordinate dc;
		
		// En sexagesimal
		double ancho = imagen.getAncho();
		double alto = imagen.getTarea().getAlto();
		// En decimal
		double ar = Double.parseDouble(imagen.getAscensionRecta());
		double dec = Double.parseDouble(imagen.getDeclinacion());
		
		sc = new SexagesimalCoordinate(0,0,0,0,alto,0);
		dc = CoordinateConverter.sexagesimalToDecimalConverter(sc);
		double incX = width/2.0 - x;
		double incY = height/2.0 - y;
		
		double dec1 = dec + incY*(dc.getDec()/height);
		double anchoAux = ancho/(15.0*Math.cos(Math.toRadians(dec1)));
		sc = new SexagesimalCoordinate(0,anchoAux,0,0,0,0);
		dc = CoordinateConverter.sexagesimalToDecimalConverter(sc);
		double ar1 = ar + incX*(dc.getAr()/width);
		
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
	
	public PlanarImage createPlanarImage(LectorImageHDU l) {
		int[] arrayDataAplanado = (int[]) ArrayFuncs.flatten(l.getArrayData());
		
		DataBufferInt dBuffer = new DataBufferInt(arrayDataAplanado, l.getWidth()*l.getHeight());
		SampleModel sm = RasterFactory.createBandedSampleModel(DataBuffer.TYPE_SHORT, l.getWidth(), l.getHeight(), 1);
		ColorModel cm = PlanarImage.createColorModel(sm);
		Raster raster = RasterFactory.createWritableRaster(sm, dBuffer, new java.awt.Point(0,0));
		TiledImage tiledImage = new TiledImage(0,0,l.getWidth(),l.getHeight(),0,0,sm,cm);
		tiledImage.setData(raster);
		
		return tiledImage;
	}

}
