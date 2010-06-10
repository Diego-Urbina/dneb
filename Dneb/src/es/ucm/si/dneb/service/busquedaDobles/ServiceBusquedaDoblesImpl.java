package es.ucm.si.dneb.service.busquedaDobles;

import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import es.ucm.si.dneb.domain.Image;
import es.ucm.si.dneb.domain.RelevantInformation;
import es.ucm.si.dneb.domain.ImageProsec;
import es.ucm.si.dneb.domain.RelevantInfoType;
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
import es.ucm.si.dneb.util.Util;


@Service("serviceBusquedaDobles")
public class ServiceBusquedaDoblesImpl implements ServiceBusquedaDobles{
	
	@PersistenceContext
	private EntityManager manager;
	
	private LectorImageHDU l1;
	private LectorImageHDU l2;
	private PlanarImage pi;
	private ArrayList<Pair<Point>> candidatas;
	
	private static final Log LOG = LogFactory
	.getLog(ServiceBusquedaDoblesImpl.class);
		
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void iniciarProcesamiento(List<ImageProsec> procImgs) {
		
		// Obtener nombre de ficheros y parametros configurables
		Image im1, im2;
		ArrayList<Pair<Point>> res;
		List<RelevantInformation> infoRels = new ArrayList<RelevantInformation>();
		RelevantInformation ir;
		List<Image> imagenes = new ArrayList<Image>();
		DecimalCoordinate dc;
		
		LOG.info("PROCESAMIENTO DE BUSQUEDA DE ESTRELLAS DOBLES INFO:");
		
		im1 = procImgs.get(0).getImagen();
		im2 = procImgs.get(1).getImagen();
		res = busquedaEstrellasDobles(im1, im2);
		
		if (res == null || res.size() == 0) {
			LOG.info("No se ha encontrado ninguna estrella doble en estas dos imagenes");
		} else {
			for (Pair<Point> p : res) {
				dc = pixelToCoordinatesConverter(im1, pi.getWidth(), pi.getHeight(), p.getA().getX(), p.getA().getY());
				LOG.info("Estrella: " + dc.getAr() + "    +" + dc.getDec());
				ir = new RelevantInformation();
				imagenes.clear();
				ir.setDescription("BUSQUEDA ESTRELLAS DOBLES: Estrella: " + dc.getAr() + "    +" + dc.getDec());
				ir.setFecha(Util.dameFechaActual());
				imagenes.add(im1);
				imagenes.add(im2);
				ir.setImagenes(imagenes);
				ir.setTipoInformacionRelevante(manager.find(RelevantInfoType.class, 1L));
				infoRels.add(ir);
			}
		}
		
		procImgs.get(0).setFinalizada(true);
		procImgs.get(1).setFinalizada(true);
		
		manager.merge(procImgs.get(0));
		manager.merge(procImgs.get(1));
		
		for (RelevantInformation i : infoRels) {
			manager.persist(i);
		}
		
	}
	
	public ArrayList<Pair<Point>> busquedaEstrellasMovimiento(Image im1, Image im2) {
		
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
			LOG.info("Image 1: " + filename1);
			String filename2 = im2.getRutaFichero();
			LOG.info("Image 2: " + filename2);
			
			
			ArrayList<Pair<Point>> resultado = new ArrayList<Pair<Point>>();
			candidatas = new ArrayList<Pair<Point>>();
			
			float umbral, brillo;
			
			// Crear imagenes y buscar estrellas
			Fits imagenFITS1 = new Fits(new File(filename1));			
			BasicHDU imageHDU1 = imagenFITS1.getHDU(0);
			l1 = new LectorImageHDU(imageHDU1, filename1);
			pi = createPlanarImage(l1);
			StarFinder sf1 = new StarFinder();
			
			umbral = 40000;
			brillo = umbral + 2000;
			sf1.buscarEstrellas(l1, brillo, umbral);
			
			Fits imagenFITS2 = new Fits(new File(filename2));			
			BasicHDU imageHDU2 = imagenFITS2.getHDU(0);
			l2 = new LectorImageHDU(imageHDU2, filename2);
			StarFinder sf2 = new StarFinder();
			
			sf2.buscarEstrellas(l2, brillo, umbral);

			ArrayList<RectStar> recuadros1 = sf1.getRecuadros();
			ArrayList<RectStar> recuadros2 = sf2.getRecuadros();
			int nRecuadros = Math.max(sf1.getNumberOfStars(), sf2.getNumberOfStars());
			
			// Calcular los centroides de los recuadros de ambas imagenes
			ArrayList<Point> centroides1 = new ArrayList<Point>(), centroides2 = new ArrayList<Point>();
			CalculateBookCentroid cc = new CalculateBookCentroid();
			Point cent1, cent2;
			
			for (int i = 0; i < nRecuadros; i++) {
				
				if (i < recuadros1.size()) {
					cent1 = cc.giveMeTheCentroid(l1.getPorcionImagen(recuadros1.get(i).getxLeft(), recuadros1.get(i).getyTop(), 
							recuadros1.get(i).getWidth(), recuadros1.get(i).getHeight()));
					
					cent1.setX(recuadros1.get(i).getxLeft() + cent1.getX());
					cent1.setY(recuadros1.get(i).getyTop() + cent1.getY());
					
					if (cent1.getX() >= 0 && cent1.getX() <= l1.getWidth() && cent1.getY() >= 0 && cent1.getY() <= l1.getHeight())
						centroides1.add(cent1);
				}
				
				if (i < recuadros2.size()) {
					cent2 = cc.giveMeTheCentroid(l2.getPorcionImagen(recuadros2.get(i).getxLeft(), recuadros2.get(i).getyTop(), 
							recuadros2.get(i).getWidth(), recuadros2.get(i).getHeight()));
					
					cent2.setX(recuadros2.get(i).getxLeft() + cent2.getX());
					cent2.setY(recuadros2.get(i).getyTop() + cent2.getY());
					
					if (cent2.getX() >= 0 && cent2.getX() <= l2.getWidth() && cent2.getY() >= 0 && cent2.getY() <= l2.getHeight())
						centroides2.add(cent2);
				}
			}
			
			LectorImageHDU auxLI;
			Image auxIm;
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
			double scaleW, scaleH, scaleBrillo = 1;
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
				elegido = getCentroideEmparejado(centroide, recuadros1.get(i), centroides2, recuadros2, 2.25, scaleBrillo);
				
				if (elegido != null) { // se ha encontrado coincidente
					
					// Comprobar si el centroide ya esta en el array
					if (elegidos.contains(elegido)) {
						int pos = elegidos.indexOf(elegido);
						
						// Si la distancia del nuevo centroide es menor que la del antiguo se descarta el antiguo
						if (centroide.getDistancia(elegido) < centroidesFin.get(pos).getDistancia(elegido)) {
							centroidesFin.remove(pos);
							elegidos.remove(pos);
							porcentaje--;
						} else continue;
					}

					centroidesFin.add(centroides1.get(i));
					elegidos.add(elegido);
					porcentaje++;
					
					// Escalar el brillo
					if (centroidesFin.size() == 1) {
						scaleBrillo = (double) recuadros1.get(i).getArea() / recuadros2.get(centroides2.indexOf(elegido)).getArea();
					}
				}
			}
			
			porcentaje = (porcentaje/centroides1.size()) * 100;
			
			// Construir las matrices de ambas listas y encontrar la matriz de transformacion
			// Y a la vez se calcula el error cuadratico medio inicial
			double errorInicial = 0;
			Point p1 = new Point(), p2 = new Point();
			
			if (centroidesFin.size() < 3) return resultado;
			
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
		
		    // Comparar errores y si el final es mayor que el inicial descartar
			if (errorFinal > errorInicial) return resultado;
				
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
				
				centroide = new Point();
				centroide.setX(Q.get(i, 0));
				centroide.setY(Q.get(i, 1));
				elegido = getCentroideEmparejado(centroide, recuadros1.get(i), centroides2, recuadros2, 10, scaleBrillo);
				
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
						} else continue;
					}
					
					centroidesIni.add(centroides1.get(i));
					centroidesFin.add(centroide);
					elegidos.add(elegido);
					porcentaje++;
				}
			}
				
			porcentaje = (porcentaje/centroides1.size()) * 100;
			
			// Si el porcentaje es mayor o igual que 50 calcular errores y ver cual es mayor que 2*desviacion tipica (candidato a que se haya movido)
			double error, desviacion;
			DescriptiveStatistics calcEstadisticos = new DescriptiveStatistics();
			double[] errores = new double[centroidesFin.size()];
			
			if (porcentaje < 50) return resultado;
				
			// Calculo de la desviacion tipica
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
			
			// Calcular candidatos a haberse movido
			int cont = 0;
			for (int i = 0; i < centroidesFin.size(); i++) {
				if (errores[i] > 1.75*desviacion) {
					centroide = centroidesFin.get(i);
					if (sf1.getNumberOfStars()>sf2.getNumberOfStars()) {
						candidatas.add(new Pair<Point>(elegidos.get(i), centroide));
						resultado.add(new Pair<Point>(elegidos.get(i), centroidesIni.get(i)));
					} else {
						candidatas.add(new Pair<Point>(centroide, elegidos.get(i)));
						resultado.add(new Pair<Point>(centroidesIni.get(i), elegidos.get(i)));
					}
					cont++;
				}
			}
			
			return resultado;
		
		} catch (FitsException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (ServicioGestionProcesamientosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	public ArrayList<Pair<Point>> busquedaEstrellasDobles(Image im1, Image im2) {
		
		/* Algoritmo de busqueda de estrellas candidatas a ser dobles
		 * 
		 * 1 - Obtener la lista de estrellas candidatas a haberse movido
		 * 2 - Emparejar las estrellas de la lista con el criterio de que se hayan movido en la misma direccion
		 * 3 - Para ello calcularemos el vector director de cada una y veremos cuales coinciden en dirección, sentido y módulo
		 */
		
		ArrayList<Pair<Point>> movimiento = busquedaEstrellasMovimiento(im1, im2);
		
		ArrayList<Pair<Point>> resultado = new ArrayList<Pair<Point>>();
		DecimalCoordinate dc1, dc2;
		MathService ms = (MathService) ContextoAplicacion.getApplicationContext().getBean("mathService");
		Point p1, p2, e1, e2;
		Distance d;
		boolean[] usados = new boolean[candidatas.size()];
		double modulo1, modulo2, direccion1, direccion2;
		double difModulo = 0.35; // diferencia entre modulos de un 35%
		double difDireccion = Math.toRadians(25); // diferencia de 25 grados entre ambas direcciones
		
		for (int i = 0; i < candidatas.size(); i++) {
			p1 = candidatas.get(i).getA();
			e1 = candidatas.get(i).getB();
			dc1 = pixelToCoordinatesConverter(im1, pi.getWidth(), pi.getHeight(), p1.getX(), p1.getY());
			modulo1 = p1.getDistancia(e1);
			if (modulo1 < 0.70) continue;
			direccion1 = p1.getDireccion(e1);
			
			for (int j = i+1; j < candidatas.size(); j++) {
				p2 = candidatas.get(j).getA();
				e2 = candidatas.get(j).getB();
				dc2 = pixelToCoordinatesConverter(im1, pi.getWidth(), pi.getHeight(), p2.getX(), p2.getY());
				d = ms.calculateDecimalDistance(dc1.getAr(), dc1.getDec(), dc2.getAr(), dc2.getDec());
				if (d.getDistanceSeconds() > 120) continue; // distancia entre estrellas mayor de 2 minutos
				
				modulo2 = p2.getDistancia(e2);
				if (modulo2 < 0.70) continue;
				direccion2 = p2.getDireccion(e2);
				
				if (Math.min(modulo1, modulo2) >= Math.max(modulo1, modulo2) * (1 - difModulo) &&
					Math.abs(direccion1 - direccion2) <= difDireccion) {
					// Si los modulos y las direcciones son semejantes mirar que los sentidos sean iguales
					
					// Si el producto escalar es positivo tienen el mismo sentido
					if ((e1.getX()-p1.getX())*(e2.getX()-p2.getX()) + (e1.getY()-p1.getY())*(e2.getY()-p2.getY()) > 0) {
						if (!usados[i]) {
							usados[i] = true;
							resultado.add(new Pair<Point>(movimiento.get(i).getA(), movimiento.get(i).getB()));
						}
						if (!usados[j]) {
							usados[j] = true;
							resultado.add(new Pair<Point>(movimiento.get(j).getA(), movimiento.get(j).getB()));
						}
					}
				}
			}
		}
		
		if (resultado.size() > 2)
			resultado.clear();
		
		return resultado;
	}
	
	private Point getCentroideEmparejado(Point punto, RectStar rec, ArrayList<Point> listaPuntos,
			ArrayList<RectStar> listaRecs, double radioBusqueda, double scaleBrillo) {
		
		// Devuelve el indice de la estrella mas cercana y con semejante brillo dentro de un radio
		
		double distancia, distanciaMin = radioBusqueda, brillo, brilloParecido = 0, brilloEscalado = 0;
		double scaleBrilloAux = scaleBrillo;
		Point elegido = null;
		Point aux;
		
		for (int i = 0; i < listaPuntos.size(); i++) {
			aux = listaPuntos.get(i);
			distancia = punto.getDistancia(aux);
			brillo = listaRecs.get(i).getArea();
			brilloEscalado = brillo * scaleBrilloAux;
			if (elegido == null) {
				if (distancia <= distanciaMin) {
					distanciaMin = distancia;
					brilloParecido = brillo * scaleBrilloAux;
					elegido = aux;
				}
			} else {
				if (distancia <= distanciaMin && Math.abs(rec.getArea() - brilloEscalado) <= Math.abs(rec.getArea() - brilloParecido)) {
					brilloParecido = brillo;
					distanciaMin = distancia;
					elegido = aux;
				}
			}
		}
		
		return elegido;
	}

	/**
	 * @return
	 * @uml.property  name="manager"
	 */
	public EntityManager getManager() {
		return manager;
	}

	/**
	 * @param manager
	 * @uml.property  name="manager"
	 */
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
	public DecimalCoordinate pixelToCoordinatesConverter(Image imagen, int width, int height, double x, double y) {
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
